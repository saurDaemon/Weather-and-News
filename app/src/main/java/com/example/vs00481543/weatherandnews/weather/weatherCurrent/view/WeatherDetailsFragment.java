package com.example.vs00481543.weatherandnews.weather.weatherCurrent.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.example.vs00481543.weatherandnews.Network.VolleySingleton;
import com.example.vs00481543.weatherandnews.R;
import com.example.vs00481543.weatherandnews.weather.weatherCurrent.model.WeatherDetails;

/**
 * Created by VS00481543 on 29-11-2017.
 */

public class WeatherDetailsFragment extends Fragment {

    ImageLoader imageLoader;
    private String TODAY="Today";
    private String TIME_NOW="Now";

    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState)
    {

        final View view=inflater.inflate(R.layout.weather_constraint,viewGroup,false);

        ImageView weatherIcon= (ImageView) view.findViewById(R.id.iconw);

        TextView name = (TextView) view.findViewById(R.id.cityName);
        TextView today=(TextView) view.findViewById(R.id.today);
        TextView timeNow=(TextView) view.findViewById(R.id.timeNow);
        TextView description = (TextView) view.findViewById(R.id.desc);
        TextView temperature = (TextView) view.findViewById(R.id.temp);
        TextView minTemp=(TextView) view.findViewById(R.id.min_temp);
        TextView maxTemp=(TextView) view.findViewById(R.id.max_temp);
        TextView pressure = (TextView) view.findViewById(R.id.press);
        TextView humidity = (TextView) view.findViewById(R.id.humid);
        TextView seaLevel = (TextView) view.findViewById(R.id.seaLevel);
        TextView windSpeed = (TextView) view.findViewById(R.id.windSpeed);

        if(getArguments()!=null)
        {
            WeatherDetails wd=(WeatherDetails) getArguments().getSerializable("weatherObject");

            Log.d("Fragment", "onCreateView: "+wd);

            name.setText(wd.getName());
            today.setText(TODAY);
            timeNow.setText(TIME_NOW);
            description.setText(wd.getWeather()[0].getDescription());
            temperature.setText(wd.getMain().getTemp()+"°c");
            maxTemp.setText("↑"+wd.getMain().getTemp_max()+"°");
            minTemp.setText("↓"+wd.getMain().getTemp_min()+"°");
            pressure.setText(wd.getMain().getPressure());
            humidity.setText(wd.getMain().getHumidity());
            if(wd.getMain().getSea_level()==null)
                seaLevel.setText("Data error");
            else
                seaLevel.setText(wd.getMain().getSea_level());
            windSpeed.setText(wd.getWind().getSpeed());

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
