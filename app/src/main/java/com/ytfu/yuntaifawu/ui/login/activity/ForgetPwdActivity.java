package com.ytfu.yuntaifawu.ui.login.activity;

import android.content.Intent;
import android.os.Build;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.yanzhenjie.permission.Permission;
import com.ytfu.yuntaifawu.R;
import com.ytfu.yuntaifawu.app.AppConstant;
import com.ytfu.yuntaifawu.base.BaseActivity;
import com.ytfu.yuntaifawu.ui.login.bean.ForgectDosePwdBean;
import com.ytfu.yuntaifawu.ui.login.bean.ForgetSendBean;
import com.ytfu.yuntaifawu.ui.login.presenter.ForgectPwdPresenter;
import com.ytfu.yuntaifawu.ui.login.view.IForgetPwdView;
import com.ytfu.yuntaifawu.ui.register.activity.SetPwdActivity;
import com.ytfu.yuntaifawu.utils.AndPermissionUtil;
import com.ytfu.yuntaifawu.utils.CommonUtil;
import com.ytfu.yuntaifawu.utils.MobileUtil;
import com.ytfu.yuntaifawu.utils.MyCountDownTimer;
import com.ytfu.yuntaifawu.utils.ToastUtil;

import java.util.HashMap;
import java.util.List;

/**
 * @Auther gxy
 * @Date 2019/11/14
 * @Des 忘记密码
 */
public class ForgetPwdActivity extends BaseActivity<IForgetPwdView, ForgectPwdPresenter> implements IForgetPwdView, View.OnClickListener {

