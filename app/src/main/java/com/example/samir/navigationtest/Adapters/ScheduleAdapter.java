package com.example.samir.navigationtest.Adapters;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;
import android.widget.Toast;


import com.example.samir.navigationtest.Fragments.ScheduleFragment;
import com.example.samir.navigationtest.MainActivity;
import com.example.samir.navigationtest.Modules.Route;
import com.example.samir.navigationtest.R;
import com.example.samir.navigationtest.ViewHolders.ScheduleViewHolder;

import java.util.ArrayList;
import java.util.List;

import static com.example.samir.navigationtest.R.id.container;

/**
 * Created by Shogun on 17.11.2016..
 */
// Adapter that lists schedule info
public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleViewHolder> {

    private List<Route> scheduleList;
    private Context mContext;

    public ScheduleAdapter(List<Route> scheduleList,Context context) {

        this.scheduleList = scheduleList;
        this.mContext=context;
    }

    @Override
    public int getItemCount() {

        return scheduleList.size();
    }

    @Override
    public void onBindViewHolder(final ScheduleViewHolder scheduleViewHolder, int i) {
        final Route si = scheduleList.get(i);
        scheduleViewHolder.bindSchedule(si);
        scheduleViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)mContext).setAdapterToMap();
            }
        });
    }

    @Override
    public ScheduleViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.fragment_schedule_card, viewGroup, false);
        return new ScheduleViewHolder(itemView);
    }
}
