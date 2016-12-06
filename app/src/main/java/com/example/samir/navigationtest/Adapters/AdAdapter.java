package com.example.samir.navigationtest.Adapters;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.samir.navigationtest.AdDetails;
import com.example.samir.navigationtest.ViewHolders.AdViewHolder;
import com.example.samir.navigationtest.Modules.Ad;
import com.example.samir.navigationtest.R;
import java.util.ArrayList;

// Adapter that lists ads
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
        final Ad ad = mAds.get(position);
        holder.bindAd(ad);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(view.getContext(), "Hello", Toast.LENGTH_SHORT).show();
                //Open New Activity show details about advertisment
                Intent intent = new Intent(view.getContext(),AdDetails.class);
                intent.putExtra("ad",ad);
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mAds.size();
    }
}
