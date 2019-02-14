package com.example.luismauricio.weatherappedlio.data.database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "weather")
public class WeatherItem {

    @PrimaryKey
    private int cityId;
    private float temperature;
    private int timestamp;
    private String cityName;
    private String main;
    private String description;
    private String iconUrl;

    public int getCityId() {
        return cityId;
    }

    public float getTemperature() {
        return temperature;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public String getCityName() {
        return cityName;
    }

    public String getMain() {
        return main;
    }

    public String getDescription() {
        return description;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }
}
