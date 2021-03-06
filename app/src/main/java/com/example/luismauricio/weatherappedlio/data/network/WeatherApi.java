package com.example.luismauricio.weatherappedlio.data.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApi {

    String BASE_URL = "http://api.openweathermap.org/data/2.5/";
    String API_KEY = "56529b2d2e5475be0800bfcd97f3e1f0";
    String API_QUERY_PARAM = "APPID";

    @GET("weather")
    Call<WeatherResponse> getWeather(@Query("q") String city);

    @GET("group")
    Call<AllCitiesResponse> getAllWeathers(@Query("id") Integer... ids);

}
