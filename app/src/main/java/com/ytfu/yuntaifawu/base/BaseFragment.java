package com.ytfu.yuntaifawu.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.github.lee.annotation.InjectLayout;
import com.github.lee.annotation.InjectPresenter;
import com.kingja.loadsir.callback.Callback;
import com.kingja.loadsir.core.LoadService;
import com.kingja.loadsir.core.LoadSir;
import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.core.BasePopupView;
import com.orhanobut.logger.Logger;
import com.uber.autodispose.AutoDisposeConverter;
import com.ytfu.yuntaifawu.R;
import com.ytfu.yuntaifawu.app.AppConstant;
import com.ytfu.yuntaifawu.callback.EmptyCallback;
import com.ytfu.yuntaifawu.callback.ErrorCallback;
import com.ytfu.yuntaifawu.callback.LoadingCallback;
import com.ytfu.yuntaifawu.callback.TimeoutCallback;
import com.ytfu.yuntaifawu.helper.RxLifecycleUtil;
import com.ytfu.yuntaifawu.ui.login.activity.LoginCodeActivity;
import com.ytfu.yuntaifawu.utils.CustomDialog;
import com.ytfu.yuntaifawu.utils.ToastUtil;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.annotations.Nullable;

/**
 * @作者 gxy
 * @创建时间 2019/11/9
 * @描述
 */
