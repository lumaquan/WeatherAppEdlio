package com.example.luismauricio.weatherappedlio.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.luismauricio.weatherappedlio.R;
import com.example.luismauricio.weatherappedlio.data.database.WeatherItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.WeatherHolder> {

    private List<WeatherItem> weatherList = new ArrayList<>();
    private Context context;

    public WeatherAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public WeatherHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.weather_row, viewGroup, false);
        return new WeatherHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherHolder weatherHolder, int i) {
        WeatherItem weatherItem = weatherList.get(i);
        weatherHolder.cityNameTextView.setText(weatherItem.getCityName());
        weatherHolder.descriptionTextView.setText(weatherItem.getDescription());
        weatherHolder.temperatureTextView.setText(formatTemperature(weatherItem.getTemperature()));

        Glide.with(context)
                .load(weatherItem.getIconUrl())
                .into(weatherHolder.iconImageView);
    }

    private String formatTemperature(float temperature) {
        return String.format(Locale.getDefault(), "%.1f \u2070C", temperature);
    }

    @Override
    public int getItemCount() {
        if (weatherList != null) {
            return weatherList.size();
        }
        return 0;
    }

    public void setAllWeathers(List<WeatherItem> items) {
        weatherList = items;
        notifyDataSetChanged();
    }

    class WeatherHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.city_name)
        TextView cityNameTextView;
        @BindView(R.id.temperature)
        TextView temperatureTextView;
        @BindView(R.id.description)
        TextView descriptionTextView;
        @BindView(R.id.icon)
        ImageView iconImageView;

        WeatherHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public boolean addWeather(WeatherItem weatherResponse) {
        boolean added = weatherList.add(weatherResponse);
        notifyDataSetChanged();
        return added;
    }

}
