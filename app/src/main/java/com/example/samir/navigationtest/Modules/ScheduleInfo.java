package com.example.samir.navigationtest.Modules;

import java.io.Serializable;

/**
 * Created by Shogun on 16.11.2016..
 */

public class ScheduleInfo implements Serializable {
    public String arrival_time;
    public String departure_time;

    public  ScheduleInfo(String arrival_time, String departure_time) {

        this.arrival_time=arrival_time;
        this.departure_time=departure_time;

    }


}
