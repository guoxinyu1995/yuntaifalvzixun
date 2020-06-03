package com.ytfu.yuntaifawu.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;

import com.ytfu.yuntaifawu.app.App;

/**
 *
 * @作者  gxy
 *
 * @创建时间  2019/11/9
 *
 * @描述  单位转换工具类
 */
public class DensityUtil {
    /**
     * 把dp转换为像素
     */
    public static int dip2px(float dpValue) {
        final float scale = App.getContext().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5);
    }

    /**
     * 把像素转换为dp
     */
    public static int px2dip(float pxValue) {
        final float scale = App.getContext().getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     */
    public static int sp2px(float spValue) {
        final float fontScale = App.getContext().getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * 获取屏幕尺寸
     */
    public static Point getScreenSize(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        assert windowManager != null;
        Display display = windowManager.getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        return point;
    }

    /**
     * 获得屏幕宽度
     */
    public static int getScreenWidth() {
        WindowManager windowManager = (WindowManager) App.getContext().getSystemService(Context.WINDOW_SERVICE);
        assert windowManager != null;
        Display display = windowManager.getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        return point.x;
    }

    /**
     * 获得屏幕高度
     */
    public static int getScreenHeight() {
        WindowManager windowManager = (WindowManager) App.getContext().getSystemService(Context.WINDOW_SERVICE);
        assert windowManager != null;
        Display display = windowManager.getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        return point.y;
    }

    /**
     * 获取系统状态栏高度
     */
    public static int getStatusBarHeight() {
        int result = 0;
        int resourceId = App.getContext().getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = App.getContext().getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    /**
     * 改变activity背景透明度
     * 1为全透明，值越小越暗
     */
    public static void changeActivityBg(Activity activity, float f) {
        if (null == activity) {
            return;
        }
        WindowManager.LayoutParams params = activity.getWindow().getAttributes();
        params.alpha = f;
        activity.getWindow().setAttributes(params);
    }
}
