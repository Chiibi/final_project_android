package com.chiibi.greenery.task;

import android.os.AsyncTask;

import com.chiibi.greenery.database.GreeneryDB;
import com.chiibi.greenery.database.entity.PotCard;


public class DeletePotTask extends AsyncTask<PotCard, Void, Void> {

    private GreeneryDB greeneryDB;
    private OnDeleteSuccessListener onDeleteSuccessListener;

    public interface OnDeleteSuccessListener {
        void onDeleteSuccess();
    }

    public DeletePotTask(GreeneryDB greeneryDB, OnDeleteSuccessListener listener) {
        this.greeneryDB = greeneryDB;
        this.onDeleteSuccessListener = listener;
    }

    @Override
    protected Void doInBackground(PotCard... potCards) {
        for(PotCard potCard : potCards){
            greeneryDB.potDao().deletePot(potCard);
        }
        return null;
    }
}
