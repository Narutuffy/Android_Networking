package com.example.vamsirao.network;

/**
 * Created by Vamsi Rao on 2/10/2017.
 */

public class Earthquake {
    private String magnitude;
    private String location;
    private long timeMilliseconds;

    public Earthquake(String magnitude,String location,long timeMilliseconds){
       this.magnitude=magnitude;
        this.location=location;
        this.timeMilliseconds=timeMilliseconds;
    }

    public long getTimeMilliseconds() {
        return timeMilliseconds;
    }

    public String getLocation() {
        return location;
    }

    public String getMagnitude() {
        return magnitude;
    }
}
