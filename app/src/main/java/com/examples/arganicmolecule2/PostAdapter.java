package com.examples.arganicmolecule2;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {

    List<Post> postList;
    Context context;

    public PostAdapter(Context context, List<Post> postList) {
        this.context = context;
        this.postList = postList;
    }

    private static String IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w185";

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_movie, parent, false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        Post currentPost = postList.get(position);

        String postImagePath = IMAGE_BASE_URL + currentPost.getImagePath();
        String postTitle = currentPost.getTitle();

        holder.pPostTitle.setText(postTitle);
        Log.v("imagepath", postImagePath);

        Picasso.get()
                .load(postImagePath)
                .fit()
                .centerInside()
                .into(holder.pPostImage);
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public static class PostViewHolder extends RecyclerView.ViewHolder {
        public ImageView pPostImage;
        public TextView pPostTitle;
        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            pPostImage = itemView.findViewById(R.id.movie_image);
            pPostTitle = itemView.findViewById(R.id.movie_title);
        }
    }
}
