package com.example.samir.navigationtest.ViewHolders;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.samir.navigationtest.MainActivity;
import com.example.samir.navigationtest.Modules.Route;
import com.example.samir.navigationtest.Modules.SimpleRoute;
import com.example.samir.navigationtest.R;

/**
 * Created by Shogun on 17.11.2016..
 */
// Representation of Schedule information
public class ScheduleViewHolder extends RecyclerView.ViewHolder{

    private TextView mTime;
    private TextView mRoute;

    public ScheduleViewHolder(View v) {
        super(v);

        mTime = (TextView) v.findViewById(R.id.mTime);
        mRoute = (TextView) v.findViewById(R.id.route);
    }

    public void bindSchedule(final SimpleRoute route, ScheduleViewHolder scheduleViewHolder, final Context mContext) {
        mTime.setText(route.depTime + "-" + route.arrTime);
        mRoute.setText(route.startAddress +" to "+ route.endAddress);

        scheduleViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)mContext).setAdapterToMap(route.startAddress+","+route.endAddress,route.allStopovers);
            }
        });
    }

}
