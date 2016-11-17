package com.example.samir.navigationtest.ViewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.samir.navigationtest.Modules.ScheduleInfo;
import com.example.samir.navigationtest.R;

/**
 * Created by Shogun on 17.11.2016..
 */

public class ScheduleViewHolder extends RecyclerView.ViewHolder {

    private TextView mArrTime;
    private ScheduleInfo scheduleInfo;

    public ScheduleViewHolder(View v) {
        super(v);
        mArrTime = (TextView) v.findViewById(R.id.arrTime);
    }

    public void bindSchedule(ScheduleInfo si) {
        scheduleInfo = si;
        mArrTime.setText(si.arrival_time + "-" + si.departure_time);

    }
}
