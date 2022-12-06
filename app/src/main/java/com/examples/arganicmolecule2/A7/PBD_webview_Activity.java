package com.examples.arganicmolecule2.A7;


import android.graphics.Bitmap;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;

import android.os.Bundle;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.examples.arganicmolecule2.R;

public class PBD_webview_Activity extends AppCompatActivity {

    private WebView webView;
    private final String PBD_URL_ADDRESS="https://www.rcsb.org/";
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pbd_website);
        webView= (WebView) findViewById(R.id.webview_view);
        webView.setWebViewClient( new WebViewClient());
        webView.loadUrl(PBD_URL_ADDRESS);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        progressBar = findViewById(R.id.progressBar);


        webView.setWebViewClient( new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                progressBar.setVisibility(View.VISIBLE);
                setTitle("LOADING...");
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                progressBar.setVisibility(View.GONE);
                super.onPageFinished(view, url);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (true) {
            AlertDialog.Builder alertdialog = new AlertDialog.Builder(
                    PBD_webview_Activity.this
            );
            alertdialog.setTitle("Alert!");
            alertdialog.setMessage("Are you sure you want to leave this page?");
            alertdialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });

            alertdialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            AlertDialog alert = alertdialog.create();
            alertdialog.show();
        } else {
            finish();
        }
    }
}
