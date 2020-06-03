package com.ytfu.yuntaifawu.ui.mseeage.activity;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ytfu.yuntaifawu.R;
import com.ytfu.yuntaifawu.base.BaseActivity;
import com.ytfu.yuntaifawu.base.BasePresenter;
import com.ytfu.yuntaifawu.ui.mseeage.other.MJavascriptInterface;
import com.ytfu.yuntaifawu.ui.mseeage.other.MyWebViewClient;
import com.ytfu.yuntaifawu.ui.mseeage.tool.StringUtils;
import com.ytfu.yuntaifawu.utils.CommonUtil;
import com.ytfu.yuntaifawu.utils.Eyes;
import com.ytfu.yuntaifawu.utils.ToastUtil;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.stream.Stream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FwhMessageDetailsActivity extends BaseActivity {
    @BindView(R.id.iv_fanhui)
    ImageView ivFanhui;
    @BindView(R.id.tv_top_title)
    TextView tvTopTitle;
    @BindView(R.id.webView)
    WebView webView;

    private static final String TAG = "FwhMessageDetailsActivity";
    private String html;
    private String[] imageUrls;
    private String url;

    //    private String[] imageUrls = StringUtils.returnImageUrlsFromHtml();
    @Override
    protected int provideContentViewId() {
        return R.layout.activity_fwh_zixun_details;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    public void init() {
        super.init();
        Eyes.setStatusBarColor(this, CommonUtil.getColor(R.color.transparent_4c));
    }

    @Override
    protected void initView() {
        hideLoading();
        url = getIntent().getStringExtra("url");
//        ToastUtil.showToast(url);
//        try {
//            html = getHtml(url);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        ToastUtil.showToast(html);
//        imageUrls = StringUtils.returnImageUrlsFromHtml(html);
    }

    @Override
    protected void initData() {
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setAppCacheEnabled(true);
        webView.getSettings().setDatabaseEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
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
        webView.loadUrl(url);
//        webView.addJavascriptInterface(new MJavascriptInterface(this, imageUrls), "imagelistener");
//        webView.setWebViewClient(new MyWebViewClient());
    }

    /**
     * @param aUrl    网址
     *
     * @return 返回的HTML代码
     * @throws Exception 对外抛出异常
     */
//    public String getHTML(String aUrl) throws Exception {
//        URL url = new URL(aUrl);
//        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//        conn.setConnectTimeout(5000);
//        conn.setRequestMethod("GET");
//        if (conn.getResponseCode() == 200) {
//            InputStream inputStream = conn.getInputStream();
//            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
//            byte[] buffer = new byte[1024];
//            int len = 0;
//            while ((len = inputStream.read(buffer)) != -1) {
//                outStream.write(buffer, 0, len);
//            }
//            String htmlStr = new String(outStream.toByteArray());
//            inputStream.close();
//            outStream.close();
//            return htmlStr;
//        }
//        return null;
//    }

    /**
     * 获取HTML数据
     *
     * @author David
     */

    public static String getHtml(String path) throws Exception {
        // 通过网络地址创建URL对象
        URL url = new URL(path);
        // 根据URL
        // 打开连接，URL.openConnection函数会根据URL的类型，返回不同的URLConnection子类的对象，这里URL是一个http，因此实际返回的是HttpURLConnection
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        // 设定URL的请求类别，有POST、GET 两类
        conn.setRequestMethod("GET");
        //设置从主机读取数据超时（单位：毫秒）
        conn.setConnectTimeout(5000);
        //设置连接主机超时（单位：毫秒）
        conn.setReadTimeout(5000);
        // 通过打开的连接读取的输入流,获取html数据
        InputStream inStream = conn.getInputStream();
        // 得到html的二进制数据
        byte[] data = readInputStream(inStream);
        // 是用指定的字符集解码指定的字节数组构造一个新的字符串
        String html = new String(data, "utf-8");
        return html;
    }

    /**
     * 读取输入流，得到html的二进制数据
     *
     * @param inStream
     * @return
     * @throws Exception
     */
    public static byte[] readInputStream(InputStream inStream) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }
        inStream.close();
        return outStream.toByteArray();
    }

    @OnClick(R.id.iv_fanhui)
    public void onViewClicked() {
        finish();
    }

    @Override
    protected void onDestroy() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Glide.get(FwhMessageDetailsActivity.this).clearDiskCache();//清理磁盘缓存需要在子线程中执行
            }
        }).start();
        Glide.get(this).clearMemory();//清理内存缓存可以在UI主线程中进行
        super.onDestroy();
    }
}
