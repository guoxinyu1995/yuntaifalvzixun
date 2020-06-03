package com.ytfu.yuntaifawu.base;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.kingja.loadsir.callback.Callback;
import com.kingja.loadsir.core.LoadService;
import com.kingja.loadsir.core.LoadSir;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.core.BasePopupView;
import com.uber.autodispose.AutoDisposeConverter;
import com.umeng.analytics.MobclickAgent;
import com.ytfu.yuntaifawu.R;
import com.ytfu.yuntaifawu.app.App;
import com.ytfu.yuntaifawu.app.AppConstant;
import com.ytfu.yuntaifawu.callback.EmptyCallback;
import com.ytfu.yuntaifawu.callback.ErrorCallback;
import com.ytfu.yuntaifawu.callback.LoadingCallback;
import com.ytfu.yuntaifawu.callback.TimeoutCallback;
import com.ytfu.yuntaifawu.helper.RxLifecycleUtil;
import com.ytfu.yuntaifawu.ui.login.activity.LoginCodeActivity;
import com.ytfu.yuntaifawu.utils.CommonUtil;
import com.ytfu.yuntaifawu.utils.CustomDialog;
import com.ytfu.yuntaifawu.utils.NetworkReceiver;
import com.ytfu.yuntaifawu.utils.NetworkUtil;
import com.ytfu.yuntaifawu.utils.ToastUtil;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @Auther gxy
 * @Date 2019/12/6
 * @Des
 */
public abstract class BaseActivity<V, P extends BasePresenter<V>> extends AppCompatActivity {

    protected P mPresenter;
    private CustomDialog mCustomDialog;
    protected LoadService mBaseLoadService;
    private NetworkReceiver mNetworkReceiver;
    private Unbinder mUnbinder;

    private BasePopupView progressDialog;

    //    private CompleteInfoPop completeInfoPop;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.getInstance().addActivity(this);

