package com.chiibi.greenery.database.dataAccessObj;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.chiibi.greenery.database.entity.PotCard;

import java.util.List;

@Dao
public interface PotDao {

    @Insert()
    void addPot(PotCard potCard);

    @Update
    void update(PotCard potCard);

    @Query("select * from POT_DETAIL where uid = :uid")
    List<PotCard> loadPotByUID(String uid);

    @Delete
    void deletePot(PotCard PotCard);
}
