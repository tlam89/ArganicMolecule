package com.examples.arganicmolecule2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

public class ChemAPI_Activity extends AppCompatActivity {
    Button webViewButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chem_api);

        final LoadingDialog loadingDialog = new LoadingDialog(ChemAPI_Activity.this);

        // Wire up the Molecule of the Month button
        Button mtn = (Button) findViewById(R.id.button3);
        mtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadingDialog.startLoadingDialog();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadingDialog.dismissDialog();
                    }
                }, 5000);
                Intent intent1 = new Intent(ChemAPI_Activity.this, PBD_Date_Activity.class);
                startActivity(intent1);
            }
        });

        // Wire up the Web Service button
        Button web_view_Button = (Button) findViewById(R.id.webview_button);
        web_view_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent webView_Intent1 = new Intent(ChemAPI_Activity.this, PBD_WebService_Activity.class);
                startActivity(webView_Intent1);
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