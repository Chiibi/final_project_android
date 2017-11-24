package com.chiibi.greenery.database.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.net.Uri;

@Entity(tableName = "POT_DETAIL")
public class PotEntity {

    @PrimaryKey(autoGenerate = true)
    private int pid;
    private int uid;
    private String potName;
    private String potNote;
    private Uri potUri;

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getPotName() {
        return potName;
    }

    public void setPotName(String potName) {
        this.potName = potName;
    }

    public String getPotNote() {
        return potNote;
    }

    public void setPotNote(String potNote) {
        this.potNote = potNote;
    }

    public Uri getPotUri() {
        return potUri;
    }

    public void setPotUri(Uri potUri) {
        this.potUri = potUri;
    }
}
