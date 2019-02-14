package com.example.luismauricio.weatherappedlio.di;

import android.app.Activity;
import android.app.Application;


public class WeatherAppliccation extends Application {
    private WeatherComponent component;

    public static WeatherAppliccation get(Activity activity) {
        return (WeatherAppliccation) activity.getApplication();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerWeatherComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public WeatherComponent getComponent() {
        return component;
    }
}
