package com.ytfu.yuntaifawu.ui.mine.activity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.ytfu.yuntaifawu.R;
import com.ytfu.yuntaifawu.base.BaseActivity;
import com.ytfu.yuntaifawu.base.BasePresenter;
import com.ytfu.yuntaifawu.ui.register.activity.ActivityYhxy;
import com.ytfu.yuntaifawu.ui.register.activity.ActivityYs;
import com.ytfu.yuntaifawu.utils.LoginHelper;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @Auther gxy
 * @Date 2019/12/27
 * @Des 设置
 */
public class ActivitySetting extends BaseActivity {
    @BindView(R.id.iv_fanhui)
    ImageView ivFanhui;
    @BindView(R.id.tv_top_title)
    TextView tvTopTitle;
    @BindView(R.id.tv_gywm)
    TextView tvGywm;
    @BindView(R.id.iv_rg1)
    ImageView ivRg1;
    @BindView(R.id.tv_banben)
    TextView tvBanben;
    @BindView(R.id.cl_gywm)
    ConstraintLayout clGywm;
    @BindView(R.id.tv_yhfwxy)
    TextView tvYhfwxy;
    @BindView(R.id.iv_rg2)
    ImageView ivRg2;
    @BindView(R.id.cl_yhfwxy)
    ConstraintLayout clYhfwxy;
    @BindView(R.id.tv_ysxy)
    TextView tvYsxy;
    @BindView(R.id.iv_rg3)
    ImageView ivRg3;
    @BindView(R.id.cl_ysxy)
    ConstraintLayout clYsxy;
    @BindView(R.id.btn_tuichu)
    Button btnTuichu;
    @Override
    protected int provideContentViewId() {
        return R.layout.activity_setting;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }
    @Override
    public void init() {
        super.init();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
//      window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.transparent_half));

        }
    }
    @Override
    protected void initView() {
        hideLoading();
        tvTopTitle.setText("设置");
    }

    @Override
    protected void initData() {
        try {
            PackageManager manager = this.getPackageManager();
            PackageInfo info = manager.getPackageInfo(this.getPackageName(), 0);
            String version = info.versionName;
            tvBanben.setText("版本 " + version);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClick({R.id.iv_fanhui, R.id.cl_gywm, R.id.cl_yhfwxy, R.id.cl_ysxy,R.id.btn_tuichu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_fanhui:
                finish();
                break;
            case R.id.cl_gywm:
                startActivity(new Intent(ActivitySetting.this, ActivityGywm.class));
                break;
            case R.id.cl_yhfwxy:
                startActivity(new Intent(ActivitySetting.this, ActivityYhxy.class));
                break;
            case R.id.cl_ysxy:
                startActivity(new Intent(ActivitySetting.this, ActivityYs.class));
                break;
            case R.id.btn_tuichu:
                // 退出登录
                showWaitingDialog("退出登录...", false);
                LoginHelper.getInstance().logout(this);
                break;
        }
    }
}
