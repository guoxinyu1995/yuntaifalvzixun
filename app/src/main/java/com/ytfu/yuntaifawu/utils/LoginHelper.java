package com.ytfu.yuntaifawu.utils;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;

import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.orhanobut.logger.Logger;
import com.ytfu.yuntaifawu.app.AppConstant;
import com.ytfu.yuntaifawu.base.BaseActivity;
import com.ytfu.yuntaifawu.ui.login.activity.LoginCodeActivity;

import org.greenrobot.eventbus.EventBus;

/**
 * @Auther gxy
 * @Date 2020/1/9
 * @Des
 */
public class LoginHelper {

    private static LoginHelper instance = null;
    private final int MAX_HX_LOGIN_COUNT = 3;
    private int hxLoginCount;

    public synchronized static LoginHelper getInstance() {
        if (instance == null) {
            instance = new LoginHelper();
        }
        return instance;
    }

    //判断环信是否登录
    public void loginSuccess(String uid, String pwd) {
        if (!TextUtils.isEmpty(uid) && !TextUtils.isEmpty(pwd)) {
            EMClient.getInstance().login(uid, pwd, new EMCallBack() {
                @Override
                public void onSuccess() {
                    EMClient.getInstance().groupManager().loadAllGroups();
                    EMClient.getInstance().chatManager().loadAllConversations();
                    DemoHelper.getInstance().getUserProfileManager().asyncGetCurrentUserInfo();
                    Log.d("main", "登录聊天服务器成功！");
                }

                @Override
                public void onError(int i, String s) {
                    Logger.e("22code ---> " + i + ",,, msg ---> " + s);
                    Log.d("main", "登录聊天服务器失败！");
                }

                @Override
                public void onProgress(int i, String s) {

                }
            });
        }
    }

    public interface OnEMLoginCallback {
        void onSuccess();

        void onFail(int code, String msg);
    }
    //判断环信是否登录
    public void loginSuccess(String uid, String pwd, OnEMLoginCallback callback) {
        if (!TextUtils.isEmpty(uid) && !TextUtils.isEmpty(pwd)) {
            EMClient.getInstance().login(uid, pwd, new EMCallBack() {
                @Override
                public void onSuccess() {
                    EMClient.getInstance().groupManager().loadAllGroups();
                    EMClient.getInstance().chatManager().loadAllConversations();
                    DemoHelper.getInstance().getUserProfileManager().asyncGetCurrentUserInfo();
                    Log.d("main", "登录聊天服务器成功！");
                    callback.onSuccess();
                }

                @Override
                public void onError(int i, String s) {
                    callback.onFail(i,s);
                    Logger.e("22code ---> " + i + ",,, msg ---> " + s);
                    Log.d("main", "登录聊天服务器失败！");
                }

                @Override
                public void onProgress(int i, String s) {

                }
            });
        }
    }
//    public <T extends BaseActivity> void loginSuccess(T activity, LoginBean bean) {
//        if (null != activity) {
//            SpUtil.putString(AppConstant.TOKEN, bean.getToken());
////            SpUtil.putString(AppConstant.MOBILE, bean.getMobile());
////            SpUtil.putString(AppConstant.USERNAME, bean.getUser_name());
////            SpUtil.putString(AppConstant.USER_PORTRAIT, bean.getThumb());
////            SpUtil.putString(AppConstant.OPEN_MID, bean.getOpen_mid());
//
//            // 用户类型：1 普通用户  2 规划师
//            // 2.判断是否是规划师
//            if (AppConstant.TWO.equals(bean.getUser_type())) {
//                // 2.1保存规划师类型
//                SpUtil.putInteger(AppConstant.PLANNER_TYPE, Integer.parseInt(bean.getPlanner_type()));
//            } else {
//                // 2.2默认规划师类型为普通
//                SpUtil.putInteger(AppConstant.PLANNER_TYPE, AppConstant.PLANNER_COMMON);
//            }
//
//            // APP登录成功通知
//            EventBus.getDefault().postSticky(new MessageEvent(AppConstant.LOGIN_SUCCESS));
//
//            if (!TextUtils.isEmpty(bean.getHx_username()) && !TextUtils.isEmpty(bean.getHx_password())) {
//                DemoDBManager.getInstance().closeDB();
//                DemoHelper.getInstance().setCurrentUserName(bean.getHx_username());
//                EMClient.getInstance().login(bean.getHx_username(), bean.getHx_password(), new EMCallBack() {
//                    //回调
//                    @Override
//                    public void onSuccess() {
//                        activity.runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                SpUtil.putString(AppConstant.USERHXNAME, bean.getHx_username());
//                                EMClient.getInstance().groupManager().loadAllGroups();
//                                EMClient.getInstance().chatManager().loadAllConversations();
//                                DemoHelper.getInstance().getUserProfileManager().asyncGetCurrentUserInfo();
//                                EventBus.getDefault().postSticky(new MessageEvent(AppConstant.LOGINSUCCESS));
//                                Log.d("main", "登录聊天服务器成功！");
//                            }
//                        });
//
//                    }
//
//                    @Override
//                    public void onProgress(int progress, String status) {
//
//                    }
//
//                    @Override
//                    public void onError(int code, String message) {
//                        Log.d("main", "登录聊天服务器失败！" + code + "-=-=-=-=-" + message);
//                        SpUtil.putString(AppConstant.USERHXNAME, "");
//                        EMClient.getInstance().chatManager().loadAllConversations();
//                        DemoHelper.getInstance().setCurrentUserName("");
//                        DemoHelper.getInstance().getUserProfileManager().asyncGetCurrentUserInfo();
//                    }
//                });
//            }
//        }
//        // 设置JPush别名
//        if (!SpUtil.getBoolean(AppConstant.JIGUANG_ALIAS, false)) {
////            String alias = StringUtil.getStringRandom(32);
//            setAlias(bean.getToken());
//        }
//    }

