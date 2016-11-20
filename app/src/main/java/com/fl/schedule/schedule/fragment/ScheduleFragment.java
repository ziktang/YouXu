package com.fl.schedule.schedule.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fl.schedule.R;
import com.fl.schedule.base.contact.BasePresenter;
import com.fl.schedule.home.model.bean.WeatherInfo;
import com.fl.schedule.schedule.activity.AddScheduleActivity;
import com.fl.schedule.schedule.adapter.ScheListAdapter;
import com.fl.schedule.schedule.contact.CalContact;
import com.fl.schedule.schedule.contact.ScheMainContact;
import com.fl.schedule.schedule.model.dataSource.CalDataSource;
import com.fl.schedule.schedule.model.dataSource.ScheMainDataSource;
import com.fl.schedule.schedule.model.bean.CalDate;
import com.fl.schedule.schedule.model.bean.Schedule;
import com.fl.schedule.schedule.presenter.CalPresenter;
import com.fl.schedule.schedule.presenter.ScheMainPresenter;
import com.fl.schedule.utils.ChinaDate;
import com.fl.schedule.utils.DateUtils;
import com.fl.schedule.utils.FontUtils;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.fl.schedule.R.id.date;
import static com.fl.schedule.R.id.day;

/**
 * Created by tctctc on 2016/10/26.
 */

public class ScheduleFragment extends Fragment implements View.OnClickListener,ScheMainContact.View{

    private static final int POSITION = 60;
    public static final int ADD_SCHE_CODE = 100;
    public static final int EDIT_SCHE_CODE = 101;
    @BindView(day)
    TextView mDay;
    @BindView(R.id.week)
    TextView mWeek;
    @BindView(date)
    TextView mDate;
    @BindView(R.id.weatherName)
    TextView mWeatherName;
    @BindView(R.id.weatherIcon)
    TextView mWeatherIcon;
    @BindView(R.id.calendar_pager)
    ViewPager mCalendarPager;
    @BindView(R.id.cal_tab)
    FloatingActionButton mCalTab;
    @BindView(R.id.schedule_recycle)
    EasyRecyclerView mScheduleRecycle;
    @BindView(R.id.add_tab)
    FloatingActionButton mAddTab;
    private CustomFragmentStatePagerAdapter pagerAdapter;
    private int mCurPosition;
    private ScheMainContact.Presenter mPresenter;
    private List<Schedule> mScheduleList;
    private ScheListAdapter mAdapter;
    private CalDate mCalDate;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_schedule, container, false);
        ButterKnife.bind(this, view);
        initData();
        initView();
        return view;
    }

    private void initData() {
        new ScheMainPresenter(new ScheMainDataSource(),this);
        mWeatherIcon.setTypeface(FontUtils.getTypeface(getContext()));
        mScheduleList = new ArrayList<>();
    }

    private void initView() {
        DefaultItemAnimator defaultItemAnimator;
        mScheduleRecycle.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new ScheListAdapter(getContext(),mScheduleList);
        mScheduleRecycle.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                OnAddActivityIntent(mScheduleList.get(position).getId());
            }
        });
        pagerAdapter = new CustomFragmentStatePagerAdapter(getChildFragmentManager());
        mCalendarPager.setAdapter(pagerAdapter);
        mCalendarPager.setOffscreenPageLimit(1);
        mCalendarPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                if (position != POSITION && !mCalTab.isShown()) {
                    mCalTab.show();
                } else if (position == POSITION && mCalTab.isShown()) {
                    mCalTab.hide();
                }
                CalFragment thisFragment = (CalFragment) pagerAdapter.getFragment(position);
                if (thisFragment != null) {
                    thisFragment.onPagerSelect();
                }
                mCurPosition = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if (state == 1) {
                    CalFragment last = (CalFragment) pagerAdapter.getFragment(mCurPosition + 1);
                    if (last != null)
                        last.refresh();
                    CalFragment next = (CalFragment) pagerAdapter.getFragment(mCurPosition - 1);
                    if (next != null)
                        next.refresh();
                }
            }
        });

        mCalendarPager.setCurrentItem(POSITION);
        mCalendarPager.setOverScrollMode(View.OVER_SCROLL_NEVER);
        mCalTab.setOnClickListener(this);
        mCalTab.hide();
        mAddTab.setOnClickListener(this);
        changeDate(mPresenter.getTodayDate());
    }

    private void OnAddActivityIntent(long id) {
        Intent intent = new Intent(getActivity(),AddScheduleActivity.class);
        intent.putExtra("id",id);
        String month = mCalDate.getMonth()>9?mCalDate.getMonth()+"":"0"+mCalDate.getMonth();
        String day = mCalDate.getDay()>9?mCalDate.getDay()+"":"0"+mCalDate.getDay();
        intent.putExtra("date",mCalDate.getYear()+"-"+month+"-"+day);
        startActivityForResult(intent,EDIT_SCHE_CODE);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cal_tab:
                mCalendarPager.setCurrentItem(POSITION);
                break;
            case R.id.add_tab:
                OnAddActivityIntent(0);
                break;
        }
    }

    @Override
    public void setHeadWeather(WeatherInfo weather) {

    }

    @Override
    public void setScheList(List<Schedule> scheList) {
        mScheduleList.clear();
        mScheduleList.addAll(scheList);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void changeDate(CalDate calDate) {
        mCalDate = calDate;
        mDay.setText(calDate.getDay() + "");
        mWeek.setText("星期"+DateUtils.getChineseWeek(calDate.getDate()) + " 今天");
        String lunarStr;
        try {
            lunarStr = ChinaDate.oneDay(calDate.getYear(), calDate.getMonth(), calDate.getDay());
            lunarStr = lunarStr.substring(lunarStr.length() - 4, lunarStr.length());
        } catch (Exception e) {
            lunarStr = "";
        }
        mDate.setText(calDate.getYear() + "年 " + calDate.getMonth() + "月 " + lunarStr);
        Log.i("www",""+DateUtils.isToday(calDate.getDate()));
        if (DateUtils.isToday(calDate.getDate())) {
            mCalTab.hide();
        } else {
            mCalTab.show();
        }
        mPresenter.refreshScheList();
    }

    @Override
    public void setPresenter(BasePresenter presenter) {
        mPresenter = (ScheMainContact.Presenter) presenter;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mPresenter.refreshScheList();
    }

    public class CustomFragmentStatePagerAdapter extends FragmentStatePagerAdapter {
        private SparseArray<Fragment> fragments;

        public CustomFragmentStatePagerAdapter(FragmentManager fm) {
            super(fm);
            fragments = new SparseArray<>();
        }

        @Override
        public Fragment getItem(int position) {
            CalDate calDate = mPresenter.getNewDate(position);
            CalFragment fragment = CalFragment.newInstance();
            new CalPresenter((CalContact.OnScheMainPresenter) mPresenter,new CalDataSource(calDate),fragment);
            return fragment;
        }

        @Override
        public int getCount() {
            return 2*POSITION;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            Fragment fragment = (Fragment) super.instantiateItem(container, position);
            fragments.put(position, fragment);
            return fragment;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            super.destroyItem(container, position, object);
            fragments.remove(position);
        }

        public Fragment getFragment(int position) {
            return fragments.get(position);
        }
    }
}
