package com.example.samir.navigationtest.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

// The fragment that is used as a model
public abstract class PlaceholderFragment extends Fragment {

    public PlaceholderFragment() {
    }

    public static PlaceholderFragment newInstance(int sectionNumber) {

        MapViewFragment mapViewFragment = new MapViewFragment(); // Samir
        AdvertismentFragment advertismentFragment = new AdvertismentFragment(); // Ahmed
        ScheduleFragment scheduleFragment = new ScheduleFragment(); // Shogun V
        if(sectionNumber==1){ // Ahmed
            return  advertismentFragment;
        }
        else if(sectionNumber==2){          // Shogun V
            return  scheduleFragment;
        }
        else { // Samir
            return  mapViewFragment;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return null;
    }
}
