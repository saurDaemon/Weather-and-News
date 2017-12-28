package com.example.vs00481543.weatherandnews.landing;

import android.view.View;

import com.example.vs00481543.weatherandnews.base.BaseView;
import com.example.vs00481543.weatherandnews.landing.model.WeatherDetails;
import com.example.vs00481543.weatherandnews.landing.model.WeatherForecastDetails;

import java.util.List;

/**
 * Created by VS00481543 on 22-11-2017.
 */

public class LandingContract {

    public interface LandingView extends BaseView<LandPresent>
    {
        void displayText(WeatherDetails weatherDetails);

    }

    public interface LandPresent
    {
        void getWeatherInfo(double lat,double longi);

        void responseToView(WeatherDetails weatherDetails);

        void getLatLong(String str);

        void hideKeyboard(View view);

        void responseToForecastView(WeatherForecastDetails weatherForecastDetails);

    }


}
