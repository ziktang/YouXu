package com.fl.schedule.widget.behaivor;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by tctctc on 2016/11/14.
 */

public class CarlendarBehavior extends CoordinatorLayout.Behavior<View> {

    public CarlendarBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child, View directTargetChild, View target, int nestedScrollAxes) {
        Log.i("aaa", (nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL) + "");
        return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL;
    }


    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dx, int dy, int[] consumed) {
//        Log.i("aaa", "dyConsumed:" + dyConsumed);
//        Log.i("aaa", "dyUnconsumed:" + dyUnconsumed);
//        ViewCompat.offsetTopAndBottom(child, -dyConsumed);

        if (dy > 0) { //表示向上滚动
            if (child.getTranslationY() > -child.getHeight()) {
                float trY = child.getTranslationY() - dy <= -child.getHeight()
                        ? -child.getHeight() : child.getTranslationY() - dy;
                consumed[1] = (int) (child.getTranslationY() - trY);
                ViewCompat.setTranslationY(child, trY);
            }
        } else if (dy < 0) { //向下滚动
            if (child.getTranslationY() < 0) {
                float trY = child.getTranslationY() - dy >= 0 ? 0 : child.getTranslationY() - dy;
                consumed[1] = (int) (child.getTranslationY() - trY);
                ViewCompat.setTranslationY(child, trY);
            }
        }
    }
}
