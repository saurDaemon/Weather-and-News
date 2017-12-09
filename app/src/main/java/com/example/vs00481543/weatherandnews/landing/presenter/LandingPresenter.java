package com.example.vs00481543.weatherandnews.landing.presenter;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.vs00481543.weatherandnews.R;
import com.example.vs00481543.weatherandnews.Network.NetworkData;
import com.example.vs00481543.weatherandnews.landing.LandingContract;
import com.example.vs00481543.weatherandnews.landing.model.WeatherDetails;
import com.example.vs00481543.weatherandnews.landing.view.WeatherDetailsFragment;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.List;

/**
 * Created by VS00481543 on 22-11-2017.
 */

public class LandingPresenter implements LandingContract.LandPresent {
    AppCompatActivity landingContext;
    LandingContract.LandingView landView;

    public LandingPresenter(LandingContract.LandingView landView1, AppCompatActivity context)
    {
        landingContext=context;
        this.landView=landView1;
        landView.setPresenter(this);
    }

    @Override
    public void getWeatherInfo(double lat,double longi) {

//        new getWeatherData().execute();
        //new NetworkData().getNetworkDataVolley(this,landingContext,lat,longi);
        new NetworkData().getNetworkDataRetrofit(this,landingContext,lat,longi);

    }

    @Override
    public void responseToView(WeatherDetails weatherDetails) {

        if(weatherDetails!=null)
        {

              Log.d("", "responseToView: "+ weatherDetails);

              WeatherDetailsFragment frag=new WeatherDetailsFragment();
              Bundle bundle=new Bundle();
              bundle.putSerializable("weatherObject", weatherDetails);
              frag.setArguments(bundle);
              landingContext.getSupportFragmentManager().beginTransaction().replace(R.id.frame,frag).commit();


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
    public void getLatLong(String location) {
        double latitude=0,longitude=0;

        if(Geocoder.isPresent())
        {
            try {
                Geocoder gc=new Geocoder(landingContext);
                List<Address> addresses=gc.getFromLocationName(location,1);

                if(addresses.size()>0)
                {
                    latitude=addresses.get(0).getLatitude();
                    longitude=addresses.get(0).getLongitude();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

            getWeatherInfo(latitude,longitude);
        }
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
