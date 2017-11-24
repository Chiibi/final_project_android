package com.chiibi.greenery.utility;

import android.arch.persistence.room.TypeConverter;
import android.net.Uri;

public class UriConverter {
    @TypeConverter
    public static String toString(Uri uri){
        return uri == null ? null : uri.toString();
    }

    @TypeConverter
    public static Uri toUri(String string){
        return string == null ? null : Uri.parse(string);
    }
}
