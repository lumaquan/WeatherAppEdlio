package com.example.luismauricio.weatherappedlio.data;

import android.arch.lifecycle.LiveData;

import com.example.luismauricio.weatherappedlio.data.database.WeatherDatabase;
import com.example.luismauricio.weatherappedlio.data.database.WeatherItem;
import com.example.luismauricio.weatherappedlio.data.network.AllCitiesResponse;
import com.example.luismauricio.weatherappedlio.data.network.WeatherApi;
import com.example.luismauricio.weatherappedlio.data.network.WeatherResponse;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Response;

public class WeatherRepository {

    private final WeatherDatabase weatherDatabase;
    private final WeatherApi weatherApi;

    @Inject
    public WeatherRepository(WeatherApi weatherApi, WeatherDatabase database) {
        this.weatherApi = weatherApi;
        this.weatherDatabase = database;
    }

    public LiveData<List<WeatherItem>> getAllWeathers() {
        return weatherDatabase.getWeatherDao().getAllWeathers();
    }

    public void fetchWeather(String city) {
        WeatherMapper weatherMapper = new WeatherMapper();
        new Thread(() -> {
            try {
                Response<WeatherResponse> weatherResponseResponse = weatherApi.getWeather(city).execute();
                if (weatherResponseResponse.body() != null)
                    weatherDatabase.getWeatherDao().insertWeather(weatherMapper.map(weatherResponseResponse.body()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void refreshAllWeathers() {
        WeatherMapper weatherMapper = new WeatherMapper();
        new Thread(() -> {
            try {
                Response<AllCitiesResponse> weatherResponseResponse = weatherApi.getAllWeathers(weatherDatabase.getWeatherDao().getAllIds()).execute();
                if (weatherResponseResponse.body() != null) {
                    for (WeatherResponse weatherResponse : weatherResponseResponse.body().list) {
                        weatherDatabase.getWeatherDao().insertWeather(weatherMapper.map(weatherResponse));
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
