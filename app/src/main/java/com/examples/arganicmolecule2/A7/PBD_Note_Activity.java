package com.examples.arganicmolecule2.A7;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.examples.arganicmolecule2.R;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PBD_Note_Activity extends AppCompatActivity {
    ArrayList<Note> notes;
    RecyclerView recyclerView;
    Button search;
    NoteAdapter customAdapter;

    static final int ADD_NOTE_REQUEST = 1;
    private static final String KEY_OF_NOTE = "KEY_OF_NOTE";
    private static final String NUMBER_OF_NOTES = "NUMBER_OF_NOTES";

    FirebaseDatabase firebaseDB;
    DatabaseReference databaseRef;
    String owner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pbd_note);


        firebaseDB = FirebaseDatabase.getInstance();
        databaseRef = firebaseDB.getReference("MoleculeSummary");
        notes = new ArrayList<>();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        customAdapter = new NoteAdapter(notes);
        recyclerView.setAdapter(customAdapter );

        getMoleculeSummary();
        getData();

        search = findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PBD_Note_Activity.this,
                        PBD_WebService_Activity.class);
                startActivity(intent);
            }
        });
    }



    public void getData(){
        DatabaseReference dbRef = databaseRef.child(owner);
        dbRef .addListenerForSingleValueEvent(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot datasnapshot : snapshot.getChildren()) {
                    String formula =  datasnapshot.child("formula").getValue().toString();
                    String formula_weight =  datasnapshot.child("formula_weight").getValue().toString();
                    String id =  datasnapshot.child("id").getValue().toString();
                    String name =  datasnapshot.child("name").getValue().toString();
                    Note note =  new Note(formula, formula_weight, id, name);
                    notes.add(note);
                    Toast.makeText(getApplicationContext(), formula, Toast.LENGTH_LONG).show();
                    customAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void getMoleculeSummary() {
        Intent IDIntent = getIntent();
        if(IDIntent.hasExtra("edu.ArganicMolecule.ID_KEY")){
            owner =IDIntent.getExtras().getString("edu.ArganicMolecule.ID_KEY");
            Toast.makeText(getApplicationContext(), owner, Toast.LENGTH_LONG).show();
        }
    }









}