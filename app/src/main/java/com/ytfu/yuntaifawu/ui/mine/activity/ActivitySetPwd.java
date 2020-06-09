package com.ytfu.yuntaifawu.ui.mine.activity;

import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ytfu.yuntaifawu.R;
import com.ytfu.yuntaifawu.app.AppConstant;
import com.ytfu.yuntaifawu.base.BaseActivity;
import com.ytfu.yuntaifawu.ui.mine.bean.SetPwdBean;
import com.ytfu.yuntaifawu.ui.mine.present.SetPwdPresenter;
import com.ytfu.yuntaifawu.ui.mine.view.ISetPwdView;
import com.ytfu.yuntaifawu.utils.SpUtil;
import com.ytfu.yuntaifawu.utils.ToastUtil;
import com.ytfu.yuntaifawu.utils.ValidatorUtil;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Auther gxy
 * @Date 2020/1/3
 * @Des 设置密码
 */
public class ActivitySetPwd extends BaseActivity<ISetPwdView, SetPwdPresenter> implements ISetPwdView {
    @BindView(R.id.iv_fanhui)
    ImageView ivFanhui;
    @BindView(R.id.tv_top_title)
    TextView tvTopTitle;
    @BindView(R.id.tv_qd)
    TextView tvQd;
    @BindView(R.id.et_pwd)
    EditText etPwd;
    @BindView(R.id.iv_yanjing)
    ImageView ivYanjing;
    @BindView(R.id.et_pwd_qr)
    EditText etPwdQr;
    @BindView(R.id.iv_yanjing_qr)
    ImageView ivYanjingQr;
    @BindView(R.id.tv_tishi)
    TextView tvTishi;
    private boolean isHideFirst = true;// 输入框密码是否是隐藏的，默认为true
    private String uid;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_setting_pwd;
    }

    @Override
    protected SetPwdPresenter createPresenter() {
        return new SetPwdPresenter(this);
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
        tvTopTitle.setText("设置密码");
        uid = SpUtil.getString(AppConstant.UID, "");

    }

    @Override
    protected void initData() {

    }


    @OnClick({R.id.iv_fanhui, R.id.tv_qd, R.id.iv_yanjing, R.id.iv_yanjing_qr})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_fanhui:
                finish();
                break;
            case R.id.tv_qd:
                setData();
                break;
            case R.id.iv_yanjing:
                if (isHideFirst == true) {
                    ivYanjing.setImageResource(R.drawable.xiaoyanjingkai);
                    etPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    isHideFirst = false;
                } else {
                    ivYanjing.setImageResource(R.drawable.xiaoyanjingbi);
                    etPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    isHideFirst = true;
                }
                // 光标的位置
                int index = etPwd.getText().toString().length();
                etPwd.setSelection(index);
                break;
            case R.id.iv_yanjing_qr:
                if (isHideFirst == true) {
                    ivYanjingQr.setImageResource(R.drawable.xiaoyanjingkai);
                    etPwdQr.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    isHideFirst = false;
                } else {
                    ivYanjingQr.setImageResource(R.drawable.xiaoyanjingbi);
                    etPwdQr.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    isHideFirst = true;
                }
                // 光标的位置
                int index1 = etPwdQr.getText().toString().length();
                etPwdQr.setSelection(index1);
                break;
        }
    }

    private void setData() {
        String pwd = etPwd.getText().toString().trim();
        String qrPwd = etPwdQr.getText().toString().trim();
        if (TextUtils.isEmpty(pwd) || TextUtils.isEmpty(qrPwd)) {
            tvTishi.setVisibility(View.VISIBLE);
            tvTishi.setText("密码输入为空");
//            showCenterToast("密码输入为空");
        } else {
            tvTishi.setVisibility(View.GONE);
            boolean t_pwd = ValidatorUtil.Validator.isPassword(pwd);
            boolean t_qrPwd = ValidatorUtil.Validator.isPassword(qrPwd);
            if (t_pwd == true || t_qrPwd == true) {
                tvTishi.setVisibility(View.GONE);
                showWaitingDialog("请稍后...", true);
                HashMap<String, String> map = new HashMap<>();
                map.put("uid", uid);
                map.put("pwd", pwd);
                map.put("repwd", qrPwd);
                getPresenter().setPwd(map);
            } else {
                tvTishi.setVisibility(View.VISIBLE);
                tvTishi.setText("密码格式不正确");
            }
        }
    }

    @Override
    public void onSetPwdSuccess(SetPwdBean setPwdBean) {
        hideWaitingDialog();
        if (setPwdBean != null) {
            int status = setPwdBean.getStatus();
            if (status == 1) {
                ToastUtil.showCenterToast("设置成功");
                finish();
            } else if (status == 0) {
                ToastUtil.showCenterToast("设置失败");
            } else if (status == 2) {
                ToastUtil.showCenterToast(setPwdBean.getMsg());
            }
        }
    }

    @Override
    public void onSetPwdFiled() {

    }
}
