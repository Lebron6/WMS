package com.ocean.wms.tools;

import android.content.Context;

import com.ocean.wms.app.WMSAPP;

/**
 * 具体实现sp 
 * Created by James on 2016/10/30.
 */  
public class PreferenceUtils extends BasePreference {  
    private static PreferenceUtils preferenceUtils;

    /** 
     * 需要增加key就在这里新建 
     */  
    //用户名的key  
    private String USER_NAME = "user_name";

    //用户token的key
    private String USER_TOKEN = "user_token";

    //用户ID的key
    private String USER_ID = "user_id";

    //是否登录的key
    private String LOGIN_STATUS = "login_status";


    public void loginOut(){
        userLoginOut();
    }
  
    public void setLoginStatus(boolean isFirst) {
        setBoolean(LOGIN_STATUS,isFirst);
    }

    public boolean getLoginStatus() {
        return getBoolean(LOGIN_STATUS);
    }

    public void setUserID(int id){
        setInt(USER_ID,id);
    }

    public int getUserId(){
        return getInt(USER_ID);
    }

    public String getUserToken() {
        return getString(USER_TOKEN);


    }
    public void setUserToken(String token){
        setString(USER_TOKEN,token);
    }

    public void setUserName(String name) {
        setString(USER_NAME, name);
    }  
  
    public String getUserName() {
        return getString(USER_NAME);  
    }



    private PreferenceUtils(Context context) {
        super(context);
    }
    /**
     * 这里我通过自定义的Application来获取Context对象，所以在获取preferenceUtils时不需要传入Context。
     * @return
     */
    public synchronized static PreferenceUtils getInstance() {
        if (null == preferenceUtils) {
            preferenceUtils = new PreferenceUtils(WMSAPP.getApplication());
        }
        return preferenceUtils;
    }
}