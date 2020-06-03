package com.ytfu.yuntaifawu.ui.mseeage.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.ytfu.yuntaifawu.R;
import com.ytfu.yuntaifawu.base.BaseActivity;
import com.ytfu.yuntaifawu.base.BasePresenter;
import com.ytfu.yuntaifawu.utils.CommonUtil;
import com.ytfu.yuntaifawu.utils.Eyes;
import com.ytfu.yuntaifawu.utils.GlideManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LvShiDaTuActivity extends BaseActivity {
    @BindView(R.id.iv_fanhui)
    ImageView ivFanhui;
    @BindView(R.id.tv_top_title)
    TextView tvTopTitle;
    @BindView(R.id.lvshi_da_icon)
    ImageView lvshiDaIcon;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_detail_lingyu)
    TextView tvDetailLingyu;
    @BindView(R.id.tv_detail_dengji)
    TextView tvDetailDengji;
    @BindView(R.id.tv_detail_xueli)
    TextView tvDetailXueli;
    @BindView(R.id.tv_detail_nianling)
    TextView tvDetailNianling;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_lvshi_datu;
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
    }


    @Override
    protected void initData() {
        String name = getIntent().getStringExtra("name");
        String lingyu = getIntent().getStringExtra("lingyu");
        String lvshitype = getIntent().getStringExtra("lvshitype");
        String xueli = getIntent().getStringExtra("xueli");
        String cyear = getIntent().getStringExtra("cyear");
        String info_img = getIntent().getStringExtra("info_img");
        if(!TextUtils.isEmpty(name)){
            tvName.setText(name);
        }
        if(!TextUtils.isEmpty(lingyu)){
            tvDetailLingyu.setText(lingyu);
        }
        if(!TextUtils.isEmpty(lvshitype)){
            tvDetailDengji.setText(lvshitype);
        }
        if(!TextUtils.isEmpty(xueli)){
            tvDetailXueli.setText(xueli);
        }
        if(!TextUtils.isEmpty(cyear)){
            tvDetailNianling.setText(cyear);
        }
        if(!TextUtils.isEmpty(info_img)){
            GlideManager.loadImageByUrl(this,info_img,lvshiDaIcon);
        }
    }

    @OnClick(R.id.iv_fanhui)
    public void onViewClicked() {
        finish();
    }
}
