package com.examples.arganicmolecule2.A7;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.examples.arganicmolecule2.R;

public class NoteViewHolder extends RecyclerView.ViewHolder{
    TextView name;
    TextView id;
    TextView formula;
    TextView formula_weight;


    public NoteViewHolder(@NonNull View itemView) {
        super(itemView);
        this.name = itemView.findViewById(R.id.name);
        this.id = itemView.findViewById(R.id.id);
        this.formula = itemView.findViewById(R.id.formula);
        this.formula_weight = itemView.findViewById(R.id.formula_weight);
    }
}