        // Debug模式设置屏幕常亮
        if (AppConstant.DEBUG) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        }

        mNetworkReceiver = new NetworkReceiver();
        registerReceiver(mNetworkReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));

        /*Android P刘海屏*/
        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            //设置页面延伸到刘海区显示
            WindowManager.LayoutParams lp = getWindow().getAttributes();
            lp.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
            getWindow().setAttributes(lp);
        }*/
        //        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //        ActionBar supportActionBar = getSupportActionBar();
        //        if(supportActionBar!=null){
        //            supportActionBar.hide();
        //        }
        init();

        // 设置布局文件id(由子类实现)，子类也不再需要使用ButterKnife.bind()
        if (provideContentViewId() != 0) {
            setContentView(provideContentViewId());
        } else {
            throw new IllegalArgumentException("You must return a right contentView layout resource Id");
        }

        mUnbinder = ButterKnife.bind(this);

        if (null != provideLoadServiceRootView()) {
            initLoadService(provideLoadServiceRootView());
        } else {
            mBaseLoadService = LoadSir.getDefault().register(this, new Callback.OnReloadListener() {
                @Override
                public void onReload(View v) {
                    onMyReload(v);
                }
            });
        }

        //判断是否使用MVP模式
        mPresenter = createPresenter();
        if (mPresenter != null) {
            //因为之后所有的子类都要实现对应的View接口
            mPresenter.attachView((V) this);
            mPresenter.attachLifecycle(this);
        }

        initView();
        initData();
    }

    /**
     * 在setContentView()调用之前调用，可以设置WindowFeature(如：this.requestWindowFeature(Window.FEATURE_NO_TITLE);)
     */
    public void init() {
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            //            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            //                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.setStatusBarColor(CommonUtil.getColor(R.color.transparent_4c));
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            //            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            //                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.setStatusBarColor(CommonUtil.getColor(R.color.transparent_4c));
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 4.4全透明状态栏
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            excuteStatesBar();
        }

        /*if (UIUtil.isMIUI()) {

        }

        if (UIUtil.isFlyme()) {

        }*/
    }

    /**
     * 得到当前界面的布局文件id(由子类实现)
     *
     * @return 布局文件id
     */
    protected abstract int provideContentViewId();

    /**
     * 提供显示加载状态页面的RootView
     */
    protected View provideLoadServiceRootView() {
        return null;
    }

    /**
     * Register
     */
    protected void initLoadService(View view) {
        mBaseLoadService = LoadSir.getDefault().register(view, new Callback.OnReloadListener() {
            @Override
            public void onReload(View v) {
                onMyReload(v);
            }
        });
    }

    /**
     * 自定义点击重新加载
     */
    protected void onMyReload(View v) {

    }

    /**
     * 用于创建Presenter和判断是否使用MVP模式(由子类实现)
     *
     * @return presenter
     */
    protected abstract P createPresenter();

    /**
     * initView
     */
    protected abstract void initView();

    /**
     * initData
     */
    protected abstract void initData();

    /**
     * 解决4.4设置状态栏颜色之后，布局内容嵌入状态栏位置问题
     */
    private void excuteStatesBar() {
        ViewGroup mContentView = getWindow().findViewById(Window.ID_ANDROID_CONTENT);
        View mChildView = mContentView.getChildAt(0);
        if (mChildView != null) {
            //注意不是设置 ContentView 的 FitsSystemWindows,
            // 而是设置 ContentView 的第一个子 View ，预留出系统 View 的空间.
            mChildView.setFitsSystemWindows(true);
        }
    }

    /**
     * 显示等待提示框
     */
    public void showWaitingDialog(String tip) {
        showWaitingDialog(tip, true);
    }

    /**
     * 显示等待提示框
     *
     * @param tip        提示语
     * @param cancelable 返回按钮是否可关闭
     */
    public void showWaitingDialog(String tip, Boolean cancelable) {
        hideWaitingDialog();
        View view = View.inflate(this, R.layout.dialog_waiting, null);
        if (!TextUtils.isEmpty(tip)) {
            ((TextView) view.findViewById(R.id.tvTip)).setText(tip);
        }
        mCustomDialog = new CustomDialog(this, view, R.style.MyDialog);
        // 返回按钮不关闭
        mCustomDialog.setCancelable(cancelable);
        // 点击外部不关闭
        mCustomDialog.setCanceledOnTouchOutside(false);
        mCustomDialog.show();
    }

    /**
     * 隐藏等待提示框
     */
    public void hideWaitingDialog() {
        if (mCustomDialog != null) {
            mCustomDialog.dismiss();
            mCustomDialog = null;
        }
    }

    public void showRealNamePop(Activity activity, View view, int popType) {
       /* completeInfoPop = new CompleteInfoPop(App.getContext(), activity, false, popType);
        completeInfoPop.show(view);
        completeInfoPop.setMyOnDismissListener(new CompleteInfoPop.OnMyDismissListener() {
            @Override
            public void onDismiss() {
                if (completeInfoPop != null) {
                    completeInfoPop = null;
                }
            }
        });*/
    }

    /**
     * 关闭Activity弹框提示
     */
    public void showRemind() {
        //        showRemind(CommonUtil.getString(R.string.input_back_remind));
    }

    /**
     * 关闭Activity弹框提示
     */
    public void showRemind(String content) {
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setCancelable(true)
                .setTitle(R.string.title_dialog)
                .setMessage(content)
                .setNegativeButton(R.string.no, (dialog, which) -> dialog.cancel())
                .setPositiveButton(R.string.yes, (dialog, which) -> finish())
                .create();

        alertDialog.show();
        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(CommonUtil.getColor(R.color.primaryColor));
        alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(CommonUtil.getColor(R.color.textcolo_A6));
    }

    /**
     * 改变activity背景透明度
     * 1为全透明，值越小越暗
     */
    public void changeActivityBg(float f) {
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.alpha = f;
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getWindow().setAttributes(params);
    }

    /**
     * 去登录
     */
    public void toLogin() {
        Intent intent = new Intent(this, LoginCodeActivity.class);
        intent.putExtra(AppConstant.NOTLOGIN, true);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    /**
     * 弹出Toast
     */
    public void showToast(String msg) {
        ToastUtil.showToast(msg);
    }

    public void showImageToast(String msg) {
        //        ToastUtil.showDeleteToast(this, msg);
    }

    /**
     * 弹出Toast
     */
    public void showLongToast(String msg) {
        ToastUtil.showLong(msg);
    }

    /**
     * 弹出Toast
     */
    public void showCenterToast(String msg) {
        ToastUtil.showCenterToast(msg);
    }

    /**
     * 显示loading
     */
    public void showLoading() {
        mBaseLoadService.showCallback(LoadingCallback.class);
    }

    /**
     * 隐藏loading
     */
    public void hideLoading() {
        mBaseLoadService.showSuccess();
    }

    /**
     * 显示空页面
     */
    public void showEmpty() {
        mBaseLoadService.showCallback(EmptyCallback.class);
    }

    /**
     * 显示超时页面
     */
    public void showTimeout() {
        mBaseLoadService.showCallback(TimeoutCallback.class);
    }

    /**
     * 显示错误页面
     */
    public void showError() {
        mBaseLoadService.showCallback(ErrorCallback.class);
    }


    public void showProgress() {
        if (null == progressDialog) {
            progressDialog = new XPopup.Builder(this)
                    .asLoading()
                    .show();
        }
    }

    public void hideProgress() {
        if (null != progressDialog) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

    /**
     * Observable的生命周期绑定
     *
     * @deprecated 报错，不能用
     */
    protected <T> AutoDisposeConverter<T> bindLifecycle() {
        return RxLifecycleUtil.bindLifecycle(this);
    }

    /**
     * 网络变化监听
     */
    NetworkReceiver.NetworkEvent networkEvent = netWorkState -> {
        switch (netWorkState) {
            case NetworkUtil.NETWORK_WIFI:
                hideLoading();
                break;
            case NetworkUtil.NETWORK_MOBILE:
                hideLoading();
                break;
            case NetworkUtil.NETWORK_NONE:
                showTimeout();
                break;
            default:
                break;
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
        if (mPresenter != null) {
            mPresenter.detachView();
        }

        if (null != mNetworkReceiver) {
            unregisterReceiver(mNetworkReceiver);
        }

        /*if (mCustomDialog != null) {
            mCustomDialog.dismiss();
            mCustomDialog = null;
        }*/

        mUnbinder.unbind();
        App.getInstance().finishActivity(this);
    }

    @Override
    public Resources getResources() {
        Resources mResources = super.getResources();
        Configuration configuration = mResources.getConfiguration();
        if (configuration.fontScale != 1) {
            // 还原字体大小
            configuration.fontScale = 1;
            //            configuration.setToDefaults();

            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
                mResources = createConfigurationContext(configuration).getResources();
            } else {
                mResources.updateConfiguration(configuration, mResources.getDisplayMetrics());
            }
        }
        return mResources;
    }

    //===Desc:=================================================================

    protected String getBundleString(@Nullable String key, String defaultValue) {
        Bundle bundle = getIntent().getExtras();
        if (null == bundle) {
            return defaultValue;
        }
        return bundle.getString(key, defaultValue);
    }

    protected boolean getBundleBoolean(@Nullable String key, boolean defaultValue) {
        Bundle bundle = getIntent().getExtras();
        if (null == bundle) {
            return defaultValue;
        }
        return bundle.getBoolean(key, defaultValue);
    }
    protected int getBundleInt(@Nullable String key, int defaultValue) {
        Bundle bundle = getIntent().getExtras();
        if (null == bundle) {
            return defaultValue;
        }
        return bundle.getInt(key, defaultValue);
    }
    protected int getBundleInt(Intent intent,@Nullable String key, int defaultValue) {
        Bundle bundle = intent.getExtras();
        if (null == bundle) {
            return defaultValue;
        }
        return bundle.getInt(key, defaultValue);
    }
}