package com.example.samir.navigationtest.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.samir.navigationtest.ViewHolders.AdViewHolder;
import com.example.samir.navigationtest.Modules.Ad;
import com.example.samir.navigationtest.R;
import java.util.ArrayList;


public class AdAdapter extends RecyclerView.Adapter<AdViewHolder> {

    ArrayList<Ad> mAds;

    public AdAdapter(ArrayList<Ad> mAds) {
        this.mAds = mAds;
    }

    @Override
    public AdViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(parent.getContext()).inflate(R.layout.ad_item, parent, false);
        return new AdViewHolder(inflatedView);
    }

    @Override
    public void onBindViewHolder(AdViewHolder holder, int position) {
        Ad ad = mAds.get(position);
        holder.bindAd(ad);
    }

    @Override
    public int getItemCount() {
        return mAds.size();
    }
}
