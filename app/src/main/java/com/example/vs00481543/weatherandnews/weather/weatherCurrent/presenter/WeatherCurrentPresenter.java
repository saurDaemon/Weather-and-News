package com.example.vs00481543.weatherandnews.weather.weatherCurrent.presenter;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.location.Address;
import android.location.Geocoder;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.example.vs00481543.weatherandnews.Network.NetworkData;
import com.example.vs00481543.weatherandnews.R;
import com.example.vs00481543.weatherandnews.sharedPreferencesManager.PreferencesManager;
import com.example.vs00481543.weatherandnews.weather.weatherCurrent.WeatherContract;
import com.example.vs00481543.weatherandnews.weather.weatherCurrent.model.WeatherForecastDetails;
import com.example.vs00481543.weatherandnews.weather.weatherCurrent.model.WeatherDetails;

import java.io.IOException;
import java.util.List;

/**
 * Created by VS00481543 on 22-11-2017.
 */

public class WeatherCurrentPresenter implements WeatherContract.WeatherPresent {

    Context landingContext;
    WeatherContract.WeatherView landView;
    PreferencesManager preferencesManager;

    public WeatherCurrentPresenter(WeatherContract.WeatherView landView1, Context context)
    {
        landingContext=context;
        this.landView=landView1;
        landView.setPresenter(this);
        preferencesManager=new PreferencesManager(landingContext);
    }

    @Override
    public void getWeatherInfo(double lat,double longi) {

//        new getWeatherData().execute();
        //new NetworkData().getNetworkDataVolley(this,landingContext,lat,longi);
        new NetworkData().getNetworkDataRetrofit(this,landingContext,lat,longi,"Current");

    }


    public void getWeatherForecastInfo(double lat,double longi){
        new NetworkData().getNetworkDataRetrofit(this,landingContext,lat,longi,"Forecast");
    }


    @Override
    public void responseToView(WeatherDetails weatherDetails) {

        if(weatherDetails!=null)
        {

              landView.callFragment(weatherDetails);


   // landView.displayText(weatherDetails);

            /*try {
                JSONObject jsonObject = new JSONObject(str);
                JSONObject coord=jsonObject.getJSONObject("coord");
                String lat=coord.getString("lat");
                String lon=coord.getString("lon");

                Log.d("Response", "responseToView: "+lat+"  "+lon);

            } catch (JSONException e) {
                e.printStackTrace();
            }*/

             }
             Log.d("Response", "responseToView: "+weatherDetails);
    }

    @Override
    public void apiCalls() {

        String lati=preferencesManager.getStringPref("Latitude");
        String longi=preferencesManager.getStringPref("Longitude");

        double latitude=Double.parseDouble(lati);
        double longitude=Double.parseDouble(longi);


            getWeatherInfo(latitude,longitude);
            getWeatherForecastInfo(latitude,longitude);

    }


    @Override
    public void responseToForecastView(WeatherForecastDetails weatherForecastDetails) {

        Log.d("forecast", "responseToForecastView: "+ weatherForecastDetails);
        Log.d("forecast", "responseToForecastView: "+ weatherForecastDetails.getList()[0].getMain().getTemp());
        Log.d("forecast", "responseToForecastView: "+ weatherForecastDetails.getList()[1].getDt_txt());
        Log.d("forecast", "ResponseToForeCastView: "+ weatherForecastDetails.getList()[2].getWeather()[0].getDescription());


        landView.displayForecast(weatherForecastDetails);
    }





    /*private class getWeatherData extends AsyncTask<String,String,String>
    {

        @Override
        protected String doInBackground(String... strings) {

            String resp="";
            HttpHandler httpHandler=new HttpHandler();
            try {
                resp=httpHandler.makeServiceCall(jsonUrl);
            } catch (IOException e) {
                e.printStackTrace();
            }

            Log.d("Response", "doInBackground: "+resp);
            return null;
        }
    }*/
}
