package com.examples.arganicmolecule2.A8;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.examples.arganicmolecule2.R;
import com.examples.arganicmolecule2.model.historysticker;
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

    RecyclerView history_recyclerView;
    private HistoryAdapter customerAdapter;
    ArrayList<historysticker> historystickerList;
    Context context;
    FirebaseDatabase firebaseDB;
    DatabaseReference databaseRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        firebaseDB = FirebaseDatabase.getInstance();
        databaseRef = firebaseDB.getReference("History");


        //For displaying history in the recyclerview
        history_recyclerView = findViewById(R.id.history_recyclerView);
        //history_recyclerView.setHasFixedSize(true);
        history_recyclerView.setLayoutManager(new LinearLayoutManager(this));
        historystickerList = new ArrayList<>();
        ClearAll();
        getHistory();

    }

    private void getHistory() {
        DatabaseReference dbRef = databaseRef.child("3");
        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.i("snapshot", snapshot.getKey());
                ClearAll();
                for(DataSnapshot datasnapshot: snapshot.getChildren()){

                    //Creating temporary history sticker and append to the list
                    historysticker temp = new historysticker();
                    temp.setImage(datasnapshot.child("imageURL").getValue().toString());
                    temp.setUsername(datasnapshot.child("friendName").getValue().toString());
                    temp.setReceived_time(datasnapshot.child("datetime").getValue().toString());
                    historystickerList.add(temp);
                }
                context = getApplicationContext();
                customerAdapter = new HistoryAdapter(historystickerList, context);
                history_recyclerView.setAdapter(customerAdapter);
                customerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void ClearAll(){
        if(historystickerList!=null){
            historystickerList.clear();
            if(customerAdapter!=null){
                customerAdapter.notifyDataSetChanged();
            }
        }
        historystickerList = new ArrayList<>();
    }

}