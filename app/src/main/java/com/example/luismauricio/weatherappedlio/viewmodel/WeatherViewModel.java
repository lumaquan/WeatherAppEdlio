package com.example.luismauricio.weatherappedlio.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.example.luismauricio.weatherappedlio.data.WeatherRepository;
import com.example.luismauricio.weatherappedlio.model.WeatherResponse;

public class WeatherViewModel extends ViewModel {

    private final WeatherRepository repository;

    public WeatherViewModel(WeatherRepository repository) {
        this.repository = repository;
    }

    public LiveData<WeatherResponse> getWeather(String city) {
        return repository.getWeather(city);
    }

}
