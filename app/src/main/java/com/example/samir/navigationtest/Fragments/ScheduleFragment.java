package com.example.samir.navigationtest.Fragments;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
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
import java.util.List;

/**
 * Created by Vildan on 16.11.2016..
 */
// Fragment that shows the schedule info
public class ScheduleFragment extends Fragment {

    private static ScheduleFragment instance = null;
    private StaggeredGridLayoutManager mStaggeredGridLayoutManager;
    private RecyclerView recyclerView;
    private ScheduleAdapter scheduleAdapter;

    private com.toptoche.searchablespinnerlibrary.SearchableSpinner spinner1;
    private com.toptoche.searchablespinnerlibrary.SearchableSpinner spinner2;

    private ArrayList<SimpleRoute> routes;

    private String selection;
    private String selection1;
    private String selection2;
    private String dayOfWeek = "Monday";

    Button startDate;

    final Calendar cal = Calendar.getInstance();

    private int year_s = cal.get(Calendar.YEAR);
    private int month_s = cal.get(Calendar.MONTH);
    private int day_s = cal.get(Calendar.DAY_OF_WEEK);

    public ScheduleFragment() {
    }

    public static ScheduleFragment getInstance() {
        if(instance == null) {
            instance = new ScheduleFragment();
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
            Toast.makeText(view.getContext(), dayOfWeek, Toast.LENGTH_LONG).show();
        }
    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        routes = new ArrayList<>();

        selection = "Sarajevo-Visoko";
        selection1 = "Sarajevo";
        selection2 = "Visoko";

        //initializeRoutes();
        final DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), dpListener, year_s, month_s, day_s);

        View view = inflater.inflate(R.layout.fragment_schedule, container, false);
        spinner1 = (com.toptoche.searchablespinnerlibrary.SearchableSpinner) view.findViewById(R.id.locationn);
        spinner2 = (com.toptoche.searchablespinnerlibrary.SearchableSpinner) view.findViewById(R.id.destinationn);
        Button displayRoutes = (Button) view.findViewById (R.id.btnDisplayRoutes);
        displayRoutes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scheduleAdapter.scheduleList = getStation(selection);
                for (SimpleRoute r : scheduleAdapter.scheduleList) {
                        scheduleAdapter.notifyDataSetChanged();
                }
                Toast.makeText(view.getContext(), "You have searched!", Toast.LENGTH_LONG).show();
            }
        });

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
                // Set 1 spinner
                final ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, a);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner1.setAdapter(adapter);
                spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        selection1 = a.get(position);
                        selection = selection1+"-"+selection2;
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });
                //
                // Set 2 spinner
                ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, a);
                adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner2.setAdapter(adapter2);
                spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        selection2 = a.get(position);
                        selection = selection1+"-"+selection2;
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

        startDate = (Button) view.findViewById(R.id.startDate);

        startDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });

        // when I get location and destination I need to find routes
        //routes = getStation(selection1);
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
        // Main emphasis on the name as it allows the data to change when we change it
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
                        boolean added = false;
                        for (String st: r.operatingDays) {
                            if(!added) {
                                s.add(r);
                                added = true;
                            }
                        }
                    }
                    else
                        cond = true;
                    if(!r.endAddress.equals(selection2)){
                        // DO Something
                    }
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
        ArrayList<String> workingDays = new ArrayList<>();
        workingDays.add("Monday");
        workingDays.add("Tuesday");
        workingDays.add("Wednesday");
        workingDays.add("Thursday");
        workingDays.add("Firday");

        ArrayList<String> nonWorkingDays = new ArrayList<>();
        nonWorkingDays.add("Saturday");
        nonWorkingDays.add("Sunday");

        ArrayList<String> weekDays = new ArrayList<>();
        weekDays.addAll(workingDays);
        weekDays.addAll(nonWorkingDays);

        ArrayList<String> VS = new ArrayList<>();
        VS.add("Autobuska stanica Visoko, Federacija Bosne i Hercegovine");
        VS.add("R445 Ljesevo, Federacija Bosne i Hercegovine");
        VS.add("Bosanski Put, Ilijas, Federacija Bosne i Hercegovine");
        VS.add("R445 Malesici, Federacija Bosne i Hercegovine");
        VS.add("Stara Cesta, Semizovac, Federacija Bosne i Hercegovine");
        VS.add("Igmanska 44, Vogošća, Federacija Bosne i Hercegovine");
        VS.add("Maršala Tita 10, Sarajevo, Federacija Bosne i Hercegovine");
        VS.add("Autobuska stanica Sarajevo, Federacija Bosne i Hercegovine");
        ArrayList<String> SV = new ArrayList<>();
        SV.add("Autobuska stanica Sarajevo, Federacija Bosne i Hercegovine");
        SV.add("Hamze Hume, Sarajevo, Federacija Bosne i Hercegovine");
        SV.add("Igmanska 44, Vogošća, Federacija Bosne i Hercegovine");
        SV.add("Stara Cesta, Semizovac, Federacija Bosne i Hercegovine");
        SV.add("R445 Malesici, Federacija Bosne i Hercegovine");
        SV.add("Bosanski Put, Ilijas, Federacija Bosne i Hercegovine");
        SV.add("R445 Ljesevo, Federacija Bosne i Hercegovine");
        SV.add("Autobuska stanica Visoko, Federacija Bosne i Hercegovine");
        ArrayList<String> GS = new ArrayList<>();
        GS.add("Mašica Munje 8, Goražde, Federacija Bosne i Hercegovine");
        GS.add("Sarajevska, Goražde, Federacija Bosne i Hercegovine");
        GS.add("R448, Jabuka, Republika Srpska");
        GS.add("R448, Deševa, Federacija Bosne i Hercegovine");
        GS.add("Karađorđeva, Pale, Republika Srpska");
        GS.add("Bentbaša, Sarajevo, Federacija Bosne i Hercegovine");
        GS.add("Autobuska stanica Sarajevo, Federacija Bosne i Hercegovine");
        ArrayList<String> SG = new ArrayList<>();
        SG.add("Autobuska stanica Sarajevo, Federacija Bosne i Hercegovine");
        SG.add("Bentbaša, Sarajevo, Federacija Bosne i Hercegovine");
        SG.add("Karađorđeva, Pale, Republika Srpska");
        SG.add("R448, Deševa, Federacija Bosne i Hercegovine");
        SG.add("R448, Jabuka, Republika Srpska");
        SG.add("Sarajevska, Goražde, Federacija Bosne i Hercegovine");
        SG.add("Mašica Munje 8, Goražde, Federacija Bosne i Hercegovine");

        ArrayList<SimpleRoute> routes1 = new ArrayList<>();
        routes1.add(new SimpleRoute("6:00","7:00","Visoko","Sarajevo",VS,weekDays));
        routes1.add(new SimpleRoute("8:00","9:00","Visoko","Sarajevo",VS,workingDays));
        routes1.add(new SimpleRoute("10:00","11:00","Visoko","Sarajevo",VS,weekDays));
        routes1.add(new SimpleRoute("7:00","8:00","Sarajevo","Visoko",SV,weekDays));
        routes1.add(new SimpleRoute("9:00","10:00","Sarajevo","Visoko",SV,workingDays));
        routes1.add(new SimpleRoute("11:00","12:00","Sarajevo","Visoko",SV,weekDays));
        routes1.add(new SimpleRoute("6:00","8:00","Sarajevo","Goražde",SG,weekDays));
        routes1.add(new SimpleRoute("10:00","12:00","Sarajevo","Goražde",SG,workingDays));
        routes1.add(new SimpleRoute("13:00","15:00","Sarajevo","Goražde",SG,workingDays));
        routes1.add(new SimpleRoute("6:00","8:00","Goražde","Sarajevo",GS,weekDays));
        routes1.add(new SimpleRoute("10:00","12:00","Goražde","Sarajevo",GS,weekDays));
        routes1.add(new SimpleRoute("13:00","15:00","Goražde","Sarajevo",GS,workingDays));

        ArrayList<String> SZ = new ArrayList<>();
        SZ.add("Autobuska stanica Sarajevo, Federacija Bosne i Hercegovine");
        SZ.add("Autobuska stanica Visoko, Bosne Srebrene, Visoko, Federacija Bosne i Hercegovine");
        SZ.add("Autobuska stanica Kakanj, Kakanj, Federacija Bosne i Hercegovine");
        SZ.add("Autobuska stanica Zenica, Bulevar Kralja Tvrtka I, Zenica, Federacija Bosne i Hercegovine");
        ArrayList<String> ZS = new ArrayList<>();
        ZS.add("Autobuska stanica Sarajevo, Federacija Bosne i Hercegovine");
        ZS.add("Autobuska stanica Visoko, Bosne Srebrene, Visoko, Federacija Bosne i Hercegovine");
        ZS.add("Autobuska stanica Kakanj, Kakanj, Federacija Bosne i Hercegovine");
        ZS.add("Autobuska stanica Zenica, Bulevar Kralja Tvrtka I, Zenica, Federacija Bosne i Hercegovine");
        ArrayList<String> ST = new ArrayList<>();
        ST.add("Autobuska stanica Sarajevo, Federacija Bosne i Hercegovine");
        ST.add("M18, Semizovac, Federacija Bosne i Hercegovine");
        ST.add("Autobuska stanica Olovo, Branilaca Olova, Olovo, Federacija Bosne i Hercegovine");
        ST.add("M18, Kladanj, Federacija Bosne i Hercegovine");
        ST.add("Autobuska stanica Živinice, Živinice Grad, Federacija Bosne i Hercegovine");
        ST.add("Autobuska stanica Tuzla, Federacija Bosne i Hercegovine");
        ArrayList<String> TS = new ArrayList<>();
        TS.add("Autobuska stanica Tuzla, Federacija Bosne i Hercegovine");
        TS.add("Autobuska stanica Živinice, Živinice Grad, Federacija Bosne i Hercegovine");
        TS.add("M18, Kladanj, Federacija Bosne i Hercegovine");
        TS.add("Autobuska stanica Olovo, Branilaca Olova, Olovo, Federacija Bosne i Hercegovine");
        TS.add("M18, Semizovac, Federacija Bosne i Hercegovine");
        TS.add("Autobuska stanica Sarajevo, Federacija Bosne i Hercegovine");

        routes1.add(new SimpleRoute("6:00","7:00","Sarajevo","Zenica",SZ,weekDays));
        routes1.add(new SimpleRoute("10:00","11:00","Sarajevo","Zenica",SZ,weekDays));
        routes1.add(new SimpleRoute("13:00","14:00","Sarajevo","Zenica",SZ,nonWorkingDays));
        routes1.add(new SimpleRoute("6:00","7:00","Zenica","Sarajevo",ZS,weekDays));
        routes1.add(new SimpleRoute("10:00","11:00","Zenica","Sarajevo",ZS,weekDays));
        routes1.add(new SimpleRoute("13:00","14:00","Zenica","Sarajevo",ZS,nonWorkingDays));
        routes1.add(new SimpleRoute("6:00","9:00","Tuzla","Sarajevo",TS,weekDays));
        routes1.add(new SimpleRoute("10:00","13:00","Tuzla","Sarajevo",TS,weekDays));
        routes1.add(new SimpleRoute("13:00","16:00","Tuzla","Sarajevo",TS,weekDays));
        routes1.add(new SimpleRoute("6:00","9:00","Sarajevo","Tuzla",ST,weekDays));
        routes1.add(new SimpleRoute("10:00","13:00","Sarajevo","Tuzla",ST,weekDays));
        routes1.add(new SimpleRoute("13:00","16:00","Sarajevo","Tuzla",ST,weekDays));
        // Sarajevo 43.858113, 18.412156
        // Visoko 43.987477, 18.177795
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference();
        for (int i=0;i<routes1.size();i++) {
            databaseReference.child(routes1.get(i).startAddress+"-"+routes1.get(i).endAddress).push().setValue(routes1.get(i));
        }
    }
}