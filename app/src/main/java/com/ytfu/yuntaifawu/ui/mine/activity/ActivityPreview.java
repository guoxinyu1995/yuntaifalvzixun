package com.ytfu.yuntaifawu.ui.mine.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ytfu.yuntaifawu.R;
import com.ytfu.yuntaifawu.base.BaseActivity;
import com.ytfu.yuntaifawu.base.BasePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Auther gxy
 * @Date 2019/11/18
 * @Des 预览
 */
public class ActivityPreview extends BaseActivity {
    @BindView(R.id.lin_webview)
    LinearLayout linWebview;
    @BindView(R.id.iv_fanhui)
    ImageView ivFanhui;
    @BindView(R.id.tv_top_title)
    TextView tvTopTitle;
    private WebView webView;
    private String url;
    private String htmlSavePath;


    @Override
    protected int provideContentViewId() {
        return R.layout.activity_preview;
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
        Intent intent = getIntent();
//        url = intent.getStringExtra("path");
        url = intent.getStringExtra("url");
        Log.e("url", "initView: -----url" + url);
//        initWord();
        initWebView();
    }

//    private void initWord() {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                String htmlFilePath = Environment.getExternalStorageDirectory() + "/Pictures/html";
//                String htmlFileName = "picTextDoc";
//                BasicSet basicSet = new BasicSet(ActivityPreview.this,url,htmlFilePath,htmlFileName);
//                htmlSavePath = WordUtils.getInstance(basicSet).word2html();
//                Log.e("htmlSavePath", "initView: -----htmlSavePath"+htmlSavePath);
//            }
//        }).start();
//        initWebView();
//    }

    private void initWebView() {
        tvTopTitle.setText("预览");
        webView = new WebView(getApplicationContext());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        webView.setLayoutParams(params);
        linWebview.addView(webView);
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
        webView.getSettings().setAllowFileAccessFromFileURLs(true);
        webView.loadUrl("https://view.officeapps.live.com/op/view.aspx?src=" + url);
//        webView.loadUrl("https://docs.google.com/viewer?url="+url);
//        webView.loadUrl("file://"+url);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        linWebview.removeView(webView);
        webView.stopLoading();
        webView.removeAllViews();
        webView.destroy();
        webView = null;
    }
    @OnClick(R.id.iv_fanhui)
    public void onViewClicked() {
        finish();
    }
}
