package com.example.luismauricio.weatherappedlio.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.example.luismauricio.weatherappedlio.data.WeatherRepository;
import com.example.luismauricio.weatherappedlio.data.database.WeatherItem;

import java.util.List;

import javax.inject.Inject;

public class WeatherViewModel extends ViewModel {

    private final WeatherRepository repository;

    @Inject
    public WeatherViewModel(WeatherRepository repository) {
        this.repository = repository;
    }

    public void fetchCity(String city) {
        repository.fetchWeather(city);
    }

    public LiveData<List<WeatherItem>> getAllWeathers() {
        return repository.getAllWeathers();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
