package com.chiibi.greenery.database.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

@Entity(tableName = "POT_DETAIL")
public class PotCard implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private int pid;
    private String uid;

    private Uri imgUri;
    private String potName;
    private String note;

    public PotCard() {
    }

    public PotCard(String uid, Uri imgUri, String potName, String note) {
        this.uid = uid;
        this.imgUri = imgUri;
        this.potName = potName;
        this.note = note;
    }

    public int getPid() {
        return pid;
    }

    public Uri getImgUri() {
        return imgUri;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setImgUri(Uri imgUri) {
        this.imgUri = imgUri;
    }

    public String getPotName() {
        return potName;
    }

    public void setPotName(String potName) {
        this.potName = potName;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void updateInfo(PotCard potCard) {
        this.imgUri  = potCard.getImgUri();
        this.potName = potCard.getPotName();
        this.note    = potCard.getNote();
        this.uid     = potCard.getUid();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.pid);
        dest.writeString(this.uid);
        dest.writeParcelable(this.imgUri, flags);
        dest.writeString(this.potName);
        dest.writeString(this.note);
    }

    protected PotCard(Parcel in) {
        this.pid = in.readInt();
        this.uid = in.readString();
        this.imgUri = in.readParcelable(Uri.class.getClassLoader());
        this.potName = in.readString();
        this.note = in.readString();
    }

    public static final Creator<PotCard> CREATOR = new Creator<PotCard>() {
        @Override
        public PotCard createFromParcel(Parcel source) {
            return new PotCard(source);
        }

        @Override
        public PotCard[] newArray(int size) {
            return new PotCard[size];
        }
    };
}
