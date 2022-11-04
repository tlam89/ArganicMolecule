package com.examples.arganicmolecule2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class PBD_Note_Activity extends AppCompatActivity {
    ArrayList<Note> notes;
    RecyclerView recyclerView;
    Button search;
    NoteAdapter customAdapter;

    static final int ADD_NOTE_REQUEST = 1;
    private static final String KEY_OF_NOTE = "KEY_OF_NOTE";
    private static final String NUMBER_OF_NOTES = "NUMBER_OF_NOTES";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pbd_note);
        notes = new ArrayList<>();

        // initial link item data
        //initialItemData(savedInstanceState);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        customAdapter = new NoteAdapter(notes);
        recyclerView.setAdapter(customAdapter );

        search = findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PBD_Note_Activity.this, PBD_WebService_Activity.class);
                startActivity(intent);
            }
        });
        getData();
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_NOTE_REQUEST && resultCode == RESULT_OK) {
            assert data != null;
            String formula = data.getStringExtra(PBD_WebService_Activity.FORMULA_KEY);
            String formula_weight = data.getStringExtra(PBD_WebService_Activity.FORMULA_WEIGHT_KEY);
            String name = data.getStringExtra(PBD_WebService_Activity.NAME_KEY);
            String id = data.getStringExtra(PBD_WebService_Activity.ID_KEY);
            Note note = new Note(formula, formula_weight, id, name);
            notes.add(note);
            Snackbar.make(findViewById(R.id.recyclerView), "Note Saved", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            customAdapter.notifyDataSetChanged();
        }
        else {
            Snackbar.make(findViewById(R.id.recyclerView), "Invalid input. Please try again.", Snackbar.LENGTH_LONG).setAction("Action", null).show();
        }
    }

    public void getData(){
        if(getIntent().hasExtra(PBD_WebService_Activity.FORMULA_KEY)){
            String formula = getIntent().getStringExtra(PBD_WebService_Activity.FORMULA_KEY);
            String formula_weight = getIntent().getStringExtra(PBD_WebService_Activity.FORMULA_WEIGHT_KEY);
            String name = getIntent().getStringExtra(PBD_WebService_Activity.NAME_KEY);
            String id = getIntent().getStringExtra(PBD_WebService_Activity.ID_KEY);
            Note note = new Note(formula, formula_weight, id, name);
            notes.add(note);
            Snackbar.make(findViewById(R.id.recyclerView), "Note Saved", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            customAdapter.notifyDataSetChanged();
        }
    }



    // Handling Orientation Changes on Android
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {

        int size = notes== null ? 0 : notes.size();
        outState.putInt(NUMBER_OF_NOTES, size);

        // Need to generate unique key for each item
        for (int i = 0; i < size; i++) {
            outState.putString(KEY_OF_NOTE + i + "1", notes.get(i).getFormula());
            outState.putString(KEY_OF_NOTE + i + "2", notes.get(i).getFormula_weight());
            outState.putString(KEY_OF_NOTE + i + "3", notes.get(i).getId());
            outState.putString(KEY_OF_NOTE + i + "4", notes.get(i).getName());
        }
        super.onSaveInstanceState(outState);
    }

    private void initialItemData(Bundle savedInstanceState) {

        // Not the first time to open this Activity
        if (savedInstanceState != null && savedInstanceState.containsKey(NUMBER_OF_NOTES)) {
            if (notes== null || notes.size() == 0) {
                int size = savedInstanceState.getInt(NUMBER_OF_NOTES);

                // Retrieve keys we stored in the instance
                for (int i = 0; i < size; i++) {
                    String formula = savedInstanceState.getString(KEY_OF_NOTE + i + "1");
                    String formula_weight = savedInstanceState.getString(KEY_OF_NOTE + i + "2");
                    String id = savedInstanceState.getString(KEY_OF_NOTE + i + "3");
                    String name = savedInstanceState.getString(KEY_OF_NOTE + i + "4");
                    Note note = new Note(formula, formula_weight, id, name);
                    notes.add(note);
                }
            }
        }
    }



}