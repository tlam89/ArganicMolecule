package com.examples.arganicmolecule2.A9;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.examples.arganicmolecule2.R;

public class AR_Activity extends AppCompatActivity {
    String [] items={"ALIPHATIC","AROMATIC","ACIDIC","BASIC","HYDROXYLIC","SULFUR_CONTAINING","AMIDIC","ESSENTIAL","NON-ESSENTIAL"};
    AutoCompleteTextView autoCompleteTextView;
    ArrayAdapter<String> adapterItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ar);


        autoCompleteTextView=findViewById(R.id.auto_Complete_txt);
        adapterItems= new ArrayAdapter<String>(this,R.layout.activity_ar_listitem,items);
        autoCompleteTextView.setAdapter(adapterItems);

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                String item= parent.getItemAtPosition(i).toString();
                Toast.makeText(getApplicationContext(),":Amino acid "+item,Toast.LENGTH_SHORT).show();
            }
        });
    }

}