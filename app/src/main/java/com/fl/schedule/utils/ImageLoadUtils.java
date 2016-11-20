package com.fl.schedule.utils;

import android.app.Application;
import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by tctctc on 2016/11/6.
 */

public class ImageLoadUtils {
    private static Context mContext;

    public static void init(Context context){
        mContext = context;
    }
    public static void loadImageRemote(ImageView imageView,String url){
        Glide.with(mContext)
                .load(url)
                .into(imageView);
    }

    public static void loadImageLocal(ImageView imageView,String url){
        Glide.with(mContext)
                .load(url)
                .into(imageView);
    }
}
