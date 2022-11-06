package com.examples.arganicmolecule2.model;

public class historysticker {
    private String image;
    private String username;
    private String received_time;

    public historysticker (){
    }

    public historysticker(String image, String username, String received_time) {
        this.image = image;
        this.username = username;
        this.received_time = received_time;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getReceived_time() {
        return received_time;
    }

    public void setReceived_time(String received_time) {
        this.received_time = received_time;
    }


}
