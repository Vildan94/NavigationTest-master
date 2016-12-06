package com.example.samir.navigationtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.samir.navigationtest.Modules.Ad;

public class AdDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_details);
        // TO DO
        //Ad ad = (Ad) savedInstanceState.getSerializable("ad");
        ImageView imageView = (ImageView) findViewById(R.id.imageAdDetails);
        TextView textView = (TextView) findViewById(R.id.textAdDetails);
        if (getIntent().hasExtra("ad")) {
            Ad ad = (Ad) getIntent().getSerializableExtra("ad");
            textView.setText(ad.AdName);
            Glide.with(imageView.getContext()).load(ad.AdImage).into(imageView);
        }
    }
}
