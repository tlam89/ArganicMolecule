package com.examples.arganicmolecule2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PBD_WebView_Activity extends AppCompatActivity {

    private WebView webView;
    private final String PBD_URL_ADDRESS="https://www.rcsb.org/";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pbd_website);
        webView =(WebView) findViewById(R.id.webview_view);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(PBD_URL_ADDRESS);
        //webView.loadUrl("https://www.rcsb.org/");
    }

    @Override
    public void onBackPressed(){
        if(webView.canGoBack()){
            webView.goBack();
            Toast.makeText(this,"Going back inside a Webview",Toast.LENGTH_SHORT);
        }else{
            Toast.makeText(this,"Exiting a WebView",Toast.LENGTH_SHORT).show();
            super.onBackPressed();
        }
    }
}
