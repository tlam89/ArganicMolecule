package com.examples.arganicmolecule2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    Button RSChem_Button;
    Button FirebaseBtn;
    LinearLayout buttonsLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RSChem_Button = findViewById(R.id.PubChem_button);

        RSChem_Button.setOnClickListener(view -> openRSChemWebViewScreen());

        //DataBase
        FirebaseBtn= findViewById(R.id.Firebase_button);
        FirebaseBtn.setOnClickListener(view -> openFirebaseViewScreen());


    }

    public void openRSChemWebViewScreen() {
        Intent openRSChemWebViewActivity = new Intent(this, ChemAPI_Activity.class);
        startActivity(openRSChemWebViewActivity);
    }
    public void openFirebaseViewScreen(){
        Intent openFirebase_activity = new Intent(MainActivity.this,DB_authentication_activity.class);
        startActivity(openFirebase_activity);
    }
}