    /*public void loginSuccess(LoginBean bean, BaseActivity activity) {
        this.appLogin(bean, activity, null);
    }

    public void loginSuccess(LoginBean bean, BaseActivity activity, Boolean notLogin) {
        this.appLogin(bean, activity, notLogin);
    }

    *//**
     * App登录
     *//*
    private void appLogin(LoginBean bean, BaseActivity activity, Boolean notLogin) {
        SpUtil.putString(AppConstant.TOKEN, bean.getToken());

        // 用户类型：1 普通用户  2 规划师
        // 2.判断是否是规划师
        if (AppConstant.TWO.equals(bean.getUser_type())) {
            // 2.1保存规划师类型
            SpUtil.putInteger(AppConstant.PLANNER_TYPE, Integer.parseInt(bean.getPlanner_type()));
        } else {
            // 2.2默认规划师类型为普通
            SpUtil.putInteger(AppConstant.PLANNER_TYPE, AppConstant.PLANNER_COMMON);
        }

        hxLogin(bean, activity, notLogin);

        // 设置JPush别名
        *//*if (!SpUtil.getBoolean(AppConstant.JIGUANG_ALIAS, false)) {
            String alias = StringUtil.getStringRandom(32);
            setAlias(alias);
        }*//*
    }

    *//**
     * 环信登录
     *//*
    private void hxLogin(LoginBean bean, BaseActivity activity, Boolean notLogin) {
        if (TextUtils.isEmpty(bean.getHx_username()) || TextUtils.isEmpty(bean.getHx_password())) {
            Logger.d("main", "username or password is null or empty!");

            appLoginSuccess(activity, notLogin);
            ToastUtil.showToast("登录聊天服务器失败！");
            return;
        }

        activity.showWaitingDialog("登录聊天服务器...");
        DemoDBManager.getInstance().closeDB();
        DemoHelper.getInstance().setCurrentUserName(bean.getHx_username());
        EMClient.getInstance().login(bean.getHx_username(), bean.getHx_password(), new EMCallBack() {
            //回调
            @Override
            public void onSuccess() {
                Log.d("main", "登录聊天服务器成功！");

                SpUtil.putString(AppConstant.USERHXNAME, bean.getHx_username());
                EMClient.getInstance().groupManager().loadAllGroups();
                EMClient.getInstance().chatManager().loadAllConversations();
                DemoHelper.getInstance().getUserProfileManager().asyncGetCurrentUserInfo();
                // 环信登录成功
                EventBus.getDefault().postSticky(new MessageEvent(AppConstant.LOGINSUCCESS));

                appLoginSuccess(activity, notLogin);
            }

            @Override
            public void onProgress(int progress, String status) {

            }

            @Override
            public void onError(int code, String message) {
                Log.d("main", "登录聊天服务器失败！" + code + "-=-=-=-=-" + message);

                if (hxLoginCount++ < MAX_HX_LOGIN_COUNT) {
                    hxLogin(bean, activity, notLogin);
                } else {
                    SpUtil.putString(AppConstant.USERHXNAME, "");
                    EMClient.getInstance().chatManager().loadAllConversations();
                    DemoHelper.getInstance().setCurrentUserName("");
                    DemoHelper.getInstance().getUserProfileManager().asyncGetCurrentUserInfo();
                    ToastUtil.showToast("登录聊天服务器失败！");

                    appLoginSuccess(activity, notLogin);
                }
            }
        });
    }

    *//**
     * App登录成功
     *//*
    private void appLoginSuccess(BaseActivity activity, Boolean notLogin) {
        activity.hideWaitingDialog();

        // APP登录成功
        EventBus.getDefault().postSticky(new MessageEvent(AppConstant.LOGIN_SUCCESS));

        if (null != notLogin) {
            // 登录页登录
            if (!notLogin) {
                App.getInstance().finishActivity(MainActivity.class);
                Intent intent = new Intent(activity, MainActivity.class);
                activity.startActivity(intent);
            }
            activity.finish();
        } else {
            // 注册成功自动登录
            App.getInstance().finishActivity(LoginActivity.class);
            Intent intent = new Intent(activity, AuthenticateActivity.class);
            activity.startActivity(intent);
            activity.finish();
        }
    }*/

