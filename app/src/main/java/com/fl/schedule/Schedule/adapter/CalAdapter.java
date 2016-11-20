package com.fl.schedule.schedule.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.fl.schedule.R;
import com.fl.schedule.schedule.model.bean.CalDate;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

import static com.fl.schedule.R.drawable.cal_select_bg;
/**
 * Created by tctctc on 2016/11/8.
 */

public class CalAdapter extends RecyclerArrayAdapter<CalDate> {

    private LinearLayout mlastItem;
    private OnCalItemListener mItemListener;

    public CalAdapter(Context context, List<CalDate> calDateList) {
        super(context, calDateList);
    }

    public void setItemListener(OnCalItemListener itemListener) {
        mItemListener = itemListener;
    }

    @Override
    public CalViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(getContext()).inflate(R.layout.cal_item, null);
        return new CalViewHolder(view);
    }

    class CalViewHolder extends BaseViewHolder<CalDate> {
        TextView mDayTv;
        TextView mLunarDayTv;
        LinearLayout mLayout;

        public CalViewHolder(View itemView) {
            super(itemView);
            mDayTv = (TextView) itemView.findViewById(R.id.day);
            mLunarDayTv = (TextView) itemView.findViewById(R.id.lunar_day);
            mLayout = (LinearLayout) itemView.findViewById(R.id.ll_cal);
        }

        @Override
        public void setData(final CalDate data) {
            super.setData(data);
            if (!data.isDate()) {
                return;
            }
            mDayTv.setText(data.getDay() + "");
            mLunarDayTv.setText(data.getLunarStr());

            if (data.isSelectday()) {
                int res;
                if (data.isToday()) {
                    mDayTv.setTextColor(getContext().getResources().getColor(R.color.daily_color));
                    res = R.drawable.cal_today_bg;
                }else {
                    res = cal_select_bg;
                }
                mlastItem = mLayout;
                mLayout.setBackground(getContext().getResources().getDrawable(res));
            }else{
                mLayout.setBackground(getContext().getResources().getDrawable(R.color.white));
            }

            mLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mlastItem != null) {
                        mlastItem.setBackground(getContext().getResources().getDrawable(R.color.white));
                    }
                    int res;
                    res = data.isToday()?R.drawable.cal_today_bg:R.drawable.cal_select_bg;
                    mLayout.setBackground(getContext().getResources().getDrawable(res));
                    mlastItem = mLayout;
                    mItemListener.OnItemSelected(data);
                }
            });
        }
    }

    public interface OnCalItemListener {
        void OnItemSelected(CalDate date);
    }
}
