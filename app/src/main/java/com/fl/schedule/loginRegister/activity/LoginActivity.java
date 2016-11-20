package com.fl.schedule.loginRegister.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.animation.LinearInterpolator;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fl.schedule.R;
import com.fl.schedule.home.activity.MainActivity;
import com.fl.schedule.loginRegister.model.bean.LoReDataSouce;
import com.fl.schedule.utils.UiUtils;
import com.fl.schedule.widget.CodeButton;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.smssdk.EventHandler;
import cn.smssdk.OnSendMessageHandler;
import cn.smssdk.SMSSDK;
import okhttp3.ResponseBody;
import rx.Observer;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.layout_login)
    LinearLayout mLayoutLogin;
    @BindView(R.id.num_code)
    CodeButton mNumCode;
    @BindView(R.id.layout_register)
    LinearLayout mLayoutRegister;
    @BindView(R.id.login_bt)
    AppCompatButton mLoginBt;
    @BindView(R.id.register_bt)
    AppCompatButton mRegisterBt;
    @BindView(R.id.to_register)
    TextView mToRegister;
    @BindView(R.id.to_login)
    TextView mToLogin;
    @BindView(R.id.login_psw_til)
    TextInputLayout mLoginPswTil;
    @BindView(R.id.login_name_til)
    TextInputLayout mLoginNameTil;
    @BindView(R.id.register_num_til)
    TextInputLayout mRegisterNumTil;
    @BindView(R.id.register_psw_til)
    TextInputLayout mRegisterPswTil;
    @BindView(R.id.nickName_til)
    TextInputLayout mNickNameTil;
    @BindView(R.id.register_code)
    EditText mRegisterCode;
    private ProgressDialog progressDialog;
    private String number;
    private LoReDataSouce mLoReDataSouce;
    private Observer<ResponseBody> mObserver;
    private String re_psw;
    private String re_username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initView();
        initData();

    }

    private void initView() {
        mLoginBt.setOnClickListener(this);
        mRegisterBt.setOnClickListener(this);
        mToLogin.setOnClickListener(this);
        mToRegister.setOnClickListener(this);
        mNumCode.setOnClickListener(this);
    }

    private void initData() {
        mLoReDataSouce = new LoReDataSouce();
        mObserver = new Observer<ResponseBody>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(ResponseBody responseBody) {
                try {
                    Log.i("aaa", responseBody.string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_bt:
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
//                loginInfoConfirm();
                break;
            case R.id.num_code:
                getVerificationCode();
                break;
            case R.id.to_register:
                showRegister();
                break;
            case R.id.to_login:
                showLogin();
                break;
            case R.id.register_bt:
                VerificationCodeConfirm();
                break;
        }

    }

    private void VerificationCodeConfirm() {
        re_psw = mRegisterPswTil.getEditText().getText().toString().trim();
        re_username = mNickNameTil.getEditText().getText().toString().trim();
        String code = mRegisterCode.getText().toString().trim();
        if (re_username.isEmpty()) {
            mNickNameTil.setError("注册用户名不能为空！");
        } else {
            mNickNameTil.setErrorEnabled(false);
        }
        if (re_psw.isEmpty()) {
            mRegisterPswTil.setError("注册密码不能为空！");
        } else {
            mRegisterPswTil.setErrorEnabled(false);
        }
        if (re_username.isEmpty() || re_psw.isEmpty()) {
            return;
        }
        if (code.isEmpty()) {
            Toast.makeText(this, "请填写验证码", Toast.LENGTH_SHORT).show();
            return;
        }
        SMSSDK.submitVerificationCode("86", number, mRegisterCode.getText().toString().trim());
    }

    private void showLogin() {
        mToRegister.setVisibility(View.VISIBLE);
        mToLogin.setVisibility(View.GONE);
        mLoginBt.setVisibility(View.VISIBLE);
        mRegisterBt.setVisibility(View.GONE);
        ViewCompat.animate(mLayoutLogin)
                .translationX((UiUtils.getScreenWidthPixels(this) - mLayoutLogin.getWidth()) / 2)
                .setInterpolator(new LinearInterpolator())
                .setDuration(400).start();
        ViewCompat.animate(mLayoutRegister)
                .translationX(UiUtils.getScreenWidthPixels(this))
                .setInterpolator(new LinearInterpolator())
                .setDuration(400).start();
    }

    private void showRegister() {
        mToRegister.setVisibility(View.GONE);
        mToLogin.setVisibility(View.VISIBLE);
        mLoginBt.setVisibility(View.GONE);
        mRegisterBt.setVisibility(View.VISIBLE);
        mLayoutRegister.setX(UiUtils.getScreenWidthPixels(this));
        mLayoutRegister.setVisibility(View.VISIBLE);
        ViewCompat.animate(mLayoutLogin)
                .translationX(0 - mLayoutLogin.getWidth())
                .setInterpolator(new LinearInterpolator())
                .setDuration(400).start();

        ViewCompat.animate(mLayoutRegister)
                .translationX((UiUtils.getScreenWidthPixels(this) - mLayoutRegister.getWidth()) / 2)
                .setInterpolator(new LinearInterpolator())
                .setDuration(400).start();
        mLayoutRegister.setX((UiUtils.getScreenWidthPixels(this) - mLayoutRegister.getWidth()) / 2);

    }

    private void loginInfoConfirm() {
        String username = mLoginNameTil.getEditText().getText().toString().trim();
        String password = mLoginPswTil.getEditText().getText().toString().trim();
        if (username.isEmpty()) {
            mLoginNameTil.setError("用户名不能为空！");
        } else {
            mLoginNameTil.setErrorEnabled(false);
        }
        if (password.isEmpty()) {
            mLoginPswTil.setError("密码不能为空！");
        } else {
            mLoginPswTil.setErrorEnabled(false);
        }
        if (username.isEmpty() || password.isEmpty()) {
            return;
        }

        progressDialog = new ProgressDialog(LoginActivity.this, R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("");
        progressDialog.show();
        reqeustLogin(username, password);
    }

    private void getVerificationCode() {
        number = mRegisterNumTil.getEditText().getText().toString().trim();
        if (number.isEmpty()) {
            mRegisterNumTil.setError("请填写注册号码！");
            return;
        } else {
            mRegisterNumTil.setErrorEnabled(false);
        }
        initSMS();
        mNumCode.start();
        SMSSDK.getVerificationCode("86", number, new OnSendMessageHandler() {
            @Override
            public boolean onSendMessage(String s, String s1) {
                return false;
            }
        });
    }

    private void initSMS() {
        SMSSDK.registerEventHandler(new EventHandler() {
            @Override
            public void afterEvent(int event, int result, Object data) {
                super.afterEvent(event, event, event);
                if (result == SMSSDK.RESULT_COMPLETE) {
                    //回调完成
                    if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                        Toast.makeText(LoginActivity.this, "验证成功", Toast.LENGTH_SHORT).show();
//                        registerRequest();
                        //提交验证码成功
                    } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                        //获取验证码成功
                    } else if (event == SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES) {
                        //返回支持发送验证码的国家列表
                    }
                } else {
                    ((Throwable) data).printStackTrace();
                    if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                        Toast.makeText(LoginActivity.this, "验证码有误", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void registerRequest() {
        mLoReDataSouce.postRegister(number, re_username, re_psw).subscribe(mObserver);

    }

    private void reqeustLogin(String name, String password) {
        mLoReDataSouce.postLogin(name, password).subscribe(mObserver);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterAllEventHandler();
    }
}
