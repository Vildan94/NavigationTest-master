package com.example.samir.navigationtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.samir.navigationtest.Modules.Ad;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdDetails extends AppCompatActivity {


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_details);
        // TO DO
        //Ad ad = (Ad) savedInstanceState.getSerializable("ad");
        ImageView imageView = (ImageView) findViewById(R.id.imageAdDetails);
        TextView title = (TextView) findViewById(R.id.textAdDetails);
        TextView desc = (TextView) findViewById(R.id.descriptionAdDetails);
        final TextView votes = (TextView) findViewById(R.id.votes);
        RatingBar ratingBar = (RatingBar) findViewById(R.id.ratingBar);

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            //TextView votes = (TextView) findViewById(R.id.votes);
            final Ad ad = (Ad) getIntent().getSerializableExtra("ad");
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference databaseReference = database.getReference();

               // Ad ad = (Ad) getIntent().getSerializableExtra("ad");
                float sum = Float.parseFloat(ad.AdSumVotes)+rating;
                int votes1= Integer.parseInt(ad.AdVotes)+1;
                float rating1= sum/votes1;
                ad.AdSumVotes=""+sum;
                ad.AdVotes=""+votes1;
                ad.AdRating=""+rating1;
                ratingBar.setRating(Float.parseFloat(ad.AdRating));
                votes.setText(ad.AdVotes+"Votes");
                databaseReference.child("Advertisements").child(ad.AdId).setValue(ad);
            }
        });

        if (getIntent().hasExtra("ad")) {
            final Ad ad = (Ad) getIntent().getSerializableExtra("ad");

            votes.setText(ad.AdVotes+" Votes");
            ratingBar.setRating(Float.parseFloat(ad.AdRating));
            title.setText(ad.AdName);
            desc.setText(ad.AdDescription);
            Glide.with(imageView.getContext()).load(ad.AdImage).into(imageView);
                    }

    }


}
