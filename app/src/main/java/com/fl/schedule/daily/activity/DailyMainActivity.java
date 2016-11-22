package com.fl.schedule.daily.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fl.schedule.R;
import com.fl.schedule.app.MyApplication;
import com.fl.schedule.base.activity.BaseActivity;
import com.fl.schedule.base.contact.BasePresenter;
import com.fl.schedule.daily.adapter.DailyListAdapter;
import com.fl.schedule.daily.contact.DailyMainContact;
import com.fl.schedule.daily.model.bean.Daily;
import com.fl.schedule.daily.model.dataSource.DailyMainSource;
import com.fl.schedule.daily.presenter.DailyMainPresenter;
import com.fl.schedule.utils.FontUtils;
import com.fl.schedule.utils.UiUtils;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.decoration.SpaceDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DailyMainActivity extends BaseActivity implements DailyMainContact.View, View.OnClickListener {

    @BindView(R.id.daily_toolbar)
    Toolbar mDailyToolbar;
    @BindView(R.id.recycle_daily)
    EasyRecyclerView mRecycleDaily;
    @BindView(R.id.date)
    TextView mDate;
    @BindView(R.id.add_daily)
    FloatingActionButton mAddDaily;
    private DailyListAdapter mAdapter;
    private DailyMainContact.Presenter mPresenter;
    private List<Daily> mDailyList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_daily_main);
        ButterKnife.bind(this);
        new DailyMainPresenter(this, new DailyMainSource());
        mPresenter.init();
    }

    @Override
    public void initView() {
        setSupportActionBar(mDailyToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mRecycleDaily.setLayoutManager(new StaggeredGridLayoutManager(2, LinearLayout.VERTICAL));
        mDailyList = new ArrayList<>();
        mAdapter = new DailyListAdapter(this, mDailyList);
        mRecycleDaily.setAdapter(mAdapter);
        mRecycleDaily.setEmptyView(R.layout.empty_view);
        mRecycleDaily.addItemDecoration(new SpaceDecoration(UiUtils.dipToPx(this, 10)));

        mAddDaily.setOnClickListener(this);
    }

    @Override
    public void showList(List<Daily> dailyList) {
        mDailyList.clear();
        mAdapter.addAll(mDailyList);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void initData(String date) {
        mDate.setText(date);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.daily_main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_daily:
                Toast.makeText(this, "添加", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.search_bt:
                Toast.makeText(this, "搜索", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.book_daily:
                Toast.makeText(this, "文集", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.manny_delete:
                Toast.makeText(this, "批量删除", Toast.LENGTH_SHORT).show();
                return true;
            case android.R.id.home:
                finish();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void setPresenter(BasePresenter presenter) {
        mPresenter = (DailyMainContact.Presenter) presenter;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.egg_daily:
                Toast.makeText(this, "彩蛋", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
