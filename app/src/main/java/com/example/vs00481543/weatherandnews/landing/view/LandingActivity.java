package com.example.vs00481543.weatherandnews.landing.view;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vs00481543.weatherandnews.R;
import com.example.vs00481543.weatherandnews.base.BaseActivity;
import com.example.vs00481543.weatherandnews.landing.LandingContract;
import com.example.vs00481543.weatherandnews.landing.model.WeatherDetails;
import com.example.vs00481543.weatherandnews.landing.presenter.LandingPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by VS00481543 on 22-11-2017.
 */

public class LandingActivity extends BaseActivity implements LandingContract.LandingView {

    EditText locationtext;
    Button enterButton;
    Context context;
    LandingContract.LandPresent landPresent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.landing_activity);
        context=this;

        initComponents();
    }

    @Override
    protected void onStart() {
        super.onStart();

        new LandingPresenter(this,this);

    }

    @Override
    public void setPresenter(LandingContract.LandPresent mpresenter) {
        this.landPresent=mpresenter;
    }


    public void initComponents()
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
                landPresent.hideKeyboard(view);
                landPresent.getLatLong(location);
            }
        });
    }

    @Override
    public void displayText(WeatherDetails weatherDetails) {

    }
}
