package com.example.vs00481543.weatherandnews.weather.weatherCurrent.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.vs00481543.weatherandnews.R;
import com.example.vs00481543.weatherandnews.weather.weatherCurrent.model.WeatherForecastDetails;

/**
 * Created by vs00481543 on 29-12-2017.
 */

public class CurrentForecastAdapter extends RecyclerView.Adapter<CurrentForecastAdapter.MyViewHolder> {

    WeatherForecastDetails weatherForecastDetails;
    Context context;

     public  CurrentForecastAdapter(WeatherForecastDetails weatherForecastDetails1, Context context1)
    {
        this.weatherForecastDetails=weatherForecastDetails1;
        this.context=context1;

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView timeF,descF,tempF;
        ImageView iconF;

        public MyViewHolder(View itemView) {
            super(itemView);
            timeF=(TextView) itemView.findViewById(R.id.timeForecast);
            iconF=(ImageView) itemView.findViewById(R.id.iconForecast);
           descF=(TextView) itemView.findViewById(R.id.descForecast);
            tempF=(TextView) itemView.findViewById(R.id.tempForecast);

        }
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        MyViewHolder viewHolder=null;
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.forecast_items_current,parent,false);
        viewHolder=new MyViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        String iconCode=weatherForecastDetails.getList()[position].getWeather()[0].getIcon();
        String url="http://openweathermap.org/img/w/"+iconCode+".png";

        holder.timeF.setText(weatherForecastDetails.getList()[position].getDt_txt());
        Log.d("forecast", "onBindViewHolder: "+ weatherForecastDetails.getList()[position].getWeather()[0].getDescription());
        Glide.with(context).load(url).into(holder.iconF);

        holder.descF.setText(weatherForecastDetails.getList()[position].getWeather()[0].getDescription());
        holder.tempF.setText(weatherForecastDetails.getList()[position].getMain().getTemp());

    }

    @Override
    public int getItemCount() {
        return 8;
    }
}
