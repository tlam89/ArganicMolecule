package com.examples.arganicmolecule2.model;

import android.net.Uri;

public class sticker {
    private String image;
    private String name;

    public sticker(){
    }

    public sticker(String image, String name){
        this.image = image;
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
