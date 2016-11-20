package com.fl.schedule.schedule.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fl.schedule.R;
import com.fl.schedule.schedule.model.bean.Schedule;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by tctctc on 2016/11/18.
 */

public class ScheListAdapter extends RecyclerArrayAdapter<Schedule> {
    public ScheListAdapter(Context context, List<Schedule> objects) {
        super(context, objects);
    }

    @Override
    public ScheViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ScheViewHolder(parent,R.layout.sche_list_item);
    }

    public class ScheViewHolder extends BaseViewHolder<Schedule> {
        @BindView(R.id.sche_title)
        TextView mScheTitle;
        @BindView(R.id.sche_time)
        TextView mScheTime;
        @BindView(R.id.done_img)
        ImageView mDoneImg;

        public ScheViewHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, R.layout.sche_list_item);
            ButterKnife.bind(this,itemView);
        }

        @Override
        public void setData(Schedule data) {
            super.setData(data);
            mScheTitle.setText(data.getDescription());
            mScheTime.setText(data.getStartTime()+"-"+data.getEndTime());
            if (data.getStatus() == Schedule.DONE_UN_DELETE)
                mDoneImg.setVisibility(View.VISIBLE);
            else{
                mDoneImg.setVisibility(View.GONE);
            }
        }
    }
}
