package com.examples.arganicmolecule2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

public class PBD_Website_Activity extends AppCompatActivity {
    Button webViewButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pbd_webview_service);

        webViewButton=findViewById(R.id.webview_button);
        webViewButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View viewWeb) {
                Intent webView_intent = new Intent(PBD_Website_Activity.this,PBD_WebView_Activity.class);
                startActivity(webView_intent);
            }
        });
    }
}

