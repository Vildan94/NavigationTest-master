package com.example.samir.navigationtest.Modules;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;

public class Route {
    public Distance distance;
    public Duration duration;
    public String depTime;
    public String arrTime;
    public String endAddress;
    public LatLng endLocation;
    public String startAddress;
    public LatLng startLocation;

    public List<LatLng> points;

    public Route(){
        // Empty Constructor
    }

    public Route(String dTime,String aTime, String sAddress, String eAddress){
        depTime = dTime;
        arrTime = aTime;
        startAddress = sAddress;
        endAddress = eAddress;
    }
}
