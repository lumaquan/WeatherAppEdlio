package com.example.luismauricio.weatherappedlio.view;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.example.luismauricio.weatherappedlio.R;
import com.example.luismauricio.weatherappedlio.di.WeatherAppliccation;
import com.example.luismauricio.weatherappedlio.data.WeatherViewModelFactory;
import com.example.luismauricio.weatherappedlio.viewmodel.WeatherViewModel;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class MainFragment extends Fragment {
    @BindView(R.id.weatherList)
    RecyclerView weatherList;
    @BindView(R.id.loading)
    ProgressBar loading;


    private WeatherAdapter weatherAdapter;
    private WeatherViewModel weatherViewModel;
    private Unbinder unbinder;

    @Inject
    WeatherViewModelFactory weatherViewModelFactory;

    public MainFragment() {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        WeatherAppliccation.get(getActivity()).getComponent().inject(this);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        unbinder = ButterKnife.bind(this, view);
        setupWeatherListView();
        weatherViewModel = ViewModelProviders.of(this, weatherViewModelFactory).get(WeatherViewModel.class);
        return view;
    }

    private void setupWeatherListView() {
        weatherList.setLayoutManager(new LinearLayoutManager(getActivity()));
        weatherList.setHasFixedSize(true);
        weatherAdapter = new WeatherAdapter(getActivity());
        weatherList.setAdapter(weatherAdapter);
        weatherList.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayout.VERTICAL));
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        weatherViewModel.getAllWeathers().observe(this, weatherItems -> {
                    loading.setVisibility(View.INVISIBLE);
                    weatherAdapter.setAllWeathers(weatherItems);
                }
        );
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public void addCity(String city) {
        weatherViewModel.fetchCity(city);
    }

}
