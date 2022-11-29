package com.examples.arganicmolecule2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.examples.arganicmolecule2.A7.ChemAPI_Activity;
import com.examples.arganicmolecule2.A9.AR_Activity;
import com.examples.arganicmolecule2.A8.DB_authentication_activity;

public class MainActivity extends AppCompatActivity {
    Button RSChem_Button;
    Button FirebaseBtn;
    Button Ar;
    LinearLayout buttonsLayout;
    ImageView PubChem_clickable;
    ImageView database_clickable;
    ImageView ar_clickable;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.green)));
        //RSChem_Button = findViewById(R.id.PubChem_button);
        PubChem_clickable= findViewById(R.id.PubChem_clickable_image);
        PubChem_clickable.setOnClickListener(view -> openWebViewScreen());

        //RSChem_Button.setOnClickListener(view -> openRSChemWebViewScreen());

        //DataBase
        //FirebaseBtn= findViewById(R.id.Firebase_button);
        //FirebaseBtn.setOnClickListener(view -> openFirebaseViewScreen());
        database_clickable=findViewById(R.id.database_clickable_image);
        database_clickable.setOnClickListener(view -> openFirebaseViewScreen());

        //AR
        //Ar=findViewById(R.id.AR_Button);
        //Ar.setOnClickListener(view -> openArViewScreen());
        ar_clickable=findViewById(R.id.ar_clickable_image);
        ar_clickable.setOnClickListener(view -> openArViewScreen());

    }

    public void openRSChemWebViewScreen() {
        Intent openRSChemWebViewActivity = new Intent(this, ChemAPI_Activity.class);
        startActivity(openRSChemWebViewActivity);
    }
    public void openFirebaseViewScreen(){
        Intent openFirebase_activity = new Intent(MainActivity.this, DB_authentication_activity.class);
        startActivity(openFirebase_activity);
    }
    public void openArViewScreen(){
        Intent openAr_activity = new Intent(MainActivity.this, AR_Activity.class);
        startActivity(openAr_activity);
    }
    public void openWebViewScreen(){
         Intent openWebViewScreen_activity= new Intent(this, ChemAPI_Activity.class);
         startActivity(openWebViewScreen_activity);
    }
}