package com.example.vs00481543.weatherandnews.Network;

import com.example.vs00481543.weatherandnews.weather.weatherCurrent.model.WeatherDetails;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by VS00481543 on 07-12-2017.
 */

public interface RetrofitApiInterface {


   /* http://api.openweathermap.org/data/2.5/weather?lat="+latitude+"&lon="+longitude+
    // "&units=metric&type=accurate&APPID=ec6cba60275372b25bed284fded6205d*/
    @GET("weather")
    Call<WeatherDetails> getWeatherDetails(@Query("lat") double lat,@Query("lon") double lon,@Query("units")
            String units,@Query("type") String type,@Query("APPID") String APPID);

}
