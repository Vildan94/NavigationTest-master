package com.example.samir.navigationtest.Fragments;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;
import com.example.samir.navigationtest.Adapters.ScheduleAdapter;
import com.example.samir.navigationtest.Modules.SimpleRoute;
import com.example.samir.navigationtest.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Vildan on 16.11.2016..
 */
// Fragment that shows the schedule info
public class DepartureFragment extends Fragment {

    private static DepartureFragment instance = null;
    private StaggeredGridLayoutManager mStaggeredGridLayoutManager;
    private RecyclerView recyclerView;
    private ScheduleAdapter departureAdapter;

    private com.toptoche.searchablespinnerlibrary.SearchableSpinner spinner1;

    private ArrayList<SimpleRoute> routes;
    private ArrayList<String> routeNames = new ArrayList<>();
    private String selection;
    private String dayOfWeek = "Monday";


    final Calendar cal = Calendar.getInstance();

    private int year_s = cal.get(Calendar.YEAR);
    private int month_s = cal.get(Calendar.MONTH);
    private int day_s = cal.get(Calendar.DAY_OF_WEEK);

    public DepartureFragment() {
    }

    public static DepartureFragment getInstance() {
        if(instance == null) {
            instance = new DepartureFragment();
        }
        return instance;
    }

    private DatePickerDialog.OnDateSetListener dpListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            year_s = year;
            month_s = month;
            day_s = dayOfMonth;

            SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
            Date d = new Date(year_s, month_s, day_s-1);
            dayOfWeek = sdf.format(d);
            // Toast.makeText(view.getContext(), dayOfWeek, Toast.LENGTH_LONG).show();
        }
    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        routes = new ArrayList<>();
        selection = "Sarajevo";

        //initializeRoutes();
        //  final DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), dpListener, year_s, month_s, day_s);

        View view = inflater.inflate(R.layout.fragment_departure, container, false);
        spinner1 = (com.toptoche.searchablespinnerlibrary.SearchableSpinner) view.findViewById(R.id.locationn);

        final ArrayList<String> stations = new ArrayList();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference();
        databaseReference.child("Stations").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                for (DataSnapshot child:children) {
                    String value = child.getValue(String.class);
                    if(!stations.contains(value))
                        stations.add(value);
                }
                // Set 1 spinner
                final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, stations);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner1.setAdapter(adapter);
                spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        selection = stations.get(position);

                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });


            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        Button displayRoutes = (Button) view.findViewById (R.id.btnDisplayRoutes);
        displayRoutes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                departureAdapter.scheduleList = getStation(selection);
                departureAdapter.notifyDataSetChanged();
                Toast.makeText(view.getContext(), "You have searched!", Toast.LENGTH_LONG).show();
            }
        });

        departureAdapter = new ScheduleAdapter(routes,getContext());
        recyclerView = (RecyclerView) view.findViewById(R.id.scheduleList);
        mStaggeredGridLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(mStaggeredGridLayoutManager);
        recyclerView.setAdapter(departureAdapter);
        return view;
    }

    private ArrayList<SimpleRoute> getStation(final String name) {
        final ArrayList<SimpleRoute> s = new ArrayList<>();
        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

        databaseReference.child("Routes").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                for (DataSnapshot child:children) {
                    String value = child.getValue(String.class);
                    // process value
                    String[] arr = value.split("-");
                    if(arr[0].equals(name)){
                        databaseReference.child(value).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                                for (DataSnapshot child : children) {
                                    SimpleRoute r = child.getValue(SimpleRoute.class);
                                    s.add(r);
                                    departureAdapter.notifyDataSetChanged();
                                }
                            }
                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                            }
                        });
                    }
                    ///
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        return s;
    }

}
