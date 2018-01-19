package com.example.vs00481543.weatherandnews.base;

import android.util.Log;

import java.util.Calendar;

/**
 * Created by vs00481543 on 27-12-2017.
 */

public class BasePresenter {

    final String TAGCM="Inside BasePresenter";
    Calendar cal=Calendar.getInstance();

    public String getTimeNow()
    {
        String am_pm="";
        int sec=cal.get(Calendar.SECOND);
        int min=cal.get(Calendar.MINUTE);
        int hr=cal.get(Calendar.HOUR);
        int amPm=cal.get(Calendar.AM_PM);
        if(amPm==1)
            am_pm="PM";
        else if(amPm==0)
            am_pm="AM";

        String time=String.valueOf(hr)+":"+String.valueOf(min)+":"+String.valueOf(sec)+" "+am_pm;

        Log.d(TAGCM, "Date "+time);
        return time;
    }
}
