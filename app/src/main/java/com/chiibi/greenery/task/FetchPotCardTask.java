package com.chiibi.greenery.task;

import android.os.AsyncTask;

import com.chiibi.greenery.database.GreeneryDB;
import com.chiibi.greenery.database.entity.PotCard;
import com.chiibi.greenery.model.UserProfile;

import java.util.List;

public class FetchPotCardTask extends AsyncTask<Void, Void, List<PotCard>> {

    private String uid;
    private GreeneryDB greeneryDB;
    private OnFetchSuccessListener onFetchSuccessListener;

    public interface OnFetchSuccessListener{
        void onFetchSuccess(List<PotCard> potCardList);
    }

    public FetchPotCardTask(String uid, GreeneryDB greeneryDB, OnFetchSuccessListener onFetchSuccessListener) {
        this.uid = uid;
        this.greeneryDB = greeneryDB;
        this.onFetchSuccessListener = onFetchSuccessListener;
    }

    @Override
    protected List<PotCard> doInBackground(Void... voids) {
        return greeneryDB.potDao().loadPotByUID(uid);
    }

    @Override
    protected void onPostExecute(List<PotCard> potCardList) {
        super.onPostExecute(potCardList);
        onFetchSuccessListener.onFetchSuccess(potCardList);
    }
}
