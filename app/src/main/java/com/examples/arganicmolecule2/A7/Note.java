package com.examples.arganicmolecule2.A7;

public class Note {
    private String formula;
    private String formula_weight;
    private String id;
    private String name;

    public Note() {}

    public Note(String formula, String formula_weight,  String id, String name){
        this.formula = formula;
        this.formula_weight= formula_weight;
        this.name = name;
        this.id = id;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public String getFormula_weight() {
        return formula_weight;
    }

    public void setFormula_weight(String formula_weight) {
        this.formula_weight = formula_weight;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
