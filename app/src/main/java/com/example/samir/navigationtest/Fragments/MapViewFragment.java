package com.example.samir.navigationtest.Fragments;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.samir.navigationtest.Fragments.PlaceholderFragment;
import com.example.samir.navigationtest.Modules.DirectionFinder;
import com.example.samir.navigationtest.Modules.DirectionFinderListener;
import com.example.samir.navigationtest.Modules.Route;
import com.example.samir.navigationtest.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;


public class MapViewFragment extends PlaceholderFragment implements DirectionFinderListener {

    MapView mMapView;
    private GoogleMap googleMap;
    private SearchView findPath;
    private Spinner location;
    private Spinner destination;
    private List<Marker> locationMarkers = new ArrayList<>();
    private List<Marker> destinationMarkers = new ArrayList<>();
    private List<Polyline> polylinePaths = new ArrayList<>();
    private ProgressDialog progressDialog;
    private String loc;
    private String dest;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_maps, container, false);

        mMapView = (MapView) rootView.findViewById(R.id.mapView);
        findPath = (SearchView) rootView.findViewById(R.id.Search);
        location = (Spinner) rootView.findViewById(R.id.location);
        destination = (Spinner) rootView.findViewById(R.id.destination);

        // Testing list, should be getting this from the database
        ArrayList<String> a = new ArrayList();
        a.add("Sarajevo");
        a.add("Ilidza");
        a.add("Visoko");


        mMapView.onCreate(savedInstanceState);

        mMapView.onResume(); // needed to get the map to display immediately

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Set 1 spiner
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, a);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        location.setAdapter(adapter);
        location.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //

        // Set 2 spiner
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, a);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        destination.setAdapter(adapter2);
        destination.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //

        // Send request to get path
        findPath.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendRequest();
            }
        });
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.

        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                googleMap = mMap;
                googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                float zoom = 15;

                // Add a marker and move the camera
                LatLng point1 = new LatLng(43.8221298,18.3062157);
                googleMap.moveCamera(CameraUpdateFactory.newLatLng(point1));
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(point1,zoom));
            }
        });

        return rootView;
    }

    private void sendRequest() {
        TextView temp = (TextView) location.getSelectedView();
        TextView tempo = (TextView) destination.getSelectedView();
        loc = temp.getText().toString();
        dest = tempo.getText().toString();

        try {
            new DirectionFinder(this, loc, dest).execute();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDirectionFinderStart() {
        progressDialog = ProgressDialog.show(getContext(), "Please wait.",
                "Finding direction..!", true);

        if (locationMarkers != null) {
            for (Marker marker : locationMarkers) {
                marker.remove();
            }
        }

        if (destinationMarkers != null) {
            for (Marker marker : destinationMarkers) {
                marker.remove();
            }
        }

        if (polylinePaths != null) {
            for (Polyline polyline:polylinePaths ) {
                polyline.remove();
            }
        }
    }

    @Override
    public void onDirectionFinderSuccess(List<Route> routes) {
        progressDialog.dismiss();
        polylinePaths = new ArrayList<>();
        locationMarkers = new ArrayList<>();
        destinationMarkers = new ArrayList<>();

        for (Route route : routes) {
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(route.startLocation, 16));

            PolylineOptions polylineOptions = new PolylineOptions().
                    geodesic(true).
                    color(Color.BLUE).
                    width(10);

            for (int i = 0; i < route.points.size(); i++)
                polylineOptions.add(route.points.get(i));

            polylinePaths.add(googleMap.addPolyline(polylineOptions));
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }
}