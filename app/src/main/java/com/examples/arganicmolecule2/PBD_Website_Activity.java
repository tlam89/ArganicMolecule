package com.examples.arganicmolecule2;

import android.os.Bundle;
import android.webkit.WebView;
import android.os.Bundle;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PBD_Website_Activity extends AppCompatActivity {

    private WebView webView;
    private final String PBD_URL_ADDRESS="https://www.rcsb.org/";
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pbd_website);
        webView= (WebView) findViewById(R.id.webview_view);
        webView.setWebViewClient( new WebViewClient());
        webView.loadUrl(PBD_URL_ADDRESS);
    }
    @Override
    public void onBackPressed(){
        if(webView.canGoBack()){
            webView.goBack();
            Toast.makeText(this,"Going back inside a WebView",Toast.LENGTH_SHORT);
        }else{
            Toast.makeText(this,"Exiting a webView",Toast.LENGTH_SHORT).show();
            super.onBackPressed();
        }
    }



}

/*


package com.examples.arganicmolecule2;

import android.os.Bundle;
import android.webkit.WebView;
import android.os.Bundle;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PBD_webview_Activity extends AppCompatActivity {

    private WebView webView;
    private final String PBD_URL_ADDRESS="https://www.rcsb.org/";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pbd_website);
        webView= (WebView) findViewById(R.id.webview_view);
        webView.setWebViewClient( new WebViewClient());
        webView.loadUrl(PBD_URL_ADDRESS);
    }
    @Override
    public void onBackPressed(){
        if(webView.canGoBack()){
            webView.goBack();
            Toast.makeText(this,"Going back inside a WebView",Toast.LENGTH_SHORT);
        }else{
            Toast.makeText(this,"Exiting a webView",Toast.LENGTH_SHORT).show();
            super.onBackPressed();
        }
    }
}

 */