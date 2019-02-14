package com.example.luismauricio.weatherappedlio.data.network;

import android.support.annotation.NonNull;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

class WeatherClient {

    private static WeatherApi INSTANCE;
    private static final Object lock = new Object();

    static WeatherApi getWeatherService() {
        if (INSTANCE == null) {
            synchronized (lock) {
                INSTANCE = new Retrofit.Builder()
                        .baseUrl(WeatherApi.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(getClient())
                        .build()
                        .create(WeatherApi.class);
            }
        }
        return INSTANCE;
    }

    private static OkHttpClient getClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(new ApiInterceptor())
                .build();
    }

    private static class ApiInterceptor implements Interceptor {
        @NonNull
        @Override
        public Response intercept(@NonNull Chain chain) throws IOException {
            Request request = chain.request();
            HttpUrl url = request.url().newBuilder()
                    .addQueryParameter(WeatherApi.API_QUERY_PARAM, WeatherApi.API_KEY)
                    .build();
            request = request.newBuilder().url(url).build();
            return chain.proceed(request);
        }
    }

}
