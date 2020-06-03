package com.ytfu.yuntaifawu.utils;


import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import androidx.annotation.ColorInt;
import androidx.core.content.ContextCompat;

import com.ytfu.yuntaifawu.app.App;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Random;

/**
 * @作者 gxy
 * @创建时间 2019/11/9
 * @描述
 */
public class CommonUtil {

    /**
     * 获取Resource对象
     */
    private Resources getResources() {
        return App.getContext().getResources();
    }

    /**
     * 获取drawable资源
     */
    public static Drawable getDrawable(int resId) {
        return ContextCompat.getDrawable(App.getContext(), resId);
    }

    /**
     * 获取字符串资源
     */
    public static String getString(int resId) {
        return App.getContext().getResources().getString(resId);
    }

    /**
     * 获取color资源
     */
    public static int getColor(int resId) {
        return ContextCompat.getColor(App.getContext(), resId);
    }

    /**
     * 获取dimens资源
     * 基于当前DisplayMetrics进行转换，获取指定资源id对应的尺寸。文档里并没说这里返回的就是像素，要注意这个函数的返回值是float，像素肯定是int。
     */
    public static float getDimens(int resId) {
        return App.getContext().getResources().getDimension(resId);
    }

    /**
     * 获取dimens资源
     * 与getDimension()功能类似，不同的是将结果转换为int，并且小数部分四舍五入。
     */
    public static int getDimensionPixelSize(int resId) {
        return App.getContext().getResources().getDimensionPixelSize(resId);
    }

    /**
     * 获取dimens资源
     * 不同的是将结果转换为int，并且偏移转换（offset conversion，函数命名中的offset是这个意思）是直接截断小数位，即取整（其实就是把float强制转化为int，注意不是四舍五入哦）。
     */
    public static int getDimensionPixelOffset(int resId) {
        return App.getContext().getResources().getDimensionPixelOffset(resId);
    }

    /**
     * 获取字符串数组资源
     */
    public static String[] getStringArray(int resId) {
        return App.getContext().getResources().getStringArray(resId);
    }

    /**
     * 将指定的child从它 的父View中移除
     */
    public static void removeSelfFromParent(View child) {
        if (child != null) {
            ViewParent parent = child.getParent();
            if (parent instanceof ViewGroup) {
                ViewGroup group = (ViewGroup) parent;
                //将子View从父View中移除
                group.removeView(child);
            }
        }
    }

    @ColorInt
    public static int generateBeautifulColor() {
        Random random = new Random();
        int r = random.nextInt(150) + 50;
        int g = random.nextInt(150) + 50;
        int b = random.nextInt(150) + 50;
        return Color.rgb(r, g, b);
    }

    /**
     * 格式化数值显示
     */
    public static String doubleFormat(double number, String pattern) {
        BigDecimal decimal = new BigDecimal(number);
        DecimalFormat format = new DecimalFormat(pattern);
        return format.format(decimal);
    }

    /**
     * 关闭软键盘
     */
    public static void hideSoftInput(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        View view = activity.getWindow().peekDecorView();
        if (null != view && null != imm) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    /**
     * 打开软键盘
     */
    public static void openSoftInput(Context context, EditText et) {
        et.requestFocus();
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (null != imm) {
            imm.showSoftInput(et, InputMethodManager.SHOW_IMPLICIT);
        }
    }


}
