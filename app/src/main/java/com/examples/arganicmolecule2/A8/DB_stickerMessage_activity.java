package com.examples.arganicmolecule2.A8;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.examples.arganicmolecule2.A7.Note;
import com.examples.arganicmolecule2.A7.PBD_WebService_Activity;
import com.examples.arganicmolecule2.R;
import com.examples.arganicmolecule2.model.sticker;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DB_stickerMessage_activity extends AppCompatActivity {
    private RecyclerView stickers;
    private ImageAdapter customerAdapter;
    ArrayList<sticker> stickerList;
    Context context;
    LinkClickListener listener;
    ImageView sendImage;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    Uri uri;

    //Thinh Lam
    EditText target_EditText;
    ArrayList<String> friendList;
    String userID="";
    Button sendButton;

    static final int USER_ID_REQUEST = 1;

    //Thinh Lam

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db_sticker_message);

        stickers = findViewById(R.id.sticker);
        target_EditText = findViewById(R.id.targetID_EditText);
//        TextView user_num = findViewById(R.id.recent_sticker_received2);

        stickers.setHasFixedSize(true);
        stickers.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        stickerList = new ArrayList<>();
        ClearAll();
        GetDataFromFirebase();
        getUSER_ID();
//        user_num.setText(userID);
        Log.i("USER_ID1", userID);
        // Wire up the About button
        Button atn = (Button) findViewById(R.id.about);
        atn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(DB_stickerMessage_activity.this, DB_about_activity.class);
                startActivity(intent1);
            }
        });

        // Wire up the History button
        Button htn = (Button) findViewById(R.id.history);
        htn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(DB_stickerMessage_activity.this, DB_history_activity.class);
                startActivity(intent2);
            }
        });

        //Click on recyclerview display at new ImageView

        listener = new LinkClickListener() {
            @Override
            public void onItemClick(int position) {
                String URL = stickerList.get(position).getImage();
                uri = Uri.parse(URL);
                Log.d(TAG, "Clicked on: " + uri);
                //Intent intent = new Intent(Intent.ACTION_VIEW);
                //intent.setData(uri);
                //startActivity(intent);
                Glide.with(context).load(uri).into(sendImage);
            }
        };
        sendImage = findViewById(R.id.sendImage);




        //Thinh Lam
        sendButton = findViewById(R.id.send);

        Log.i("USER_ID2", userID);
        //Check if a target exists. Otherwise, show a Toast.
        sendButton.setOnClickListener(view -> {

            String temp = "History/" + userID;
            Log.i("USER_ID3", userID);
            DatabaseReference userRef = databaseReference.child(temp);
            Toast.makeText(this,temp,Toast.LENGTH_LONG).show();
//            String userHistory= user_num.getText().toString();
            Date currentTime = Calendar.getInstance().getTime();
            String dateTime = currentTime.toString();
            String signalType = "sendTo";
            String friendName = target_EditText.getText().toString();
            String imageURL = uri.toString();
            updateHistory(userRef, dateTime, signalType, friendName, imageURL);
        });
    }

    private void updateHistory(DatabaseReference userRef, String datetime, String signalType, String friendName, String imageURL ) {
        //Thinh Lam
        //Create,Read,Update,Delete in History
        DatabaseReference currentUser = userRef.push();
        currentUser.setValue(new Record(signalType,datetime,friendName,imageURL));
    }

    public static class Record {
        public String datetime;
        public String signalType;
        public String friendName;
        public String imageURL;

        public Record(String signalType, String datetime, String friendName, String imageURL) {
            this.signalType= signalType;
            this.friendName = friendName;
            this.imageURL = imageURL;
            this.datetime = datetime;
        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == USER_ID_REQUEST && resultCode == RESULT_OK) {
            assert data != null;
            userID = data.getStringExtra(DB_authentication_activity.USER_ID);
        } else {
            Toast.makeText(this, "No ID", Toast.LENGTH_LONG).show();
        }
    }

    private void GetDataFromFirebase() {
        Query query = databaseReference.child("Data");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ClearAll();
                for(DataSnapshot datasnapshot: snapshot.getChildren()){
                    sticker sticker = new sticker();
                    sticker.setImage(datasnapshot.child("image").getValue().toString());
                    sticker.setName(datasnapshot.child("name").getValue().toString());
                    stickerList.add(sticker);
                }
                System.out.println(stickerList);
                context = getApplicationContext();
                customerAdapter = new ImageAdapter(stickerList, context, listener);
                stickers.setAdapter(customerAdapter);
                customerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });


    }



    private void ClearAll(){
        if(stickerList!=null){
            stickerList.clear();
            if(customerAdapter!=null){
                customerAdapter.notifyDataSetChanged();
            }
        }
        stickerList = new ArrayList<>();
    }

    // Handling Orientation Changes on Android for ImageView

    public void getUSER_ID() {
        if(getIntent().hasExtra(DB_authentication_activity.USER_ID)){
            userID = getIntent().getStringExtra(DB_authentication_activity.USER_ID);
        }
    }

}