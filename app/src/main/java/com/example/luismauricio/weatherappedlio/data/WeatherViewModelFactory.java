package com.example.luismauricio.weatherappedlio.data;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.example.luismauricio.weatherappedlio.viewmodel.WeatherViewModel;

import javax.inject.Inject;

public class WeatherViewModelFactory implements ViewModelProvider.Factory {

    private WeatherRepository repository;

    @Inject
    public WeatherViewModelFactory(WeatherRepository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new WeatherViewModel(repository);
    }
}
