package com.examples.arganicmolecule2.A7;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.examples.arganicmolecule2.R;

import java.util.ArrayList;

public class NoteAdapter extends RecyclerView.Adapter<NoteViewHolder>{

    private ArrayList<Note> notes;
    //private Context context;

    public NoteAdapter(ArrayList<Note> notes) {
        this.notes = notes;
        //this.context = context;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_data, parent, false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        holder.formula.setText(notes.get(position).getFormula());
        holder.formula_weight.setText(notes.get(position).getFormula_weight());
        holder.id .setText(String.valueOf(notes.get(position).getId()));
        holder.name.setText(notes.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }
}
