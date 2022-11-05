package com.examples.arganicmolecule2.A8;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.examples.arganicmolecule2.R;
import com.examples.arganicmolecule2.model.sticker;

import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {
    private List<sticker> stickerList;
    private Context context;
    private LinkClickListener listener;


    public ImageAdapter (List<sticker> stickerList, Context context, LinkClickListener listener){
        this.stickerList = stickerList;
        this.context = context;
        this.listener = listener;
    }
    public void setOnItemClickListener(LinkClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sticker_layout, parent, false);
        //View view = LayoutInflater.from(context).inflate(R.layout.sticker_layout, parent, false);
        return new ImageViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        holder.name.setText(stickerList.get(position).getName());
        //holder.image.setImageResource(stickerList.get(position).getImage());
        Glide.with(context).load(stickerList.get(position).getImage()).into(holder.image);

    }

    @Override
    public int getItemCount() {
        return stickerList.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder{
        private TextView name;
        private ImageView image;

        public ImageViewHolder(@NonNull View itemView, LinkClickListener listener) {
            super(itemView);
            name = itemView.findViewById(R.id.Text);
            image = itemView.findViewById(R.id.StickerImage);

            itemView.setOnClickListener(v -> {
                if (listener != null) {
                    int position = getLayoutPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(position);
                    }
                }
            });

        }
    }
}
