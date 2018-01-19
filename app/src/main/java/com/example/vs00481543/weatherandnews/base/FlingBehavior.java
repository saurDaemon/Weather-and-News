package com.example.vs00481543.weatherandnews.base;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ScrollingView;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by VS00481543 on 12-01-2018.
 */

public final class FlingBehavior extends AppBarLayout.Behavior {

    public FlingBehavior()
    {

    }

    public FlingBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public boolean onNestedFling(CoordinatorLayout coordinatorLayout, AppBarLayout child, View target, float velocityX, float velocityY, boolean consumed) {
        if (target instanceof ScrollingView) {
            final ScrollingView scrollingView = (ScrollingView) target;
            consumed = velocityY > 0 || scrollingView.computeVerticalScrollOffset() > 0;
        }
        return super.onNestedFling(coordinatorLayout, child, target, velocityX, velocityY, consumed);
    }

}
