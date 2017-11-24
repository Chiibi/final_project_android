package com.chiibi.greenery.task;

import android.os.AsyncTask;

import com.chiibi.greenery.database.GreeneryDB;
import com.chiibi.greenery.database.entity.PotCard;

public class UpdatePotCardTask extends AsyncTask<PotCard, Void, Void> {

    private GreeneryDB greeneryDB;
    private OnUpdateSuccessListener onUpdateSuccessListener;

    public interface OnUpdateSuccessListener {
        void onUpdateSuccess();
    }

    public UpdatePotCardTask(GreeneryDB db, OnUpdateSuccessListener listener) {
        this.greeneryDB = db;
        this.onUpdateSuccessListener = listener;
    }

    @Override
    protected Void doInBackground(PotCard... potCards) {
        for(PotCard potCard : potCards){
            greeneryDB.potDao().update(potCard);
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        onUpdateSuccessListener.onUpdateSuccess();
    }


}
