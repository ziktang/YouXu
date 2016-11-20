package com.fl.schedule.CustomView.behaivor;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by tctctc on 2016/11/14.
 */

public class RecycleViewSelfBehavior extends CoordinatorLayout.Behavior<View>{
    public RecycleViewSelfBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
        Log.i("aaa","RecycleViewBehavior");
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child, View directTargetChild, View target, int nestedScrollAxes) {
        Log.i("aaa",(nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL)+"");
        return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL;
    }

    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        Log.i("aaa","dyConsumed:"+dyConsumed);
        Log.i("aaa","dyUnconsumed:"+dyUnconsumed);
        ViewCompat.offsetTopAndBottom(child,-dyConsumed);
    }
}
