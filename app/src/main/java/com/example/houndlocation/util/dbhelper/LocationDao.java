package com.example.houndlocation.util.dbhelper;

import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;
import com.example.houndlocation.util.model.Location;
import java.util.List;
import io.reactivex.Single;

@Dao
public interface LocationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Location location);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<Location> locations);

    @Query("DELETE FROM location")
    void deleteAll();

    @Query("SELECT * FROM location ORDER BY name ASC")
    DataSource.Factory<Integer, Location> getAllLocations();

    @Transaction
    @Query("SELECT * FROM location WHERE uid=:locationID")
    Single<Location> getLocationById(int locationID);

    @Transaction
    @Update
    Single<Integer> updateLocation(Location location);
}
