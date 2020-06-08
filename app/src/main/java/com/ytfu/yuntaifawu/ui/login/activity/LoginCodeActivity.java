package com.ytfu.yuntaifawu.ui.login.activity;

import android.content.Intent;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;


import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.yanzhenjie.permission.Permission;
import com.ytfu.yuntaifawu.R;
import com.ytfu.yuntaifawu.app.App;
import com.ytfu.yuntaifawu.app.AppConstant;
import com.ytfu.yuntaifawu.base.BaseActivity;
import com.ytfu.yuntaifawu.ui.LvShiMainActivity;
import com.ytfu.yuntaifawu.ui.MainActivity;
import com.ytfu.yuntaifawu.ui.login.bean.WxLoginBean;
import com.ytfu.yuntaifawu.ui.login.presenter.CodePresent;
import com.ytfu.yuntaifawu.ui.login.view.ICodeView;
import com.ytfu.yuntaifawu.ui.login.bean.CodeBean;
import com.ytfu.yuntaifawu.ui.login.bean.CodeLoginBean;
import com.ytfu.yuntaifawu.ui.register.activity.ActivityYhxy;
import com.ytfu.yuntaifawu.ui.register.activity.ActivityYs;
import com.ytfu.yuntaifawu.ui.register.activity.SetPwdActivity;
import com.ytfu.yuntaifawu.utils.AndPermissionUtil;
import com.ytfu.yuntaifawu.utils.CommonUtil;
import com.ytfu.yuntaifawu.utils.LoginHelper;
import com.ytfu.yuntaifawu.utils.MessageEvent;
import com.ytfu.yuntaifawu.utils.MobileUtil;
import com.ytfu.yuntaifawu.utils.MyCountDownTimer;
import com.ytfu.yuntaifawu.utils.SpUtil;
import com.ytfu.yuntaifawu.utils.ToastUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.List;

/**
 * @Auther gxy
 * @Date 2019/11/14
 * @Des 验证码登录Activity
 */
public class LoginCodeActivity extends BaseActivity<ICodeView, CodePresent> implements ICodeView, View.OnClickListener {

