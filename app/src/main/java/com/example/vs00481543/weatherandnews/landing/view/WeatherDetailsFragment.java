package com.example.vs00481543.weatherandnews.landing.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.bumptech.glide.Glide;
import com.example.vs00481543.weatherandnews.Network.VolleySingleton;
import com.example.vs00481543.weatherandnews.R;
import com.example.vs00481543.weatherandnews.landing.model.WeatherDetails;

/**
 * Created by VS00481543 on 29-11-2017.
 */

public class WeatherDetailsFragment extends Fragment {

    ImageLoader imageLoader;

    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState)
    {

        final View view=inflater.inflate(R.layout.weather_fragment,viewGroup,false);

        ImageView weatherIcon= (ImageView) view.findViewById(R.id.iconw);

        TextView name = (TextView) view.findViewById(R.id.cityName);
        TextView description = (TextView) view.findViewById(R.id.desc);
        TextView temperature = (TextView) view.findViewById(R.id.temp);
        TextView pressure = (TextView) view.findViewById(R.id.press);
        TextView humidity = (TextView) view.findViewById(R.id.humid);
        TextView seaLevel = (TextView) view.findViewById(R.id.seaLevel);
        TextView windSpeed = (TextView) view.findViewById(R.id.windSpeed);

        if(getArguments()!=null)
        {
            WeatherDetails wd=(WeatherDetails) getArguments().getSerializable("weatherObject");

            Log.d("Fragment", "onCreateView: "+wd);

            name.setText("City  : "+wd.getName());
            description.setText(" "+wd.getWeather()[0].getDescription());
            temperature.setText("Temperature : "+wd.getMain().getTemp());
            pressure.setText("Pressure : "+wd.getMain().getPressure());
            humidity.setText("Humidity : "+wd.getMain().getHumidity());
            seaLevel.setText("Sea Level : "+wd.getMain().getSea_level());
            windSpeed.setText("Wind speed : "+wd.getWind().getSpeed());

            String iconCode=wd.getWeather()[0].getIcon();
            String url="http://openweathermap.org/img/w/"+iconCode+".png";

            // Volley implementation
            imageLoader=  VolleySingleton.getInstance(getActivity()).getImageLoader();
            imageLoader.get(url,ImageLoader.getImageListener(weatherIcon,R.mipmap.blank,R.mipmap.error));

            //Glide implementation
            //Glide.with(this).load(url).into(weatherIcon);

        }

        return view;

    }
}
