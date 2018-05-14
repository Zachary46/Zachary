package com.zlf.demo.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.zlf.demo.model.UserModel;


import java.lang.ref.WeakReference;


public class PrefUtil {

    private static SharedPreferences mSharedPreferences;
    private static SharedPreferences.Editor mEditor;
    private static final String KEY_APPPLICATION_SPF_MAIN = "TB_APPLICATION_SPF_MAIN";
    private static final String KEY_FIRST_LAUNCHING = "KEY_FIRST_LAUNCHING";
    private static final String KEY_SHOW_GUIDE = "KEY_SHOW_GUIDE";
    private static final String KEY_USER_TOKEN = "KEY_USER_TOKEN";
    private static final String KEY_USER_ACCOUNT = "KEY_USER_ACCOUNT";
    private static final String KEY_USER_LOCATION = "KEY_USER_LOCATION";
    private static final String KEY_USER_INFO = "KEY_USER_INFO";
    private static PrefUtil mInstance = null;
    private static WeakReference<Context> mReference;

    private static final String LOGIN = "LOGIN";

    public synchronized static PrefUtil init(Context context) {
        if (null == mInstance) {
            synchronized (PrefUtil.class) {
                if (null == mInstance) {
                    mInstance = new PrefUtil(context.getApplicationContext());
                    mReference = new WeakReference<>(context.getApplicationContext());
                }
            }
        }
        return mInstance;
    }


    public synchronized static PrefUtil getInstance() {
        if (null == mInstance) {
            mInstance = init(mReference.get());
        }
        return mInstance;
    }

    private PrefUtil(Context context) {
        mSharedPreferences = context.getSharedPreferences(KEY_APPPLICATION_SPF_MAIN, Activity.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();
        mEditor.apply();
    }

    public boolean isLogin() {
        return mSharedPreferences.getBoolean(LOGIN, false);
    }

    /**
     * 是否显示过落地页
     *
     * @return
     */
    public boolean isFirstLunching() {
        boolean isFirst = mSharedPreferences.getBoolean(KEY_FIRST_LAUNCHING, true);
        mEditor.putBoolean(KEY_FIRST_LAUNCHING, false).commit();
        return isFirst;
    }

    /**
     * 是否显示引导页
     *
     * @return
     */
    public boolean showGuide() {
        boolean showGuide = mSharedPreferences.getBoolean(KEY_SHOW_GUIDE, true);
        mEditor.putBoolean(KEY_SHOW_GUIDE, false).commit();
        return showGuide;
    }

    public void saveUser(UserModel userModel) {
        mEditor.putString(KEY_USER_INFO, new Gson().toJson(userModel)).commit();
    }
    public void saveToken(String token){
        mEditor.putString(KEY_USER_TOKEN,token).commit();
        setLogin(true);

    }

    /**
     * 用户 TOKEN
     *
     * @return
     */
    public String getToken() {
        return mSharedPreferences.getString(KEY_USER_TOKEN, "");
    }

    /**
     * 用户 TOKEN
     *
     * @return
     */
    public UserModel getUser() {
        String json = mSharedPreferences.getString(KEY_USER_INFO, "");
        if (TextUtils.isEmpty(json)) {
            return new UserModel();
        }
        return new Gson().fromJson(json, UserModel.class);
    }

    public void setLogin(boolean login) {
        mEditor.putBoolean(LOGIN, login).commit();
    }

    /**
     * 用户 用户名
     *
     * @return
     */
    public void saveAccount(String userName) {
        mEditor.putString(KEY_USER_ACCOUNT, userName).commit();
    }
    public String getAccount() {
      return   mSharedPreferences.getString(KEY_USER_ACCOUNT,"");
    }

    public static void destory() {
        mReference.clear();
    }
}
