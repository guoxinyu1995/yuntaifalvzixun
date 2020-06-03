package com.ytfu.yuntaifawu.ui.lawyer.setting.act;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.ytfu.yuntaifawu.R;
import com.ytfu.yuntaifawu.app.AppConstant;
import com.ytfu.yuntaifawu.base.BaseActivity;
import com.ytfu.yuntaifawu.ui.lawyer.setting.p.LawyerSettingPresenter;
import com.ytfu.yuntaifawu.ui.lawyer.setting.v.LawyerSettingView;
import com.ytfu.yuntaifawu.ui.lvshiwode.activity.LawyerAuthenticationManagerActivity;
import com.ytfu.yuntaifawu.ui.mine.activity.ActivityBdPhoneNum;
import com.ytfu.yuntaifawu.ui.mine.activity.ActivityGrzx;
import com.ytfu.yuntaifawu.ui.mine.activity.ActivityGywm;
import com.ytfu.yuntaifawu.ui.mine.activity.ActivitySetPwd;
import com.ytfu.yuntaifawu.ui.mine.activity.ActivitySetting;
import com.ytfu.yuntaifawu.ui.mine.activity.ActivityUpDataPwd;
import com.ytfu.yuntaifawu.ui.mine.bean.InformationBean;
import com.ytfu.yuntaifawu.utils.CommonUtil;
import com.ytfu.yuntaifawu.utils.Eyes;
import com.ytfu.yuntaifawu.utils.LoginHelper;
import com.ytfu.yuntaifawu.utils.SpUtil;
import com.ytfu.yuntaifawu.utils.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class LawyerSettingActivity extends BaseActivity<LawyerSettingView, LawyerSettingPresenter> implements LawyerSettingView {


    @BindView(R.id.tl_setting_toolbar)
    Toolbar tl_setting_toolbar;
    @BindView(R.id.tv_room_title)
    TextView tv_room_title;
    @BindView(R.id.tv_setting_mobile)
    TextView tv_setting_mobile;
    @BindView(R.id.tv_set_pwd)
    TextView tvSetPwd;
    private int mobile_status;
    private int pwd_status;

    public static void start(Context context) {
        Intent starter = new Intent(context, LawyerSettingActivity.class);
        context.startActivity(starter);
    }

    @Override
    public void init() {
        super.init();
        Eyes.setStatusBarColor(this, CommonUtil.getColor(R.color.transparent_4c));
    }

    //===Desc:=================================================================
    @Override
    protected int provideContentViewId() {
        return R.layout.activity_lawyer_setting;
    }

    @Override
    protected LawyerSettingPresenter createPresenter() {
        return new LawyerSettingPresenter();
    }

    @Override
    protected void initView() {
        hideLoading();
    }

    @Override
    protected void onResume() {
        super.onResume();
        String uid = SpUtil.getString(AppConstant.UID, "");
        mPresenter.getInformation(uid);
    }

    @Override
    protected void initData() {
        tv_room_title.setText(R.string.setting);
        tl_setting_toolbar.setTitle("");
        setSupportActionBar(tl_setting_toolbar);
        tl_setting_toolbar.setNavigationIcon(R.drawable.fanhui_bai);
        tl_setting_toolbar.setNavigationOnClickListener(v -> onBackPressed());


        String uid = SpUtil.getString(AppConstant.UID, "");
        mPresenter.getInformation(uid);

    }


    //===Desc:=================================================================

    @Override
    public void onGetInformationSuccess(InformationBean info) {
        //回显手机号码
        String mobile = info.getUser().getMobile();
        mobile_status = info.getMobile_status();
        pwd_status = info.getPwd_status();
        if (mobile_status == 0) {
            tvSetPwd.setVisibility(View.GONE);
            tv_setting_mobile.setText("");
        } else {
            tvSetPwd.setVisibility(View.VISIBLE);
            if (TextUtils.isEmpty(mobile)) {
                tv_setting_mobile.setText("");
            } else {
                tv_setting_mobile.setText(mobile);
            }
            if (pwd_status == 0) {
                tvSetPwd.setText("设置密码");
            } else if (pwd_status == 1) {
                tvSetPwd.setText("修改密码");
            }
        }

    }

    @Override
    public void onGetInformationFail(String msg) {
        ToastUtil.showToast(msg);

    }

    @OnClick({R.id.tv_lvshi_renzheng_guanli, R.id.tv_set_pwd, R.id.tv_phone_number, R.id.tv_guanyuwomen, R.id.tv_tuichu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_lvshi_renzheng_guanli:
                //律师认证管理
                LawyerAuthenticationManagerActivity.stareActivity(this);
                break;
            case R.id.tv_set_pwd:
                //修改密码
                if (pwd_status == 0) {
                    startActivity(new Intent(LawyerSettingActivity.this, ActivitySetPwd.class));
                } else if (pwd_status == 1) {
                    startActivity(new Intent(LawyerSettingActivity.this, ActivityUpDataPwd.class));
                }
                break;
            case R.id.tv_phone_number:
                //绑定手机号
                if (mobile_status == 0) {
                    startActivity(new Intent(LawyerSettingActivity.this, ActivityBdPhoneNum.class));
                } else if (mobile_status == 1) {
                    showCenterToast("你已绑定过手机号");
                }
                break;
            case R.id.tv_guanyuwomen:
                //关于我们
                startActivity(new Intent(LawyerSettingActivity.this, ActivityGywm.class));
                break;
            case R.id.tv_tuichu:
                //退出登录
                showWaitingDialog("退出登录...", false);
                LoginHelper.getInstance().logout(this);
                break;
        }
    }
}