public abstract class BaseFragment<V extends BaseView, P extends BasePresenter<V>>
        extends Fragment implements BaseView {
    protected Context mContext;
    protected LayoutInflater mInflater;
    protected Handler mHandler;

    protected Toolbar mToolbar;

    private P mPresenter;
    protected LoadService mBaseLoadService;
    private CustomDialog mCustomDialog;
    private Unbinder mUnbinder;
    //    private CompleteInfoPop completeInfoPop;
    private BasePopupView progressDialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
        mInflater = LayoutInflater.from(mContext);
        mHandler = new Handler();

        init();

        //获取InjectPresenter注解,进行P层对象自动注入
        InjectPresenter injectPresenter = getClass().getAnnotation(InjectPresenter.class);
        if (null == injectPresenter) {
            //使用createPresenter
            Logger.w("新版BaseFragment封装已经不建议复写 createPresenter() 方法,请使用@InjectPresenter(clazz)注解");
            mPresenter = createPresenter();
        } else {
            //反射创建Presenter对象进行自动注入
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

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = View.inflate(mContext, R.layout.fragment_base, null);
        if (null != provideLoadServiceRootView()) {
            initLoadService(provideLoadServiceRootView());
        } else {
            initLoadService(rootView);
            return mBaseLoadService.getLoadLayout();
        }
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @androidx.annotation.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //自动注入显示的布局
        InjectLayout injectLayout = getClass().getAnnotation(InjectLayout.class);

        int toolbarLayoutId;
        int showLayoutId;
        if (null == injectLayout) {
            toolbarLayoutId = -1;
            showLayoutId = -1;
        } else {
            toolbarLayoutId = injectLayout.toolbarLayoutId();
            showLayoutId = injectLayout.value();
        }
        //设置toolbar
        FrameLayout toolbarContainer = view.findViewById(R.id.fl_base_fragment_toolbar_container);
        if (toolbarLayoutId == -1) {
            toolbarContainer.setVisibility(View.GONE);
        } else {
            mToolbar = inflateToolbar(toolbarLayoutId);
            mToolbar.setTitle("");
            toolbarContainer.removeAllViews();
            toolbarContainer.addView(mToolbar);
            Activity activity = getActivity();
            if (activity instanceof BaseActivity) {
                BaseActivity<?, ?> a = (BaseActivity<?, ?>) activity;
                a.setSupportActionBar(mToolbar);
            }
        }
        if (showLayoutId == -1) {
            Logger.w("新版BaseActivity封装不建议复写 provideContentViewId() 方法, 请使用@InjectLayout(R.layout.xxx)");
            showLayoutId = provideContentViewId();
        }

        FrameLayout baseContent = view.findViewById(R.id.fl_base_fragment_content);
        if (showLayoutId != -1) {
            View rootView = inflateView(showLayoutId);
            baseContent.removeAllViews();
            baseContent.addView(rootView);

            mUnbinder = ButterKnife.bind(this, rootView);
        }

        initView(view);
        setViewListener(view);
        initData();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public void init() {

    }

    /**
     * 得到当前界面的布局文件id(由子类实现)
     *
     * @return 布局文件id
     */
    protected int provideContentViewId() {
        return -1;
    }

    /**
     * 用于创建Presenter和判断是否使用MVP模式(由子类实现)
     *
     * @return presenter
     * @deprecated 请使用@InjectPresenter(clazz)绑定Presenter
     */
    protected P createPresenter() {
        return null;
    }

    /**
     * initView
     *
     * @param rootView 根布局
     */
    protected void initView(View rootView) {
    }

    protected void setViewListener(View rootView) {

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

    protected void setToolbar(Toolbar toolbar) {
        mToolbar = toolbar;
        mToolbar.setTitle("");
        FrameLayout toolbarContainer = getView().findViewById(R.id.fl_base_fragment_toolbar_container);
        toolbarContainer.removeAllViews();
        toolbarContainer.addView(mToolbar);
        Activity activity = getActivity();
        if (activity instanceof BaseActivity) {
            BaseActivity<?, ?> a = (BaseActivity<?, ?>) activity;
            a.setSupportActionBar(mToolbar);
        }
    }

    protected P getPresenter() {
        if (null == mPresenter) {
            throw new IllegalStateException(getClass().getSimpleName() + "不是标准的MVP设计模式实现,请使用@InjectPresenter(clazz)绑定标准的MVP设计模式");
        }
        return mPresenter;
    }

    /**
     * 设置Toolbar上左边图标和点击事件
     */
    protected void setToolbarLeftImage(@DrawableRes int drawableId, @androidx.annotation.Nullable View.OnClickListener listener) {
        if (null == mToolbar) {
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
        TextView tv = mToolbar.findViewById(viewId);
        tv.setText(title);
    }

    protected void setToolbarTextColor(@IdRes int viewId, @ColorInt int color) {
        TextView tv = mToolbar.findViewById(viewId);
        tv.setTextColor(color);
    }

    protected void setToolbarText(@IdRes int viewId, @StringRes int titleId) {
        TextView tv = mToolbar.findViewById(viewId);
        tv.setText(titleId);
    }

    /**
     * 设置Toolbar中控件的点击事件
     */
    protected void setToolbarViewClickListener(@IdRes int viewId, @androidx.annotation.Nullable View.OnClickListener listener) {
        if (null == mToolbar) {
            Logger.e(getClass().getSimpleName() + "没有设置有效的Toolbar显示");
            return;
        }
        mToolbar.findViewById(viewId).setOnClickListener(listener);

    }

    ///////////////////////////////////////////////////////////////////////////
    //
    ///////////////////////////////////////////////////////////////////////////


    protected View provideLoadServiceRootView() {
        return null;
    }

    protected void initLoadService(View view) {
        mBaseLoadService = LoadSir.getDefault().register(view, new Callback.OnReloadListener() {
            @Override
            public void onReload(View v) {
                onMyReload(v);
            }
        });
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
     * @param cancelable 返回按钮是否关闭
     */
    public void showWaitingDialog(String tip, Boolean cancelable) {
        hideWaitingDialog();
        View view = View.inflate(getContext(), R.layout.dialog_waiting, null);
        if (!TextUtils.isEmpty(tip)) {
            ((TextView) view.findViewById(R.id.tvTip)).setText(tip);
        }
        mCustomDialog = new CustomDialog(getContext(), view, R.style.MyDialog);
        // 返回按钮不关闭
        mCustomDialog.setCancelable(cancelable);
        // 点击外部不关闭
        mCustomDialog.setCanceledOnTouchOutside(true);
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
     * 弹出Toast
     */
    public void showToast(String msg) {
        ToastUtil.showToast(msg);
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
        if (!isAdded()) {
            return;
        }
        if (null == progressDialog) {
            progressDialog = new XPopup.Builder(getContext())
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


    @Override
    public void runOnUi(Runnable runnable) {
        mHandler.post(runnable);
    }

    /**
     * 自定义点击重新加载
     */
    protected void onMyReload(View v) {

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
     * 去登录
     */
    public void toLogin() {
        Intent intent = new Intent(getContext(), LoginCodeActivity.class);
        intent.putExtra(AppConstant.NOTLOGIN, true);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    /**
     * 去实名认证
     */
    public void toRealName() {
        //        Intent intent = new Intent(getContext(), AuthenticateActivity.class);
        //        startActivity(intent);
    }

    public void showRealNamePop(Activity activity, View view, int popType) {
        //        completeInfoPop = new CompleteInfoPop(App.getContext(), activity, false, popType);
        //        completeInfoPop.show(view);
        //        completeInfoPop.setMyOnDismissListener(new CompleteInfoPop.OnMyDismissListener() {
        //            @Override
        //            public void onDismiss() {
        //                if (completeInfoPop != null) {
        //                    completeInfoPop = null;
        //                }
        //            }
        //        });
    }

    /**
     * 改变activity背景透明度
     * 1为全透明，值越小越暗
     */
    public void changeActivityBg(float f) {
        if (null != getActivity()) {
            Window window = getActivity().getWindow();
            WindowManager.LayoutParams params = window.getAttributes();
            params.alpha = f;
            window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
            window.setAttributes(params);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
        if (mPresenter != null) {
            mPresenter.detachView();
        }

        mUnbinder.unbind();
    }
}
