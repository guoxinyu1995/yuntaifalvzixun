package com.ytfu.yuntaifawu.ui.mine.activity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.ytfu.yuntaifawu.R;
import com.ytfu.yuntaifawu.base.BaseActivity;
import com.ytfu.yuntaifawu.base.BasePresenter;
import com.ytfu.yuntaifawu.ui.register.activity.ActivityYhxy;
import com.ytfu.yuntaifawu.ui.register.activity.ActivityYs;

import butterknife.BindView;
import butterknife.OnClick;
/**
*
*  @Auther  gxy
*
*  @Date    2019/12/27
*
*  @Des  关于我们
*
*/
public class ActivityGywm extends BaseActivity {
    @BindView(R.id.iv_fanhui)
    ImageView ivFanhui;
    @BindView(R.id.tv_top_title)
    TextView tvTopTitle;
    @BindView(R.id.tv_version)
    TextView tvVersion;
    @BindView(R.id.tv_yhxy)
    TextView tvYhxy;
    @BindView(R.id.tv_yisi)
    TextView tvYisi;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_gywm;
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
        tvTopTitle.setText("关于我们");
    }

    @Override
    protected void initData() {
        try {
            PackageManager manager = this.getPackageManager();
            PackageInfo info = manager.getPackageInfo(this.getPackageName(), 0);
            String version = info.versionName;
            tvVersion.setText("Version " + version);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClick({R.id.tv_yhxy, R.id.tv_yisi,R.id.iv_fanhui})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_yhxy:
                startActivity(new Intent(ActivityGywm.this, ActivityYhxy.class));
                break;
            case R.id.tv_yisi:
                startActivity(new Intent(ActivityGywm.this, ActivityYs.class));
                break;
            case R.id.iv_fanhui:
                finish();
                break;
                default:
                    break;
        }
    }
}
