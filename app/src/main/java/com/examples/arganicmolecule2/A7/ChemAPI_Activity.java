package com.examples.arganicmolecule2.A7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.examples.arganicmolecule2.R;

public class ChemAPI_Activity extends AppCompatActivity {
    Button webViewButton;
    ImageView pbd_homepage;
    ImageView pbd_webservice;
    ImageView pbd_mom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chem_api);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.green)));

        // Wire up the Molecule of the Month button
        //Button mtn = (Button) findViewById(R.id.button3);
//        mtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent1 = new Intent(ChemAPI_Activity.this, PBD_Date_Activity.class);
//                startActivity(intent1);
//            }
//        });

        //lunching the website
        pbd_homepage= findViewById(R.id.homePage_clickable_image);
        pbd_homepage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent homepage= new Intent(ChemAPI_Activity.this,PBD_webview_Activity.class);
                startActivity(homepage);
            }
        });
        //lunching the web service
        pbd_webservice=findViewById(R.id.molecule_clickable_image);
        pbd_webservice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ws=new Intent(ChemAPI_Activity.this,PBD_WebService_Activity.class);
                startActivity(ws);
            }
        });

        // Wire up the Web Service button
        //Button web_view_Button = (Button) findViewById(R.id.webview_button);
//        web_view_Button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent webView_Intent1 = new Intent(ChemAPI_Activity.this, PBD_WebService_Activity.class);
//                startActivity(webView_Intent1);
//            }
//        });


        // Lunch WEB VIEW for Launch WEBVIEW button
        //webViewButton=findViewById(R.id.button_for_webView);
//        webViewButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent webView_intent= new Intent(ChemAPI_Activity.this, PBD_webview_Activity.class);
//                startActivity(webView_intent);
//            }
//        });
    }
}