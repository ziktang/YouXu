package com.fl.schedule.home.model.bean;

/**
 * Created by tctctc on 2016/11/17.
 */

public class UserConfig {
    private UserInfo mUserInfo;
    private static UserConfig sUserConfig;

    private UserConfig(){

    }

    public static UserConfig getInstance(){
        if (sUserConfig==null){
            sUserConfig = new UserConfig();
        }
        return sUserConfig;
    }

    public UserInfo getUserInfo() {
        return mUserInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        mUserInfo = userInfo;
    }
}
