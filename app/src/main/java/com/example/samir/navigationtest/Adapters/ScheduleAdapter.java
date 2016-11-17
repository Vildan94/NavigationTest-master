package com.example.samir.navigationtest.Adapters;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.samir.navigationtest.Modules.ScheduleInfo;
import com.example.samir.navigationtest.R;
import com.example.samir.navigationtest.ViewHolders.ScheduleViewHolder;

import java.util.List;
/**
 * Created by Shogun on 17.11.2016..
 */

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleViewHolder> {

    private List<ScheduleInfo> scheduleList;

    public ScheduleAdapter(List<ScheduleInfo> scheduleList) {
        this.scheduleList = scheduleList;
    }
        @Override
        public int getItemCount() {
            return scheduleList.size();
        }

        @Override
        public void onBindViewHolder(ScheduleViewHolder scheduleViewHolder, int i) {
            ScheduleInfo si = scheduleList.get(i);
            scheduleViewHolder.bindSchedule(si);
        }

        @Override
        public ScheduleViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
                View itemView = LayoutInflater.
                    from(viewGroup.getContext()).
                    inflate(R.layout.fragment_schedule_card, viewGroup, false);

            return new ScheduleViewHolder(itemView);
        }

}
