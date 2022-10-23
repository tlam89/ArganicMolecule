package com.examples.arganicmolecule2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;

public class PBD_Website_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pbd_website);
    }

    @Override
    public void onBackPressed() {
        if (true) {
            AlertDialog.Builder alertdialog = new AlertDialog.Builder(
                    PBD_Website_Activity.this
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