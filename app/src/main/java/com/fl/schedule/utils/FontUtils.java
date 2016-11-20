package com.fl.schedule.utils;

import android.content.Context;
import android.graphics.Typeface;

public class FontUtils {
    private static Typeface typeface;
    public static Typeface getTypeface(Context context){
        if (typeface == null){
            typeface = Typeface.createFromAsset(context.getAssets(), "iconfont/iconfont.ttf");
        }
        return typeface;
    }

}