package com.example.vamsirao.network;

/**
 * Created by Vamsi Rao on 2/10/2017.
 */

public class Earthquake {
    private double magnitude;
    private String location;
    private long timeMilliseconds;
    private String url;

    public Earthquake(double magnitude,String location,long timeMilliseconds,String url){
       this.magnitude=magnitude;
        this.location=location;
        this.timeMilliseconds=timeMilliseconds;
        this.url=url;
    }

    public long getTimeMilliseconds() {
        return timeMilliseconds;
    }

    public String getLocation() {
        return location;
    }

    public double getMagnitude() {
        return magnitude;
    }

    public String getUrl() {
        return url;
    }
}

