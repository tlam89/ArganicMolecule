package com.examples.arganicmolecule2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    Button RSChem_Button;
    LinearLayout buttonsLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RSChem_Button = findViewById(R.id.PubChem_button);

        RSChem_Button.setOnClickListener(view -> openRSChemWebViewScreen());


        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        myRef.setValue("Hello, World!");
    }

    public void openRSChemWebViewScreen() {
        Intent openRSChemWebViewActivity = new Intent(this, ChemAPI_Activity.class);
        startActivity(openRSChemWebViewActivity);
    }
}