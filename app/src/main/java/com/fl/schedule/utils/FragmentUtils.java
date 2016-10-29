package com.fl.schedule.utils;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

/**
 * Created by tctctc on 2016/10/26.
 */

public class FragmentUtils {
    public static void changeFragment(AppCompatActivity activity, Fragment fragment){
        FragmentManager manager = activity.getSupportFragmentManager();
        manager.beginTransaction().show(fragment).commit();
    }

    public static void addFragments(AppCompatActivity activity,ArrayList<Fragment> fragments,int container){
        FragmentManager manager = activity.getSupportFragmentManager();
        for (int i = 0; i < fragments.size(); i++) {
            manager.beginTransaction().add(container,fragments.get(i),i+"");
        }
    }
}
