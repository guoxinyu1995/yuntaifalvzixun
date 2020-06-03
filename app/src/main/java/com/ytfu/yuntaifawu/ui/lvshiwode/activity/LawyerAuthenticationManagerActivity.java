package com.ytfu.yuntaifawu.ui.lvshiwode.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.ytfu.yuntaifawu.R;
import com.ytfu.yuntaifawu.apis.HttpUtil;
import com.ytfu.yuntaifawu.app.AppConstant;
import com.ytfu.yuntaifawu.base.BaseActivity;
import com.ytfu.yuntaifawu.base.BasePresenter;
import com.ytfu.yuntaifawu.helper.BaseRxObserver;
import com.ytfu.yuntaifawu.helper.RxLifecycleUtil;
import com.ytfu.yuntaifawu.ui.lvshiwode.bean.LawyreManagerBean;
import com.ytfu.yuntaifawu.ui.updatapk.UpDateApkBean;
import com.ytfu.yuntaifawu.utils.ApkUtil;
import com.ytfu.yuntaifawu.utils.CommonUtil;
import com.ytfu.yuntaifawu.utils.Eyes;
import com.ytfu.yuntaifawu.utils.GlideManager;
import com.ytfu.yuntaifawu.utils.SpUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @Auther gxy
 * @Date 2020/5/27
 * @Des 律师认证管理
 */
public class LawyerAuthenticationManagerActivity extends BaseActivity {
    @BindView(R.id.iv_fanhui)
    ImageView ivFanhui;
    @BindView(R.id.tv_top_title)
    TextView tvTopTitle;
    @BindView(R.id.iv_lvshi_header)
    ImageView ivLvshiHeader;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_shen_id)
    TextView tvShenId;
    @BindView(R.id.tv_lvshi_xy)
    TextView tvLvshiXy;
    @BindView(R.id.rl_card)
    RelativeLayout rlCard;
    @BindView(R.id.tv_lvshi_xinxi)
    TextView tvLvshiXinxi;
    private String uid;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_lawyer_authentiction_manager;
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    public static void stareActivity(Context context) {
        Intent intent = new Intent(context, LawyerAuthenticationManagerActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void init() {
        super.init();
        Eyes.setStatusBarColor(this, CommonUtil.getColor(R.color.transparent_4c));
    }

    @Override
    protected void initView() {
        hideLoading();
        tvTopTitle.setText("律师认证管理");
        uid = SpUtil.getString(AppConstant.UID, "");
    }

    @Override
    protected void initData() {
        setData();
    }

    private void setData() {
        HttpUtil.getInstance().getApiService().setLawyerManager(uid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(RxLifecycleUtil.bindLifecycle(this))
                .subscribe(new BaseRxObserver<LawyreManagerBean>() {
                    @Override
                    public void onNextImpl(LawyreManagerBean managerBean) {
                        if (null != managerBean) {
                            GlideManager.loadImageByUrl(LawyerAuthenticationManagerActivity.this, managerBean.getInfo().getPicurl(), ivLvshiHeader);
                            tvName.setText(managerBean.getInfo().getName());
                            tvShenId.setText(managerBean.getInfo().getCard());
                        }
                    }

                    @Override
                    public void onErrorImpl(String errorMessage) {
                        Logger.e(errorMessage);
                    }
                });
    }

    @OnClick({R.id.iv_fanhui, R.id.tv_lvshi_xy, R.id.tv_lvshi_xinxi})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_fanhui:
                finish();
                break;
            case R.id.tv_lvshi_xy:
                PrivacyGuaranteeAgreementActivity.startAt(this);
                break;
            case R.id.tv_lvshi_xinxi:
                LawyerRenZhengInforActivity.startAt(this);
                break;
        }
    }
}
