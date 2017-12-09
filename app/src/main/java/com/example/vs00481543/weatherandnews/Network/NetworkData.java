package com.example.vs00481543.weatherandnews.Network;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.StringRequest;
import com.example.vs00481543.weatherandnews.landing.LandingContract;
import com.example.vs00481543.weatherandnews.landing.model.WeatherDetails;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by VS00481543 on 23-11-2017.
 */

public class NetworkData {

        private static String WEATHER_URL;

        public void getNetworkDataVolley(final LandingContract.LandPresent landPresent, final Context context,double latitude,double longitude)
        {
            WEATHER_URL="http://api.openweathermap.org/data/2.5/weather?lat="+latitude+"&lon="+longitude+"&units=metric&type=accurate&APPID=ec6cba60275372b25bed284fded6205d";

            RequestQueue requestQueue= VolleySingleton.getInstance(context).getRequestQueue();


            StringRequest stringRequest=new StringRequest(Request.Method.GET, WEATHER_URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    GsonBuilder gsonBuilder=new GsonBuilder();
                    Gson mGson=gsonBuilder.create();

                    WeatherDetails weatherDetails = new WeatherDetails();
                    weatherDetails = mGson.fromJson(response,WeatherDetails.class);
                    landPresent.responseToView(weatherDetails);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(context,"Error in getting data, check Internet connection",Toast.LENGTH_SHORT).show();
                }
            });

            requestQueue.add(stringRequest);
        }


        public void getNetworkDataRetrofit(final LandingContract.LandPresent landPresent,final Context context,double latitude,double longitude)
        {
            RetrofitApiInterface apiService=ApiClientRetrofit.getRetrofitClient().create(RetrofitApiInterface.class);

            Call<WeatherDetails> call = apiService.getWeatherDetails(latitude,longitude,"metric","accurate","ec6cba60275372b25bed284fded6205d");
            call.enqueue(new Callback<WeatherDetails>() {
                @Override
                public void onResponse(Call<WeatherDetails> call, retrofit2.Response<WeatherDetails> response) {
                    if (response.isSuccessful()) {
                        Log.d("retrofit", "onResponse: " + response.body());
                        landPresent.responseToView(response.body());
                    }
                    Log.d("retrofit", "onResponse: " + "fail");

                }

                @Override
                public void onFailure(Call<WeatherDetails> call, Throwable t) {
                    Log.d("retrofit", "onFailure: " +t);
                }
            });

        }

}
