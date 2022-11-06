package com.examples.arganicmolecule2.A8;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.examples.arganicmolecule2.R;
import com.examples.arganicmolecule2.model.historysticker;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>{
    private List<historysticker> historystickerList;
    private Context context;

    public HistoryAdapter (List<historysticker> stickerList, Context context){
        this.historystickerList = stickerList;
        this.context = context;
    }

    @NonNull
    @Override
    public HistoryAdapter.HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_block_layout, parent, false);
        //View view = LayoutInflater.from(context).inflate(R.layout.sticker_layout, parent, false);
        return new HistoryAdapter.HistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryAdapter.HistoryViewHolder holder, int position) {
        holder.username.setText(historystickerList.get(position).getUsername());
        holder.received_time.setText(historystickerList.get(position).getReceived_time());
        //holder.image.setImageResource(stickerList.get(position).getImage());
        Glide.with(context).load(historystickerList.get(position).getImage()).into(holder.image);

    }

    @Override
    public int getItemCount() {
        return historystickerList.size();
    }

    public class HistoryViewHolder extends RecyclerView.ViewHolder{
        private ImageView image;
        private TextView username;
        private TextView received_time;

        public HistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.stickersample);
            username = itemView.findViewById(R.id.username);
            received_time = itemView.findViewById(R.id.received_time);

        }
    }

}
