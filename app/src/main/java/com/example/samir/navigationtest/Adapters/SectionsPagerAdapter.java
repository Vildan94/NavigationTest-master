package com.example.samir.navigationtest.Adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.SparseArray;

import com.example.samir.navigationtest.Fragments.MapViewFragment;
import com.example.samir.navigationtest.Fragments.PlaceholderFragment;

public class SectionsPagerAdapter extends FragmentPagerAdapter {

    private SparseArray<PlaceholderFragment> registeredFragments = new SparseArray<PlaceholderFragment>();

    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        PlaceholderFragment fragment = PlaceholderFragment.newInstance(position + 1);
        registeredFragments.put(position, fragment);
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Advertisment";
            case 1:
                return "Schedule";
            case 2:
                return "Map";
        }
        return null;
    }
}
