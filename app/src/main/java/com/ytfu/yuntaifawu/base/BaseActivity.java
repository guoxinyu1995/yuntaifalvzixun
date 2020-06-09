package com.ytfu.yuntaifawu.base;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.github.lee.annotation.InjectLayout;
import com.github.lee.annotation.InjectPresenter;
import com.kingja.loadsir.callback.Callback;
import com.kingja.loadsir.core.LoadService;
import com.kingja.loadsir.core.LoadSir;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.core.BasePopupView;
import com.orhanobut.logger.Logger;
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
public class BaseActivity<V extends BaseView, P extends BasePresenter<V>> extends AppCompatActivity implements BaseView {
    protected Context mContext;
    protected LayoutInflater mInflater;
    /**
     * Toolbar是悬浮还是线性
     */
    protected boolean toolbarOverlap = false;

    /**
     * 显示在界面的Toolbar,为null不显示
     */
    protected Toolbar mToolbar = null;

    private P mPresenter;
    private CustomDialog mCustomDialog;
    protected LoadService mBaseLoadService;
    private NetworkReceiver mNetworkReceiver;
    private Unbinder mUnbinder;

    /**
     * MVP标准模式限定,请不要使用之前的mPresenter对象
     */
    protected P getPresenter() {
        if (null == mPresenter) {
            throw new IllegalStateException(getClass().getSimpleName() + "并不是标准的MVP设计模式,请使用@InjectPresenter(clazz)注解绑定MVP设计模式");
        }
        return mPresenter;
    }

    private BasePopupView progressDialog;

    private View loadingView;
    private View successView;
    private View emptyView;
    private View errorView;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        mInflater = LayoutInflater.from(mContext);
        App.getInstance().addActivity(this);

        onEnterActivity(savedInstanceState);

