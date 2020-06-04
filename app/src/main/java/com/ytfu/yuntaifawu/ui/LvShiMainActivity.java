package com.ytfu.yuntaifawu.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentTransaction;

import com.orhanobut.logger.Logger;
import com.ytfu.yuntaifawu.R;
import com.ytfu.yuntaifawu.apis.HttpUtil;
import com.ytfu.yuntaifawu.app.App;
import com.ytfu.yuntaifawu.app.AppConstant;
import com.ytfu.yuntaifawu.base.BaseActivity;
import com.ytfu.yuntaifawu.base.BasePresenter;
import com.ytfu.yuntaifawu.helper.BaseRxObserver;
import com.ytfu.yuntaifawu.helper.RxLifecycleUtil;
import com.ytfu.yuntaifawu.ui.chat.fragment.ChatListFragment;
import com.ytfu.yuntaifawu.ui.lvshihetong.fragment.LvShiHeTongFragment;
import com.ytfu.yuntaifawu.ui.lvshiwenti.fragment.LvShiWenTiFragment;
import com.ytfu.yuntaifawu.ui.lvshiwode.fragment.LvShiWodeFragment;
import com.ytfu.yuntaifawu.ui.updatapk.UpDateApkBean;
import com.ytfu.yuntaifawu.utils.ApkUtil;
import com.ytfu.yuntaifawu.utils.CommonUtil;
import com.ytfu.yuntaifawu.utils.DemoHelper;
import com.ytfu.yuntaifawu.utils.Eyes;
import com.ytfu.yuntaifawu.utils.SpUtil;
import com.ytfu.yuntaifawu.utils.ToastUtil;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.OnClick;
import constacne.UiType;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import listener.UpdateDownloadListener;
import model.UiConfig;
import model.UpdateConfig;
import qiu.niorgai.StatusBarCompat;
import update.UpdateAppUtils;

/**
 * @Auther gxy
 * @Date 2020/5/25
 * @Des 律师端主页面
 */
public class LvShiMainActivity extends BaseActivity {
    @BindView(R.id.fram_layout)
    FrameLayout framLayout;
    @BindView(R.id.iv_main_bottom_wenti)
    ImageView ivMainBottomWenti;
    @BindView(R.id.tv_main_bottom_wenti)
    TextView tvMainBottomWenti;
    @BindView(R.id.rl_main_bottom_wenti)
    RelativeLayout rlMainBottomWenti;
    @BindView(R.id.iv_main_bottom_message)
    ImageView ivMainBottomMessage;
    @BindView(R.id.tv_main_bottom_message)
    TextView tvMainBottomMessage;
    @BindView(R.id.rl_main_bottom_message)
    RelativeLayout rlMainBottomMessage;
    @BindView(R.id.iv_main_bottom_wode)
    ImageView ivMainBottomWode;
    @BindView(R.id.tv_main_bottom_wode)
    TextView tvMainBottomWode;
    @BindView(R.id.rl_main_bottom_wode)
    RelativeLayout rlMainBottomWode;
    @BindView(R.id.iv_main_bottom_hetong)
    ImageView ivMainBottomHetong;
    @BindView(R.id.tv_main_bottom_hetong)
    TextView tvMainBottomHetong;
    @BindView(R.id.rl_main_bottom_hetong)
    RelativeLayout rlMainBottomHetong;
    @BindView(R.id.rl_main_bottom)
    ConstraintLayout rlMainBottom;
    private String uid;
    private LvShiWenTiFragment lvShiWenTiFragment;
    private ChatListFragment chatListFragment;
    private LvShiHeTongFragment lvShiHeTongFragment;
    private LvShiWodeFragment lvShiWodeFragment;
    private FragmentTransaction beginTransaction;
    /**
     * 是否强制更新
     */
    private boolean force = false;
    /**
     * 版本code
     * 当前版本Code
     * 强制更新code
     */
    private int localVersionCode, allowCode = 0;
    private MyHandler myHandler;

    private static final String KEY_CURRENT_POSITION = "KEY_CURRENT_POSITION";

