package com.examples.arganicmolecule2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.Telephony;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class PBD_WebService_Activity extends AppCompatActivity {
    TextView searchBy_textView;
    Button idButton;
    Button nameButton;
    Button dateButton;
    EditText enter_editText;
    Button summaryButton;

    LinearLayout horizontal_buttons;
    LinearLayout vertical_layout1;

    Boolean pdbConnecting = false;
    HttpURLConnection urlConnection = null;
    PDBThread pdbThread;
    String query = "";

    URL url;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pbd_web_service);
        vertical_layout1  = findViewById(R.id.linearLayout_Control_Panel);
        horizontal_buttons = vertical_layout1.findViewById(R.id.buttons_linearLayout);
        idButton = horizontal_buttons.findViewById(R.id.button_Id);
        nameButton = horizontal_buttons.findViewById(R.id.button_Name);
        dateButton = horizontal_buttons.findViewById(R.id.button_Date);

        searchBy_textView = horizontal_buttons.findViewById(R.id.textView_Search_By);
        enter_editText = findViewById(R.id.editText_Enter_ID_Name_Date);

        summaryButton = findViewById(R.id.button_Molecule_Summary);

        summaryButton.setOnClickListener(view -> {
            pdbConnecting = true;
//            pdbThread = new PDBThread();
//            pdbThread.start();
        });
    }



    class PDBThread extends Thread {

        public String getJSON() {
            String result = "";
            try {
                CharSequence comp_id = (CharSequence) enter_editText.getText();
                query = "https://data.rcsb.org/rest/v1/core/chemcomp/{" + comp_id +"}";

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
            String s = getJSON();
//            try {
//                if (urlConnection.getResponseCode() == 200) {
//                    try {
//                        JSONObject jsonObject = new JSONObject(s);
//
//                        JSONArray jsonArray1 = jsonObject.getJSONArray("chem_comp");
//
//
//
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                } else {
//                    // Error handling code goes here
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }


            while (pdbConnecting) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                    }
                });

            }


        }







    }
}