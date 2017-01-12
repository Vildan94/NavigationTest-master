package com.example.samir.navigationtest.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.samir.navigationtest.Adapters.AdAdapter;
import com.example.samir.navigationtest.Modules.Ad;
import com.example.samir.navigationtest.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

// Fragment used to show Advertisment
public class AdvertismentFragment extends Fragment {

    private static AdvertismentFragment instance = null;
    ArrayList<Ad> ads=new ArrayList<Ad>();
    StaggeredGridLayoutManager mStaggeredLayoutManager;
    RecyclerView mRecyclerView;
    AdAdapter mAdAdapter;

    public AdvertismentFragment() {
    }

    public static AdvertismentFragment getInstance() {
        if(instance == null) {
            instance = new AdvertismentFragment();
        }
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_ads, container, false);
        //initializePlaces();
      // ArrayList<Ad> ads;

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference();
        databaseReference.child("Advertisement").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                for (DataSnapshot child : children) {
                    Ad r = child.getValue(Ad.class);
                    boolean cond = true;
                    for (Ad i:ads) {
                        if(i == r){
                            cond = false;
                        }
                    }
                    if(cond){
                        ads.add(r);
                    }
                    else
                        cond = true;
                    mAdAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mAdAdapter = new AdAdapter(ads);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        mStaggeredLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mStaggeredLayoutManager);

        mRecyclerView.setAdapter(mAdAdapter);

        return rootView;
    }

    public void initializePlaces() {
        ArrayList<Ad> places = new ArrayList<>();
        // Creating ads in database should be considered
        places.add(new Ad("http://www.akta.ba/resources/Article/SmallImages/885b461e-5456-414f-9ae9-dd133bc0a3datoskana_mala.jpg", "Biss-Tours"));
        places.add(new Ad("http://www.centrotrans.com/uimages/drugislider/18102016_news_home.jpg", "Centrotrans"));
        places.add(new Ad("http://www.centrotrans.com/uimages/odrzavanje/zimskiredvoznje_ab1617_big.jpg", "Centrotrans"));
        places.add(new Ad("http://072info.com/wp-content/uploads/2014/04/BRATISLAVA-BEC-BUDIMPESTA-BISS-TOURS.jpg", "Biss-Tours"));
        places.add(new Ad("http://www.fksarajevo.ba/assets/uploads/articles/centrotrans-eurolines-se-ukljucuje-u-lbc-projekat-fk-sarajevo.jpg", "Centrotrans"));
        places.add(new Ad("http://www.akta.ba/resources/Article/SmallImages/91c97092-9326-4f48-8d65-6f53fbe27f34Spanija_Bisstours1.jpg", "Biss-Tours"));
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference();
        for (int i=0;i<places.size();i++) {
            databaseReference.child("Advertisements").push().setValue(places.get(i));
        }


    }

}