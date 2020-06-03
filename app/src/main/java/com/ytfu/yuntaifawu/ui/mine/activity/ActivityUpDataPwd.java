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
import com.ytfu.yuntaifawu.ui.mine.bean.UpdataPwdBean;
import com.ytfu.yuntaifawu.ui.mine.present.UpDataPwdPresenter;
import com.ytfu.yuntaifawu.ui.mine.view.IUpdataPwdView;
import com.ytfu.yuntaifawu.utils.SpUtil;
import com.ytfu.yuntaifawu.utils.ToastUtil;
import com.ytfu.yuntaifawu.utils.ValidatorUtil;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Auther gxy
 * @Date 2019/12/30
 * @Des 修改密码
 */
public class ActivityUpDataPwd extends BaseActivity<IUpdataPwdView, UpDataPwdPresenter> implements IUpdataPwdView {
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
    @BindView(R.id.et_jiu_pwd)
    EditText etJiuPwd;
    @BindView(R.id.iv_jiu)
    ImageView ivJiu;
    @BindView(R.id.tv_tishi)
    TextView tvTishi;
    private boolean isHideFirst = true;// 输入框密码是否是隐藏的，默认为true
    private String uid;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_update_pwd;
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
    protected UpDataPwdPresenter createPresenter() {
        return new UpDataPwdPresenter(this);
    }

    @Override
    protected void initView() {
        hideLoading();
        uid = SpUtil.getString(AppConstant.UID, "");
        tvTopTitle.setText("修改密码");
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.iv_fanhui, R.id.tv_qd, R.id.iv_yanjing, R.id.iv_yanjing_qr, R.id.iv_jiu})
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
            case R.id.iv_jiu:
                if (isHideFirst == true) {
                    ivJiu.setImageResource(R.drawable.xiaoyanjingkai);
                    etJiuPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    isHideFirst = false;
                } else {
                    ivJiu.setImageResource(R.drawable.xiaoyanjingbi);
                    etJiuPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    isHideFirst = true;
                }
                // 光标的位置
                int index2 = etJiuPwd.getText().toString().length();
                etJiuPwd.setSelection(index2);
                break;
        }
    }

    private void setData() {
        String new_pwd = etPwd.getText().toString().trim();
        String renew_pwd = etPwdQr.getText().toString().trim();
        String pwd = etJiuPwd.getText().toString().trim();
        if (TextUtils.isEmpty(pwd) || TextUtils.isEmpty(new_pwd) || TextUtils.isEmpty(renew_pwd)) {
            tvTishi.setVisibility(View.VISIBLE);
            tvTishi.setText("密码输入为空");
        } else {
            tvTishi.setVisibility(View.GONE);
            if (new_pwd.equals(renew_pwd)) {
                tvTishi.setVisibility(View.GONE);
                boolean newpwd = ValidatorUtil.Validator.isPassword(new_pwd);
                boolean renewpwd = ValidatorUtil.Validator.isPassword(renew_pwd);
                if (newpwd == true || renewpwd == true) {
                    tvTishi.setVisibility(View.GONE);
                    showWaitingDialog("请稍后...",true);
                    HashMap<String, String> map = new HashMap<>();
                    map.put("uid", uid);
                    map.put("pwd", pwd);
                    map.put("newpwd", new_pwd);
                    map.put("renewpwd", renew_pwd);
                    mPresenter.setUpdataPwd(map);
                } else {
                    tvTishi.setVisibility(View.VISIBLE);
                    tvTishi.setText("密码格式不正确");
                }
            } else {
                tvTishi.setVisibility(View.VISIBLE);
                tvTishi.setText("两次密码输入不一致");
            }

        }
    }


    @Override
    public void onUpDataSuccess(UpdataPwdBean updataPwdBean) {
        if (updataPwdBean != null) {
            int status = updataPwdBean.getStatus();
            if (status == 1) {
                ToastUtil.showCenterToast("修改成功");
                finish();
            } else if (status == 0) {
                ToastUtil.showCenterToast("修改失败");
            } else if (status == 2) {
                ToastUtil.showCenterToast(updataPwdBean.getMsg());
            }
        }
    }

    @Override
    public void onUpDataFiled() {

    }

}
