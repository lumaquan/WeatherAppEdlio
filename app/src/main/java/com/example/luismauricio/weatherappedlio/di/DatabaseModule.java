package com.example.luismauricio.weatherappedlio.di;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.example.luismauricio.weatherappedlio.data.database.WeatherDatabase;

import dagger.Module;
import dagger.Provides;

@Module
public class DatabaseModule {

    @Provides
    @WeatherApplicationScope
    WeatherDatabase provideWeatherDatabase(Application application) {
        return Room.databaseBuilder(application, WeatherDatabase.class, "daggerdatabase.db")
                .build();
    }
}
