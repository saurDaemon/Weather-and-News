package com.example.vs00481543.weatherandnews.weather.weatherCurrent.view;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vs00481543.weatherandnews.R;
import com.example.vs00481543.weatherandnews.weather.weatherCurrent.WeatherContract;
import com.example.vs00481543.weatherandnews.weather.weatherCurrent.adapter.CurrentForecastAdapter;
import com.example.vs00481543.weatherandnews.weather.weatherCurrent.model.WeatherDetails;
import com.example.vs00481543.weatherandnews.weather.weatherCurrent.model.WeatherForecastDetails;
import com.example.vs00481543.weatherandnews.weather.weatherCurrent.presenter.WeatherCurrentPresenter;

/**
 * Created by VS00481543 on 22-11-2017.
 */

public class WeatherMainFragment extends Fragment implements WeatherContract.WeatherView {

    RecyclerView recyclerView;
    CurrentForecastAdapter currentForecastAdapter;
    Context context;
    WeatherContract.WeatherPresent weatherPresent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final View view=inflater.inflate(R.layout.tab_weather_fragment,viewGroup,false);
        context= this.getActivity();

        initComponents(view);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        new WeatherCurrentPresenter(WeatherMainFragment.this,context);

        weatherPresent.apiCalls();
    }

    @Override
    public void setPresenter(WeatherContract.WeatherPresent mpresenter) {
        this.weatherPresent =mpresenter;
    }


    public void initComponents(View view)
    {
        recyclerView=(RecyclerView) view.findViewById(R.id.recycle);

    }


    @Override
    public void displayForecast(WeatherForecastDetails weatherForecastDetails) {

        currentForecastAdapter=new CurrentForecastAdapter(weatherForecastDetails,getActivity());
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(currentForecastAdapter);
    }

    @Override
    public void callFragment(WeatherDetails weatherDetails) {
        if(weatherDetails!=null) {

            Log.d("", "responseToView: " + weatherDetails);

            WeatherDetailsFragment frag = new WeatherDetailsFragment();
            Bundle bundle = new Bundle();
            bundle.putSerializable("weatherObject", weatherDetails);
            frag.setArguments(bundle);
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame, frag).commit();
        }
    }

}
