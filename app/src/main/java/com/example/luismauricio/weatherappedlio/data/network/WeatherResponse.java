
package com.example.luismauricio.weatherappedlio.data.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WeatherResponse {

    @SerializedName("weather")
    @Expose
    public List<Weather> weather = null;
    @SerializedName("main")
    @Expose
    public Main main;
    @SerializedName("dt")
    @Expose
    public int dt;
    ;
    @SerializedName("id")
    @Expose
    public int id;
    @SerializedName("name")
    @Expose
    public String name;


    public static class Main {

        @SerializedName("temp")
        @Expose
        public float temp;

    }


    public static class Weather {

        public String main;
        @SerializedName("description")
        @Expose
        public String description;
        @SerializedName("icon")
        @Expose
        public String icon;

    }

}
