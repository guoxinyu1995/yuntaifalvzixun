package com.ytfu.yuntaifawu.ui.lawyer.bind.act;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.lxj.xpopup.XPopup;
import com.lxj.xpopup.interfaces.OnCancelListener;
import com.lxj.xpopup.interfaces.OnConfirmListener;
import com.ytfu.yuntaifawu.R;
import com.ytfu.yuntaifawu.app.AppConstant;
import com.ytfu.yuntaifawu.base.BaseActivity;
import com.ytfu.yuntaifawu.base.BasePresenter;
import com.ytfu.yuntaifawu.ui.lawyer.bind.p.BindAliPresenter;
import com.ytfu.yuntaifawu.ui.lawyer.bind.v.BindAliView;
import com.ytfu.yuntaifawu.utils.CommonUtil;
import com.ytfu.yuntaifawu.utils.SpUtil;
import com.ytfu.yuntaifawu.utils.ToastUtil;

import butterknife.BindView;

/**
 * 绑定支付宝界面
 */
public class BindAliActivity extends BaseActivity<BindAliView, BindAliPresenter> implements BindAliView {

    @BindView(R.id.tl_bind_toolbar)
    Toolbar tl_bind_toolbar;
    @BindView(R.id.tv_bind_title)
    TextView tv_bind_title;
    @BindView(R.id.et_bind_name)
    EditText et_bind_name;
    @BindView(R.id.et_bind_account)
    EditText et_bind_account;
    @BindView(R.id.iv_bind_tip_name)
    ImageView iv_bind_tip_name;
    @BindView(R.id.iv_bind_tip_account)
    ImageView iv_bind_tip_account;


    //===Desc:=================================================================

    public static void start(Context context) {
        Intent starter = new Intent(context, BindAliActivity.class);
        context.startActivity(starter);
    }

    public static void startActivityForResult(Activity activity, int requestCode) {
        Intent starter = new Intent(activity, BindAliActivity.class);
        activity.startActivityForResult(starter, requestCode);
    }

    //===Desc:=================================================================

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_bind_ali;
    }

    @Override
    protected BindAliPresenter createPresenter() {
        return new BindAliPresenter();
    }

    @Override
    protected void initView() {
        et_bind_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String name = et_bind_name.getText().toString().trim();
                if (!TextUtils.isEmpty(name)) {
                    iv_bind_tip_name.setVisibility(View.GONE);
                }
            }
        });
        et_bind_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String name = et_bind_name.getText().toString().trim();
                if (!TextUtils.isEmpty(name)) {
                    iv_bind_tip_name.setVisibility(View.GONE);
                }
            }
        });
        et_bind_account.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String account = et_bind_account.getText().toString().trim();
                if (!TextUtils.isEmpty(account)) {
                    iv_bind_tip_account.setVisibility(View.GONE);
                }
            }
        });

        findViewById(R.id.tv_bind_next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isValid = checkInput();
                if (isValid) {
                    //显示弹窗警告
                    showTipsDialog();
                }

            }
        });

    }

    @Override
    protected void initData() {
        tv_bind_title.setText(R.string.txt_alipay_bind);
        tl_bind_toolbar.setTitle("");
        setSupportActionBar(tl_bind_toolbar);
        tl_bind_toolbar.setNavigationIcon(R.drawable.fanhui_bai);
        tl_bind_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        hideLoading();
    }

    //===Desc:=================================================================


    /**
     * 检测用户输入,并且显示提示等操作
     */
    private boolean checkInput() {
        String name = et_bind_name.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            ToastUtil.showToast(getString(R.string.hint_input_name));
            iv_bind_tip_name.setVisibility(View.VISIBLE);
            et_bind_name.requestFocus();
            CommonUtil.openSoftInput(this, et_bind_name);
            return false;
        }
        String account = et_bind_account.getText().toString().trim();
        if (TextUtils.isEmpty(account)) {
            ToastUtil.showToast(getString(R.string.hint_input_ali_account));
            iv_bind_tip_account.setVisibility(View.VISIBLE);
            et_bind_account.requestFocus();
            CommonUtil.openSoftInput(this, et_bind_account);
            return false;
        }
        return true;
    }

    private void showTipsDialog() {
        //关闭软键盘
        CommonUtil.hideSoftInput(this);
        new XPopup.Builder(this)
                .dismissOnBackPressed(true)
                .dismissOnTouchOutside(true)
                .asConfirm(getString(R.string.txt_bind_tips_title),
                        getString(R.string.txt_bind_tips_content),
                        () -> {
                            //绑定支付宝
                            String uid = SpUtil.getString(AppConstant.UID, "");
                            String account = et_bind_account.getText().toString().trim();
                            String realName = et_bind_name.getText().toString().trim();
                            mPresenter.bindAliPay(uid, account, realName);
                        })
                .hideCancelBtn()
                .show();

    }

    //===Desc:=================================================================


    @Override
    public void onBindAliPaySuccess() {
        ToastUtil.showToast("绑定成功");
        hideLoading();
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public void onBindAliPayFail(String errorMsg) {
        ToastUtil.showToast(errorMsg);
        hideLoading();
    }
}
