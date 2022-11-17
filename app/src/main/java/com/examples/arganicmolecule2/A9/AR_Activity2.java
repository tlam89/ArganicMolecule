package com.examples.arganicmolecule2.A9;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;

import com.examples.arganicmolecule2.R;

public class AR_Activity2 extends AppCompatActivity {
    ImageView amino_acid_molecule_image;
    int imageId;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ar2);
        amino_acid_molecule_image = findViewById(R.id.amino_acid_molecule_image);
        Bundle imageBundle = getIntent().getExtras();
        if(imageBundle != null) {
            imageId = imageBundle.getInt("Amino_Acid_Image");
        }
        amino_acid_molecule_image.setImageResource(imageId);
    }
}