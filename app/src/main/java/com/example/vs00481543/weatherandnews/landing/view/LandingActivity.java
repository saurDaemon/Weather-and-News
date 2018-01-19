package com.example.vs00481543.weatherandnews.landing.view;


import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.vs00481543.weatherandnews.R;
import com.example.vs00481543.weatherandnews.landing.LandingContract;
import com.example.vs00481543.weatherandnews.landing.presenter.LandingPresenter;
import com.example.vs00481543.weatherandnews.news.view.NewsMainFragment;
import com.example.vs00481543.weatherandnews.sharedPreferencesManager.PreferencesManager;
import com.example.vs00481543.weatherandnews.weather.weatherCurrent.WeatherContract;
import com.example.vs00481543.weatherandnews.weather.weatherCurrent.view.WeatherMainFragment;

public class LandingActivity extends AppCompatActivity implements LandingContract.LandingView {

    EditText locationtext;
    Button enterButton;
    LandingContract.LandPresent landPresent;
    private static Boolean tabsCreated;

    WeatherMainFragment fragmentWeather;
    NewsMainFragment fragmentNews;
    TabLayout tabLayout;
    PreferencesManager preferencesManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tabsCreated=true;

        setContentView(R.layout.landing_activity);

        preferencesManager=new PreferencesManager(this);
        new LandingPresenter(this,this);
        initComponents();
    }


    public void setUpTabLayout()
    {
        fragmentWeather = new WeatherMainFragment();
        fragmentNews = new NewsMainFragment();
        if(tabsCreated) {
            tabLayout = (TabLayout) findViewById(R.id.tabs);
            tabLayout.addTab(tabLayout.newTab().setText(R.string.weather), true);
            tabLayout.addTab(tabLayout.newTab().setText(R.string.news));
            tabsCreated=false;
        }
        setCurrentTabFragment(tabLayout.getSelectedTabPosition());


        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                setCurrentTabFragment(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }


    private void setCurrentTabFragment(int tabPosition)
    {
        switch (tabPosition)
        {
            case 0 :
                replaceFragment(fragmentWeather);
                break;
            case 1 :
                replaceFragment(fragmentNews);
                break;
        }

    }

    private void replaceFragment(Fragment fragment)
    {
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_weather,fragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.commit();
    }



    private void initComponents()
    {
        locationtext=(EditText) findViewById(R.id.location_id);

        locationtext.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(!hasFocus)
                    landPresent.hideKeyboard(view);
            }
        });

        enterButton=(Button) findViewById(R.id.loc_button);
        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String location=locationtext.getText().toString();
                Toast.makeText(LandingActivity.this,"Locations is : "+location,Toast.LENGTH_SHORT).show();

                preferencesManager.setStringPref("Location",location);

                landPresent.hideKeyboard(view);
                landPresent.getLatLong(location);
            }
        });
    }


    @Override
    public void setPresenter(LandingContract.LandPresent presenter) {
        landPresent=presenter;
    }
}
