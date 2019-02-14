package com.example.luismauricio.weatherappedlio.di;


import com.example.luismauricio.weatherappedlio.view.MainFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@WeatherApplicationScope
@Component(modules = {ApplicationModule.class, NetworkModule.class, DatabaseModule.class})
public interface WeatherComponent {

    void inject(MainFragment fragment);

}
