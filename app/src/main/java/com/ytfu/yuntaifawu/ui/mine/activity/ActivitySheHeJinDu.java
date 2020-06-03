package com.ytfu.yuntaifawu.ui.mine.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.ytfu.yuntaifawu.R;
import com.ytfu.yuntaifawu.base.BaseActivity;
import com.ytfu.yuntaifawu.base.BasePresenter;
import com.ytfu.yuntaifawu.utils.GlideManager;
import com.ytfu.yuntaifawu.utils.LoginHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Auther gxy
 * @Date 2020/5/21
 * @Des 律师认证审核进度
 */
public class ActivitySheHeJinDu extends BaseActivity {

    @BindView(R.id.iv_fanhui)
    ImageView ivFanhui;
    @BindView(R.id.tv_top_title)
    TextView tvTopTitle;
    @BindView(R.id.iv_jindu_hesder)
    ImageView ivJinduHesder;
    @BindView(R.id.tv_jindu_name)
    TextView tvJinduName;
    @BindView(R.id.v1)
    View v1;
    @BindView(R.id.iv_ziliao)
    ImageView ivZiliao;
    @BindView(R.id.iv_zash)
    ImageView ivZash;
    @BindView(R.id.iv_zhuangtai)
    ImageView ivZhuangtai;
    @BindView(R.id.tv_ziliao)
    TextView tvZiliao;
    @BindView(R.id.tv_shenhe)
    TextView tvShenhe;
    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.tv_yuanyin)
    TextView tvYuanyin;
    @BindView(R.id.tv_tishi)
    TextView tvTishi;
    @BindView(R.id.tv_dianji)
    TextView tvDianji;
    private int status;
    private String name;
    private String phone;
    private String liyou;
    private String yuanyin;

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
    protected int provideContentViewId() {
        return R.layout.activity_lvshi_shenhejindu;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    protected void initView() {
        hideLoading();
        tvTopTitle.setText("审核进度查询");
        status = getIntent().getIntExtra("status", 10);
        name = getIntent().getStringExtra("name");
        phone = getIntent().getStringExtra("photo");
        liyou = getIntent().getStringExtra("liyou");
        yuanyin = getIntent().getStringExtra("yuanyin");
    }

    @Override
    protected void initData() {
        tvJinduName.setText(name);
        tvTishi.setText(liyou);
        GlideManager.loadImageByUrl(this,phone,ivJinduHesder);
        switch (status) {
            case 1:
                ivZhuangtai.setImageResource(R.drawable.shenhe_tongguo);
                tvYuanyin.setVisibility(View.GONE);
                tvType.setText("审核通过");
                break;
            case 2:
                ivZhuangtai.setImageResource(R.drawable.shenghe_zhong);
                tvYuanyin.setVisibility(View.GONE);
                tvType.setText("审核中");
                tvDianji.setVisibility(View.GONE);
                break;
            case 3:
                ivZhuangtai.setImageResource(R.drawable.shenghe_weitongguo);
                tvType.setText("审核未通过");
                tvYuanyin.setVisibility(View.VISIBLE);
                tvYuanyin.setText(yuanyin);
                tvType.setTextColor(Color.parseColor("#F9383C"));
                tvDianji.setText("重新提交审核");
                break;
        }
    }

    @OnClick({R.id.iv_fanhui, R.id.tv_dianji})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_fanhui:
                finish();
                break;
            case R.id.tv_dianji:
                switch (status){
                    case 1:
                        // 退出登录
                        showWaitingDialog("退出登录...", false);
                        LoginHelper.getInstance().logout(this);
                        break;
                    case 3:
                        startActivity(new Intent(ActivitySheHeJinDu.this, ActivityLvShiRenZheng.class));
                        finish();
                        break;
                }
                break;
        }
    }
}
