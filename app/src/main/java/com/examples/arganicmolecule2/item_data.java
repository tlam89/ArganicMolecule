package com.examples.arganicmolecule2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class item_data extends AppCompatActivity {
    TextView moleculename;
    TextView moleculedate;
    ImageView moleculepicture;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_data);

        //find view
        moleculename = findViewById(R.id.name);
        moleculedate = findViewById(R.id.date);
        moleculepicture = findViewById(R.id.picture);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String date = intent.getStringExtra("date");
        String image = intent.getStringExtra("image");

        //set data
        moleculename.setText(title);
        moleculedate.setText(date);
        Glide.with(this).load(image).into(moleculepicture);

    }
}