package com.fl.schedule.schedule.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fl.schedule.R;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

/**
 * Created by tctctc on 2016/11/18.
 */

public class LinearTextAdapter extends RecyclerArrayAdapter<String> {
    public LinearTextAdapter(Context context, String[] objects) {
        super(context, objects);
    }

    @Override
    public StringViewholder OnCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.list_item,null);
        return new StringViewholder(view);
    }

    public class StringViewholder extends BaseViewHolder<String>{

        private TextView mTextView;

        public StringViewholder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.tv);
        }

        @Override
        public void setData(String data) {
            super.setData(data);
            mTextView.setText(data);
        }
    }
}
