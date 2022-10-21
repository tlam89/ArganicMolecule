package com.examples.arganicmolecule2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button task1Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        task1Button = findViewById(R.id.buttonTask1);
        task1Button.setOnClickListener(view -> {
            Intent task1Intent = new Intent(MainActivity.this, task1Activity.class);
            startActivity( task1Intent);
        });
    }
}