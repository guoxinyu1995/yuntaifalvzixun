package com.ytfu.yuntaifawu.ui.register.activity;

import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.ytfu.yuntaifawu.R;
import com.ytfu.yuntaifawu.base.BaseActivity;
import com.ytfu.yuntaifawu.base.BasePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Auther gxy
 * @Date 2019/11/25
 * @Des 阅读条款
 */
public class ActivityYs extends BaseActivity {
    @BindView(R.id.web_text)
    WebView webText;
    @BindView(R.id.iv_fanhui)
    ImageView ivFanhui;
    @BindView(R.id.tv_top_title)
    TextView tvTopTitle;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_tdtk;
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
        tvTopTitle.setText("隐私协议");
        webText.setHorizontalScrollBarEnabled(false);//水平不显示
        webText.setVerticalScrollBarEnabled(false); //垂直不显示
        webText.loadUrl("http://www.yuntaifawu.com/index/yinsi");
    }

    @Override
    protected void initData() {

    }


    @OnClick(R.id.iv_fanhui)
    public void onViewClicked() {
        finish();
    }
}
