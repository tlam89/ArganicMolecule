package com.examples.arganicmolecule2.A7;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.examples.arganicmolecule2.A8.DB_authentication_activity;
import com.examples.arganicmolecule2.A8.DB_stickerMessage_activity;
import com.examples.arganicmolecule2.R;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PBD_Note_Activity extends AppCompatActivity {
    ArrayList<Note> notesList;
    Context context;
    FirebaseDatabase firebaseDB;
    DatabaseReference databaseRef;
    RecyclerView noteRecyclerView;
    Button search;
    NoteAdapter noteAdapter;
    String formula, formula_weight, id, name;

    static final int ADD_NOTE_REQUEST = 1;
    private static final String KEY_OF_NOTE = "KEY_OF_NOTE";
    private static final String NUMBER_OF_NOTES = "NUMBER_OF_NOTES";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pbd_note);
        notesList = new ArrayList<>();

        // initial link item data
        //initialItemData(savedInstanceState);

        firebaseDB = FirebaseDatabase.getInstance();
        databaseRef = firebaseDB.getReference("Molecule Recycler View");

        noteRecyclerView = findViewById(R.id.recyclerView);
        noteRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        noteAdapter = new NoteAdapter(notesList);
        noteRecyclerView.setAdapter(noteAdapter);

        search = findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PBD_Note_Activity.this, PBD_WebService_Activity.class);
                startActivity(intent);
            }
        });
        ClearAll();
        getMoleculeSummary();
        getData();
    }

    private void getData() {
        DatabaseReference dbRef = databaseRef.child(id);
        Log.i("Molecule ID: ", id);
        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.i("snapshot", snapshot.getKey());
                ClearAll();
                for (DataSnapshot datasnapshot : snapshot.getChildren()) {
                    Note note = new Note();
                    note.setFormula(datasnapshot.child("formula").getValue().toString());
                    note.setFormula_weight(datasnapshot.child("formula_weight").getValue().toString());
                    note.setId(datasnapshot.child("id").getValue().toString());
                    note.setName(datasnapshot.child("name").getValue().toString());
                    notesList.add(note);
                }
                noteAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @SuppressLint("NotifyDataSetChanged")
    private void ClearAll() {
        if (notesList != null) {
            notesList.clear();
            if (noteAdapter != null) {
                noteAdapter.notifyDataSetChanged();
            }
        }
        notesList = new ArrayList<>();
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_NOTE_REQUEST && resultCode == RESULT_OK) {
            assert data != null;
            formula = data.getStringExtra(PBD_WebService_Activity.FORMULA_KEY);
            formula_weight = data.getStringExtra(PBD_WebService_Activity.FORMULA_WEIGHT_KEY);
            id = data.getStringExtra(PBD_WebService_Activity.ID_KEY);
            name = data.getStringExtra(PBD_WebService_Activity.NAME_KEY);
            Note note = new Note(formula, formula_weight, id, name);
            notesList.add(note);
            Snackbar.make(findViewById(R.id.recyclerView), "Note Saved", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            noteAdapter.notifyDataSetChanged();
        } else {
            Snackbar.make(findViewById(R.id.recyclerView), "Invalid input. Please try again.",
                    Snackbar.LENGTH_LONG).setAction("Action", null).show();
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    public void getMoleculeSummary() {
        if(getIntent().hasExtra(PBD_WebService_Activity.FORMULA_KEY)){
            formula = getIntent().getStringExtra(PBD_WebService_Activity.FORMULA_KEY);
            formula_weight = getIntent().getStringExtra(PBD_WebService_Activity.FORMULA_WEIGHT_KEY);
            id = getIntent().getStringExtra(PBD_WebService_Activity.ID_KEY);
            name = getIntent().getStringExtra(PBD_WebService_Activity.NAME_KEY);
            Note note = new Note(formula, formula_weight, id, name);
            notesList.add(note);
            noteAdapter.notifyDataSetChanged();
        }
    }

    // Handling Orientation Changes on Android
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {

        int size = notesList == null ? 0 : notesList.size();
        outState.putInt(NUMBER_OF_NOTES, size);

        // Need to generate unique key for each item
        for (int i = 0; i < size; i++) {
            outState.putString(KEY_OF_NOTE + i + "1", notesList.get(i).getFormula());
            outState.putString(KEY_OF_NOTE + i + "2", notesList.get(i).getFormula_weight());
            outState.putString(KEY_OF_NOTE + i + "3", notesList.get(i).getId());
            outState.putString(KEY_OF_NOTE + i + "4", notesList.get(i).getName());
        }
        super.onSaveInstanceState(outState);
    }

//    private void initialItemData(Bundle savedInstanceState) {
//
//        // Not the first time to open this Activity
//        if (savedInstanceState != null && savedInstanceState.containsKey(NUMBER_OF_NOTES)) {
//            if (notesList== null || notesList.size() == 0) {
//                int size = savedInstanceState.getInt(NUMBER_OF_NOTES);
//
//                // Retrieve keys we stored in the instance
//                for (int i = 0; i < size; i++) {
//                    String formula = savedInstanceState.getString(KEY_OF_NOTE + i + "1");
//                    String formula_weight = savedInstanceState.getString(KEY_OF_NOTE + i
//                            + "2");
//                    String id = savedInstanceState.getString(KEY_OF_NOTE + i + "3");
//                    String name = savedInstanceState.getString(KEY_OF_NOTE + i + "4");
//                    Note note = new Note(formula, formula_weight, id, name);
//                    notesList.add(note);
//                }
//            }
//        }
//    }
}