    /**
     * 退出登录
     *
     * @param activity 继承了{@link BaseActivity}的activity or null
     */
    public <T extends BaseActivity> void logout(T activity) {
        clearCache();
        EventBus.getDefault().post(new MessageEvent(AppConstant.LOGOUT));

        /*
         * 使用第三方推送时需要在退出登录时解绑设备 token，
         * 调用EMClient#getInstance()#logout(true)或者EMClient#getInstance()#logout(true,callback)方法，
         * 如果是被踢的情况下，则要求设置为 false。
         * */
        activity.runOnUiThread(() -> {
            if (!activity.isDestroyed()) {
                activity.hideWaitingDialog();
            }
            Intent intent = new Intent(activity, LoginCodeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); //关键的一句，将新的activity置为栈顶
            activity.startActivity(intent);
            activity.finish();
        });

        if (null != activity) {
            EMClient.getInstance().logout(true, new EMCallBack() {
                @Override
                public void onSuccess() {
                    activity.runOnUiThread(() -> {
                        if (!activity.isDestroyed()) {
                            activity.hideWaitingDialog();
                        }
                        activity.finish();
                    });

                }

                @Override
                public void onError(int i, String s) {
                    activity.runOnUiThread(() -> {
                        if (!activity.isDestroyed()) {
                            activity.hideWaitingDialog();
                        }
                        activity.finish();
                        Logger.e("logout:onError" + i + s);
                    });
                }

                @Override
                public void onProgress(int i, String s) {
                    activity.runOnUiThread(() -> {
                        if (!activity.isDestroyed()) {
                            activity.hideWaitingDialog();
                        }
                        activity.finish();
                        Logger.e("logout:onProgress" + i + s);
                    });
                }
            });
        } else {
            // 账号被踢的情况
            EMClient.getInstance().logout(false);
        }
    }

