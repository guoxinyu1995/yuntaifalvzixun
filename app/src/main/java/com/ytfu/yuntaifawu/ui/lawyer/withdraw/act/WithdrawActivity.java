package com.ytfu.yuntaifawu.ui.lawyer.withdraw.act;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.ytfu.yuntaifawu.R;
import com.ytfu.yuntaifawu.app.AppConstant;
import com.ytfu.yuntaifawu.base.BaseActivity;
import com.ytfu.yuntaifawu.ui.lawyer.bind.act.BindAliActivity;
import com.ytfu.yuntaifawu.ui.lawyer.wallet.bean.WalletResponseBean;
import com.ytfu.yuntaifawu.ui.lawyer.withdraw.bean.WithdrawResponseBean;
import com.ytfu.yuntaifawu.ui.lawyer.withdraw.p.WithdrawPresenter;
import com.ytfu.yuntaifawu.ui.lawyer.withdraw.v.WithdrawView;
import com.ytfu.yuntaifawu.utils.CommonUtil;
import com.ytfu.yuntaifawu.utils.SpUtil;
import com.ytfu.yuntaifawu.utils.ToastUtil;

import butterknife.BindView;

public class WithdrawActivity extends BaseActivity<WithdrawView, WithdrawPresenter> implements WithdrawView {

    @BindView(R.id.tl_withdraw_toolbar)
    Toolbar tl_withdraw_toolbar;
    @BindView(R.id.tv_withdraw_title)
    TextView tv_withdraw_title;

    @BindView(R.id.et_withdraw_money)
    EditText et_withdraw_money;
    @BindView(R.id.tv_withdraw_account)
    TextView tv_withdraw_account;
    @BindView(R.id.tv_withdraw_max)
    TextView tv_withdraw_max;
    @BindView(R.id.tv_withdraw_commit)
    TextView tv_withdraw_commit;
    @BindView(R.id.tv_withdraw_notice)
    TextView tv_withdraw_notice;

    //===Desc:=================================================================

    private static final String KEY_ACCOUNT_INFO = "KEY_ACCOUNT_INFO";

    public static void start(Context context, WalletResponseBean.DataBean info) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(KEY_ACCOUNT_INFO, info);
        Intent starter = new Intent(context, WithdrawActivity.class);
        starter.putExtras(bundle);
        context.startActivity(starter);
    }

    public static void startActivityForResult(Activity activity, int requestCode, WalletResponseBean.DataBean info) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(KEY_ACCOUNT_INFO, info);
        Intent starter = new Intent(activity, WithdrawActivity.class);
        starter.putExtras(bundle);
        activity.startActivityForResult(starter, requestCode);
    }

    //===Desc:=================================================================

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_withdraw;
    }

    @Override
    protected WithdrawPresenter createPresenter() {
        return new WithdrawPresenter();
    }

    @Override
    protected void initView() {
    }

    @Override
    protected void initData() {
        tv_withdraw_title.setText(R.string.txt_withdraw);
        tl_withdraw_toolbar.setTitle("");
        setSupportActionBar(tl_withdraw_toolbar);
        tl_withdraw_toolbar.setNavigationIcon(R.drawable.fanhui_bai);
        tl_withdraw_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        Bundle bundle = getIntent().getExtras();
        if (null == bundle) {
            ToastUtil.showToast("应用程序出现未知错误,请稍后重试");
            finish();
            return;
        }
        WalletResponseBean.DataBean info = bundle.getParcelable(KEY_ACCOUNT_INFO);
        if (null == info) {
            ToastUtil.showToast("应用程序出现未知错误,请稍后重试");
            finish();
            return;
        }
        //回显数据
        //账号
        String account = info.getAccount();
        String accountShow = String.format(getString(R.string.txt_account_echo_with_alipay), account);
        tv_withdraw_account.setText(accountShow);
        //最大提现
        String balance = info.getBalance();
        String balanceShow = getString(R.string.money_symbol) + info.getBalance();
        tv_withdraw_max.setText(balanceShow);
        //全部提现按钮点击事件
        findViewById(R.id.tv_withdraw_all).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_withdraw_money.setText(balance);
            }
        });

        //金额输入监听
        et_withdraw_money.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String inputMoney = et_withdraw_money.getText().toString().trim();
                if (TextUtils.isEmpty(inputMoney)) {
                    et_withdraw_money.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
                } else {
                    et_withdraw_money.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);
                    double money;
                    try {
                        money = Double.parseDouble(inputMoney);
                    } catch (NumberFormatException e) {
                        money = 0.0;
                    }
                    tv_withdraw_commit.setEnabled(money >= 100);
                }
            }
        });

        tv_withdraw_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //隐藏软键盘
                CommonUtil.hideSoftInput(WithdrawActivity.this);
                //判断输入金额
                String inputMoney = et_withdraw_money.getText().toString().trim();
                if (TextUtils.isEmpty(inputMoney)) {
                    et_withdraw_money.requestFocus();
                    ToastUtil.showToast(getString(R.string.toast_please_input_money));
                    return;
                }
                double money;
                double myBalance;
                try {
                    money = Double.parseDouble(inputMoney);
                    myBalance = Double.parseDouble(balance);
                } catch (NumberFormatException e) {
                    money = 0.0;
                    myBalance = 0.0;
                }
                if (money > myBalance) {
                    et_withdraw_money.requestFocus();
                    ToastUtil.showToast(getString(R.string.toast_input_money_too_much));
                    return;
                }
                //进行提现操作
                String uid = SpUtil.getString(AppConstant.UID, "");
                mPresenter.withdraw(uid, String.valueOf(money));

            }
        });

        tv_withdraw_notice.setText(info.getNotice());
        hideLoading();
    }

    //===Desc:=================================================================
    @Override
    public void onWithdrawSuccess(WithdrawResponseBean bean) {
        ToastUtil.showToast(getString(R.string.txt_withdraw_success));
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public void onWithdrawFail(String errorMsg) {
        ToastUtil.showToast(errorMsg);
    }
}
