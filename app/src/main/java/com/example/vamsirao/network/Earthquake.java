package com.example.vamsirao.network;

/**
 * Created by Vamsi Rao on 2/10/2017.
 */

public class Earthquake {
    private int magnitude;
    private String location;
    private String date;

    public Earthquake(int magnitude,String location,String date){
       this.magnitude=magnitude;
        this.location=location;
        this.date=date;
    }

    public String getDate() {
        return date;
    }

    public String getLocation() {
        return location;
    }

    public int getMagnitude() {
        return magnitude;
    }
}
