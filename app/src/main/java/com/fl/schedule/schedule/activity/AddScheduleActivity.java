package com.fl.schedule.schedule.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fl.schedule.R;
import com.fl.schedule.base.contact.BasePresenter;
import com.fl.schedule.schedule.adapter.LinearTextAdapter;
import com.fl.schedule.schedule.contact.AddScheContact;
import com.fl.schedule.schedule.fragment.ScheduleFragment;
import com.fl.schedule.schedule.model.bean.Schedule;
import com.fl.schedule.schedule.model.dataSource.AddScheDataSource;
import com.fl.schedule.schedule.presenter.ScheAddPresenter;
import com.fl.schedule.widget.behaivor.ListRecyclerAdapter;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.zcw.togglebutton.ToggleButton;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.qqtheme.framework.picker.DatePicker;
import cn.qqtheme.framework.picker.DateTimePicker;

public class AddScheduleActivity extends AppCompatActivity implements View.OnClickListener, AddScheContact.View {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.all_day_toggle)
    ToggleButton mAllDayToggle;
    //2016-09-22 14:20:00
    String startTime;
    String endTime;
    @BindView(R.id.add_sche_cancel)
    TextView mAddScheCancel;
    @BindView(R.id.startTimeTv)
    TextView mStartTimeTv;
    @BindView(R.id.startTime)
    RelativeLayout mStartTime;
    @BindView(R.id.endTimeTv)
    TextView mEndTimeTv;
    @BindView(R.id.endTime)
    RelativeLayout mEndTime;
    @BindView(R.id.warnTimeTv)
    TextView mWarnTimeTv;
    @BindView(R.id.warnTime)
    RelativeLayout mWarnTime;
    @BindView(R.id.place)
    EditText mPlace;
    @BindView(R.id.note)
    EditText mNote;
    @BindView(R.id.title)
    EditText mTitle;
    @BindView(R.id.done)
    TextView mDone;
    @BindView(R.id.delete)
    TextView mDelete;
    @BindView(R.id.sche_edit_status)
    LinearLayout mScheEditStatus;
    private DateTimePicker picker;
    private long id;
    private AddScheContact.Presenter mPresenter;
    private int status = 0;
    private BottomSheetDialog mDialog;
    private BottomSheetBehavior<View> behavior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_add_schedule);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        initData();
        initView();
    }

    private void initData() {
        id = getIntent().getLongExtra("id", 0);
        String date = getIntent().getStringExtra("date");
        if (id != 0) {
            mScheEditStatus.setVisibility(View.VISIBLE);
        } else {
            mScheEditStatus.setVisibility(View.GONE);
        }
        new ScheAddPresenter(new AddScheDataSource(date), this);
    }


    private void initView() {
        mAddScheCancel.setOnClickListener(this);
        mStartTime.setOnClickListener(this);
        mEndTime.setOnClickListener(this);
        mWarnTime.setOnClickListener(this);
        mDelete.setOnClickListener(this);
        mDone.setOnClickListener(this);
        mAllDayToggle.setOnToggleChanged(new ToggleButton.OnToggleChanged() {
            @Override
            public void onToggle(boolean on) {
                mPresenter.allDay(on);
            }
        });
        mPresenter.init(id);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_schedule, menu);
        final MenuItem menuItem = menu.getItem(0);
        menuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
