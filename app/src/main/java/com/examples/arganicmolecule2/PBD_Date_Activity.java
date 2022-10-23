package com.examples.arganicmolecule2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.examples.arganicmolecule2.model.molecule;
import com.examples.arganicmolecule2.network.APIclient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PBD_Date_Activity extends AppCompatActivity {

    GridView gridView;
    CustomAdapter customerAdapter;
    public static List<molecule> moleculeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pbd_date);

        gridView = findViewById(R.id.gridView);
        moleculeList = new ArrayList<>();

        //make network call
        Call<List<molecule>> call = APIclient.apiInterface().getmolecule();
        call.enqueue(new Callback<List<molecule>>() {
            @Override
            public void onResponse(Call<List<molecule>> call, Response<List<molecule>> response) {
                if(response.isSuccessful()){
                    moleculeList = response.body();
                    customerAdapter = new CustomAdapter(response.body(),PBD_Date_Activity.this );
                    gridView.setAdapter(customerAdapter );
                    gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            //intent
                            Intent intent = new Intent();
                            //intent.putExtra("title",moleculeList.get(position).getTitle());
                            //intent.putExtra("date",moleculeList.get(position).getDate());
                            //intent.putExtra("image",moleculeList.get(position).getLink());

                            startActivity(new Intent(getApplicationContext(), item_data.class)
                                    .putExtra("title",moleculeList.get(position).getTitle())
                                    .putExtra("date",moleculeList.get(position).getDate())
                                    .putExtra("image",moleculeList.get(position).getLink()));

                        }
                    });

                }else{
                    Toast.makeText(getApplicationContext(),"An error Occured",Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<List<molecule>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"An error Occured"+t.getLocalizedMessage(),Toast.LENGTH_LONG).show();
            }
        });



    }

    public class CustomAdapter extends BaseAdapter{
         public List<molecule> moleculeList;
        public Context context;

        public CustomAdapter(List<molecule> moleculeList, Context context) {
            this.moleculeList = moleculeList;
            this.context = context;
        }

        @Override
        public int getCount() {
            return moleculeList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = LayoutInflater.from(context).inflate(R.layout.row_data,null);
            //find view
            TextView title = view.findViewById(R.id.title);
            //TextView date = view.findViewById(R.id.date);
            ImageView image = view.findViewById(R.id.imageView);

            //set data
            title.setText(moleculeList.get(position).getTitle());
            //date.setText(moleculeList.get(position).getDate());
            Glide.with(context).load(moleculeList.get(position).getLink()).into(image);

            return view;
        }
    }

    @Override
    public void onBackPressed() {
        if (true) {
            AlertDialog.Builder alertdialog = new AlertDialog.Builder(PBD_Date_Activity.this);
            alertdialog.setTitle("Alert!");
            alertdialog.setMessage("Are you sure you want to leave this page?");
            alertdialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });

            alertdialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            AlertDialog alert = alertdialog.create();
            alertdialog.show();
        } else {
            finish();
        }
    }
}