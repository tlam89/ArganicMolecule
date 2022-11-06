package com.examples.arganicmolecule2.A8;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.examples.arganicmolecule2.R;
import com.examples.arganicmolecule2.model.sticker;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class DB_history_activity extends AppCompatActivity {
    //Create this list to store a user's history from firebase.
    ArrayList<Rec> recordList;

    ArrayList<String> uniqueIdList;
    Context context;
    LinkClickListener listener;
    FirebaseDatabase firebaseDB;
    DatabaseReference databaseRef;

    Button showHistory_Button;
    RecyclerView history_recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        history_recyclerView = findViewById(R.id.history_recyclerView);
        showHistory_Button = findViewById(R.id.show_Button);

        firebaseDB = FirebaseDatabase.getInstance();
        databaseRef = firebaseDB.getReference("History");

        recordList = new ArrayList<>();

        showHistory_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getHistory();
            }
        });


    }

    private void getHistory() {
        DatabaseReference dbRef = databaseRef.child("3");
        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.i("snapshot", snapshot.getKey());
                for(DataSnapshot datasnapshot: snapshot.getChildren()){

                    String datetime = datasnapshot.child("datetime").getValue().toString();
                    String friendName = datasnapshot.child("friendName").getValue().toString();
                    String signalType = datasnapshot.child("signalType").getValue().toString();
                    String imageURL = datasnapshot.child("imageURL").getValue().toString();
                    Log.i("datetime1", datetime);
                    Log.i("friend", friendName);
                    Log.i("signal", signalType);
                    Log.i("url", imageURL);

//                    sticker.setName(datasnapshot.child("name").getValue().toString());
                    Rec tempRecord = new Rec(datetime, friendName, signalType, imageURL);
                    Log.i("tempR", tempRecord.toString());

                    recordList.add(tempRecord);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
//

        });


    }

    public static class Rec {
        public String datetime;
        public String signalType;
        public String friendName;
        public String imageURL;

        public Rec(String signalType, String datetime, String friendName, String imageURL) {
            this.signalType= signalType;
            this.friendName = friendName;
            this.imageURL = imageURL;
            this.datetime = datetime;
        }

    }






}