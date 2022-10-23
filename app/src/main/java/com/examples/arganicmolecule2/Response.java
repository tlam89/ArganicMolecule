package com.examples.arganicmolecule2;

import android.graphics.Movie;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Response {
    public class TMDB_Response {

        @SerializedName("results")
        @Expose
        private ArrayList<Post> results;

        public ArrayList<Post> getResults() {
            return results;
        }

        @NonNull
        @Override
        public String toString() {
            return results.toString();
        }
    }
}
