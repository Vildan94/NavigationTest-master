package com.example.samir.navigationtest.Fragments;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.Toast;


import com.example.samir.navigationtest.Adapters.ScheduleAdapter;
import com.example.samir.navigationtest.MainActivity;
import com.example.samir.navigationtest.Modules.DirectionFinderListener;
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
        routes.add(new Route("7:00","8:00","Ilijas","Visoko"));
        routes.add(new Route("8:00","9:00","Gorazde","Sarajevo"));
        routes.add(new Route("9:00","10:00","Zenica","Visoko"));
        routes.add(new Route("10:00","11:00","Tuzla","Sarajevo"));
        routes.add(new Route("11:00","12:00","Sarajevo","Mostar"));

        return routes;
    }

    }