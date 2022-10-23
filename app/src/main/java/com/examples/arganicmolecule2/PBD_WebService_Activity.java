package com.examples.arganicmolecule2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class PBD_WebService_Activity extends AppCompatActivity {

    private final service_api_key = https://api.themoviedb.org/3/discover/movie?22&api_key=8a026a6164781758d7295d0a434718dc;
    private RecyclerView pRecyclerView;
    private PostAdapter myPostAdapter;
    private ArrayList<Post> pPostList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pbd_web_service);

        pRecyclerView = findViewById(R.id.webservice_recyclerview);
        pRecyclerView.setHasFixedSize(true);
        pRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}