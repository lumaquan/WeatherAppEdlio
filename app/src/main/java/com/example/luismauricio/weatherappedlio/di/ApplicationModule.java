package com.example.luismauricio.weatherappedlio.di;

import android.app.Application;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    private Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides
    @WeatherApplicationScope
    Application providesApplication() {
        return application;
    }
}
