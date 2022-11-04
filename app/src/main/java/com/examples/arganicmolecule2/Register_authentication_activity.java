package com.examples.arganicmolecule2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Register_authentication_activity extends AppCompatActivity {


    //add the Realtime database link
    DatabaseReference databaseRefrence= FirebaseDatabase.getInstance().getReferenceFromUrl("https://arganicmolecule2-66023-default-rtdb.firebaseio.com");
    //DatabaseRefrence databaseRefrence= FirebaseDatabase.getInstance().getToken();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_authentication);

        final EditText fullname= findViewById(R.id.fullName);
        final EditText email=findViewById(R.id.emailAddress);
        final EditText phoneN=findViewById(R.id.phoneNumberTxtV);
        final EditText setPassword = findViewById(R.id.setPassword);
        final EditText conPassword= findViewById(R.id.comPassword);
        final Button registerBtn= findViewById(R.id.registrationBtn);
        final TextView loginNowBtn=findViewById(R.id.backToLoginNowBtn);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String fullnameTxt= fullname.getText().toString();
                final String emailTxt= email.getText().toString();
                final String phoneTxt= phoneN.getText().toString();
                final String passwordTxt= setPassword.getText().toString();
                final String conPasswordTxt= conPassword.getText().toString();

                if(fullnameTxt.isEmpty() || emailTxt.isEmpty() || phoneTxt.isEmpty() || passwordTxt.isEmpty()){
                    Toast.makeText(Register_authentication_activity.this,"fill all the fields",
                            Toast.LENGTH_SHORT).show();
                }else if( ! passwordTxt.equals(conPasswordTxt)){
                    Toast.makeText(Register_authentication_activity.this,"Passwords are not match",
                            Toast.LENGTH_SHORT).show();
                }else{
                    databaseRefrence.child("user").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.hasChild(phoneTxt)){
                                Toast.makeText(Register_authentication_activity.this,"Phone is already registered",
                                        Toast.LENGTH_SHORT).show();
                            }else {
                                databaseRefrence.child("user").child(phoneTxt).child("fullname").setValue(fullnameTxt);
                                databaseRefrence.child("user").child(phoneTxt).child("email").setValue(emailTxt);
                                databaseRefrence.child("user").child(phoneTxt).child("password").setValue(passwordTxt);

                                Toast.makeText(Register_authentication_activity.this, "User registered successfully.", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
        });
        loginNowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
};