    private ImageView icon_finish;
    private EditText et_name;
    private EditText et_code;
    private EditText et_new_pwd;
    private ImageView iv_bi;
    private EditText et_queren_pwd;
    private ImageView im_queren_pwd;
    private Button btn_queren;
    private TextView text_yzm;
    /**
     * 手机号是否正确
     */
    private boolean trueMobile = false;
    /**
     * 是否正在倒计时
     */
    private boolean onTick = false;
    private String second;
    private MyCountDownTimer countDownTimer;
    private int status;
    private boolean isHideFirst = true;// 输入框密码是否是隐藏的，默认为true

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_forget_pwd;
    }

    @Override
    protected ForgectPwdPresenter createPresenter() {
        return new ForgectPwdPresenter(this);
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
        icon_finish = findViewById(R.id.icon_finish);
        et_name = findViewById(R.id.et_name);
        et_code = findViewById(R.id.et_code);
        et_new_pwd = findViewById(R.id.et_new_pwd);
        iv_bi = findViewById(R.id.iv_bi);
        et_queren_pwd = findViewById(R.id.et_queren_pwd);
        im_queren_pwd = findViewById(R.id.im_queren_pwd);
        btn_queren = findViewById(R.id.btn_queren);
        text_yzm = findViewById(R.id.text_yzm);

        icon_finish.setOnClickListener(this);
        iv_bi.setOnClickListener(this);
        im_queren_pwd.setOnClickListener(this);
        btn_queren.setOnClickListener(this);
        text_yzm.setOnClickListener(this);
        initTimer();
    }

    //倒计时
    private void initTimer() {
        countDownTimer = new MyCountDownTimer(AppConstant.GETCODEDURATION, 1000);
        countDownTimer.setCountListener(new MyCountDownTimer.CountListener() {
            @Override
            public void onTick(Long millisUntilFinished) {
                second = String.valueOf(millisUntilFinished / 1000).concat("s");
                text_yzm.setText(String.format(CommonUtil.getString(R.string.resend_code_tips), second));
                text_yzm.setEnabled(false);
                if (!onTick) {
                    onTick = true;
                }
            }

            @Override
            public void onFinish() {
                text_yzm.setText("获取验证码");
                text_yzm.setEnabled(true);
                if (onTick) {
                    onTick = false;
                }
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.icon_finish:
                finish();
                break;
            case R.id.iv_bi:
                if (isHideFirst == true) {
                    iv_bi.setImageResource(R.drawable.xiaoyanjingkai);
                    et_new_pwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    isHideFirst = false;
                } else {
                    iv_bi.setImageResource(R.drawable.xiaoyanjingbi);
                    et_new_pwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    isHideFirst = true;
                }
                // 光标的位置
                int index = et_new_pwd.getText().toString().length();
                et_new_pwd.setSelection(index);
                break;
            case R.id.im_queren_pwd:
                if (isHideFirst == true) {
                    im_queren_pwd.setImageResource(R.drawable.xiaoyanjingkai);
                    et_queren_pwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    isHideFirst = false;
                } else {
                    im_queren_pwd.setImageResource(R.drawable.xiaoyanjingbi);
                    et_queren_pwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    isHideFirst = true;
                }
                // 光标的位置
                int index1 = et_new_pwd.getText().toString().length();
                et_new_pwd.setSelection(index1);
                break;
            case R.id.btn_queren:
                initForgectPwd();
                break;
            case R.id.text_yzm:
                //获取验证码
                AndPermissionUtil.getInstance().requestPermissions(this, new AndPermissionUtil.OnPermissionGranted() {
                    @Override
                    public void onPermissionGranted() {
                        initGetCode();
                    }
                }, Permission.Group.SMS);
                break;
            default:
                break;
        }
    }

    private void initForgectPwd() {
        String mobile = et_name.getText().toString().trim();
        String code = et_code.getText().toString().trim();
        String pwd = et_new_pwd.getText().toString().trim();
        String repwd = et_queren_pwd.getText().toString().trim();
        switch (status) {
            case AppConstant.LOGIN_OLD_USER:
                //去重置
                goForget(mobile, code, pwd, repwd);
                break;
            case AppConstant.LOGIN_NEW_USER:
                //去注册
                goReg(mobile, code, pwd, repwd);
                break;
            case AppConstant.LOGIN_REEOR:
                ToastUtil.showToast("验证码发送失败");
                break;
            default:
                break;
        }
    }

    //去注册
    private void goReg(String mobile, String code, String pwd, String repwd) {
        if (!TextUtils.isEmpty(mobile) || !TextUtils.isEmpty(code) || !TextUtils.isEmpty(pwd) || !TextUtils.isEmpty(repwd)) {
            if (mobile.length() >= 11) {
                if (MobileUtil.isMobileNO(mobile)) {
                    if (code.length() >= 6) {
                        if (pwd.length() >= 6 && pwd.length() <= 12 || repwd.length() >= 6 && repwd.length() <= 12) {
                            Intent intent = new Intent(ForgetPwdActivity.this, LoginCodeActivity.class);
//                            intent.putExtra("mobile",mobile);
//                            intent.putExtra("code",code);
                            startActivity(intent);
                        } else {
                            ToastUtil.showCenterToast("密码为6到12位");
                        }
                    } else {
                        ToastUtil.showCenterToast("请输入6位数据验证码");
                    }
                } else {
                    ToastUtil.showCenterToast("手机号格式不正确");
                }
            } else {

            }
        } else {
            ToastUtil.showCenterToast("输入不能为空");
        }
    }

    //去重置
    private void goForget(String mobile, String code, String pwd, String repwd) {
        if (!TextUtils.isEmpty(mobile) || !TextUtils.isEmpty(code) || !TextUtils.isEmpty(pwd) || !TextUtils.isEmpty(repwd)) {
            if (mobile.length() >= 11) {
                if (MobileUtil.isMobileNO(mobile)) {
                    if (code.length() >= 6) {
                        if (pwd.length() >= 6 && pwd.length() <= 12 || repwd.length() >= 6 && repwd.length() <= 12) {
                            HashMap<String, String> map = new HashMap<>();
                            map.put("mobile", mobile);
                            map.put("code", code);
                            map.put("pwd", pwd);
                            map.put("repwd", repwd);
                            mPresenter.getForgectPwd(map);
                        } else {
                            ToastUtil.showCenterToast("密码为6到12位");
                        }
                    } else {
                        ToastUtil.showCenterToast("请输入6位数据验证码");
                    }
                } else {
                    ToastUtil.showCenterToast("手机号格式不正确");
                }
            } else {

            }
        } else {
            ToastUtil.showCenterToast("输入不能为空");
        }
    }
    private void initGetCode() {
        String mobile = et_name.getText().toString().trim();
        if (TextUtils.isEmpty(mobile)) {
            ToastUtil.showToast("手机号输入为空");
        } else {
            if (mobile.length() >= 11) {
                if (MobileUtil.isMobileNO(mobile)) {
                    trueMobile = true;
//                    countDownTimer.start();
                    HashMap<String, String> map = new HashMap<>();
                    map.put("mobile", mobile);
                    mPresenter.getForgectSend(map);
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
    public void onForgetPwdSuccess(ForgetSendBean forgetSendBean) {
        if (forgetSendBean != null) {
            status = forgetSendBean.getStatus();
            if (status == AppConstant.LOGIN_REEOR) {
                ToastUtil.showCenterToast("验证码发送失败");
            } else if (status == AppConstant.LOGIN_NEW_USER) {
                ToastUtil.showCenterToast("此账号还未注册,请先快捷登录进行注册");
                startActivity(new Intent(ForgetPwdActivity.this, LoginCodeActivity.class));
                finish();
            }
            countDownTimer.start();
        }
    }

    @Override
    public void onForgetPweFilded() {

    }

    @Override
    public void onForgetDosetPwd(ForgectDosePwdBean dosePwdBean) {
        if (dosePwdBean != null) {
            int status = dosePwdBean.getStatus();
            switch (status) {
                case 0:
                    ToastUtil.showCenterToast("重置失败");
                    break;
                case 1:
                    showToast("密码重置成功，请登录");
                    startActivity(new Intent(ForgetPwdActivity.this, LoginPhonePwdActivity.class));
                    finish();
                    break;
                case 2:
                    ToastUtil.showCenterToast(dosePwdBean.getMsg());
                    break;
                default:
                    break;
            }
        }
    }


}
