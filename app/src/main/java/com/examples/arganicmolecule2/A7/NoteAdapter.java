package com.examples.arganicmolecule2.A7;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.examples.arganicmolecule2.R;

import java.util.ArrayList;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NotesViewHolder> {

    private final ArrayList<Note> notes;
    //private Context context;

    public NoteAdapter(ArrayList<Note> notes) {
        this.notes = notes;
        //this.context = context;
    }

    @NonNull
    @Override
    public NoteAdapter.NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_data, parent,
                false);
        return new NoteAdapter.NotesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteAdapter.NotesViewHolder holder, int position) {
        holder.formula.setText(notes.get(position).getFormula());
        holder.formula_weight.setText(notes.get(position).getFormula_weight());
        holder.id.setText(notes.get(position).getId());
        holder.name.setText(notes.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public static class NotesViewHolder extends RecyclerView.ViewHolder {
        private final TextView formula;
        private final TextView formula_weight;
        private final TextView id;
        private final TextView name;
        public NotesViewHolder(@NonNull View notesView) {
            super(notesView);
            formula = notesView.findViewById(R.id.molecule_formula);
            formula_weight = notesView.findViewById(R.id.molecule_formular_weight);
            id = notesView.findViewById(R.id.molecule_id);
            name = notesView.findViewById(R.id.molecule_name);
        }
    }
}
