package com.examples.arganicmolecule2.A8;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.examples.arganicmolecule2.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DB_authentication_activity extends AppCompatActivity {
    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReferenceFromUrl("https://arganicmolecule2-66023-default-rtdb.firebaseio.com");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db_authentication);


        final EditText phone = findViewById(R.id.phoneTxtV);
        final EditText password = findViewById(R.id.passwordTxtV);
        final Button login_btn= findViewById(R.id.loginBtn);
        final TextView registerNow= findViewById(R.id.registerNowBtn);

        login_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                final String phoneTxt = phone.getText().toString();
                final String passwordTxt = password.getText().toString();
                if (phoneTxt.isEmpty() || passwordTxt.isEmpty()) {
                    Toast.makeText(DB_authentication_activity.this, "Please enter your mobile" +
                            " or password", Toast.LENGTH_SHORT).show();
                } else {
                    //adding to the firebase
                    databaseReference.child("user").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.hasChild(phoneTxt)) {
                                final String getPassword= "";

                                if (getPassword.equals("")) {
                                    Toast.makeText(DB_authentication_activity.this, "Successfully logged in",
                                            Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(DB_authentication_activity.this, Testing_Image_Display.class));
                                    finish();
                                } else {
                                    Toast.makeText(DB_authentication_activity.this, "Wrong password",
                                            Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(DB_authentication_activity.this, "Wrong Mobile number",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }

        });
        registerNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent(DB_authentication_activity.this,Register_authentication_activity.class));
            }
        });
    }
}