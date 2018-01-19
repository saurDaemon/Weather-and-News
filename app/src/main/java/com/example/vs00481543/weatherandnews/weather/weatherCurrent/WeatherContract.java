package com.example.vs00481543.weatherandnews.weather.weatherCurrent;

import android.view.View;

import com.example.vs00481543.weatherandnews.base.BaseView;
import com.example.vs00481543.weatherandnews.weather.weatherCurrent.model.WeatherDetails;
import com.example.vs00481543.weatherandnews.weather.weatherCurrent.model.WeatherForecastDetails;

/**
 * Created by VS00481543 on 22-11-2017.
 */

public class WeatherContract {

    public interface WeatherView extends BaseView<WeatherPresent>
    {

        void displayForecast(WeatherForecastDetails weatherForecastDetails);

        void callFragment(WeatherDetails weatherDetails);
    }

    public interface WeatherPresent
    {
        void getWeatherInfo(double lat,double longi);

        void responseToView(WeatherDetails weatherDetails);

        void apiCalls();

        void responseToForecastView(WeatherForecastDetails weatherForecastDetails);


    }


}
