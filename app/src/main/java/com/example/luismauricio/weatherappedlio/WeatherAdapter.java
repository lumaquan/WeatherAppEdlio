package com.example.luismauricio.weatherappedlio;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.luismauricio.weatherappedlio.model.WeatherResponse;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.WeatherHolder> {

    private List<WeatherResponse> weatherList = new ArrayList<>();

    @NonNull
    @Override
    public WeatherHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.weather_row, viewGroup, false);
        return new WeatherHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherHolder weatherHolder, int i) {
        weatherHolder.descriptionTextView.setText(weatherList.get(i).weather.get(0).description);
    }

    @Override
    public int getItemCount() {
        if (weatherList != null) {
            return weatherList.size();
        }
        return 0;
    }

    class WeatherHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.description)
        TextView descriptionTextView;

        WeatherHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public boolean addWeather(WeatherResponse weatherResponse) {
        boolean added = weatherList.add(weatherResponse);
        notifyDataSetChanged();
        return added;
    }

}
