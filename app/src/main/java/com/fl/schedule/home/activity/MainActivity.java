package com.fl.schedule.home.activity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.fl.schedule.R;
import com.fl.schedule.base.activity.BaseActivity;
import com.fl.schedule.daily.activity.DailyMainActivity;
import com.fl.schedule.home.model.bean.TabEntity;
import com.fl.schedule.home.model.bean.UserConfig;
import com.fl.schedule.home.model.bean.UserInfo;
import com.fl.schedule.msg.fragment.MsgFragment;
import com.fl.schedule.plan.fragment.PlanFragment;
import com.fl.schedule.schedule.fragment.ScheduleFragment;
import com.fl.schedule.utils.UiUtils;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.fl.schedule.R.id.fra;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    @BindView(fra)
    FrameLayout mFra;
    @BindView(R.id.nav)
    NavigationView mNav;
    @BindView(R.id.person)
    com.fl.schedule.widget.CircleImageView mPerson;
    @BindView(R.id.drawer)
    DrawerLayout mDrawer;
    @BindView(R.id.bottom_tab)
    CommonTabLayout mBottomTab;
    @BindView(R.id.toolbar_add)
    ImageView mToolbarAdd;
    @BindView(R.id.add_menu)
    ImageView mAddMenu;
    private ArrayList<Fragment> mFragments;
    private ArrayList<CustomTabEntity> mTabEntities;
    private String[] mTabTitles = {"日程", "计划", "", "idea", "消息"};
    private int[] mTabIconNorRes = {R.drawable.schedule_normal, R.drawable.plan_normal,
            0, R.drawable.idea_normal, R.drawable.message_normal};
    private int[] mTabIconSelRes = {R.drawable.schedule_select, R.drawable.plan_select,
            0, R.drawable.idea_select, R.drawable.message_select};
    private PopupWindow popupWindow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
        intiData();

    }

    private void intiData() {

        UserInfo userInfo = new UserInfo(111111,"testName","18515465847","fsdhfjeuu845hj3hhjdusheue");
        UserConfig.getInstance().setUserInfo(userInfo);
        //此处分别对应模块为,日程，计划，占位，idea，消息,必要时可以屏蔽他人模块独自测试
        mFragments = new ArrayList<>();
        mFragments.add(new ScheduleFragment());
        mFragments.add(new PlanFragment());
        mFragments.add(new Fragment());//这是中间加号处占位的，没有实际用处...
        mFragments.add(new PlanFragment());
        mFragments.add(new MsgFragment());

        mTabEntities = new ArrayList<>();
        for (int i = 0; i < mFragments.size(); i++) {
            mTabEntities.add(new TabEntity(mTabTitles[i], mTabIconSelRes[i], mTabIconNorRes[i]));
        }

        mBottomTab.setTabData(mTabEntities, this, R.id.fra, mFragments);
        Log.i("aaa", "getChildCount:" + mBottomTab.getChildCount());
    }

    public void showMoreMenu(View v) {
        View view = View.inflate(this, R.layout.more_menu, null);
        PopupWindow popupWindow = new PopupWindow(view, UiUtils.dipToPx(this, 150), LinearLayout.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0));
        popupWindow.setFocusable(true);
        popupWindow.setAnimationStyle(R.style.more_popwin_anim_style);
        popupWindow.getContentView().measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        int i = popupWindow.getContentView().getMeasuredWidth();
        //设置背景半透明
        backgroundAlpha(0.8f);
        //点击空白位置，popupwindow消失的事件监听，这时候让背景恢复正常
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1.0f);
            }
        });
        popupWindow.showAsDropDown(v, -UiUtils.dipToPx(this, 10) - i, 0);
    }

    public void showBottomMenu(View v) {
        View view = View.inflate(this, R.layout.add_menu, null);
        popupWindow = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0));
        popupWindow.setFocusable(true);
        popupWindow.setAnimationStyle(R.style.add_popwin_anim_style);
        ImageView imageView = (ImageView) view.findViewById(R.id.close_add);
        LinearLayout ll_schedule = (LinearLayout) view.findViewById(R.id.add_menu_schedule);
        LinearLayout ll_daily = (LinearLayout) view.findViewById(R.id.add_menu_daily);
        LinearLayout ll_plan = (LinearLayout) view.findViewById(R.id.add_menu_plan);
        imageView.setOnClickListener(this);
        ll_plan.setOnClickListener(this);
        ll_daily.setOnClickListener(this);
        ll_schedule.setOnClickListener(this);
        popupWindow.showAtLocation(v, Gravity.BOTTOM, 0, 0);
    }

    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha; // 0.0-1.0
        getWindow().setAttributes(lp);
    }

    private void initView() {
        mNav.setItemIconTintList(null);//设置菜单图标恢复本来的颜色
        mNav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                item.setChecked(true);
                mDrawer.closeDrawers();
                switch (item.getItemId()) {
                    case R.id.diary:
//                        Snackbar.make(mNav, "" + item.getTitle(), Snackbar.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, DailyMainActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.bind_phone:
                        Snackbar.make(mNav, "" + item.getTitle(), Snackbar.LENGTH_SHORT).show();
                        break;
                    case R.id.scan:
                        Snackbar.make(mNav, "" + item.getTitle(), Snackbar.LENGTH_SHORT).show();
                        break;
                    case R.id.share:
                        Snackbar.make(mNav, "" + item.getTitle(), Snackbar.LENGTH_SHORT).show();
                        break;
                    case R.id.feedback:
                        Snackbar.make(mNav, "" + item.getTitle(), Snackbar.LENGTH_SHORT).show();
                        break;
                    case R.id.setup:
                        Snackbar.make(mNav, "" + item.getTitle(), Snackbar.LENGTH_SHORT).show();
                        break;
                    case R.id.exit:
                        Snackbar.make(mNav, "" + item.getTitle(), Snackbar.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });

        mPerson.setOnClickListener(this);
        mToolbarAdd.setOnClickListener(this);
        mAddMenu.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.person:
                mDrawer.openDrawer(GravityCompat.START);
                break;
            case R.id.toolbar_add:
                showMoreMenu(mToolbarAdd);
                break;
            case R.id.add_menu:
                showBottomMenu(mToolbarAdd);
                break;
            case R.id.close_add:
                popupWindow.dismiss();
                break;
            case R.id.add_menu_schedule:
                popupWindow.dismiss();
                Toast.makeText(this,"写个新日程", Toast.LENGTH_SHORT).show();
                break;
            case R.id.add_menu_plan:
                popupWindow.dismiss();
                Toast.makeText(this,"写个新计划", Toast.LENGTH_SHORT).show();
                break;
            case R.id.add_menu_daily:
                popupWindow.dismiss();
                Toast.makeText(this,"写个新日记", Toast.LENGTH_SHORT).show();
                break;

        }
    }
}
