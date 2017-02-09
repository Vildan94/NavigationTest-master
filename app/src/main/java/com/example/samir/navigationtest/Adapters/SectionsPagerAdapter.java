package com.example.samir.navigationtest.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.samir.navigationtest.Fragments.AdvertismentFragment;
import com.example.samir.navigationtest.Fragments.DepartureFragment;
import com.example.samir.navigationtest.Fragments.MapViewFragment;
import com.example.samir.navigationtest.Fragments.ScheduleFragment;


public class SectionsPagerAdapter extends FragmentPagerAdapter  {



    public SectionsPagerAdapter(FragmentManager fm) {

        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if(position == 0)
            return AdvertismentFragment.getInstance();
        else if(position == 1)
            return DepartureFragment.getInstance();
        else if(position == 2)
            return ScheduleFragment.getInstance();
        else if(position == 3)
            return MapViewFragment.getInstance();
        return  null;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "ADS";
            case 1:
                return "Departures";
            case 2:
                return "Schedule";
            case 3:
                return "Map";
        }
        return null;
    }



}
