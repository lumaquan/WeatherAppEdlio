package com.example.luismauricio.weatherappedlio.network;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.luismauricio.weatherappedlio.model.WeatherResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherSource {

    private static final String TAG = "3.1416";
    private MutableLiveData<WeatherResponse> weatherApiLiveData = new MutableLiveData<>();

    public void fetchWeather(String city) {
        WeatherClient.getWeatherService().getWeather(city).enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(@NonNull Call<WeatherResponse> call, @NonNull Response<WeatherResponse> response) {
                if (response.isSuccessful()) {
                    weatherApiLiveData.postValue(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<WeatherResponse> call, @NonNull Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    public LiveData<WeatherResponse> getWeather() {
        return weatherApiLiveData;
    }


}

