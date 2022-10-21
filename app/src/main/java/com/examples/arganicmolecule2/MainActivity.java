package com.examples.arganicmolecule2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    Button RSChem_Button;
    LinearLayout buttonsLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonsLayout = findViewById(R.id.buttons_linearLayout);

        RSChem_Button = buttonsLayout.findViewById(R.id.PubChem_button);
        RSChem_Button.setOnClickListener(view -> {
            Intent chemAPI_Intent = new Intent(MainActivity.this, ChemAPI_Activity.class);
            startActivity( chemAPI_Intent);
        });
    }
}