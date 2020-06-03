package com.ytfu.yuntaifawu.utils;

import android.content.Context;
import android.os.Build;

import androidx.appcompat.app.AlertDialog;

import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;
import com.ytfu.yuntaifawu.R;

import java.util.List;

/**
HEAD
*
*  @Auther  gxy
*
*  @Date    2020/1/9
*
*  @Des
*
*/
public class AndPermissionUtil {

    private static AndPermissionUtil mInstance;
    /**
     * 提示用户去系统设置中授权requestCode
     */
    public static final int REQ_CODE_PERMISSION = 1818;

    public static AndPermissionUtil getInstance() {
        if (mInstance == null) {
            synchronized (AndPermissionUtil.class) {
                mInstance = new AndPermissionUtil();
            }
        }
        return mInstance;
    }

    /**
     * 请求权限
     *
     * @param context             context
     * @param onPermissionGranted 允许权限监听
     * @param permissions         One or more permissions.
     */
    public void requestPermissions(Context context, OnPermissionGranted onPermissionGranted, String... permissions) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            if (null != onPermissionGranted) {
                onPermissionGranted.onPermissionGranted();
            }
            return;
        }
        // 判断是否有权限
        if (AndPermission.hasPermissions(context, permissions)) {
            if (null != onPermissionGranted) {
                onPermissionGranted.onPermissionGranted();
            }
            return;
        }

        AndPermission.with(context)
                .runtime()
                .permission(permissions)
                .onGranted(permissions2 -> {
                    // permission are allowed.
                    if (null != onPermissionGranted) {
                        onPermissionGranted.onPermissionGranted();
                    }
                })
                .onDenied(permissions2 -> {
                    // permission are not allowed.
                    if (AndPermission.hasAlwaysDeniedPermission(context, permissions2)) {
                        // 这些权限被用户总是拒绝。
                        showSettingDialog(context, permissions2);
                    }
                })
                .start();
    }

    /**
     * 请求权限
     *
     * @param context             context
     * @param onPermissionGranted 允许权限监听
     * @param permissions         One or more permission groups.
     */
    public void requestPermissions(Context context, OnPermissionGranted onPermissionGranted, String[]... permissions) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            if (null != onPermissionGranted) {
                onPermissionGranted.onPermissionGranted();
            }
            return;
        }
        // 判断是否有权限
        if (AndPermission.hasPermissions(context, permissions)) {
            if (null != onPermissionGranted) {
                onPermissionGranted.onPermissionGranted();
            }
            return;
        }

        AndPermission.with(context)
                .runtime()
                .permission(permissions)
                .onGranted(permissions2 -> {
                    // permission are allowed.
                    if (null != onPermissionGranted) {
                        onPermissionGranted.onPermissionGranted();
                    }
                })
                .onDenied(permissions2 -> {
                    // permission are not allowed.
                    if (AndPermission.hasAlwaysDeniedPermission(context, permissions2)) {
                        // 这些权限被用户总是拒绝。
                        showSettingDialog(context, permissions2);
                    }
                })
                .start();
    }

    public interface OnPermissionGranted {
        /**
         * 允许权限监听
         */
        void onPermissionGranted();
    }

    /**
     * Display setting dialog.
     */
    private void showSettingDialog(Context context, final List<String> permissions) {
        List<String> permissionNames = Permission.transformText(context, permissions);
        String message = "权限被禁止，请在设置中打开权限\n\n被禁止的权限：" + permissionNames;

        new AlertDialog.Builder(context)
                .setCancelable(false)
                .setTitle("云台法务提示")
                .setMessage(message)
                .setPositiveButton(R.string.setting, (dialog, which) -> setPermission(context))
                .setNegativeButton(R.string.cancel, null)
                .show();
    }

    /**
     * Set permissions.
     */
    private void setPermission(Context context) {
        AndPermission.with(context)
                .runtime()
                .setting()
//                .start(REQ_CODE_PERMISSION);
                .start();
    }
}
