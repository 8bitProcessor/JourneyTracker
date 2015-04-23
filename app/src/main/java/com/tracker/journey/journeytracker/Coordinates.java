package com.tracker.journey.journeytracker;

/**
 * Created by qbit on 23/04/15.
 */
public class Coordinates {
    double longtitude;
    double latitude;
    double speed;
    double averages;
    public Coordinates(){
        this.longtitude=longtitude;
        this.latitude=latitude;
        this.speed=speed;
    }
    public Double getLongtitude(){
        return longtitude;
    }
    public Double getLatitude(){
        return latitude;
    }
    public Double getSpeed(){return  speed;}
    public Double getAverages(){return averages;}
    public void setLongtitude(Double Longtitude){
            this.longtitude=Longtitude;
    }
    public void setLatitude(Double Latitude){
            this.latitude=-Latitude;
    }
    public void setSpeed(Double Speed ){this.speed=Speed;}
    public void setAverages(Double Averages){this.averages=Averages;}
}
