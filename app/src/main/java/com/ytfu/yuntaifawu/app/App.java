package com.ytfu.yuntaifawu.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.hyphenate.easeui.EaseUI;
import com.hyphenate.push.EMPushHelper;
import com.hyphenate.push.EMPushType;
import com.hyphenate.push.PushListener;
import com.hyphenate.util.EMLog;
import com.kingja.loadsir.core.LoadSir;
import com.lxj.xpopup.XPopup;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;
import com.tencent.bugly.crashreport.CrashReport;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.UMConfigure;
import com.ytfu.yuntaifawu.callback.CustomCallback;
import com.ytfu.yuntaifawu.callback.EmptyCallback;
import com.ytfu.yuntaifawu.callback.ErrorCallback;
import com.ytfu.yuntaifawu.callback.LoadingCallback;
import com.ytfu.yuntaifawu.callback.TimeoutCallback;
import com.ytfu.yuntaifawu.helper.HMSPushHelper;
import com.ytfu.yuntaifawu.utils.DemoHelper;
import com.ytfu.yuntaifawu.utils.SpUtil;

import java.util.Stack;

public class App extends Application {
    private static App mInstance;
    private static final Stack<Activity> activityStack = new Stack<Activity>();
    public static IWXAPI wxapi;

    @Override
    public void onCreate() {
        super.onCreate();
        // 初始化
        mInstance = this;
        //设置XPopup动画时长
        XPopup.setAnimationDuration(180);
        // Logger
        Logger.addLogAdapter(new AndroidLogAdapter(PrettyFormatStrategy.newBuilder().tag(AppConstant.LOGGER_TAG).build()) {
            @Override
            public boolean isLoggable(int priority, String tag) {
                return AppConstant.DEBUG;
            }
        });

        // 加载状态页
        LoadSir.beginBuilder().addCallback(new ErrorCallback()).addCallback(new EmptyCallback()).addCallback(new LoadingCallback()).addCallback(new TimeoutCallback()).addCallback(new CustomCallback()).setDefaultCallback(LoadingCallback.class).commit();

        /*
         * 友盟 初始化common库
         * 参数1:上下文，不能为空
         * 参数2:【友盟+】 AppKey，非必须参数，如果Manifest文件中已配置AppKey，该参数可以传空，则使用Manifest中配置的AppKey，否则该参数必须传入。
         * 参数3:【友盟+】 Channel，非必须参数，如果Manifest文件中已配置Channel，该参数可以传空，则使用Manifest中配置的Channel，否则该参数必须传入，Channel命名请详见Channel渠道命名规范。
         * 参数4:设备类型，UMConfigure.DEVICE_TYPE_PHONE为手机、UMConfigure.DEVICE_TYPE_BOX为盒子，默认为手机
         * 参数5::Push推送业务的secret，需要集成Push功能时必须传入Push的secret，否则传空。
         */
        UMConfigure.init(this, AppConstant.UMENG_KEY, null, UMConfigure.DEVICE_TYPE_PHONE, null);
        // 选用LEGACY_AUTO页面采集模式
        MobclickAgent.setPageCollectionMode(MobclickAgent.PageMode.LEGACY_AUTO);
        // 设置组件化的Log开关 参数: boolean 默认为false，如需查看LOG设置为true
        // 如果查看初始化过程中的LOG，一定要在调用初始化方法前将LOG开关打开。
        UMConfigure.setLogEnabled(AppConstant.DEBUG);
        // 设置日志加密 参数：boolean 默认为false（不加密）
        UMConfigure.setEncryptEnabled(AppConstant.DEBUG);
        // Bugly
        CrashReport.initCrashReport(this, AppConstant.BUGLY_ID, AppConstant.DEBUG);
        //微信
        wxapi = WXAPIFactory.createWXAPI(this, AppConstant.WX_APP_ID);
        wxapi.registerApp(AppConstant.WX_APP_ID);
        //初始化环信
        DemoHelper.getInstance().init(this);
        // 请确保环信SDK相关方法运行在主进程，子进程不会初始化环信SDK（该逻辑在EaseUI.java中）
        if (EaseUI.getInstance().isMainProcess(this)) {
            // 初始化华为 HMS 推送服务, 需要在SDK初始化后执行
            HMSPushHelper.getInstance().initHMSAgent(mInstance);

            EMPushHelper.getInstance().setPushListener(new PushListener() {
                @Override
                public void onError(EMPushType pushType, long errorCode) {
                    // TODO: 返回的errorCode仅9xx为环信内部错误，可从EMError中查询，其他错误请根据pushType去相应第三方推送网站查询。
                    EMLog.e("PushClient", "Push client occur a error: " + pushType + " - " + errorCode);
                }
            });
        }
    }

    public static synchronized App getInstance() {
        return mInstance;
    }

    public static Context getContext() {
        return mInstance.getApplicationContext();
    }

    /**
     * add ActivityZhiYeJiGou 添加Activity到栈
     */
    public void addActivity(Activity activity) {
        activityStack.add(activity);
    }

    /**
     * 获取 登录状态
     *
     * @return true 已登录  false 未登录
     */
    public Boolean getLoginFlag() {
        return !getUid().isEmpty();
    }

    /**
     * 获取token
     */
    public String getUid() {
        return SpUtil.getString(AppConstant.UID, "");
    }

    /**
     * 结束指定类名的Activity
     */
    public void finishActivity(Class<?> cls) {
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
                break;
            }
        }
    }

    /**
     * 结束指定的Activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            activity.finish();
        }
    }

    /**
     * 判断指定的activity是否存在
     */
    public boolean activityExisted(Class<?> cls) {
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 结束所有Activity
     */
    public void finishAllActivity() {
        for (int i = 0, size = activityStack.size(); i < size; i++) {
            if (null != activityStack.get(i)) {
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }

    /**
     * 退出app
     */
    public void exitApp() {
        synchronized (activityStack) {
            for (Activity act : activityStack) {
                if (null != act) {
                    act.finish();
                }
            }
        }
        //        android.os.Process.killProcess(android.os.Process.myPid());
        //        System.exit(0);
    }
}
