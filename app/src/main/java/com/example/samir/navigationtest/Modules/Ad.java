package com.example.samir.navigationtest.Modules;

import java.io.Serializable;

/**
 * Created by Shogun on 17.11.2016..
 */

public class Ad implements Serializable{
    public String AdImage;
    public String AdName;

    public Ad (String imageUrl, String name){
        this.AdImage = imageUrl;
        this.AdName = name;

    }
}
