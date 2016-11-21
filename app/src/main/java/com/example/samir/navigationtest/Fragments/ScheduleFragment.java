package com.example.samir.navigationtest.Fragments;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;


import com.example.samir.navigationtest.Adapters.ScheduleAdapter;
import com.example.samir.navigationtest.MainActivity;
import com.example.samir.navigationtest.Modules.Route;
import com.example.samir.navigationtest.R;

import java.util.ArrayList;


/**
 * Created by Shogun on 16.11.2016..
 */

// Fragment that shows the schedule info
public class ScheduleFragment extends PlaceholderFragment {

    StaggeredGridLayoutManager mStaggeredGridLayoutManager;
    RecyclerView recyclerView;
    ScheduleAdapter scheduleAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ArrayList<Route> routes = getRoutes();
        View view = inflater.inflate(R.layout.fragment_schedule, container, false);

        scheduleAdapter = new ScheduleAdapter(routes,getContext());
        recyclerView = (RecyclerView) view.findViewById(R.id.scheduleList);

        mStaggeredGridLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(mStaggeredGridLayoutManager);
        recyclerView.setAdapter(scheduleAdapter);
        return view;
    }

    public ArrayList<Route> getRoutes(){
        // TO DO should be added from the database
        ArrayList<Route> routes = new ArrayList<>();
        routes.add(new Route("6:00","7:00","Visoko","Sarajevo"));
        routes.add(new Route("7:00","8:00","Visoko","Sarajevo"));
        routes.add(new Route("8:00","9:00","Visoko","Sarajevo"));
        routes.add(new Route("9:00","10:00","Visoko","Sarajevo"));
        routes.add(new Route("10:00","11:00","Visoko","Sarajevo"));
        routes.add(new Route("11:00","12:00","Visoko","Sarajevo"));
        routes.add(new Route("12:00","13:00","Visoko","Sarajevo"));
        routes.add(new Route("13:00","14:00","Visoko","Sarajevo"));
        routes.add(new Route("14:00","15:00","Visoko","Sarajevo"));
        routes.add(new Route("15:00","16:00","Visoko","Sarajevo"));
        routes.add(new Route("16:00","17:00","Visoko","Sarajevo"));
        routes.add(new Route("17:00","18:00","Visoko","Sarajevo"));
        routes.add(new Route("18:00","19:00","Visoko","Sarajevo"));
        routes.add(new Route("19:00","20:00","Visoko","Sarajevo"));
        routes.add(new Route("20:00","21:00","Visoko","Sarajevo"));
        return routes;
    }
}
