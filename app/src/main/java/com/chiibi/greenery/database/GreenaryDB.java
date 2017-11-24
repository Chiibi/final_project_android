package com.chiibi.greenery.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.chiibi.greenery.database.dataAccessObj.PotDao;
import com.chiibi.greenery.database.entity.PotEntity;

@Database(entities = {PotEntity.class}, version = 1)
abstract class GreeneryDB extends RoomDatabase {

    private static GreeneryDB INSTANCE;

    public abstract PotDao potModel();
}
