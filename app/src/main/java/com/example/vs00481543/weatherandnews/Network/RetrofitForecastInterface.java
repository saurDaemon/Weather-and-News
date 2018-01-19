package com.example.vs00481543.weatherandnews.Network;

import com.example.vs00481543.weatherandnews.weather.weatherCurrent.model.WeatherForecastDetails;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by VS00481543 on 22-12-2017.
 */

public interface RetrofitForecastInterface {

    @GET("forecast")
    Call<WeatherForecastDetails> getWeatherForecastDetails(@Query("lat") double lat, @Query("lon") double lon, @Query("units")
            String units, @Query("type") String type, @Query("APPID") String APPID);
}
