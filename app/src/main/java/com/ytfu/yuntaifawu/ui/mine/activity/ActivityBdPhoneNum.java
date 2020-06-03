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

import com.yanzhenjie.permission.Permission;
import com.ytfu.yuntaifawu.R;
import com.ytfu.yuntaifawu.app.AppConstant;
import com.ytfu.yuntaifawu.base.BaseActivity;
import com.ytfu.yuntaifawu.base.BasePresenter;
import com.ytfu.yuntaifawu.ui.mine.bean.PhoneNumBdBean;
import com.ytfu.yuntaifawu.ui.mine.bean.SendBdCodeBean;
import com.ytfu.yuntaifawu.ui.mine.present.PhoneNumPresenter;
import com.ytfu.yuntaifawu.ui.mine.view.IPhoneNumBdView;
import com.ytfu.yuntaifawu.utils.AndPermissionUtil;
import com.ytfu.yuntaifawu.utils.CommonUtil;
import com.ytfu.yuntaifawu.utils.MobileUtil;
import com.ytfu.yuntaifawu.utils.MyCountDownTimer;
import com.ytfu.yuntaifawu.utils.SpUtil;
import com.ytfu.yuntaifawu.utils.ToastUtil;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Auther gxy
 * @Date 2019/12/27
 * @Des 绑定手机号
 */
public class ActivityBdPhoneNum extends BaseActivity<IPhoneNumBdView, PhoneNumPresenter> implements IPhoneNumBdView {
    @BindView(R.id.iv_fanhui)
    ImageView ivFanhui;
    @BindView(R.id.tv_top_title)
    TextView tvTopTitle;
    @BindView(R.id.tv_qd)
    TextView tvQd;
    @BindView(R.id.et_num)
    EditText etNum;
    @BindView(R.id.iv_clear)
    ImageView ivClear;
    @BindView(R.id.et_yzm)
    EditText etYzm;
    @BindView(R.id.tv_hq)
    TextView tvHq;
    /**
     * 手机号是否正确
     */
    private boolean trueMobile = false;
    /**
     * 是否正在倒计时
     */
    private boolean onTick = false;
    private String uid;
    private String second;
    private MyCountDownTimer countDownTimer;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_bd_phone_num;
    }

    @Override
    protected PhoneNumPresenter createPresenter() {
        return new PhoneNumPresenter(this);
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
        tvTopTitle.setText("绑定手机号");

    }

    private void initTimer() {
        countDownTimer = new MyCountDownTimer(AppConstant.GETCODEDURATION, 1000);
        countDownTimer.setCountListener(new MyCountDownTimer.CountListener() {
            @Override
            public void onTick(Long millisUntilFinished) {
                second = String.valueOf(millisUntilFinished / 1000).concat("s");
//                try {
                    tvHq.setText(String.format(CommonUtil.getString(R.string.resend_code_tips), second));
                    tvHq.setEnabled(false);
                    if (!onTick) {
                        onTick = true;
                    }
//                }catch (Exception e){
//                    e.printStackTrace();
//                }
            }

            @Override
            public void onFinish() {
                tvHq.setText("获取验证码");
                tvHq.setEnabled(true);
                if (onTick) {
                    onTick = false;
                }
            }
        });
    }

    @Override
    protected void initData() {
        initTimer();
    }

    @OnClick({R.id.iv_fanhui, R.id.tv_qd, R.id.iv_clear, R.id.tv_hq})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_fanhui:
                finish();
                break;
            case R.id.tv_qd:
                setData();
                break;
            case R.id.iv_clear:
                etNum.setText("");
                etNum.requestFocusFromTouch();
                break;
            case R.id.tv_hq:
                //获取验证码
                AndPermissionUtil.getInstance().requestPermissions(this, new AndPermissionUtil.OnPermissionGranted() {
                    @Override
                    public void onPermissionGranted() {
                        getCode();
                    }
                }, Permission.Group.SMS);
                break;
        }
    }

    private void setData() {
        String phoneNum = etNum.getText().toString().trim();
        String code = etYzm.getText().toString().trim();
        if(TextUtils.isEmpty(phoneNum) || TextUtils.isEmpty(code)){
            showToast("手机号或验证码为空");
        }else{
            if (phoneNum.length() >= 11) {
                if (MobileUtil.isMobileNO(phoneNum)) {
                    trueMobile = true;
//                    if (TextUtils.isEmpty(code)) {
//                        ToastUtil.showToast("请输入验证码");
//                    } else {
                    if (code.length() >= 6) {
                        HashMap<String, String> map = new HashMap<>();
                        map.put("uid", uid);
                        map.put("mobile", phoneNum);
                        map.put("code", code);
                        mPresenter.setPhoneNum(map);
                    } else {
                        ToastUtil.showToast("验证码输入错误");
//                        }
                    }
                } else {
                    trueMobile = false;
                    ToastUtil.showToast("手机号格式不正确");
                }
            }
        }
    }
    //获取验证码
    private void getCode() {
        String num = etNum.getText().toString().trim();
        if (TextUtils.isEmpty(num)) {
            ToastUtil.showToast("手机号输入为空");
        } else {
            if (num.length() >= 11) {
                if (MobileUtil.isMobileNO(num)) {
                    trueMobile = true;
//                    countDownTimer.start();
                    HashMap<String, String> map = new HashMap<>();
                    map.put("uid",uid);
                    map.put("mobile", num);
                    mPresenter.setSendCode(map);
//                    initTimer();
//                    ToastUtil.showToast("手机号格式正确");
                } else {
                    trueMobile = false;
                    ToastUtil.showToast("手机号格式不正确");
                }
            }
        }
    }

    @Override
    public void onSendCodeSuccess(SendBdCodeBean sendBdCodeBean) {
        if (sendBdCodeBean != null) {
            int status = sendBdCodeBean.getStatus();
            if (status == 1) {
                countDownTimer.start();
            } else {
                ToastUtil.showToast("验证码发送失败");
            }
        }
    }

    @Override
    public void onPhoneNumSuccess(PhoneNumBdBean phoneNumBdBean) {
        if(phoneNumBdBean!=null){
            int status = phoneNumBdBean.getStatus();
            if (status == 1) {
                ToastUtil.showCenterToast("绑定成功");
//                countDownTimer.onFinish();
//                startActivity(new Intent(ActivityBdPhoneNum.this,ActivityGrzx.class));
                finish();
            } else if (status == 0) {
                ToastUtil.showCenterToast("绑定失败");
            } else if (status == 2) {
                ToastUtil.showCenterToast(phoneNumBdBean.getMsg());
            }
        }
    }

    @Override
    public void onBdFiled() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(countDownTimer!=null){
            countDownTimer.cancel();
        }
    }
}
