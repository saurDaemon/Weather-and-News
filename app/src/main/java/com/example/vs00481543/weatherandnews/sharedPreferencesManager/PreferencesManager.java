package com.example.vs00481543.weatherandnews.sharedPreferencesManager;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by VS00481543 on 17-01-2018.
 */

public class PreferencesManager {

    SharedPreferences sharedPreferences;
    Context context;
    SharedPreferences.Editor editor;
    private String my_pref="my_preferences";


    public PreferencesManager(Context context1)
    {
        context=context1;
        sharedPreferences=context.getSharedPreferences(my_pref,Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
    }

    public void setDoublePref(String key,double value)
    {
        /*editor.putLong(key,Double.doubleToRawLongBits(value));*/
        editor.putString(key,Double.toString(value));
    }

    public double getDoublePref(String key)
    {
        return Double.parseDouble(getStringPref(key));
    }

    public void setLong(String key,long value)
    {
        editor.putLong(key,value);
        editor.commit();
    }

    public long getLong(String key)
    {
        return sharedPreferences.getLong(key,0);
    }

    public void setIntPref(String key,int value)
    {
        editor.putInt(key,value);
        editor.commit();
    }

    public void setStringPref(String key,String value)
    {
        editor.putString(key,value);
        editor.commit();
    }

    public int getIntPref(String key)
    {
        return sharedPreferences.getInt(key,0);
    }


    public String getStringPref(String key)
    {
        return sharedPreferences.getString(key,"");
    }

}
