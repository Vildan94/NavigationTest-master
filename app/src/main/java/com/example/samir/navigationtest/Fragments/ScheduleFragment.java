package com.example.samir.navigationtest.Fragments;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.samir.navigationtest.Adapters.ScheduleAdapter;
import com.example.samir.navigationtest.Modules.SimpleRoute;
import com.example.samir.navigationtest.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


/**
 * Created by Shogun on 16.11.2016..
 */

// Fragment that shows the schedule info
public class ScheduleFragment extends PlaceholderFragment {

    StaggeredGridLayoutManager mStaggeredGridLayoutManager;
    RecyclerView recyclerView;
    ScheduleAdapter scheduleAdapter;
    Spinner spinner1;
    Spinner spinner2;
    ArrayList<SimpleRoute> routes;
    String selection;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        routes = new ArrayList<>();
        selection = "Sarajevo";
        //initializeRoutes();

        View view = inflater.inflate(R.layout.fragment_schedule, container, false);

        spinner1 = (Spinner) view.findViewById(R.id.locationn);
        spinner2 = (Spinner) view.findViewById(R.id.destinationn);

        final ArrayList<String> a = new ArrayList();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference();
        databaseReference.child("Stations").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                for (DataSnapshot child:children) {
                    String value = child.getValue(String.class);
                    if(!a.contains(value))
                        a.add(value);
                }
                // Set 1 spiner
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, a);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner1.setAdapter(adapter);
                spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        selection = a.get(position);
                        routes = getStation(selection);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
                //

                // Set 2 spiner
                ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, a);
                adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner2.setAdapter(adapter2);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        // when I get location and destination I need to find routes

        routes = getStation(selection);

        // need routes MUST HAVE
        scheduleAdapter = new ScheduleAdapter(routes,getContext());
        recyclerView = (RecyclerView) view.findViewById(R.id.scheduleList);
        mStaggeredGridLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(mStaggeredGridLayoutManager);
        recyclerView.setAdapter(scheduleAdapter);
        return view;
    }

    private ArrayList<SimpleRoute> getStation(String name) {
        final ArrayList<SimpleRoute> s = new ArrayList<>();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference();
        databaseReference.child(name).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                for (DataSnapshot child : children) {
                    SimpleRoute r = child.getValue(SimpleRoute.class);
                    boolean cond = true;
                    for (SimpleRoute i:routes) {
                        if(i == r){
                            cond = false;
                        }
                    }
                    if(cond){
                        s.add(r);
                    }
                    else
                        cond = true;
                    scheduleAdapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return s;
    }


    // works
    public void initializeRoutes(){
        ArrayList<String> VS = new ArrayList<>();
        VS.add("Ljeseva");
        VS.add("Podlugovi");
        VS.add("LIlijas");
        VS.add("Semizovac");
        VS.add("Vogosca");
        ArrayList<String> SV = new ArrayList<>();
        SV.add("Vogosca");
        SV.add("Semizovac");
        SV.add("Ilijas");
        SV.add("Podlugovi");
        SV.add("Ljeseva");
        ArrayList<SimpleRoute> routes1 = new ArrayList<>();
        routes1.add(new SimpleRoute("6:00","7:00","Visoko","Sarajevo",VS));
        routes1.add(new SimpleRoute("8:00","9:00","Visoko","Sarajevo",VS));
        routes1.add(new SimpleRoute("10:00","11:00","Visoko","Sarajevo",VS));
        routes1.add(new SimpleRoute("7:00","8:00","Sarajevo","Visoko",SV));
        routes1.add(new SimpleRoute("9:00","10:00","Sarajevo","Visoko",SV));
        routes1.add(new SimpleRoute("11:00","12:00","Sarajevo","Visoko",SV));

        // Sarajevo 43.858113, 18.412156
        // Visoko 43.987477, 18.177795
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference();
        for (int i=0;i<routes1.size();i++) {
            databaseReference.child(routes1.get(i).startAddress).push().setValue(routes1.get(i));
        }
    }
}