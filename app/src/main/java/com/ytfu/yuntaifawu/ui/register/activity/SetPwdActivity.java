package com.ytfu.yuntaifawu.ui.register.activity;

import android.content.Intent;
import android.os.Build;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ytfu.yuntaifawu.R;
import com.ytfu.yuntaifawu.app.AppConstant;
import com.ytfu.yuntaifawu.base.BaseActivity;
import com.ytfu.yuntaifawu.ui.MainActivity;
import com.ytfu.yuntaifawu.ui.register.bean.RegistBean;
import com.ytfu.yuntaifawu.ui.register.present.RegistPresenter;
import com.ytfu.yuntaifawu.ui.register.view.IRegistView;
import com.ytfu.yuntaifawu.utils.SpUtil;
import com.ytfu.yuntaifawu.utils.ToastUtil;

import java.util.HashMap;

/**
 * @Auther gxy
 * @Date 2019/11/14
 * @Des 设置密码Activity
 */
public class SetPwdActivity extends BaseActivity<IRegistView, RegistPresenter> implements IRegistView, View.OnClickListener {
    private ImageView icon_finish;
    private EditText et_shuru_pwd;
    private ImageView iv_shuru_bi;
    private EditText et_queren_pwd, et_email;
    private ImageView im_queren_kai;
    private Button btn_queren;
    private String mobile;
    private String code;
    private String pwd;
    private String repwd;
    private boolean isHideFirst = true;// 输入框密码是否是隐藏的，默认为true
    private CheckBox cb_ty;
    private TextView tv_tiaokuan,tv_yhxy,tv_ys;
    private boolean checked;
    private String etemail;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_set_pwd;
    }

    @Override
    protected RegistPresenter createPresenter() {
        return new RegistPresenter(this);
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
        et_shuru_pwd = findViewById(R.id.et_shuru_pwd);
        iv_shuru_bi = findViewById(R.id.iv_shuru_bi);
        et_queren_pwd = findViewById(R.id.et_queren_pwd);
        im_queren_kai = findViewById(R.id.im_queren_kai);
        btn_queren = findViewById(R.id.btn_queren);
        et_email = findViewById(R.id.et_email);
        tv_yhxy = findViewById(R.id.tv_yhxy);
        tv_ys = findViewById(R.id.tv_ys);
        cb_ty = findViewById(R.id.cb_ty);
        tv_tiaokuan = findViewById(R.id.tv_tiaokuan);
        icon_finish.setOnClickListener(this);
        iv_shuru_bi.setOnClickListener(this);
        im_queren_kai.setOnClickListener(this);
        btn_queren.setOnClickListener(this);
        tv_tiaokuan.setOnClickListener(this);
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

    }


    @Override
    protected void initData() {
        Intent intent = getIntent();
        mobile = intent.getStringExtra("mobile");
        code = intent.getStringExtra("code");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.icon_finish:
                finish();
                break;
            case R.id.iv_shuru_bi:
                if (isHideFirst == true) {
                    iv_shuru_bi.setImageResource(R.drawable.xiaoyanjingkai);
                    et_shuru_pwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    isHideFirst = false;
                } else {
                    iv_shuru_bi.setImageResource(R.drawable.xiaoyanjingbi);
                    et_shuru_pwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    isHideFirst = true;
                }
                // 光标的位置
                int index = et_shuru_pwd.getText().toString().length();
                et_shuru_pwd.setSelection(index);
                break;
            case R.id.im_queren_kai:
                if (isHideFirst == true) {
                    im_queren_kai.setImageResource(R.drawable.xiaoyanjingkai);
                    et_queren_pwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    isHideFirst = false;
                } else {
                    im_queren_kai.setImageResource(R.drawable.xiaoyanjingbi);
                    et_queren_pwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    isHideFirst = true;
                }
                // 光标的位置
                int index1 = et_queren_pwd.getText().toString().length();
                et_queren_pwd.setSelection(index1);
                break;
            case R.id.btn_queren:
                if (checked == false) {
                    ToastUtil.showCenterToast("请同意阅读用户服务协议和隐私协议");
                } else {
                    initRegist();
                }
                break;
            case R.id.tv_yhxy:
                startActivity(new Intent(SetPwdActivity.this, ActivityYhxy.class));
                break;
            case R.id.tv_ys:
                startActivity(new Intent(SetPwdActivity.this, ActivityYs.class));
                break;
            default:
                break;
        }
    }

    //去注册
    private void initRegist() {
        pwd = et_shuru_pwd.getText().toString().trim();
        repwd = et_queren_pwd.getText().toString().trim();
        etemail = this.et_email.getText().toString().trim();
        if (!TextUtils.isEmpty(pwd) || !TextUtils.isEmpty(repwd)) {
            if (pwd.equals(repwd)) {
                if (pwd.length() >= 6 && pwd.length() <= 12) {
                    if (repwd.length() >= 6 && repwd.length() <= 12) {
                        if (!TextUtils.isEmpty(etemail)) {
                            boolean contains = etemail.contains("@");
                            if (contains == true) {
                                HashMap<String, String> map = new HashMap<>();
                                map.put("mobile", mobile);
                                map.put("code", code);
                                map.put("pwd", pwd);
                                map.put("repwd", repwd);
                                map.put("user_email", etemail);
                                mPresenter.getRegistNum(map);
                            } else if (contains == false) {
                                showToast("邮箱格式不正确");
                            }
                        } else {
                            showToast("请输入邮箱");
                        }
                    } else {
                        ToastUtil.showToast("密码长度为6到12位数字字母下划线");
                    }
                } else {
                    ToastUtil.showToast("密码长度为6到12位数字字母下划线");
                }
            } else {
                ToastUtil.showToast("两次密码输入不一致");
            }
        } else {
            ToastUtil.showToast("输入不能为空");
        }
    }

    @Override
    public void onRegistSussess(RegistBean registBean) {
        hideWaitingDialog();
        if (registBean != null) {
            int status = registBean.getStatus();
            String msg = registBean.getMsg();
            String uid = registBean.getUid();
            switch (status) {
                case 1:
                    SpUtil.putString(AppConstant.UID, uid);
                    Intent intent = new Intent(SetPwdActivity.this, MainActivity.class);
                    startActivity(intent);
                    break;
                case 0:
                    ToastUtil.showCenterToast("注册失败");
                    break;
                case 2:
                    ToastUtil.showCenterToast(msg);
                    break;
                default:
                    break;

            }

        }
    }

    @Override
    public void onRegistFiled() {

    }
}

