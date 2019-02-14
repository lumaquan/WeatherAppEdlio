package com.example.luismauricio.weatherappedlio.data;

import com.example.luismauricio.weatherappedlio.data.database.WeatherItem;
import com.example.luismauricio.weatherappedlio.data.network.WeatherResponse;

public class WeatherMapper implements Mapper<WeatherResponse, WeatherItem> {

    @Override
    public WeatherItem map(WeatherResponse weatherResponse) {
        WeatherItem weatherItem = new WeatherItem();
        weatherItem.setCityId(weatherResponse.id);
        weatherItem.setCityName(weatherResponse.name);
        weatherItem.setTemperature(kelvinToCelsius(weatherResponse.main.temp));
        weatherItem.setMain(weatherResponse.weather.get(0).main);
        weatherItem.setTimestamp(weatherResponse.dt);
        weatherItem.setIconUrl("http://openweathermap.org/img/w/" + weatherResponse.weather.get(0).icon + ".png");
        weatherItem.setDescription(weatherResponse.weather.get(0).description);
        return weatherItem;
    }

    private float kelvinToCelsius(float kelvin) {
        return kelvin - 273.16f;
    }
}
