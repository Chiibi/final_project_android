package com.chiibi.greenery.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import com.chiibi.greenery.database.dataAccessObj.PotDao;
import com.chiibi.greenery.database.entity.PotCard;
import com.chiibi.greenery.utility.UriConverter;

@Database(entities = {PotCard.class}, version = 1, exportSchema = false)
@TypeConverters(UriConverter.class)
public abstract class GreeneryDB extends RoomDatabase {
    private static GreeneryDB INSTANCE;

    public abstract PotDao potDao();
}
