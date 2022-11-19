package com.examples.arganicmolecule2.A7;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.examples.arganicmolecule2.A8.DB_stickerMessage_activity;
import com.examples.arganicmolecule2.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class PBD_WebService_Activity extends AppCompatActivity {
    public static final String NAME_KEY = "edu.ArganicMolecule.NAME_KEY";
    public static final String ID_KEY = "edu.ArganicMolecule.ID_KEY";
    public static final String FORMULA_KEY = "edu.ArganicMolecule.FORMULA_KEY";
    public static final String FORMULA_WEIGHT_KEY = "edu.ArganicMolecule.FORMULA_WEIGHT_KEY";
    private static final String KEY_OF_NOTE = "KEY_OF_NOTE";
    private static final String NUMBER_OF_NOTES = "NUMBER_OF_NOTES";
    //    TextView searchBy_textView;
    Button idButton, nameButton, dateButton, summaryButton, likeButton;
    EditText enter_editText;
    String formula, formula_weight, id, name, query = "", comp_id = "";
    LinearLayout horizontal_buttons, vertical_layout1;
    Boolean pdbConnecting = true, isID = true, isName = true, isDate = false;
    HttpURLConnection urlConnection = null;
    PDBThread pdbThread;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    URL url;
    TextView input1, input2, input3, input4;
    //static final int ADD_NOTE_REQUEST = 1;
    ArrayList<Note> notes;
    //NoteAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pbd_web_service);
        vertical_layout1 = findViewById(R.id.linearLayout_Control_Panel);

        horizontal_buttons = findViewById(R.id.linearLayout_Horizontal_Buttons);
        idButton = findViewById(R.id.button_Id);
//        nameButton = findViewById(R.id.button_Name);
//        dateButton = findViewById(R.id.button_Date);

//        searchBy_textView = findViewById(R.id.textView_Search_By);
        enter_editText = findViewById(R.id.editText_Enter_ID_Name_Date);

        input1 = findViewById(R.id.textView_input1);
        input2 = findViewById(R.id.textView_input2);
        input3 = findViewById(R.id.textView_input3);
        input4 = findViewById(R.id.textView_input4);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        //
        notes = new ArrayList<>();
        //initialItemData(savedInstanceState);
        //customAdapter = new NoteAdapter(notes);

        if (savedInstanceState != null) {
            formula = savedInstanceState.getString("formula", formula);
            formula_weight = savedInstanceState.getString("formula_weight", formula_weight);
            id = savedInstanceState.getString("id", id);
            name = savedInstanceState.getString("name", name);
            pdbConnecting = savedInstanceState.getBoolean("pdbConnecting", pdbConnecting);

            input1.setText(formula);
            input2.setText(formula_weight);
            input3.setText(id);
            input4.setText(name);
        }

        idButton.setOnClickListener(view -> {
            isID = true;
        });
//
//        nameButton.setOnClickListener(view -> {
//            isName = true;
//        });
//
//        dateButton.setOnClickListener(view -> {
//            isDate = true;
//        });


        summaryButton = findViewById(R.id.button_Molecule_Summary);

        summaryButton.setOnClickListener(view -> {
            if (isID) {
                comp_id = enter_editText.getText().toString();
            } else if (isName) {
                comp_id = enter_editText.getText().toString();
            } else if (isDate) {
                comp_id = enter_editText.getText().toString();
            }
            query = "https://data.rcsb.org/rest/v1/core/chemcomp/" + comp_id;
            pdbConnecting = true;
            pdbThread = new PDBThread();
            pdbThread.start();
            enter_editText.onEditorAction(EditorInfo.IME_ACTION_DONE);
        });

        likeButton = findViewById(R.id.button_Like);

        likeButton.setOnClickListener(view -> {
            String temp = "MoleculeSummary/" + id;
            DatabaseReference molRef = databaseReference.child(temp);
            pdbConnecting = false;
            Thread.currentThread().interrupt();
            if(id != null && formula!= null && formula_weight!= null && name != null) {
                sendSummaryData(molRef, formula, formula_weight, id, name);
                Intent data = new Intent(PBD_WebService_Activity.this,
                        PBD_Note_Activity.class);
                data.putExtra(NAME_KEY, name);
                data.putExtra(ID_KEY, id);
                data.putExtra(FORMULA_KEY, formula);
                data.putExtra(FORMULA_WEIGHT_KEY, formula_weight);
                startActivity(data);
            } else {
                Toast.makeText(this,"Could not send data to Firebase as null is "
                        + "being passed.", Toast.LENGTH_LONG).show();
            }
        });

    }


    @Override
    public void onBackPressed() {
        if (true) {
            AlertDialog.Builder alertdialog = new AlertDialog.Builder(
                    PBD_WebService_Activity.this
            );
            alertdialog.setTitle("Alert!");
            alertdialog.setMessage("Are you sure  you want to leave this page?");
            alertdialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    pdbConnecting = false;
                    Thread.currentThread().interrupt();
                    finish();
                }
            }).setNegativeButton("No", new DialogInterface.OnClickListener() {
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

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("formula", formula);
        outState.putString("formula_weight", formula_weight);
        outState.putString("id", id);
        outState.putString("name", name);
        outState.putBoolean("pdbConnecting", pdbConnecting);
    }

    private void initialItemData(Bundle savedInstanceState) {
        // Not the first time to open this Activity
        if (savedInstanceState != null && savedInstanceState.containsKey(NUMBER_OF_NOTES)) {
            if (notes == null || notes.size() == 0) {
                int size = savedInstanceState.getInt(NUMBER_OF_NOTES);

                // Retrieve keys we stored in the instance
                for (int i = 0; i < size; i++) {
                    String formula = savedInstanceState.getString(KEY_OF_NOTE + i + "1");
                    String formula_weight = savedInstanceState.getString(KEY_OF_NOTE + i
                            + "2");
                    String id = savedInstanceState.getString(KEY_OF_NOTE + i + "3");
                    String name = savedInstanceState.getString(KEY_OF_NOTE + i + "4");
                    Note note = new Note(formula, formula_weight, id, name);
                    notes.add(note);
                }
            }
        }
    }

    private void sendSummaryData(DatabaseReference molSumRef, String molFormula,
                                 String molFormularWeight, String molID, String molName) {
        DatabaseReference moleculeDBRef = molSumRef.push();
        moleculeDBRef.setValue(new Note(molFormula, molFormularWeight, molID, molName));

        DatabaseReference molSummaryData = databaseReference.child("MoleculeSummary/" + molID)
                                            .push();
        molSummaryData.setValue(new Note(molFormula, molFormularWeight, molID, molName));
    }

    class PDBThread extends Thread {

        public String getJSON() {
            String result = "";
            try {
                url = new URL(query);
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = urlConnection.getInputStream();
                InputStreamReader isw = new InputStreamReader(in);
                int data = isw.read();
                while (data != -1) {
                    result += (char) data;
                    data = isw.read();
                }
                return result;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
            }
            return result;
        }

        @Override
        public void run() {
            String s = getJSON();  //string from JSON file.

            try {
                JSONObject jsonObject = new JSONObject(s);
                JSONObject chem_comp = jsonObject.getJSONObject("chem_comp");
                for (int i = 0; i < 4; i++) {
                    if (i == 0) {
                        formula = chem_comp.getString("formula");
                    } else if (i == 1) {
                        Double formula_weight_temp = chem_comp.getDouble("formula_weight");
                        formula_weight = Double.toString(formula_weight_temp);
                    } else if (i == 2) {
                        id = chem_comp.getString("id");
                    } else if (i == 3) {
                        name = chem_comp.getString("name");
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            while (pdbConnecting) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        input1.setText(formula);
                        input2.setText(formula_weight);
                        input3.setText(id);
                        input4.setText(name);
                        pdbConnecting = false;
                        Thread.currentThread().interrupt();
                    }
                });
            }
        }
    }
}