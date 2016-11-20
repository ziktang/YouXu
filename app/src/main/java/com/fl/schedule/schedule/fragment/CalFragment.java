package com.fl.schedule.schedule.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fl.schedule.R;
import com.fl.schedule.base.contact.BasePresenter;
import com.fl.schedule.schedule.adapter.CalAdapter;
import com.fl.schedule.schedule.contact.CalContact;
import com.fl.schedule.schedule.model.bean.CalDate;
import com.fl.schedule.utils.LogUtil;
import com.fl.schedule.utils.UiUtils;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.decoration.SpaceDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by tctctc on 2016/11/8.
 */

public class CalFragment extends Fragment implements CalAdapter.OnCalItemListener, CalContact.View {

    private static final int COLUMNS = 7;
    @BindView(R.id.cal_recycle)
    EasyRecyclerView mCalRecycle;
    private List<CalDate> mCalDateList;
    private CalAdapter mAdapter;
    private boolean isViewInit;
    private CalContact.Presenter mPresenter;

    public static CalFragment newInstance() {
        Bundle args = new Bundle();
        CalFragment fragment = new CalFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            if (isViewInit)
                mPresenter.initData();
//        相当于Fragment的onResume
        } else {
            //相当于Fragment的onPause
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCalDateList = new ArrayList<>();
        mAdapter = new CalAdapter(getContext(), mCalDateList);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cal, container, false);
        ButterKnife.bind(this, view);
        initView();
        mPresenter.initData();
        return view;
    }

    private void initView() {
        mAdapter.setItemListener(this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), COLUMNS);
        mCalRecycle.setLayoutManager(layoutManager);
        mCalRecycle.setAdapter(mAdapter);
        SpaceDecoration itemDecoration = new SpaceDecoration(UiUtils.dipToPx(getContext(), 4));//参数是距离宽度
        mCalRecycle.addItemDecoration(itemDecoration);
        mCalRecycle.setOverScrollMode(View.OVER_SCROLL_NEVER);
        isViewInit = true;
    }


    @Override
    public void OnItemSelected(CalDate date) {
        mPresenter.OnItemSelected(date);
    }

    @Override
    public void setPresenter(BasePresenter presenter) {
        mPresenter = (CalContact.Presenter) presenter;
    }

    @Override
    public void setCalDates(List<CalDate> calDates) {
        mCalDateList.clear();
        mCalDateList.addAll(calDates);
        mAdapter.notifyDataSetChanged();
    }

    public void onPagerSelect() {
        mPresenter.OnPageSelect();
    }

    @Override
    public void pageRefresh(int[] update) {
        mAdapter.notifyItemChanged(update[0]);
        mAdapter.notifyItemChanged(update[1]);
    }

    public void refresh() {
        mPresenter.refresh();
    }
}
