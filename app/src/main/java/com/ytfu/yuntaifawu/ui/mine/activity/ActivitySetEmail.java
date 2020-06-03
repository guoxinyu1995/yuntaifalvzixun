package com.ytfu.yuntaifawu.ui.mine.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ytfu.yuntaifawu.R;
import com.ytfu.yuntaifawu.app.AppConstant;
import com.ytfu.yuntaifawu.base.BaseActivity;
import com.ytfu.yuntaifawu.base.BasePresenter;
import com.ytfu.yuntaifawu.ui.mine.bean.SetEmailBean;
import com.ytfu.yuntaifawu.ui.mine.bean.SetNameBean;
import com.ytfu.yuntaifawu.ui.mine.present.SetEmailPresenter;
import com.ytfu.yuntaifawu.ui.mine.view.ISetEmailView;
import com.ytfu.yuntaifawu.ui.mine.view.ISetNameView;
import com.ytfu.yuntaifawu.utils.SpUtil;
import com.ytfu.yuntaifawu.utils.ToastUtil;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Auther gxy
 * @Date 2019/12/30
 * @Des 绑定邮箱
 */
public class ActivitySetEmail extends BaseActivity<ISetEmailView, SetEmailPresenter> implements ISetEmailView {
    @BindView(R.id.iv_fanhui)
    ImageView ivFanhui;
    @BindView(R.id.tv_top_title)
    TextView tvTopTitle;
    @BindView(R.id.tv_qd)
    TextView tvQd;
    @BindView(R.id.et_email)
    EditText etEmail;
    @BindView(R.id.iv_clear)
    ImageView ivClear;
    @BindView(R.id.tv_tishi)
    TextView tvTishi;
    private String uid;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_set_email;
    }

    @Override
    protected SetEmailPresenter createPresenter() {
        return new SetEmailPresenter(this);
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
        tvTopTitle.setText("设置邮箱");
        Intent intent = getIntent();
        String user_email = intent.getStringExtra("user_email");
        etEmail.setText(user_email);
    }

    @Override
    protected void initData() {

    }
    @OnClick({R.id.iv_fanhui, R.id.tv_qd, R.id.iv_clear})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_fanhui:
                finish();
                break;
            case R.id.tv_qd:
                setData();
                break;
            case R.id.iv_clear:
                etEmail.setText("");
                etEmail.requestFocusFromTouch();
                break;
        }
    }

    private void setData() {
        String et_email = etEmail.getText().toString().trim();
        if(TextUtils.isEmpty(et_email)){
            tvTishi.setVisibility(View.VISIBLE);
            tvTishi.setText("邮箱输入为空");
        }else{
            boolean contains = et_email.contains("@");
            if(contains == true){
                showWaitingDialog("请稍后...");
                HashMap<String,String> map = new HashMap<>();
                map.put("uid",uid);
                map.put("email",et_email);
                mPresenter.setEmail(map);
            }else{
                tvTishi.setVisibility(View.VISIBLE);
                tvTishi.setText("请输入正确的邮箱");
            }

        }
    }

    @Override
    public void onSetEmailSuccess(SetEmailBean setEmailBean) {
        hideWaitingDialog();
        if(setEmailBean!=null){
            int status = setEmailBean.getStatus();
            if(status == 1){
                ToastUtil.showCenterToast("修改成功");
                finish();
            }else if(status == 0){
                ToastUtil.showCenterToast("修改失败");
            }else if(status == 2){
                ToastUtil.showCenterToast(setEmailBean.getMsg());
            }
        }
    }

    @Override
    public void onSetEmailFiled() {

    }
}
