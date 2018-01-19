package com.example.vs00481543.weatherandnews.landing;

import android.view.View;

import com.example.vs00481543.weatherandnews.base.BaseView;
import com.example.vs00481543.weatherandnews.weather.weatherCurrent.WeatherContract;

/**
 * Created by VS00481543 on 15-01-2018.
 */

public class LandingContract {

    public interface LandingView extends BaseView<LandPresent>
    {
        void setUpTabLayout();

    }

    public interface LandPresent
    {

        void getLatLong(String str);

        void hideKeyboard(View view);


    }

}
