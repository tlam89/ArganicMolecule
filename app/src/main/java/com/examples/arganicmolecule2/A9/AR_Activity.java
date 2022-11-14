package com.examples.arganicmolecule2.A9;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.examples.arganicmolecule2.R;

public class AR_Activity extends AppCompatActivity {
    //String [] items={"ALIPHATIC","AROMATIC","ACIDIC","BASIC","HYDROXYLIC","SULFUR_CONTAINING","AMIDIC","ESSENTIAL","NON-ESSENTIAL"};
    private String category,aminoAcide;
    private TextView textViewCatelogSpinner,textViewAminoSpinner;
    private Spinner catalogSpinner,aminoSpinner;
    private ArrayAdapter<CharSequence> categoryAdapter,aminoAdapter;
    Button confirmbtn;

    //AutoCompleteTextView autoCompleteTextView;
    //ArrayAdapter<String> adapterItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ar);

        catalogSpinner=findViewById(R.id.spinner_categories);

        //arryAdapter
        categoryAdapter=ArrayAdapter.createFromResource(this,R.array.array_category,R.layout.spinner_layout);
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        catalogSpinner.setAdapter(categoryAdapter);

        catalogSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                aminoSpinner=findViewById(R.id.amino_spinner);
                category=catalogSpinner.getSelectedItem().toString();
                int parentID= parent.getId();
                if(parentID==R.id.spinner_categories){
                    switch (category){
                        case "Select Category":aminoAdapter=ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_amino,R.layout.spinner_layout);
                        break;
                        case "ALIPHATIC":aminoAdapter=ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_aliphatic,R.layout.spinner_layout);
                        break;
                        case "AROMATIC":aminoAdapter=ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_aromatic,R.layout.spinner_layout);
                        break;
                        case "ACIDIC":aminoAdapter=ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_acidic,R.layout.spinner_layout);
                        case "BASIC":aminoAdapter=ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_basic,R.layout.spinner_layout);
                            break;
                        case "HYDROXYLIC":aminoAdapter=ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_hydroxylic,R.layout.spinner_layout);
                            break;
                        case "SULFUR_CONTAINING":aminoAdapter=ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_sulfur_containing,R.layout.spinner_layout);
                            break;
                        case "AMIDIC":aminoAdapter=ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_amidic,R.layout.spinner_layout);
                            break;
                        case "ESSENTIAL":aminoAdapter=ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_essential,R.layout.spinner_layout);
                            break;
                        case "NON-ESSENTIAL":aminoAdapter=ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_non_essential,R.layout.spinner_layout);
                            break;
                        default:break;
                    }
                    aminoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    aminoSpinner.setAdapter(aminoAdapter);
                    aminoSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            aminoAcide=aminoSpinner.getSelectedItem().toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        confirmbtn=findViewById(R.id.submit_confirm);
        textViewCatelogSpinner=findViewById(R.id.textview_category_key);
        textViewAminoSpinner=findViewById(R.id.textview_amino_acide);

        confirmbtn.setOnClickListener(view -> {
            if(category.equals("Select Category")){
                Toast.makeText(AR_Activity.this,"Please Select the Category first",Toast.LENGTH_LONG).show();
                textViewCatelogSpinner.setError("Category is required!");
                textViewCatelogSpinner.requestFocus();
            }else if(aminoAcide.equals("Select Amino acide")){
                Toast.makeText(AR_Activity.this,"Please select the amino acid ",Toast.LENGTH_LONG).show();
                textViewAminoSpinner.setError("Amino acid is required");
                textViewAminoSpinner.requestFocus();
                textViewCatelogSpinner.setError(null);
            }else{
                textViewCatelogSpinner.setError(null);
                textViewAminoSpinner.setError(null);
                Toast.makeText(AR_Activity.this,"Select category:"+category+"\n Select Amino acid: "+aminoAcide,
                        Toast.LENGTH_LONG).show();
            }
        });







//        autoCompleteTextView=findViewById(R.id.auto_Complete_txt);
//        adapterItems= new ArrayAdapter<String>(this,R.layout.activity_ar_listitem,items);
//        autoCompleteTextView.setAdapter(adapterItems);

//        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
//                String item= parent.getItemAtPosition(i).toString();
//                Toast.makeText(getApplicationContext(),":Amino acid "+item,Toast.LENGTH_SHORT).show();
//            }
//        });
    }

}