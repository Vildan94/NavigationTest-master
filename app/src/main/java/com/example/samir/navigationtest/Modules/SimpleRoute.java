package com.example.samir.navigationtest.Modules;

import java.util.ArrayList;

public class SimpleRoute {
    public String depTime;
    public String arrTime;
    public String endAddress;
    public String startAddress;
    public ArrayList<String> allStopovers;

    public SimpleRoute(){
        this.depTime = null;
        this.arrTime = null;
        this.endAddress = null;
        this.startAddress = null;
        allStopovers = null;
    }

    public SimpleRoute(String dTime,String aTime, String sAddress, String eAddress, ArrayList<String> aStopovers){
        depTime = dTime;
        arrTime = aTime;
        startAddress = sAddress;
        endAddress = eAddress;
        allStopovers = aStopovers;
    }
}
