package com.fl.schedule.daily.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fl.schedule.R;
import com.fl.schedule.daily.model.bean.Daily;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by tctctc on 2016/11/21.
 */

public class DailyListAdapter extends RecyclerArrayAdapter<Daily> {
    public DailyListAdapter(Context context, List<Daily> objects) {
        super(context, objects);
    }

    @Override
    public DailyViewholder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new DailyViewholder(parent, R.layout.daily_list_item);
    }


    class DailyViewholder extends BaseViewHolder<Daily> {

        @BindView(R.id.time_daily)
        TextView mTimeDaily;
        @BindView(R.id.weather)
        ImageView mWeather;
        @BindView(R.id.voice_daily)
        ImageView mVoiceDaily;
        @BindView(R.id.descrip_daily)
        TextView mDescripDaily;
        @BindView(R.id.img_daily)
        ImageView mImgDaily;
        @BindView(R.id.place_daily)
        TextView mPlaceDaily;

        public DailyViewholder(ViewGroup parent, @LayoutRes int res) {
            super(parent, R.layout.daily_list_item);
            ButterKnife.bind(this,itemView);
        }

        @Override
        public void setData(Daily data) {
            super.setData(data);
            //时间
            mTimeDaily.setText(data.getTime());
            //文字内容
            mDescripDaily.setText(data.getDescription());

            //心情
            if (!data.getMood().isEmpty()) {

            }

            //图片
            if (!data.getPhotosPath().isEmpty()) {
                String[] paths = data.getPhotosPath().split(",");
                Glide.with(getContext())
                        .load(paths[0])
                        .into(mImgDaily);
            }

            //语音
            if (!data.getVoicePath().isEmpty()) {

            }

            //地点
            if (!data.getPlace().isEmpty()) {
                mPlaceDaily.setText(data.getPlace());
            }

            //天气
            if (!data.getWeather().isEmpty()) {

            }
        }
    }
}
