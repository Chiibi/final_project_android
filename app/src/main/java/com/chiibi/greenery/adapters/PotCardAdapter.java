package com.chiibi.greenery.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chiibi.greenery.R;
import com.chiibi.greenery.adapters.holder.PotCardViewHolder;
import com.chiibi.greenery.database.entity.PotCard;

import java.util.ArrayList;
import java.util.List;

public class PotCardAdapter extends RecyclerView.Adapter<PotCardViewHolder> {

    private Context context;
    private List<PotCard> potCardList;

    public PotCardAdapter(Context context) {
        this.context = context;
        this.potCardList = new ArrayList<>();
    }

    @Override
    public PotCardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_pot, parent, false);
        return new PotCardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PotCardViewHolder holder, int position) {
        setupPotCard(holder, position);
    }

    @Override
    public int getItemCount() {
        return potCardList.size();
    }

    private void setupPotCard(PotCardViewHolder holder, int position) {
        PotCard potCard = potCardList.get(position);

    }

    public void setPotCardList(List<PotCard> potCardList) {
        this.potCardList = potCardList;
    }

    public List<PotCard> getPotCardList() {
        return potCardList;
    }
}
