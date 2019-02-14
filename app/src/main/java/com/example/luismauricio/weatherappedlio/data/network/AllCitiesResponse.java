package com.example.luismauricio.weatherappedlio.data.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AllCitiesResponse {

        @SerializedName("cnt")
        @Expose
        public int cnt;
        @SerializedName("list")
        @Expose
        public java.util.List<WeatherResponse> list = null;

}

