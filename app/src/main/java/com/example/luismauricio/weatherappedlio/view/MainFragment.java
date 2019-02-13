package com.example.luismauricio.weatherappedlio.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.luismauricio.weatherappedlio.R;
import com.example.luismauricio.weatherappedlio.WeatherAdapter;
import com.example.luismauricio.weatherappedlio.data.WeatherRepository;
import com.example.luismauricio.weatherappedlio.network.WeatherSource;
import com.example.luismauricio.weatherappedlio.viewmodel.WeatherViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class MainFragment extends Fragment {
    @BindView(R.id.weatherList)
    RecyclerView weatherList;
    private WeatherAdapter weatherAdapter;
    private WeatherViewModel weatherViewModel;
    private Unbinder unbinder;

    public MainFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        unbinder = ButterKnife.bind(this, view);
        weatherList.setLayoutManager(new LinearLayoutManager(getActivity()));
        weatherList.setHasFixedSize(true);
        weatherViewModel = new WeatherViewModel(new WeatherRepository(new WeatherSource()));
        weatherAdapter = new WeatherAdapter();
        weatherList.setAdapter(weatherAdapter);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        weatherViewModel.getWeather("London,uk").observe(this, weatherResponse -> {
            weatherAdapter.addWeather(weatherResponse);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