    /**
     * 清除必要 清除/重置 的缓存数据
     */
    private void clearCache() {
        SpUtil.putString(AppConstant.UID,"");
        SpUtil.putString(AppConstant.SHENFEN,"");
        /*SpUtil.putString(AppConstant.TOKEN, "");
        SpUtil.putString(AppConstant.MOBILE, "");
        SpUtil.putString(AppConstant.USERNAME, "");
        SpUtil.putString(AppConstant.USERHXNAME, "");
        SpUtil.putString(AppConstant.USER_PORTRAIT, "");
        SpUtil.putBoolean(AppConstant.JIGUANG_ALIAS, false);
        SpUtil.putBoolean(AppConstant.NOTIFICATION_TIPS, false);
        SpUtil.putInteger(AppConstant.PLANNER_TYPE, AppConstant.PLANNER_COMMON);
        SpUtil.putString(AppConstant.HAS_COMPLETE, AppConstant.ZERO);
        SpUtil.putString(AppConstant.COMPANY_STATUS, "");
        SpUtil.putString(AppConstant.HAS_CHECK_REAL_NAME, AppConstant.ZERO);
        SpUtil.putString(AppConstant.IS_LEADER, "");
        SpUtil.putString(AppConstant.COMPANY_AUDITED, "");
        SpUtil.putString(AppConstant.MOBILE, "");
        SpUtil.putString(AppConstant.HAS_PASSWORD, "");
        SpUtil.putString(AppConstant.WALLET_BALANCE, "");*/
//        SpUtil.putBoolean(AppConstant.HAS_JUMP_TO_JOIN_TEAM,false);

        // TODO 待测试
        SpUtil.clearSp();
    }

    /**
     * 这是来自 JPush Example 的设置别名的 ActivityZhiYeJiGou 里的代码。一般 App 的设置的调用入口，在任何方便的地方调用都可以。
     */
//    private void setAlias(String alias) {
//        if (TextUtils.isEmpty(alias)) {
//            ToastUtil.showToast(CommonUtil.getString(R.string.error_alias_empty));
//            return;
//        }
//        if (!StringUtil.isValidTagAndAlias(alias)) {
//            ToastUtil.showToast(CommonUtil.getString(R.string.error_tag_gs_empty));
//            return;
//        }
//
//        // 从 3.0.7 版本开始支持，新版本别名设置
//        TagAliasOperatorHelper.TagAliasBean tagAliasBean = new TagAliasOperatorHelper.TagAliasBean();
//        tagAliasBean.setAction(ACTION_SET);
//        sequence++;
//        tagAliasBean.setAlias(alias);
//        tagAliasBean.setAliasAction(true);
//        TagAliasOperatorHelper.getInstance().handleAction(App.getContext(), sequence, tagAliasBean);
//    }
//    private void setAlias(String alias) {
//        if (TextUtils.isEmpty(alias)) {
//            ToastUtil.showToast(CommonUtil.getString(R.string.error_alias_empty));
//            return;
//        }
//        if (!StringUtil.isValidTagAndAlias(alias)) {
//            ToastUtil.showToast(CommonUtil.getString(R.string.error_tag_gs_empty));
//            return;
//        }
//
//        // 从 3.0.7 版本开始支持，新版本别名设置
//        TagAliasOperatorHelper.TagAliasBean tagAliasBean = new TagAliasOperatorHelper.TagAliasBean();
//        tagAliasBean.setAction(ACTION_SET);
//        sequence++;
//        tagAliasBean.setAlias(alias);
//        tagAliasBean.setAliasAction(true);
//        TagAliasOperatorHelper.getInstance().handleAction(App.getContext(), sequence, tagAliasBean);
//    }

}
