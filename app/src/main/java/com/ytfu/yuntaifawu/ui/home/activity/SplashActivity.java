package com.ytfu.yuntaifawu.ui.home.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.orhanobut.logger.Logger;
import com.ytfu.yuntaifawu.R;
import com.ytfu.yuntaifawu.apis.HttpUtil;
import com.ytfu.yuntaifawu.app.App;
import com.ytfu.yuntaifawu.app.AppConstant;
import com.ytfu.yuntaifawu.base.BaseActivity;
import com.ytfu.yuntaifawu.base.BasePresenter;
import com.ytfu.yuntaifawu.helper.BaseRxObserver;
import com.ytfu.yuntaifawu.helper.RxLifecycleUtil;
import com.ytfu.yuntaifawu.ui.LvShiMainActivity;
import com.ytfu.yuntaifawu.ui.MainActivity;
import com.ytfu.yuntaifawu.ui.login.activity.LoginCodeActivity;
import com.ytfu.yuntaifawu.ui.mseeage.bean.HuanXinLoginBean;
import com.ytfu.yuntaifawu.ui.register.activity.ActivityYhxy;
import com.ytfu.yuntaifawu.ui.register.activity.ActivityYs;
import com.ytfu.yuntaifawu.ui.updatapk.UpDateApkBean;
import com.ytfu.yuntaifawu.utils.AndPermissionUtil;
import com.ytfu.yuntaifawu.utils.ApkUtil;
import com.ytfu.yuntaifawu.utils.CommonUtil;
import com.ytfu.yuntaifawu.utils.DemoHelper;
import com.ytfu.yuntaifawu.utils.Eyes;
import com.ytfu.yuntaifawu.utils.SpUtil;
import com.ytfu.yuntaifawu.utils.ToastUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * @Auther gxy
 * @Date 2019/11/25
 * @Des 启动页
 */
public class SplashActivity extends BaseActivity {
    private boolean aBoolean;
    private AlertDialog alertDialog2;
    //    private String uid;
//    private String shenfen;
    //    @BindView(R.id.iv_splash)
    //    ImageView ivSplash;
    private Handler handler;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_splash;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    public void init() {
        super.init();
        handler = new Handler();
        Eyes.setStatusBarColor(this, CommonUtil.getColor(R.color.transparent_4c));
    }

    @Override
    protected void initView() {
        hideLoading();
        aBoolean = SpUtil.getBoolean(AppConstant.TONGYI, false);
        String uid = SpUtil.getString(AppConstant.UID, "");
//        shenfen = SpUtil.getString(AppConstant.SHENFEN, "");
    }


    @Override
    protected void initData() {
        if (!aBoolean) {
            showDelog();
        } else {
            handler.postDelayed(() -> {
                if (App.getInstance().getLoginFlag()) {
                    String shenfen = SpUtil.getString(AppConstant.SHENFEN, "");
                    if (shenfen.equals("1")) {
                        startActivity(new Intent(SplashActivity.this, MainActivity.class));
                        finish();
                    } else if (shenfen.equals("2")) {
                        startActivity(new Intent(SplashActivity.this, LvShiMainActivity.class));
                        finish();
                    }
                } else {
                    startActivity(new Intent(SplashActivity.this, LoginCodeActivity.class));
                    finish();
                }
            }, 2000);
        }
    }

    private void showDelog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final AlertDialog alertDialog = builder.create();
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();
        View view = View.inflate(this, R.layout.view_alert_dialog_confirm_xy, null);
        TextView tv_yhxy = view.findViewById(R.id.tv_yhxy);
        TextView tv_yisi = view.findViewById(R.id.tv_yisi);
        TextView tv_cancel_dialog = view.findViewById(R.id.tv_cancel_dialog);
        TextView tv_confirm_dialog = view.findViewById(R.id.tv_confirm_dialog);
        //用户协议
        tv_yhxy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SplashActivity.this, ActivityYhxy.class));
            }
        });
        //隐私协议
        tv_yisi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SplashActivity.this, ActivityYs.class));
            }
        });
        //取消
        tv_cancel_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                finish();
            }
        });
        //确定
        tv_confirm_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SpUtil.putBoolean(AppConstant.TONGYI, true);
                if (App.getInstance().getLoginFlag()) {
                    String shenfen = SpUtil.getString(AppConstant.SHENFEN, "");
                    if (shenfen.equals("1")) {
                        startActivity(new Intent(SplashActivity.this, MainActivity.class));
                        finish();
                    } else if (shenfen.equals("2")) {
                        startActivity(new Intent(SplashActivity.this, LvShiMainActivity.class));
                        finish();
                    }
                } else {
                    startActivity(new Intent(SplashActivity.this, LoginCodeActivity.class));
                    finish();
                }
                alertDialog.dismiss();
            }
        });
        Window window = alertDialog.getWindow();
        window.setContentView(view);
        WindowManager m = getWindowManager();
        Display d = m.getDefaultDisplay(); // 获取屏幕宽、高
        WindowManager.LayoutParams attributes = window.getAttributes();
        // 设置宽度
        attributes.width = (int) (d.getWidth() * 0.95); // 宽度设置为屏幕的0.95
        attributes.gravity = Gravity.CENTER;//设置位置
        window.setAttributes(attributes);
    }

    @Override
    protected void onDestroy() {
        handler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }

}
