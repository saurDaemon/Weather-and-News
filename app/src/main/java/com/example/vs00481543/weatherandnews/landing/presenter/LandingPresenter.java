package com.example.vs00481543.weatherandnews.landing.presenter;

import android.app.Activity;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.example.vs00481543.weatherandnews.landing.LandingContract;
import com.example.vs00481543.weatherandnews.sharedPreferencesManager.PreferencesManager;

import java.io.IOException;
import java.util.List;

/**
 * Created by VS00481543 on 15-01-2018.
 */

public class LandingPresenter implements LandingContract.LandPresent {
    LandingContract.LandingView landingView;
    AppCompatActivity context;
    PreferencesManager preferencesManager;

    public LandingPresenter(LandingContract.LandingView landingView1, AppCompatActivity context1)
    {
        landingView=landingView1;
        context=context1;
        preferencesManager=new PreferencesManager(context);
        landingView.setPresenter(this);
    }


    @Override
    public void getLatLong(String location) {
        double latitude=0,longitude=0;

        if(Geocoder.isPresent())
        {
            try {
                Geocoder gc=new Geocoder(context);
                List<Address> addresses=gc.getFromLocationName(location,1);

                if(addresses.size()>0)
                {
                    latitude=addresses.get(0).getLatitude();
                    longitude=addresses.get(0).getLongitude();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Log.d("LandingPresenter", "getLatLong: "+latitude+"   "+longitude);

        String lati=Double.toString(latitude);
        String longi=Double.toString(longitude);

        preferencesManager.setStringPref("Latitude",lati);
        preferencesManager.setStringPref("Longitude",longi);

        landingView.setUpTabLayout();
    }

    @Override
    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager=(InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(),0);
    }
}