    public static void start(Context context, int position) {
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_CURRENT_POSITION, position);
        Intent starter = new Intent(context, LvShiMainActivity.class);
        starter.putExtras(bundle);
        context.startActivity(starter);
    }
    public static void startWithNewTask(Context context, int position) {
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_CURRENT_POSITION, position);
        Intent starter = new Intent(context, LvShiMainActivity.class);
        starter.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        starter.putExtras(bundle);
        context.startActivity(starter);
    }


    @Override
    public void init() {
        super.init();
        Eyes.setStatusBarColor(this, CommonUtil.getColor(R.color.transparent_4c));
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.lvshi_main_activity;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        int position = intent.getIntExtra("frid", -1);
        if (position != -1) {
            changeTagStatus(position);
        }
    }

    @Override
    protected void initView() {
        hideLoading();
        uid = SpUtil.getString(AppConstant.UID, "");
        //初始化fragment
        initFragment();
    }

    @Override
    protected void initData() {
        //检测是否有新版本
        checkUpdate();
        myHandler = new MyHandler(LvShiMainActivity.this);

        int position = getBundleInt(KEY_CURRENT_POSITION, 0);
        changeTagStatus(position);
    }

    private void checkUpdate() {
        HttpUtil.getInstance().getApiService().checkUpdate()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(RxLifecycleUtil.bindLifecycle(this))
                .subscribe(new BaseRxObserver<UpDateApkBean>() {
                    @Override
                    public void onNextImpl(UpDateApkBean updateBean) {
                        if (null != updateBean) {
                            localVersionCode = ApkUtil.getVersionCode();
                            int serverVersionCode = Integer.parseInt(null != updateBean.getCode() ? updateBean.getCode() : "0");
                            allowCode = Integer.parseInt(null != updateBean.getAllow_code() ? updateBean.getAllow_code() : "0");

                            // 当前版本低于服务器最低版本，强制更新
                            if (localVersionCode < allowCode) {
                                force = true;
                            }

                            // 有新版本提示更新
                            if (localVersionCode < serverVersionCode) {
                                Message msg = new Message();
                                msg.what = 0;
                                msg.obj = updateBean;
                                myHandler.sendMessage(msg);
                            }
                        }
                    }

                    @Override
                    public void onErrorImpl(String errorMessage) {
                        Logger.e(errorMessage);
                    }
                });
    }

    public static class MyHandler extends Handler {
        private final WeakReference<LvShiMainActivity> mActivity;

        MyHandler(LvShiMainActivity activity) {
            mActivity = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            LvShiMainActivity mainActivity = mActivity.get();
            if (mainActivity != null) {
                if (0 == msg.what) {
                    //版本更新
                    mainActivity.showUpdate((UpDateApkBean) msg.obj);
                }
            }
        }
    }

    private void showUpdate(UpDateApkBean apkBean) {
        //UI配置
        UiConfig uiConfig = new UiConfig();
        uiConfig.setUiType(UiType.CUSTOM);
        uiConfig.setCustomLayoutId(R.layout.pop_update_new);
        uiConfig.setCancelBtnText("残忍拒绝");
        uiConfig.setUpdateBtnText("立即升级");
        // 更新配置
        UpdateConfig updateConfig = new UpdateConfig();
        updateConfig.setDebug(AppConstant.DEBUG);
        // 自动添加后缀.apk
        updateConfig.setApkSaveName("云台法律咨询");
        updateConfig.setNotifyImgRes(R.mipmap.logo);
        // 是否强制更新，强制时无取消按钮
        if (force) {
            updateConfig.setForce(true);
        }
        UpdateAppUtils.getInstance()
                .apkUrl(apkBean.getUrl())
                // title设置为版本号
                .updateTitle("v" + apkBean.getCode_str())
                .updateContent(apkBean.getMiaoshu())
                .uiConfig(uiConfig)
                // 自定义布局中控件内容填充
                .setOnInitUiListener((view, updateConfig1, uiConfig1) -> {
                    // 是否强制更新，强制时无取消按钮
                    if (null != view && force) {
                        // 隐藏分割线
                        view.findViewById(R.id.view_update_line).setVisibility(View.GONE);
                    }
                })
                .setCancelBtnClickListener(() -> {
                    return false;
                })
                .setUpdateDownloadListener(new UpdateDownloadListener() {
                    @Override
                    public void onStart() {
                        if (!force) {
                            // 非强制更新
                        }
                    }

                    @Override
                    public void onDownload(int i) {

                    }

                    @Override
                    public void onFinish() {

                    }

                    @Override
                    public void onError(@NonNull Throwable throwable) {
                        Logger.e("UpdateDownloadListener -> onError() :" + throwable.getMessage());
                    }
                })
                .updateConfig(updateConfig)
                .update();
    }

    private void initFragment() {
        //问题页面
        lvShiWenTiFragment = LvShiWenTiFragment.newInstance();
        //消息页面
        chatListFragment = ChatListFragment.newInstance();
        //合同页面
        lvShiHeTongFragment = LvShiHeTongFragment.newInstance();
        //律师我的
        lvShiWodeFragment = LvShiWodeFragment.newInstance();

        beginTransaction = getSupportFragmentManager().beginTransaction();
        beginTransaction.replace(R.id.fram_layout, lvShiWenTiFragment, lvShiWenTiFragment.getTag());//问题
        beginTransaction.add(R.id.fram_layout, chatListFragment, chatListFragment.getTag());//消息
        beginTransaction.add(R.id.fram_layout, lvShiHeTongFragment, lvShiHeTongFragment.getTag());//合同
        beginTransaction.add(R.id.fram_layout, lvShiWodeFragment, lvShiWodeFragment.getTag());//我的
        beginTransaction.commitAllowingStateLoss();
        // 默认首页
        changeTagStatus(0);
    }

    public void changeTagStatus(int i) {
        switch (i) {
            case 0:
                changeStatus(i, ivMainBottomWenti, tvMainBottomWenti);
                break;
            case 1:
                changeStatus(i, ivMainBottomMessage, tvMainBottomMessage);
                break;
            case 2:
                changeStatus(i, ivMainBottomHetong, tvMainBottomHetong);
                break;
            case 3:
                changeStatus(i, ivMainBottomWode, tvMainBottomWode);
                break;
            default:
                break;
        }
    }

    private void changeStatus(int i, ImageView img, TextView text) {
        changeFragment(i);
        if (i != 0) {
            ivMainBottomWenti.setEnabled(true);
            tvMainBottomWenti.setTextColor(getResources().getColor(R.color.textColor_66));
        }
        if (i != 1) {
            ivMainBottomMessage.setEnabled(true);
            tvMainBottomMessage.setTextColor(getResources().getColor(R.color.textColor_66));
        }
        if (i != 2) {
            ivMainBottomHetong.setEnabled(true);
            tvMainBottomHetong.setTextColor(getResources().getColor(R.color.textColor_66));
        }
        if (i != 3) {
            ivMainBottomWode.setEnabled(true);
            tvMainBottomWode.setTextColor(getResources().getColor(R.color.textColor_66));
        }
        img.setEnabled(false);
        text.setTextColor(getResources().getColor(R.color.textColor_collect_audio_Select));
    }

    private void changeFragment(int i) {
        beginTransaction = getSupportFragmentManager().beginTransaction();
        hideFragment(beginTransaction);
        StatusBarCompat.setStatusBarColor(this, getResources().getColor(R.color.transparent_4c));
        switch (i) {
            case 0:
                //问题
                beginTransaction.show(lvShiWenTiFragment);
                break;
            case 1:
                //消息
                beginTransaction.show(chatListFragment);
                break;
            case 2:
                //合同
                beginTransaction.show(lvShiHeTongFragment);
                break;
            case 3:
                //我的
                beginTransaction.show(lvShiWodeFragment);
                StatusBarCompat.translucentStatusBar(this);
                break;
            default:
                break;
        }
        beginTransaction.commitAllowingStateLoss();
    }

    private void hideFragment(FragmentTransaction beginTransaction) {
        //问题
        if (lvShiWenTiFragment != null) {
            beginTransaction.hide(lvShiWenTiFragment);
        }
        //消息
        if (chatListFragment != null) {
            beginTransaction.hide(chatListFragment);
        }
        //合同
        if (lvShiHeTongFragment != null) {
            beginTransaction.hide(lvShiHeTongFragment);
        }
        //我的
        if (lvShiWodeFragment != null) {
            beginTransaction.hide(lvShiWodeFragment);
        }
    }

    @OnClick({R.id.rl_main_bottom_wenti, R.id.rl_main_bottom_message, R.id.rl_main_bottom_wode, R.id.rl_main_bottom_hetong})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_main_bottom_wenti:
                //问题
                changeTagStatus(0);
                break;
            case R.id.rl_main_bottom_message:
                //消息
                if (App.getInstance().getLoginFlag() && DemoHelper.getInstance().isLoggedIn()) {
                    changeTagStatus(1);
                } else {
                    toLogin();
                }
                break;
            case R.id.rl_main_bottom_hetong:
                //合同
                if (App.getInstance().getLoginFlag()) {
                    changeTagStatus(2);
                } else {
                    toLogin();
                }
                break;
            case R.id.rl_main_bottom_wode:
                //我的
                if (App.getInstance().getLoginFlag()) {
                    changeTagStatus(3);
                } else {
                    toLogin();
                }
                break;
        }
    }

    private long exitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //            showRemind("确认退出应用么?");
            exit();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void exit() {
        if ((System.currentTimeMillis() - exitTime) > AppConstant.EXIT_INTERVAL_TIME) {
            ToastUtil.showToast("再按一次退出程序");
            exitTime = System.currentTimeMillis();
        } else {
            //            System.exit(0);
            //            App.getInstance().finishAllActivity();
            App.getInstance().exitApp();
        }
    }
}
