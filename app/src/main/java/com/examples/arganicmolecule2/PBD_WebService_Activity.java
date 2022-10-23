package com.examples.arganicmolecule2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.Telephony;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
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
import java.util.Date;

import javax.net.ssl.HttpsURLConnection;

public class PBD_WebService_Activity extends AppCompatActivity {
    TextView searchBy_textView;
    Button idButton;
    Button nameButton;
    Button dateButton;
    EditText enter_editText;
    TextView json_output;
    Button summaryButton;
    String formula;
    Double formula_weight;

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
        json_output = findViewById(R.id.textView_JSON);

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

                        JSONArray jsonArray1 = jsonObject.getJSONArray("chem_comp");

                        for (int i = 0; i<10; i++) {
                            JSONObject rec = jsonArray1.getJSONObject(i);
                            formula = rec.getString("formula");
                            formula_weight = rec.getDouble("formula_weight");
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
                        json_output.setText(formula);

                    }
                });

            }


        }







    }
}