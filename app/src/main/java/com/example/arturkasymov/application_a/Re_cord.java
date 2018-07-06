package com.example.arturkasymov.application_a;

public class Re_cord {
    private int id;
    private String reference;
    private int status;
    private int time;
    public Re_cord(String reference, int status, int time){
        //this.id = id;
        this.reference = reference;
        this.status = status;
        this.time = time;
    }

    public Re_cord(int id, String reference, int status, int time){
        this.id = id;
        this.reference = reference;
        this.status = status;
        this.time = time;
    }


    public String getReference() {
        return reference;
    }

    public int getStatus() {
        return status;
    }

    public int getTime() {
        return time;
    }

    public int getId() {
        return id;
    }
}
