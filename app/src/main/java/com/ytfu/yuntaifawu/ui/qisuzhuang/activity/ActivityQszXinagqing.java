package com.ytfu.yuntaifawu.ui.qisuzhuang.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ytfu.yuntaifawu.R;
import com.ytfu.yuntaifawu.app.AppConstant;
import com.ytfu.yuntaifawu.base.BaseActivity;
import com.ytfu.yuntaifawu.base.BasePresenter;
import com.ytfu.yuntaifawu.utils.SpUtil;
import com.ytfu.yuntaifawu.utils.ToastUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ActivityQszXinagqing extends BaseActivity {
    @BindView(R.id.iv_fanhui)
    ImageView ivFanhui;
    @BindView(R.id.tv_top_title)
    TextView tvTopTitle;
    @BindView(R.id.qsz_web)
    LinearLayout qszWeb;
    @BindView(R.id.web_view)
    WebView webView;
    private String id;
    private String label;
//    private WebView webView;
    private String uid;
    private String href;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_qsz_xingqing;
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
        uid = SpUtil.getString(AppConstant.UID, "");
        Log.e("uid", "initView: " + uid);
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        label = intent.getStringExtra("label");
        href = intent.getStringExtra("href");
        tvTopTitle.setText(label);
        initWebView();
    }

    @SuppressLint("WrongConstant")
    private void initWebView() {
//        webView = new WebView(getApplicationContext());
//        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//        webView.setLayoutParams(params);
//        webView.setOverScrollMode(View.OVER_SCROLL_NEVER);
//        webView.setScrollBarStyle(View.AUTOFILL_TYPE_NONE);
//        webView.setOverScrollMode(0);
//        qszWeb.addView(webView);
        //不跳转到其他浏览器
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                // 其他情况则使用系统浏览器打开网址
//                Uri uri = Uri.parse(url);
//                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//                startActivity(intent);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }
        });
        // webview必须设置支持Javascript才可打开
        webView.getSettings().setJavaScriptEnabled(true);
        // 设置此属性,可任意比例缩放
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setDisplayZoomControls(false);
        webView.getSettings().setAllowFileAccessFromFileURLs(true);
        webView.setHorizontalScrollBarEnabled(false);
        webView.addJavascriptInterface(new JavascriptInterface(this), "android");
    }

    @Override
    protected void initData() {
//        webView.loadUrl("https://view.officeapps.live.com/op/view.aspx?src="+url);
//        String url = "http://yuntaifawu.com/ssz/index?qingqiu_type=" + id + "&uid=" + uid + "&xt=" + 1;
//        Log.e("weburl", "++++++" + href);
        String url = href + "&uid=" + uid + "&xt=" + 1;
//        Log.e("weburl", "++++++url" + url);
        webView.loadUrl(url);

//        requestPermission(url);
    }
    @OnClick(R.id.iv_fanhui)
    public void onViewClicked() {
        showRemind("退出后将清空所有内容是否退出");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        webView.stopLoading();
//        webView.removeAllViews();
//        webView.destroy();
//        webView = null;
    }

    //共h5调用的跳转方法
    public class JavascriptInterface {
        private Context mContext;

        public JavascriptInterface(Context mContext) {
            this.mContext = mContext;
        }

        @android.webkit.JavascriptInterface
        public void jumpActivity(String id) {
            Log.e("sid", "-------" + id);
            Intent intent = new Intent(ActivityQszXinagqing.this, ActivityQszXqClassify.class);
            intent.putExtra("id", id);
            startActivity(intent);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            showRemind("退出后将清空所有内容是否退出");
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
