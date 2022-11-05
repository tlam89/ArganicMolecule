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
import com.examples.arganicmolecule2.model.sticker;

import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {
    private List<sticker> stickerList;
    private Context context;

    public ImageAdapter (List<sticker> stickerList, Context context){
        this.stickerList = stickerList;
        this.context = context;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sticker_layout, parent, false);
        //View view = LayoutInflater.from(context).inflate(R.layout.sticker_layout, parent, false);
        return new ImageViewHolder(view);
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

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.Text);
            image = itemView.findViewById(R.id.StickerImage);
        }
    }
}
