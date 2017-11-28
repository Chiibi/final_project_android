package com.chiibi.greenery.adapters.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chiibi.greenery.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class PotCardViewHolder extends RecyclerView.ViewHolder {

    public ImageView imgPot;
    public TextView tvPotName;
    public TextView tvLastActivity;
    public TextView tvTask;
    public TextView tvPeriod;

    public PotCardViewHolder(View itemView) {
        super(itemView);
        imgPot = itemView.findViewById(R.id.img_pot);
        tvPotName = itemView.findViewById(R.id.tv_pot_name);
        tvLastActivity = itemView.findViewById(R.id.tv_last_activity);
        tvTask = itemView.findViewById(R.id.tv_task);
        tvPeriod = itemView.findViewById(R.id.tv_period);
    }
}
