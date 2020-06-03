package com.ytfu.yuntaifawu.utils;

import android.content.pm.PackageManager;

import com.ytfu.yuntaifawu.app.App;


public class ApkUtil {
    /**
     * 获取当前本地apk的版本
     */
    public static int getVersionCode() {
        int versionCode = 0;
        try {
            //获取软件版本号，对应AndroidManifest.xml下android:versionCode
            versionCode = App.getContext().getPackageManager().
                    getPackageInfo(App.getContext().getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }

    /**
     * 获取版本号名称
     */
    public static String getVersionName() {
        String versionName = "";
        try {
            versionName = App.getContext().getPackageManager().
                    getPackageInfo(App.getContext().getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionName;
    }
}