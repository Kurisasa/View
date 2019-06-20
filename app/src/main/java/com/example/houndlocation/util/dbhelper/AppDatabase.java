package com.example.houndlocation.util.dbhelper;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.houndlocation.util.model.Location;
import com.example.houndlocation.util.util.Converters;


@Database(entities = {Location.class}, version = 2,exportSchema = false)
@TypeConverters(Converters.class)
public abstract class AppDatabase extends RoomDatabase {

    public abstract LocationDao locationDao();

    private static volatile AppDatabase INSTANCE;
}
