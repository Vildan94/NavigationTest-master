package com.example.samir.navigationtest.Modules;

import java.io.Serializable;



public class Ad implements Serializable{
    public String AdId;
    public String AdImage;
    public String AdName;
    public String AdDescription;
    public String AdRating;
    public String AdVotes;
    public String AdSumVotes;


/*
    public void setAdRating(String adRating) {
        AdRating = adRating;
    }



    public void setAdVotes(String adVotes) {
        AdVotes = adVotes;
    }



    public void setAdSumVotes(String adSumVotes) {
        AdSumVotes = adSumVotes;
    }*/

    public Ad(String adId, String adImage, String adName, String adDescription, String adVotes, String adSumVotes, String adRating) {
        AdId = adId;
        AdImage = adImage;
        AdName = adName;
        AdDescription = adDescription;
        AdVotes = adVotes;
        AdSumVotes = adSumVotes;
        AdRating = adRating;
    }


    public Ad() {

        AdId=null;
        AdImage = null;
        AdName = null;
        AdDescription = null;
        AdRating=null;
        AdVotes=null;
        AdSumVotes=null;

    }



}
