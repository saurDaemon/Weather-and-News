package com.example.vs00481543.weatherandnews.news.view;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vs00481543.weatherandnews.R;

/**
 * Created by VS00481543 on 15-01-2018.
 */

public class NewsMainFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final View view=inflater.inflate(R.layout.tab_news_fragment,container,false);
        return view;
    }
}
