package com.chiibi.greenery.database.dataAccessObj;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.chiibi.greenery.database.entity.PotEntity;

import java.util.List;

@Dao
public interface PotDao {

    @Insert()
    void addPot(PotEntity pot);

    @Query("select * from user where uid = :uid")
    List<PotEntity> loadPotByUID(int uid);

    @Delete
    void deletePotByPID(PotEntity potEntity);
}
