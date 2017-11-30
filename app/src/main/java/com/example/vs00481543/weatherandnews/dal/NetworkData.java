package com.example.vs00481543.weatherandnews.dal;

import android.content.Context;
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

/**
 * Created by VS00481543 on 23-11-2017.
 */

public class NetworkData {

        private static String WEATHER_URL;

        public void getNetworkData(final LandingContract.LandPresent landPresent, final Context context,double latitude,double longitude)
        {
            WEATHER_URL="http://api.openweathermap.org/data/2.5/weather?lat="+latitude+"&lon="+longitude+"&units=metric&type=accurate&APPID=ec6cba60275372b25bed284fded6205d";

            RequestQueue requestQueue;
            Cache cache=new DiskBasedCache(context.getCacheDir(),1024*1024);

            Network network=new BasicNetwork(new HurlStack());
            requestQueue=new RequestQueue(cache,network);
            requestQueue.start();

            StringRequest stringRequest=new StringRequest(Request.Method.GET, WEATHER_URL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    landPresent.responseToView(response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(context,"Error in getting data",Toast.LENGTH_SHORT).show();
                }
            });

            requestQueue.add(stringRequest);
        }
}
