package com.example.luismauricio.weatherappedlio.data;

import android.arch.lifecycle.LiveData;

import com.example.luismauricio.weatherappedlio.model.WeatherResponse;
import com.example.luismauricio.weatherappedlio.network.WeatherSource;

public class WeatherRepository {

    private final WeatherSource weatherSource;

    public WeatherRepository(WeatherSource weatherSource) {
        this.weatherSource = weatherSource;
    }

    public LiveData<WeatherResponse> getWeather(String city) {
        weatherSource.fetchWeather(city);
        return weatherSource.getWeather();
    }

}
