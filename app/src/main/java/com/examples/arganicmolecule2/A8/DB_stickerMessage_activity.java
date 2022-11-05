package com.examples.arganicmolecule2.A8;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
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
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.examples.arganicmolecule2.R;
import com.examples.arganicmolecule2.model.sticker;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DB_stickerMessage_activity extends AppCompatActivity {
    private RecyclerView stickers;
    private ImageAdapter customerAdapter;
    ArrayList<sticker> stickerList;
    Context context;
    LinkClickListener listener;
    ImageView sendImage;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db_sticker_message);

        stickers = findViewById(R.id.sticker);
        stickers.setHasFixedSize(true);
        stickers.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        stickerList = new ArrayList<>();
        ClearAll();
        GetDataFromFirebase();

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
        sendImage = findViewById(R.id.sendImage);
        listener = new LinkClickListener() {
            @Override
            public void onItemClick(int position) {
                String URL = stickerList.get(position).getImage();
                Uri uri = Uri.parse(URL);
                Log.d(TAG, "Clicked on: " + uri);
                //Intent intent = new Intent(Intent.ACTION_VIEW);
                //intent.setData(uri);
                //startActivity(intent);
                Glide.with(context).load(uri).into(sendImage);
            }
        };

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

}