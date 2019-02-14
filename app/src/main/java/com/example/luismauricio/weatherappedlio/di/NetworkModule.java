package com.example.luismauricio.weatherappedlio.di;

import android.support.annotation.NonNull;

import com.example.luismauricio.weatherappedlio.data.network.WeatherApi;

import java.io.IOException;

import dagger.Module;
import dagger.Provides;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
class NetworkModule {

    @Provides
    @WeatherApplicationScope
    WeatherApi provideWeatherService(Retrofit retrofit) {
        return retrofit.create(WeatherApi.class);
    }

    @Provides
    @WeatherApplicationScope
    Retrofit provideRetrofit(OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl(WeatherApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }

    @Provides
    @WeatherApplicationScope
    OkHttpClient provideClient(ApiInterceptor interceptor) {
        return new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();
    }

    @Provides
    @WeatherApplicationScope
    ApiInterceptor providesApiInterceptor() {
        return new ApiInterceptor();
    }

     static class ApiInterceptor implements Interceptor {
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
