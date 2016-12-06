package com.example.samir.navigationtest.Fragments;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.samir.navigationtest.Adapters.AdAdapter;
import com.example.samir.navigationtest.Modules.Ad;
import com.example.samir.navigationtest.R;
import java.util.ArrayList;

// Fragment used to show Advertisment
public class AdvertismentFragment extends PlaceholderFragment {

    ArrayList<Ad> ads;
    StaggeredGridLayoutManager mStaggeredLayoutManager;
    RecyclerView mRecyclerView;
    AdAdapter mAdAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_ads, container, false);
        ArrayList<Ad> ads = getPlaces();
        mAdAdapter = new AdAdapter(ads);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        mStaggeredLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mStaggeredLayoutManager);

        mRecyclerView.setAdapter(mAdAdapter);

        return rootView;
    }

    public ArrayList<Ad> getPlaces() {
        ArrayList<Ad> places = new ArrayList<>();
        // Creating ads in database should be considered
        places.add(new Ad("http://www.akta.ba/resources/Article/SmallImages/885b461e-5456-414f-9ae9-dd133bc0a3datoskana_mala.jpg", "Biss-Tours"));
        places.add(new Ad("http://www.centrotrans.com/uimages/drugislider/18102016_news_home.jpg", "Centrotrans"));
        places.add(new Ad("http://www.centrotrans.com/uimages/odrzavanje/zimskiredvoznje_ab1617_big.jpg", "Centrotrans"));
        places.add(new Ad("http://072info.com/wp-content/uploads/2014/04/BRATISLAVA-BEC-BUDIMPESTA-BISS-TOURS.jpg", "Biss-Tours"));
        places.add(new Ad("http://www.fksarajevo.ba/assets/uploads/articles/centrotrans-eurolines-se-ukljucuje-u-lbc-projekat-fk-sarajevo.jpg", "Centrotrans"));
        places.add(new Ad("http://www.akta.ba/resources/Article/SmallImages/91c97092-9326-4f48-8d65-6f53fbe27f34Spanija_Bisstours1.jpg", "Biss-Tours"));
        return places;
    }

}