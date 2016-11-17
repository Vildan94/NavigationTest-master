package com.example.samir.navigationtest.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.samir.navigationtest.R;

// The fragment that is used on the activity can be made abstract later
public class PlaceholderFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    public PlaceholderFragment() {
    }

    public static PlaceholderFragment newInstance(int sectionNumber) {

        PlaceholderFragment fragment = new PlaceholderFragment();
        MapViewFragment mapViewFragment = new MapViewFragment();
        AdvertismentFragment advertismentFragment = new AdvertismentFragment();
        ScheduleFragment scheduleFragment = new ScheduleFragment(); // Shogun V
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        mapViewFragment.setArguments(args);
        advertismentFragment.setArguments(args);
        scheduleFragment.setArguments(args); // Shogun V

        if(sectionNumber==1){
            return  advertismentFragment;
        }
        else if(sectionNumber==2){          // Shogun V
            return  scheduleFragment;
        }
        else if(sectionNumber==3){
            return  mapViewFragment;
        }
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        TextView textView = (TextView) rootView.findViewById(R.id.section_label);
        textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));

        return rootView;
    }
}
