package com.examples.arganicmolecule2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

public class ChemAPI_Activity extends AppCompatActivity {

    Button launch_WebService_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chem_api);

        launch_WebService_button = findViewById(R.id.website_button);



    }
}