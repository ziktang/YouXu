package com.fl.schedule.daily.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.fl.schedule.R;
import com.jude.easyrecyclerview.EasyRecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddDailyActivity extends AppCompatActivity {

    @BindView(R.id.add_daily_toolbar)
    Toolbar mAddDailyToolbar;
    @BindView(R.id.img_recycle)
    EasyRecyclerView mImgRecycle;
    @BindView(R.id.daily_date)
    TextView mDailyDate;
    @BindView(R.id.daily_weather)
    TextView mDailyWeather;
    @BindView(R.id.daily_mood)
    TextView mDailyMood;
    @BindView(R.id.place)
    ImageView mPlace;
    @BindView(R.id.weather)
    ImageView mWeather;
    @BindView(R.id.mood)
    ImageView mMood;
    @BindView(R.id.description)
    EditText mDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_daily);
        ButterKnife.bind(this);
        setSupportActionBar(mAddDailyToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
