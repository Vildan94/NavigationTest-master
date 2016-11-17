package com.example.samir.navigationtest.ViewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.samir.navigationtest.Modules.Ad;
import com.example.samir.navigationtest.R;

/**
 * Created by Samir on 16-Nov-16.
 */
public class AdViewHolder extends RecyclerView.ViewHolder{

    private ImageView mAdImage;
    private TextView mAdName;
    private Ad mAd;

    public AdViewHolder(View itemView) {
        super(itemView);
        mAdImage = (ImageView) itemView.findViewById(R.id.ad_image);
        mAdName = (TextView) itemView.findViewById(R.id.ad_name);
    }

    public void bindAd(Ad ad) {
        mAd = ad;
        mAdName.setText(ad.AdName);
        Glide.with(mAdImage.getContext()).load(ad.AdImage).into(mAdImage);
    }

}