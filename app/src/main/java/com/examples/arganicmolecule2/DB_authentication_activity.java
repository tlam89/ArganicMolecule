package com.examples.arganicmolecule2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class DB_authentication_activity extends AppCompatActivity {
    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReferenceFromUrl("");

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

                final String phoneTxt= phone.getText().toString();
                final String passwordTxt= password.getText().toString();
                if(phoneTxt.isEmpty() || passwordTxt.isEmpty()){
                    Toast.makeText(DB_authentication_activity.this,"Please enter your mobile" +
                            " or password",Toast.LENGTH_SHORT).show();
                }else{
                    //adding to the firebase
                    databaseRefrence.child("users").addListenerForSingleValueEventListener(){
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot){
                            if(snapshot.hasChild(phoneTxt)){
                                final String getPassword= snapshot.chil(phoneTxt).child("password").getValue(String.class);
                                if(getPassword.equals(passwordTxt)){
                                    Toast.makeText(DB_authentication_activity.this,"Successfully logged in",
                                            Toast.LENGTH_SHORT).show();
                                    startActivity( new Intent(DB_authentication_activity.this,DB_stickerMessage_activity.class));
                                    finish();
                                }else{
                                    Toast.makeText(DB_authentication_activity.this,"Wrong password",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                            }else{
                            Toast.makeText(DB_authentication_activity.this,"Wrong mobile number",
                                    Toast.LENGTH_SHORT).show();
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error){

                        }
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