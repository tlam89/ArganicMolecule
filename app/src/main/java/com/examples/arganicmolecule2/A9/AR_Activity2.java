package com.examples.arganicmolecule2.A9;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.examples.arganicmolecule2.R;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class AR_Activity2 extends AppCompatActivity {
    ImageView amino_acid_molecule_image;
    int imageId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ar2);
        amino_acid_molecule_image = (ImageView) findViewById(R.id.amino_acid_image);
        Intent imageIntent = getIntent();
        if(imageIntent.hasExtra("image")) {
            String moleculeImage = imageIntent.getExtras().getString("image");
            Glide.with(AR_Activity2.this).load(moleculeImage).into(amino_acid_molecule_image);
        } else {
            Toast.makeText(AR_Activity2.this,
                    "Image retrieval failed in Activity2", Toast.LENGTH_SHORT).show();
        }
        }
}