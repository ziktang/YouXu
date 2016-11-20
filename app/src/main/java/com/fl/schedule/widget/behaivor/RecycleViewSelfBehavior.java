package com.fl.schedule.widget.behaivor;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.fl.schedule.R;

/**
 * Created by tctctc on 2016/11/14.
 */

public class RecycleViewSelfBehavior extends CoordinatorLayout.Behavior<View>{
    public RecycleViewSelfBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
        Log.i("aaa","RecycleViewSelfBehavior");
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        return dependency.getId() == R.id.calendar_pager;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        ViewCompat.setTranslationY(child,dependency.getTranslationY());
        return true;
    }
}
