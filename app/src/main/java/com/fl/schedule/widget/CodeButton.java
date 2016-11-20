package com.fl.schedule.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.animation.LinearInterpolator;
import android.widget.Button;

import com.fl.schedule.R;

/**
 * Created by tctctc on 2016/11/7.
 */

public class CodeButton extends Button {

    private int startNum = 60;
    private int endNum = 0;
    private int duration = 60*1000;
    private String normalStr = "验证码";
    private ObjectAnimator objectAnimator;

    public CodeButton(Context context) {
        super(context);
    }

    public CodeButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        normalStr = (String) getText();
        setCheckCode();
    }

    private void setTime(int time){
        this.setText(time+"s");
    }

    private void setCheckCode(){
        duration = Math.abs(startNum - endNum)*1000;
        objectAnimator = ObjectAnimator.ofInt(this,"time",startNum,endNum).setDuration(duration);
        objectAnimator.setInterpolator(new LinearInterpolator());
        objectAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                CodeButton.this.setClickable(false);
                CodeButton.this.setBackground(getResources().getDrawable(R.drawable.code_bg_click));
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                CodeButton.this.setClickable(true);
                CodeButton.this.setBackground(getResources().getDrawable(R.drawable.code_bg));
                CodeButton.this.setText(normalStr);
            }
        });
    }

    public void start(){
        objectAnimator.start();
    }

    public void setStartNum(int startNum) {
        this.startNum = startNum;
    }


    public void setEndNum(int endNum) {
        this.endNum = endNum;
    }

    public void setNormalStr(String normalStr) {
        this.normalStr = normalStr;
    }
}
