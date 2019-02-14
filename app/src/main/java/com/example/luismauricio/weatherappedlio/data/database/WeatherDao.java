package com.example.luismauricio.weatherappedlio.data.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface WeatherDao {

    @Query("SELECT * FROM weather")
    LiveData<List<WeatherItem>> getAllWeathers();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertWeather(WeatherItem weatherItem);

    @Query("SELECT cityId FROM weather")
    Integer getAllIds();
}