    private EditText et_name;
    private EditText et_code;
    private TextView text_yzm;
    private TextView tv_zhnaghao;
    private Button btn_login;
    private ImageView icon_finish, iv_weixin;
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
    private IWXAPI wxapi;
    private boolean checked;
    private TextView tv_tiaokuan, tv_yhxy, tv_ys;
    private CheckBox cb_ty;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_code_login;
    }

    @Override
    protected CodePresent createPresenter() {
        return new CodePresent(this);
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
        EventBus.getDefault().register(this);
//        wxapi = WXAPIFactory.createWXAPI(this, AppConstant.WX_APP_ID, true);
//        wxapi.registerApp(AppConstant.WX_APP_ID);
        et_name = findViewById(R.id.et_name);
        et_code = findViewById(R.id.et_code);
        text_yzm = findViewById(R.id.text_yzm);
        tv_zhnaghao = findViewById(R.id.tv_zhnaghao);
        btn_login = findViewById(R.id.btn_login);
        icon_finish = findViewById(R.id.icon_finish);
        iv_weixin = findViewById(R.id.iv_weixin);

        tv_zhnaghao.setOnClickListener(this);
        btn_login.setOnClickListener(this);
        icon_finish.setOnClickListener(this);
        iv_weixin.setOnClickListener(this);
        text_yzm.setOnClickListener(this);

        tv_yhxy = findViewById(R.id.tv_yhxy);
        tv_ys = findViewById(R.id.tv_ys);
        cb_ty = findViewById(R.id.cb_ty);
        tv_yhxy.setOnClickListener(this);
        tv_ys.setOnClickListener(this);
        cb_ty.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
//                    btn_queren.setEnabled(true);
                    checked = true;
                } else {
//                    btn_queren.setEnabled(false);
                    checked = false;
                }
            }
        });
        initTimer();
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onBackPressed() {
        MainActivity.start(this, 0);
        super.onBackPressed();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_zhnaghao:
                Intent intent = new Intent(LoginCodeActivity.this, LoginPhonePwdActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_login:
                //验证码登录
                initGetLoginCode();
                break;
            case R.id.icon_finish:
                finish();
                break;
            case R.id.text_yzm:
                //获取验证码
//                requestPermission(text_yzm);
                AndPermissionUtil.getInstance().requestPermissions(this, new AndPermissionUtil.OnPermissionGranted() {
                    @Override
                    public void onPermissionGranted() {
                        initGetCode();
                    }
                }, Permission.Group.SMS);
                break;
            case R.id.iv_weixin:
                //微信登录
                AndPermissionUtil.getInstance().requestPermissions(this, new AndPermissionUtil.OnPermissionGranted() {
                    @Override
                    public void onPermissionGranted() {
                        showWaitingDialog("登录中...", true);
                        wxLogin();
                    }
                }, Permission.Group.STORAGE);
                break;
            case R.id.tv_yhxy:
                startActivity(new Intent(LoginCodeActivity.this, ActivityYhxy.class));
                break;
            case R.id.tv_ys:
                startActivity(new Intent(LoginCodeActivity.this, ActivityYs.class));
                break;
            default:
                break;
        }
    }


    private void wxLogin() {
//        if (!wxapi.isWXAppInstalled()) {
//            showToast("您的设备未安装微信客户端");
//        } else {
        SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = String.valueOf(System.currentTimeMillis());
        App.wxapi.sendReq(req);
//        }
    }

    //接受event事件
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void getWxLoginEvent(MessageEvent messageEvent) {
        switch (messageEvent.getWhat()) {
            case AppConstant.WX_LOGIN:
                String wxCode = messageEvent.getMessage();
//                showToast(wxCode);
                HashMap<String, String> map = new HashMap<>();
                map.put("code", wxCode);
                mPresenter.getWxLogin(map);
                break;
            default:
                break;
        }
    }

    //验证码登录
    private void initGetLoginCode() {

        String mobile = et_name.getText().toString().trim();
        String code = et_code.getText().toString().trim();
//        switch (status) {
//            case AppConstant.LOGIN_OLD_USER:
//                //去登录
//                goLogin(mobile, code);
//                break;
//            case AppConstant.LOGIN_NEW_USER:
//                //去注册
//                goReg(mobile, code);
//                break;
//            case AppConstant.LOGIN_REEOR:
//                ToastUtil.showToast("验证码发送失败");
//                break;
//            default:
//                break;
//        }
        if (TextUtils.isEmpty(mobile) || TextUtils.isEmpty(code)) {
            ToastUtil.showToast("输入为空");
        } else {
            if (mobile.length() >= 11) {
                if (MobileUtil.isMobileNO(mobile)) {
                    trueMobile = true;
                    if (code.length() >= 6) {
//                        if(checked == false){
//                            showCenterToast("请同意阅读用户协议及隐私协议");
//                        }else {
                        showWaitingDialog("登录中...", true);
                        HashMap<String, String> map = new HashMap<>();
                        map.put("mobile", mobile);
                        map.put("code", code);
                        map.put("xt", String.valueOf(1));
                        mPresenter.getLoginCode(map);
//                        }
                    } else {
                        ToastUtil.showToast("验证码输入错误");
                    }
                } else {
                    trueMobile = false;
                    ToastUtil.showToast("手机号格式不正确");
                }
            }
        }

    }

    //去注册
    private void goReg(String mobile, String code) {
        if (TextUtils.isEmpty(mobile)) {
            ToastUtil.showToast("手机号输入为空");
        } else {
            if (mobile.length() >= 11) {
                if (MobileUtil.isMobileNO(mobile)) {
                    trueMobile = true;
                    if (TextUtils.isEmpty(code)) {
                        ToastUtil.showCenterToast("请输入验证码");
                    } else {
                        if (code.length() >= 6) {
                            Intent intent = new Intent(LoginCodeActivity.this, SetPwdActivity.class);
                            intent.putExtra("mobile", mobile);
                            intent.putExtra("code", code);
                            startActivity(intent);
                        } else {
                            ToastUtil.showCenterToast("请输入6位数据验证码");
                        }
                    }
                } else {
                    trueMobile = false;
                    ToastUtil.showToast("手机号格式不正确");
                }
            }
        }
    }

    //去登录
    private void goLogin(String mobile, String code) {

    }

    //获取验证码
    private void initGetCode() {
        String pho = et_name.getText().toString().trim();
        if (TextUtils.isEmpty(pho)) {
            ToastUtil.showToast("手机号输入为空");
        } else {
            if (pho.length() >= 11) {
                if (MobileUtil.isMobileNO(pho)) {
                    trueMobile = true;
//                    countDownTimer.start();
                    HashMap<String, String> map = new HashMap<>();
                    map.put("mobile", pho);
                    mPresenter.getSendCode(map);
//                    initTimer();
//                    ToastUtil.showToast("手机号格式正确");
                } else {
                    trueMobile = false;
                    ToastUtil.showToast("手机号格式不正确");
                }
            }
        }
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

    //获取验证码成功回调
    @Override
    public void onCodeSuccess(CodeBean codeBean) {
        if (codeBean != null) {
            status = codeBean.getStatus();
            if (status == AppConstant.LOGIN_REEOR) {
                ToastUtil.showToast("验证码发送失败");
            }
            countDownTimer.start();
        }
    }

    @Override
    public void onCodeFiled() {

    }

    //验证码登录成功回调
    @Override
    public void onCodeLoginSuccess(CodeLoginBean codeLoginBean) {
        hideWaitingDialog();
        if (codeLoginBean != null) {
            int status = codeLoginBean.getStatus();
            String uid = codeLoginBean.getUid();
            String shenfen = codeLoginBean.getShenfen();
            switch (status) {
                case 1:
                    LoginHelper.OnEMLoginCallback callback = new LoginHelper.OnEMLoginCallback() {
                        @Override
                        public void onSuccess() {
                            SpUtil.putString(AppConstant.UID, uid);
                            SpUtil.putString(AppConstant.SHENFEN, shenfen);
                            if (shenfen.equals("1")) {
                                startActivity(new Intent(LoginCodeActivity.this, MainActivity.class));
                                finish();
                            } else if (shenfen.equals("2")) {
                                startActivity(new Intent(LoginCodeActivity.this, LvShiMainActivity.class));
                                finish();
                            }
                        }
                        @Override
                        public void onFail(int code, String msg) {
                            Logger.e("2233code ---> " + code+ ",,, msg ---> " + msg);
                        }

                    };
                    LoginHelper.getInstance().loginSuccess(uid, "123456",callback);
                    break;
                case 2:
                    ToastUtil.showCenterToast(codeLoginBean.getMsg());
                    break;
                case 0:
                    ToastUtil.showCenterToast("登录失败");
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void onWxLoginSuccess(WxLoginBean wxLoginBean) {
        hideWaitingDialog();
        if (wxLoginBean != null) {
            int status = wxLoginBean.getStatus();
            String uid = wxLoginBean.getUid();
            String shenfen = wxLoginBean.getShenfen();
            switch (status) {
                case 1:
                    LoginHelper.OnEMLoginCallback callback = new LoginHelper.OnEMLoginCallback() {
                        @Override
                        public void onSuccess() {
                            SpUtil.putString(AppConstant.UID, uid);
                            SpUtil.putString(AppConstant.SHENFEN, shenfen);
                            if (shenfen.equals("1")) {
                                startActivity(new Intent(LoginCodeActivity.this, MainActivity.class));
                                finish();
                            } else if (shenfen.equals("2")) {
                                startActivity(new Intent(LoginCodeActivity.this, LvShiMainActivity.class));
                                finish();
                            }
                        }
                        @Override
                        public void onFail(int code, String msg) {
                            Logger.e("2233code ---> " + code+ ",,, msg ---> " + msg);
                        }

                    };
                    LoginHelper.getInstance().loginSuccess(uid, "123456",callback);
//                    SpUtil.putString(AppConstant.UID, uid);
//                    SpUtil.putString(AppConstant.SHENFEN, shenfen);
//                    if (shenfen.equals("1")) {
//                        startActivity(new Intent(LoginCodeActivity.this, MainActivity.class));
//                        finish();
//                    } else if (shenfen.equals("2")) {
//                        startActivity(new Intent(LoginCodeActivity.this, LvShiMainActivity.class));
//                        finish();
//                    }
                    break;
                case 0:
                    ToastUtil.showCenterToast("登录失败");
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }
}
