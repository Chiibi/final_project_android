package com.chiibi.greenery.adapters.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chiibi.greenery.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class PotCardViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.img_pot) ImageView imgPot;
    @BindView(R.id.tv_pot_name) TextView tvPotName;
    @BindView(R.id.tv_last_activity) TextView tvLastActivity;

    public PotCardViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(itemView);
    }
}