        // Debug模式设置屏幕常亮
        if (AppConstant.DEBUG) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        }

        mNetworkReceiver = new NetworkReceiver();
        registerReceiver(mNetworkReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));

        init();

        if (toolbarOverlap) {
            //Toolbar悬浮
            setContentView(R.layout.activity_overlap_toolbar);
        } else {
            setContentView(R.layout.activity_linear_toolbar);
        }
        InjectLayout injectLayout = getClass().getAnnotation(InjectLayout.class);

        int toolbarLayoutId;
        int showLayoutId;

        int loadingLayoutId;
        int emptyLayoutId;
        int errorLayoutId;

        if (null == injectLayout) {
            toolbarLayoutId = -1;
            showLayoutId = -1;
            loadingLayoutId = -1;
            emptyLayoutId = -1;
            errorLayoutId = -1;
        } else {
            toolbarLayoutId = injectLayout.toolbarLayoutId();
            loadingLayoutId = injectLayout.loadingLayoutId();
            showLayoutId = injectLayout.value();
            emptyLayoutId = injectLayout.emptyLayoutId();
            errorLayoutId = injectLayout.errorLayoutId();

        }
        if (showLayoutId == -1) {
            Logger.w("新版BaseActivity封装不建议复写 provideContentViewId() 方法, 请使用@InjectLayout(R.layout.xxx)");
            showLayoutId = provideContentViewId();
        }

        //设置Toolbar
        FrameLayout toolbarContainer = findViewById(R.id.fl_toolbar_container);
        if (toolbarLayoutId == -1) {
            toolbarContainer.setVisibility(View.GONE);
        } else {
            mToolbar = inflateToolbar(toolbarLayoutId);
        }
        if (null == mToolbar) {
            //隐藏
            toolbarContainer.setVisibility(View.GONE);
        } else {
            //显示
            toolbarContainer.removeAllViews();
            toolbarContainer.addView(mToolbar);
            //初始化Toolbar
            mToolbar.setTitle("");
            setSupportActionBar(mToolbar);
            ActionBar supportActionBar = getSupportActionBar();
            if (null != supportActionBar) {
                supportActionBar.setDisplayHomeAsUpEnabled(false);
            }
            toolbarContainer.setVisibility(View.VISIBLE);
        }

        //        //注入布局
        //        if (loadingLayoutId != -1) {
        //            loadingView = inflateView(loadingLayoutId);
        //        }
        //        if (emptyLayoutId != -1) {
        //            emptyView = inflateView(emptyLayoutId);
        //        }
        //        if (errorLayoutId != -1) {
        //            errorView = inflateView(errorLayoutId);
        //        }
        if (showLayoutId != -1) {
            successView = inflateView(showLayoutId);
        } else {
            Logger.w(getClass().getSimpleName() + "没有设置布局显示");
        }

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

        //获取InjectPresenter注解,进行P层对象自动注入
        InjectPresenter injectPresenter = getClass().getAnnotation(InjectPresenter.class);
        if (null == injectPresenter) {
            //使用createPresenter
            Logger.w("新版BaseActivity封装已经不建议复写 createPresenter() 方法,请使用@InjectPresenter(clazz)注解");
            mPresenter = createPresenter();
        } else {
            //反射创建Presenter对象进行自动注入
            //创建注入
            try {
                //noinspection unchecked
                Class<P> clazz = (Class<P>) injectPresenter.value();
                mPresenter = clazz.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //判断是否使用MVP模式
        if (mPresenter != null) {
            //因为之后所有的子类都要实现对应的View接口
            mPresenter.attachView((V) this);
            mPresenter.attachLifecycle(this);
        } else {
            Logger.e(getClass().getSimpleName() + "并不是标准MVP设计模式,请使用@InjectPresenter(clazz)注解绑定MVP设计模式");
        }

        //显示加载中的
        if (null != loadingView) {
            showLoading();
        } else {//没有设置加载中的布局，直接设置成功的布局显示
            hideLoading();
        }

        //控件注入
        mUnbinder = ButterKnife.bind(this);
        initView();
        setViewListener();
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
     * @deprecated 请在类上使用@InjectLayout(R.layout.xxx)注解进行自动注入布局
     */
    protected int provideContentViewId() {
        return -1;
    }

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
     * @deprecated 请使用@InjectPresenter(clazz)注解绑定MVP设计模式
     */
    protected P createPresenter() {
        return null;
    }

    ///////////////////////////////////////////////////////////////////////////
    // 提供子类可以复写的方法
    ///////////////////////////////////////////////////////////////////////////

    /**
     * 进入当前界面,在设置布局之前调用,子类复写可以用来判断传递参数时候正确,也可以做数据的初始化操作等
     */
    protected void onEnterActivity(@Nullable Bundle savedInstanceState) {

    }

    /**
     * initView
     */
    protected void initView() {
    }

    /**
     * 设置控件监听事件操作
     */
    protected void setViewListener() {

    }

    /**
     * initData
     */
    protected void initData() {
    }

    ///////////////////////////////////////////////////////////////////////////
    //
    ///////////////////////////////////////////////////////////////////////////
    protected View inflateView(@LayoutRes int layoutId) {
        return mInflater.inflate(layoutId, null, false);
    }

    @Nullable
    protected Toolbar inflateToolbar(@LayoutRes int layoutId) {
        View view = inflateView(layoutId);
        if (view instanceof Toolbar) {
            return (Toolbar) view;
        }
        return null;
    }

    /**
     * 设置Toolbar上左边图标和点击事件
     */
    protected void setToolbarLeftImage(@DrawableRes int drawableId, @Nullable View.OnClickListener listener) {
        if (null == mToolbar) {
            Logger.w("You do not set Toolbar, Please set a valid Toolbar");
            return;
        }
        mToolbar.setNavigationIcon(drawableId);
        if (null != listener) {
            mToolbar.setNavigationOnClickListener(listener);
        }
    }

    /**
     * 设置toolbar中的TextView显示文本
     */
    protected void setToolbarText(@IdRes int viewId, String title) {
        if (null == mToolbar) {
            Logger.w("You do not set Toolbar, Please set a valid Toolbar");
            return;
        }
        TextView tv = mToolbar.findViewById(viewId);
        tv.setText(title);
    }

    protected void setToolbarText(@IdRes int viewId, @StringRes int titleId) {
        if (null == mToolbar) {
            Logger.w("You do not set Toolbar, Please set a valid Toolbar");
            return;
        }
        TextView tv = mToolbar.findViewById(viewId);
        tv.setText(titleId);
    }

    /**
     * 设置Toolbar中控件的点击事件
     */
    protected void setToolbarViewClickListener(@IdRes int viewId, @Nullable View.OnClickListener listener) {
        if (null == mToolbar) {
            Logger.w("You do not set Toolbar, Please set a valid Toolbar");
            return;
        }
        mToolbar.findViewById(viewId).setOnClickListener(listener);
    }

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
        AlertDialog alertDialog = new AlertDialog.Builder(this).setCancelable(true).setTitle(R.string.title_dialog).setMessage(content).setNegativeButton(R.string.no, (dialog, which) -> dialog.cancel()).setPositiveButton(R.string.yes, (dialog, which) -> finish()).create();

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
        if (null != loadingView) {
            FrameLayout baseContent = findViewById(R.id.fl_base_content);
            baseContent.removeAllViews();
            baseContent.addView(loadingView);
        }
        mBaseLoadService.showCallback(LoadingCallback.class);
    }

    /**
     * 隐藏loading
     */
    public void hideLoading() {
        if (successView != null) {
            FrameLayout baseContent = findViewById(R.id.fl_base_content);
            baseContent.removeAllViews();
            baseContent.addView(successView);
        }
        mBaseLoadService.showSuccess();
    }

    /**
     * 显示空页面
     */
    public void showEmpty() {
        if (null != emptyView) {
            FrameLayout baseContent = findViewById(R.id.fl_base_content);
            baseContent.removeAllViews();
            baseContent.addView(emptyView);
        }
        mBaseLoadService.showCallback(EmptyCallback.class);
    }

    /**
     * 显示超时页面
     */
    public void showTimeout() {
        if (null != errorView) {
            FrameLayout baseContent = findViewById(R.id.fl_base_content);
            baseContent.removeAllViews();
            baseContent.addView(errorView);
        }
        mBaseLoadService.showCallback(TimeoutCallback.class);
    }

    /**
     * 显示错误页面
     */
    public void showError() {
        if (null != errorView) {
            FrameLayout baseContent = findViewById(R.id.fl_base_content);
            baseContent.removeAllViews();
            baseContent.addView(errorView);
        }
        mBaseLoadService.showCallback(ErrorCallback.class);
    }


    public void showProgress() {
        if (null == progressDialog) {
            progressDialog = new XPopup.Builder(this).asLoading().show();
        }
    }

    public void hideProgress() {
        if (null != progressDialog) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

    @Override
    public void runOnUi(Runnable runnable) {
        runOnUiThread(runnable);
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

    protected int getBundleInt(Intent intent, @Nullable String key, int defaultValue) {
        Bundle bundle = intent.getExtras();
        if (null == bundle) {
            return defaultValue;
        }
        return bundle.getInt(key, defaultValue);
    }

    /**
     * 更换StatusBar上面文本颜色,只支持黑色和白色
     */
    protected void changeStatusBarTextColor(boolean isBlack) {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            int visibility;
            if (isBlack) {
                visibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            } else {
                visibility = View.SYSTEM_UI_FLAG_VISIBLE;
            }
            getWindow().getDecorView().setSystemUiVisibility(visibility);
        }
    }
}