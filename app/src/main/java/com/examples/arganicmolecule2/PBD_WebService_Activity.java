package com.examples.arganicmolecule2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.provider.Telephony;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.stream.JsonReader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;

import javax.net.ssl.HttpsURLConnection;

public class PBD_WebService_Activity extends AppCompatActivity {
    TextView searchBy_textView;
    Button idButton;
    Button nameButton;
    Button dateButton;
    EditText enter_editText;
    Button summaryButton;
    String formula;
    String formula_weight;
    String id;
    String name;
    ArrayList<String> featureList;
    ListView Json_output;

    LinearLayout horizontal_buttons;
    LinearLayout vertical_layout1;

    Boolean pdbConnecting = false;
    HttpURLConnection urlConnection = null;
    PDBThread pdbThread;
    String query = "";

    URL url;

    Boolean isID=false;
    Boolean isName=false;
    Boolean isDate=false;
    String comp_id = "";

    TextView input1;
    TextView input2;
    TextView input3;
    TextView input4;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pbd_web_service);
        vertical_layout1  = findViewById(R.id.linearLayout_Control_Panel);

        horizontal_buttons = findViewById(R.id.linearLayout_Horizontal_Buttons);
        idButton = findViewById(R.id.button_Id);
        nameButton = findViewById(R.id.button_Name);
        dateButton = findViewById(R.id.button_Date);

        searchBy_textView = findViewById(R.id.textView_Search_By);
        enter_editText = findViewById(R.id.editText_Enter_ID_Name_Date);

        input1 = findViewById(R.id.textView_input1);
        input2 = findViewById(R.id.textView_input2);
        input3 = findViewById(R.id.textView_input3);
        input4 = findViewById(R.id.textView_input4);

        idButton.setOnClickListener(view -> {
            isID = true;
        });

        nameButton.setOnClickListener(view -> {
            isName = true;
        });

        dateButton.setOnClickListener(view -> {
            isDate = true;
        });

//



        summaryButton = findViewById(R.id.button_Molecule_Summary);

        summaryButton.setOnClickListener(view -> {
            if (isID) {
//                Log.i("isID :", isID.toString());
                comp_id = enter_editText.getText().toString();
            } else if (isName) {
                comp_id = enter_editText.getText().toString();
            } else if (isDate) {
                comp_id = enter_editText.getText().toString();
            }
            query = "https://data.rcsb.org/rest/v1/core/chemcomp/" + comp_id;

//            Log.i("URL: ", query);
            pdbConnecting = true;
            pdbThread = new PDBThread();
            pdbThread.start();
        });
    }



    class PDBThread extends Thread {

        public String getJSON() {
            String result = "";
            try {

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

            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        }


        @Override
        public void run() {
            String s = getJSON();  //string from JSON file.
            try {
                if (urlConnection.getResponseCode() == 200) {
                    try {
//                        Log.i("Json string: ", s);
                        JSONObject jsonObject = new JSONObject(s);

                        JSONObject chem_comp = jsonObject.getJSONObject("chem_comp");

                        for (int i = 0; i<10; i++) {

                            if (i == 0) {
                                formula = chem_comp.getString("formula");
                                featureList.add(formula);
                            } else if (i == 1) {
                                Double formula_weight_temp =chem_comp.getDouble("formula_weight");
                                formula_weight = Double.toString(formula_weight_temp);
                                featureList.add(formula_weight);
                            } else if (i == 2) {
                                id = chem_comp.getString("id");
                                featureList.add(id);
                            } else if (i == 3) {
                                name =chem_comp.getString("name");
                                featureList.add(name);
                            }

                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    // Error handling code goes here
                }
            } catch (IOException e) {
                e.printStackTrace();
            }


            while (pdbConnecting) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        for (int j = 0; j<4 ; j++) {
                            if (j == 0) {
                                input1.setText(featureList.get(j));
                            } else if (j == 0) {
                                input2.setText(featureList.get(j));
                            } else if (j == 0) {
                                input3.setText(featureList.get(j));
                            } else {
                                input4.setText(featureList.get(j));
                            }
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });

            }


        }







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