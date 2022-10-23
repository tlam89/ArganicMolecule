package com.examples.arganicmolecule2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.Telephony;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.IOException;
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
    URL pdbEndpoint;
    HttpsURLConnection myConnection;



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
            //PDBThread.start()
        });



        try {
            pdbEndpoint = new URL("https://api.github.com/");
            myConnection =  (HttpsURLConnection) pdbEndpoint.openConnection();
            pdbConnecting = true;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            if (myConnection.getResponseCode() == 200) {
                // Success
                // Further processing here
            } else {
                // Error handling code goes here
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    class PDBThread extends Thread {



    }
}