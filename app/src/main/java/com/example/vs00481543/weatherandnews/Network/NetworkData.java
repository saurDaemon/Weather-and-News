package com.example.vs00481543.weatherandnews.Network;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.vs00481543.weatherandnews.weather.weatherCurrent.WeatherContract;
import com.example.vs00481543.weatherandnews.weather.weatherCurrent.model.WeatherDetails;
import com.example.vs00481543.weatherandnews.weather.weatherCurrent.model.WeatherForecastDetails;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by VS00481543 on 23-11-2017.
 */

public class NetworkData {

        private static String WEATHER_URL;
        private static String APP_ID="ec6cba60275372b25bed284fded6205d";
        private static String UNIT="metric" ;
        private static String TYPE="accurate";

        public void getNetworkDataVolley(final WeatherContract.WeatherPresent weatherPresent, final Context context, double latitude, double longitude)
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
                    weatherPresent.responseToView(weatherDetails);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(context,"Error in getting data, check Internet connection",Toast.LENGTH_SHORT).show();
                }
            });

            requestQueue.add(stringRequest);
        }


        public void getNetworkDataRetrofit(final WeatherContract.WeatherPresent weatherPresent, final Context context, double latitude, double longitude, String typeRequest)
        {
            if(typeRequest.equals("Current")) {
                RetrofitApiInterface apiService = ApiClientRetrofit.getRetrofitClient().create(RetrofitApiInterface.class);

                Call<WeatherDetails> call = apiService.getWeatherDetails(latitude, longitude, UNIT, TYPE, APP_ID);
                call.enqueue(new Callback<WeatherDetails>() {
                    @Override
                    public void onResponse(Call<WeatherDetails> call, retrofit2.Response<WeatherDetails> response) {
                        if (response.isSuccessful()) {
                            Log.d("retrofit", "onResponse: " + response.body());
                            weatherPresent.responseToView(response.body());
                        }
                        Log.d("retrofit", "onResponse: " + "fail");

                    }

                    @Override
                    public void onFailure(Call<WeatherDetails> call, Throwable t) {
                        Log.d("retrofit", "onFailure: " + t);
                    }
                });
            }

            else if(typeRequest.equals("Forecast"))
            {
                RetrofitForecastInterface apiService=ApiClientRetrofit.getRetrofitClient().create(RetrofitForecastInterface.class);

                Call<WeatherForecastDetails> call=apiService.getWeatherForecastDetails(latitude,longitude, UNIT, TYPE, APP_ID);
                call.enqueue(new Callback<WeatherForecastDetails>() {
                    @Override
                    public void onResponse(Call<WeatherForecastDetails> call, retrofit2.Response<WeatherForecastDetails> response) {
                        if (response.isSuccessful()) {
                            Log.d("retrofit", "onResponse: forecast  :" + response.body());
                            weatherPresent.responseToForecastView(response.body());
                        }
                        Log.d("retrofit", "onResponse: " + "fail");
                    }

                    @Override
                    public void onFailure(Call<WeatherForecastDetails> call, Throwable t) {

                        Log.d("retrofit", "onFailure: " + t);
                    }
                });
            }


        }

}
