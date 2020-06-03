package com.ytfu.yuntaifawu.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.orhanobut.logger.Logger;
import com.ytfu.yuntaifawu.app.App;

import java.io.File;

/**
 * @author gxy
 * @description SharedPreferences工具类
 */
public class SpUtil {

    private static String CONFIG = "ytfu_sp";

    public static String getConfigName() {
        return CONFIG;
    }

    /**
     * 存储boolean类型的属性
     */
    public static void putBoolean(String key, boolean value) {
        SharedPreferences sp = App.getContext().getSharedPreferences(CONFIG, 0);
        sp.edit().putBoolean(key, value).apply();
    }

    /**
     * 取得boolean类型的属性值
     */
    public static boolean getBoolean(String key, boolean defValue) {
        SharedPreferences sp = App.getContext().getSharedPreferences(CONFIG, 0);
        return sp.getBoolean(key, defValue);
    }


    /**
     * 存储String类型的属性
     */
    public static void putString(String key, String value) {
        SharedPreferences sp = App.getContext().getSharedPreferences(CONFIG, 0);
        sp.edit().putString(key, value).apply();
    }

    /**
     * 取得String类型的属性值
     */
    public static String getString(String key, String defValue) {
        SharedPreferences sp = App.getContext().getSharedPreferences(CONFIG, 0);
        return sp.getString(key, defValue);
    }


    /**
     * 存储int类型的属性
     */
    public static void putInteger(String key, int value) {
        SharedPreferences sp = App.getContext().getSharedPreferences(CONFIG, 0);
        sp.edit().putInt(key, value).apply();
    }

    /**
     * 取得int类型的属性值
     */
    public static int getInteger(String key, int defValue) {
        SharedPreferences sp = App.getContext().getSharedPreferences(CONFIG, 0);
        return sp.getInt(key, defValue);
    }


    /**
     * 存储float类型的属性
     */
    public static void putFloat(String key, float value) {
        SharedPreferences sp = App.getContext().getSharedPreferences(CONFIG, 0);
        sp.edit().putFloat(key, value).apply();
    }

    /**
     * 取得float类型的属性
     */
    public static float getFloate(String key, float defValue) {
        SharedPreferences sp = App.getContext().getSharedPreferences(CONFIG, 0);
        return sp.getFloat(key, defValue);
    }


    /**
     * 删除指定key的数据
     */
    public static void removeSpKey(String key) {
        SharedPreferences sp = App.getContext().getSharedPreferences(CONFIG, 0);
        sp.edit().remove(key).apply();
    }

    /**
     * 清除所有数据
     */
    public static void clearSp() {
        SharedPreferences sp = App.getContext().getSharedPreferences(CONFIG, Context.MODE_PRIVATE);
        sp.edit().clear().commit();
        File file = new File("/data/data/" + App.getContext().getPackageName().toString() + "/shared_prefs", CONFIG);
        if (file.exists()) {
            file.delete();
            ToastUtil.showCenterToast("删除成功");
        }
    }


    private static void deleteFilesByDirectory(File directory) {
        if (directory != null && directory.exists() && directory.isDirectory()) {
            for (File item : directory.listFiles()) {
                Logger.e(item.getAbsolutePath().toString() + " exists=" + item.exists());
                item.delete();
                Logger.e(item.getAbsolutePath().toString() + " exists=" + item.exists());
            }

        }
    }
}
