package com.chiibi.greenery.task;

import android.os.AsyncTask;

import com.chiibi.greenery.database.GreeneryDB;
import com.chiibi.greenery.database.entity.PotCard;


public class AddPotCardTask extends AsyncTask<PotCard, Void, Void> {

    private GreeneryDB greeneryDB;
    private OnAddSuccessListener onAddSuccessListener;

    public interface OnAddSuccessListener {
        void onAddSuccess();
    }

    public AddPotCardTask(GreeneryDB db, OnAddSuccessListener listener) {
        this.greeneryDB = db;
        this.onAddSuccessListener = listener;
    }

    @Override
    protected Void doInBackground(PotCard... potCards) {
        for(PotCard potCard : potCards){
            greeneryDB.potDao().addPot(potCard);
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        onAddSuccessListener.onAddSuccess();
    }
}