//                Snackbar.make(mToolbar, "添加成功", Snackbar.LENGTH_SHORT).show();
//                Log.i("aaa", "startTime:" + startTime);
//                Log.i("aaa", "endTime:" + endTime);
//                Log.i("aaa", "mTitle:" + mTitle.getText());
//                Log.i("aaa", "mPlace:" + mPlace.getText());
//                Log.i("aaa", "mNote:" + mNote.getText());
                if (mTitle.getText().toString().trim().isEmpty()) {
                    Toast.makeText(AddScheduleActivity.this, "请填写日程描述", Toast.LENGTH_SHORT).show();
                } else {
                    mPresenter.commit(mTitle.getText().toString()
                            , mStartTimeTv.getText().toString()
                            , mEndTimeTv.getText().toString()
                            , mWarnTimeTv.getText().toString()
                            , mPlace.getText().toString()
                            , mNote.getText().toString());
                }
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    public void setPresenter(AddScheContact.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onClick(View v) {
        if (status == 1 && R.id.sche_edit_status != v.getId())
            return;
        switch (v.getId()) {
            case R.id.add_sche_cancel:
                finish();
                break;
            case R.id.startTime:
                mPresenter.timeChange(mStartTimeTv.getText().toString(), R.id.startTimeTv);
                break;
            case R.id.endTime:
                mPresenter.timeChange(mStartTimeTv.getText().toString(), R.id.endTimeTv);
                break;
            case R.id.warnTime:
                mPresenter.warnTimeChange(mWarnTimeTv.getText().toString());
                break;
            case R.id.done:
                mPresenter.doneToogle();
                break;
            case R.id.delete:
                mPresenter.delete();
                break;
        }
    }

    @Override
    public void show(Schedule schedule) {
        mTitle.setText(schedule.getDescription());
        mStartTimeTv.setText(schedule.getStartTime());
        mEndTimeTv.setText(schedule.getEndTime());
        mWarnTimeTv.setText(schedule.getWarnTime());
        mPlace.setText(schedule.getPlace());
        mNote.setText(schedule.getNote());
    }

    @Override
    public void allDayShow(String[] time) {
        mStartTimeTv.setText(time[0]);
        mEndTimeTv.setText(time[1]);
    }

    @Override
    public void commitMessge(String message, int code) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        if (code == mPresenter.RESULT_OK) {
            setResult(ScheduleFragment.ADD_SCHE_CODE);
            finish();
        }
    }

    @Override
    public void datePicker(int year, int month, int day, final int viewId) {
        DatePicker picker = new DatePicker(this, DatePicker.YEAR_MONTH_DAY);
        picker.setRangeStart(year, month, day);//开始范围
        picker.setRangeEnd(year + 1, month, day);//结束范围
        picker.setOnDatePickListener(new DatePicker.OnYearMonthDayPickListener() {
            @Override
            public void onDatePicked(String year, String month, String day) {
                String time = year + "-" + month + "-" + day;
                Snackbar.make(mToolbar, startTime, Snackbar.LENGTH_SHORT).show();
                if (viewId == R.id.startTimeTv) {
                    mStartTimeTv.setText(time);
                    if (time.compareTo(mEndTimeTv.getText().toString()) > 0) {
                        mEndTimeTv.setText(time);
                    }
                } else
                    mEndTimeTv.setText(time);
            }
        });
        picker.show();
    }

    @Override
    public void dateTimePicker(int year, int month, int day, int hour, int minute, final int viewId) {
        picker = new DateTimePicker(this, DateTimePicker.HOUR_OF_DAY);
        picker.setRange(year, year + 1);
        picker.setSelectedItem(year, month, day, hour, minute);
        picker.setOnDateTimePickListener(new DateTimePicker.OnYearMonthDayTimePickListener() {
            @Override
            public void onDateTimePicked(String year, String month, String day, String hour, String minute) {
                String time = year + "-" + month + "-" + day + " " + hour + ":" + minute;
                Snackbar.make(mToolbar, time, Snackbar.LENGTH_SHORT).show();
                if (viewId == R.id.startTimeTv) {
                    mStartTimeTv.setText(time);
                    if (time.compareTo(mEndTimeTv.getText().toString()) > 0) {
                        mEndTimeTv.setText(time);
                    }
                } else
                    mEndTimeTv.setText(time);
            }
        });
        picker.show();
    }

    @Override
    public void warnTimePicker(String[] warnTimes) {
        if (mDialog == null) {
            creatBottomList(warnTimes);
        }
        if (mDialog.isShowing()) {
            mDialog.dismiss();
        } else {
            mDialog.show();
        }
    }

    @Override
    public void setTimeToggle(boolean on) {
        if (on)
            mAllDayToggle.setToggleOn();
        else
            mAllDayToggle.toggleOff();
    }

    @Override
    public void OnDoneToggle(int res) {
        Drawable drawable = getResources().getDrawable(res);
        drawable.setBounds(0,0,drawable.getMinimumWidth(),drawable.getMinimumHeight());
        mDone.setCompoundDrawables(drawable, null, null, null);
    }

    @Override
    public void Ondelete(String message, int code) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        if (code == mPresenter.RESULT_OK) {
            setResult(ScheduleFragment.ADD_SCHE_CODE);
            finish();
        }
    }

    @Override
    public void setPresenter(BasePresenter presenter){
        mPresenter = (AddScheContact.Presenter) presenter;
    }

    private void creatBottomList(final String[] strings) {
        mDialog = new BottomSheetDialog(this);
        View view = LayoutInflater.from(this).inflate(R.layout.warm_time_dialog, null);
        mDialog.setContentView(view);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        LinearTextAdapter adapter = new LinearTextAdapter(this, strings);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                mWarnTimeTv.setText(strings[position]);
                mDialog.dismiss();
            }
        });
        setCallBack();
    }

    private void setCallBack() {
        View view = mDialog.getDelegate().findViewById(android.support.design.R.id.design_bottom_sheet);
        behavior = BottomSheetBehavior.from(view);
        behavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                    mDialog.dismiss();
                    behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
            }
        });
    }
}
