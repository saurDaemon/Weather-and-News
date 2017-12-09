package com.example.vs00481543.weatherandnews.Network;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by VS00481543 on 07-12-2017.
 */

public class ApiClientRetrofit {

    private static final String WEATHER_URL="http://api.openweathermap.org/data/2.5/";

    public static Retrofit getRetrofitClient() {
        Retrofit retrofit = null;
        HttpLoggingInterceptor interceptor=new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client=new OkHttpClient.Builder().addInterceptor(interceptor).build();

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                        .baseUrl(WEATHER_URL)
                        .client(client)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
        }

        return retrofit;
    }
}
