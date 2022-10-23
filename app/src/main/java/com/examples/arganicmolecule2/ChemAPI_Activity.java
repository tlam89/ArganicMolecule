package com.examples.arganicmolecule2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

public class ChemAPI_Activity extends AppCompatActivity {
    Button webViewButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chem_api);

        // Wire up the Molecule of the Month button
        Button mtn = (Button) findViewById(R.id.button3);
        mtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(ChemAPI_Activity.this, PBD_Date_Activity.class);
                startActivity(intent1);
            }
        });


        // Lunch WEB VIEW for Launch WEBVIEW button
        webViewButton=findViewById(R.id.button_for_webView);
        webViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent webView_intent= new Intent(ChemAPI_Activity.this,PBD_Website_Activity.class);
                startActivity(webView_intent);
            }
        });
    }
}