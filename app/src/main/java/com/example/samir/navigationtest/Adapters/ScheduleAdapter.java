package com.example.samir.navigationtest.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.samir.navigationtest.Modules.Route;
import com.example.samir.navigationtest.Modules.SimpleRoute;
import com.example.samir.navigationtest.R;
import com.example.samir.navigationtest.ViewHolders.ScheduleViewHolder;
import java.util.List;

/**
 * Created by Shogun on 17.11.2016..
 */

// Adapter that lists schedule info
public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleViewHolder> {

    private List<SimpleRoute> scheduleList;
    private Context mContext;
    public SimpleRoute si;

    public ScheduleAdapter(List<SimpleRoute> scheduleList, Context context) {
        this.scheduleList = scheduleList;
        this.mContext=context;
    }

    @Override
    public int getItemCount() {
        return scheduleList.size();
    }

    @Override
    public void onBindViewHolder(ScheduleViewHolder scheduleViewHolder, int i) {
        si = scheduleList.get(i);
        scheduleViewHolder.bindSchedule(si ,scheduleViewHolder, mContext);
    }

    @Override
    public ScheduleViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.fragment_schedule_card, viewGroup, false);
        return new ScheduleViewHolder(itemView);
    }

}
