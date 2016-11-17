package com.example.samir.navigationtest.Fragments;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.samir.navigationtest.Adapters.ScheduleAdapter;
import com.example.samir.navigationtest.Modules.ScheduleInfo;
import com.example.samir.navigationtest.R;

import java.util.ArrayList;


/**
 * Created by Shogun on 16.11.2016..
 */

public class ScheduleFragment extends PlaceholderFragment {

    StaggeredGridLayoutManager mStaggeredGridLayoutManager;
    RecyclerView recyclerView;
    ScheduleAdapter scheduleAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
      //  return super.onCreateView(inflater, container, savedInstanceState);
        ArrayList<ScheduleInfo> scheduleInfo = getScheduleInfos();
        View view = inflater.inflate(R.layout.fragment_schedule, container, false);
        scheduleAdapter = new ScheduleAdapter(scheduleInfo);

        recyclerView = (RecyclerView) view.findViewById(R.id.scheduleList);

        mStaggeredGridLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(mStaggeredGridLayoutManager);
        recyclerView.setAdapter(scheduleAdapter);

        return view;
    }

    public ArrayList<ScheduleInfo> getScheduleInfos(){
        ArrayList<ScheduleInfo> scheduleInfos = new ArrayList<>() ;
        scheduleInfos.add(new ScheduleInfo("7:00","8:00"));
        scheduleInfos.add(new ScheduleInfo("8:00","9:00"));
        scheduleInfos.add(new ScheduleInfo("9:00","10:00"));
        scheduleInfos.add(new ScheduleInfo("10:00","11:00"));
        scheduleInfos.add(new ScheduleInfo("11:00","12:00"));
        return scheduleInfos;
    }
}
