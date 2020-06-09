package com.ytfu.yuntaifawu.ui.home.activity;

import android.os.Build;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ytfu.yuntaifawu.R;
import com.ytfu.yuntaifawu.base.BaseActivity;
import com.ytfu.yuntaifawu.ui.home.adaper.AudioFenleiAdaper;
import com.ytfu.yuntaifawu.ui.home.bean.AudioNavBean;
import com.ytfu.yuntaifawu.ui.home.presenter.NavPresenter;
import com.ytfu.yuntaifawu.ui.home.view.INavView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @Auther gxy
 * @Date 2019/11/19
 * @Des 音频分类
 */
public class ActivityClassification extends BaseActivity<INavView, NavPresenter>implements INavView {
    @BindView(R.id.iv_fanhui)
    ImageView ivFanhui;
    @BindView(R.id.tv_top_title)
    TextView tvTopTitle;
    @BindView(R.id.tv_tishi)
    TextView tvTishi;
    @BindView(R.id.recycle_fenlei)
    RecyclerView recycleFenlei;
    private AudioFenleiAdaper fenleiAdaper;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_classificatopn;

    }

    @Override
    protected NavPresenter createPresenter() {
        return new NavPresenter(this);
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
//        hideLoading();
        tvTopTitle.setText("咨询");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycleFenlei.setLayoutManager(linearLayoutManager);
    }

    @Override
    protected void onMyReload(View v) {
        super.onMyReload(v);
        showLoading();
        getPresenter().getNavTitle();
    }

    @Override
    protected void initData() {
        fenleiAdaper = new AudioFenleiAdaper(this);
        recycleFenlei.setAdapter(fenleiAdaper);
        getPresenter().getNavTitle();
    }
    @OnClick(R.id.iv_fanhui)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void onNavSuccess(AudioNavBean navBean) {
        hideLoading();
        if(navBean.getList() == null){
            showEmpty();
        }else{
            tvTishi.setText("已有"+navBean.getZongcount()+"人咨询");
            fenleiAdaper.setmList(navBean.getList());
        }
    }
    @Override
    public void onNavFalied() {

    }
}
