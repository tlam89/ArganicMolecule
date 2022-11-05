package com.examples.arganicmolecule2.A8;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.examples.arganicmolecule2.R;

public class Testing_Image_Display extends AppCompatActivity {
    private RecyclerView stickers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing_image_display);

        stickers = findViewById(R.id.stickers);
        stickers.setHasFixedSize(true);
        //stickers.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.Vertical));

    }
}