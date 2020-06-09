package com.ytfu.yuntaifawu.ui.lawyer.wallet.act;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import com.lxj.xpopup.XPopup;
import com.ytfu.yuntaifawu.R;
import com.ytfu.yuntaifawu.app.AppConstant;
import com.ytfu.yuntaifawu.base.BaseActivity;
import com.ytfu.yuntaifawu.ui.lawyer.bind.act.BindAliActivity;
import com.ytfu.yuntaifawu.ui.lawyer.transaction.act.TransactionActivity;
import com.ytfu.yuntaifawu.ui.lawyer.wallet.bean.WalletResponseBean;
import com.ytfu.yuntaifawu.ui.lawyer.wallet.p.WalletPresenter;
import com.ytfu.yuntaifawu.ui.lawyer.wallet.v.WalletView;
import com.ytfu.yuntaifawu.ui.lawyer.withdraw.act.WithdrawActivity;
import com.ytfu.yuntaifawu.utils.CommonUtil;
import com.ytfu.yuntaifawu.utils.Eyes;
import com.ytfu.yuntaifawu.utils.SpUtil;
import com.ytfu.yuntaifawu.utils.ToastUtil;

import butterknife.BindView;

/**
 * 我的钱包界面
 */
public class WalletActivity extends BaseActivity<WalletView, WalletPresenter> implements WalletView {
    /**
     * startActivityForResult使用的code
     */
    private final int CODE_BIND_ALI = 0;

    @BindView(R.id.tl_wallet_toolbar)
    Toolbar tl_wallet_toolbar;
    @BindView(R.id.tv_wallet_title)
    TextView tv_wallet_title;

    @BindView(R.id.tv_wallet_balance)
    TextView tv_wallet_balance;
    @BindView(R.id.tv_wallet_account)
    TextView tv_wallet_account;
    @BindView(R.id.tv_wallet_status)
    TextView tv_wallet_status;

    //===Desc:=================================================================

    public static void start(Context context) {
        Intent starter = new Intent(context, WalletActivity.class);
        context.startActivity(starter);
    }

    //===Desc:=================================================================

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_wallet;
    }

    @Override
    protected WalletPresenter createPresenter() {
        return new WalletPresenter();
    }

    @Override
    protected void initView() {
        findViewById(R.id.tv_wallet_details).setOnClickListener(v ->
                TransactionActivity.start(WalletActivity.this)
        );
    }

    @Override
    public void init() {
        super.init();
        Eyes.setStatusBarColor(this, CommonUtil.getColor(R.color.transparent_4c));
    }

    @Override
    protected void initData() {
        tl_wallet_toolbar.setTitle("");
        setSupportActionBar(tl_wallet_toolbar);
        tl_wallet_toolbar.setNavigationIcon(R.drawable.fanhui_bai);
        tl_wallet_toolbar.setNavigationOnClickListener(v -> onBackPressed());

        tv_wallet_title.setText(R.string.txt_mine_wallet);

        //获取个人账户信息
        String uid = SpUtil.getString(AppConstant.UID, "");
        getPresenter().getWalletInfo(uid);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CODE_BIND_ALI) {
            if (resultCode == RESULT_OK) {
                String uid = SpUtil.getString(AppConstant.UID, "");
                getPresenter().getWalletInfo(uid);
            }
        }
    }


    //===Desc:=================================================================

    @Override
    public void onUnbindAliPaySuccess() {
        ToastUtil.showToast("解绑成功");
        //重新加载
        String uid = SpUtil.getString(AppConstant.UID, "");
        getPresenter().getWalletInfo(uid);
    }

    @Override
    public void onUnbindAliPayFail(String errorMsg) {
        ToastUtil.showToast(errorMsg);
    }

    @Override
    public void onGetWalletInfoSuccess(WalletResponseBean bean) {
        WalletResponseBean.DataBean data = bean.getData();
        if (null == data) {
            tv_wallet_balance.setText("0.0");
            findViewById(R.id.ll_wallet_alipay)
                    .setOnClickListener(v -> ToastUtil.showToast("应用程序出错,请稍后重试"));
            findViewById(R.id.tv_wallet_details).setOnClickListener(v ->
                    ToastUtil.showToast("应用程序出错,请稍后重试"));
            findViewById(R.id.tv_wallet_withdraw).setOnClickListener(v ->
                    ToastUtil.showToast("应用程序出错,请稍后重试"));

            hideLoading();
            return;
        }
        String balanceStr = data.getBalance();
        double balance;
        try {
            balance = Double.parseDouble(balanceStr);
        } catch (NumberFormatException e) {
            balance = 0.0;
        }

        tv_wallet_balance.setText(CommonUtil.doubleFormat(balance, "0.00"));
        //支付宝账号回显
        String account = data.getAccount();
        if (TextUtils.isEmpty(account)) {
            tv_wallet_account.setVisibility(View.INVISIBLE);
        } else {
            tv_wallet_account.setVisibility(View.VISIBLE);
            String showAccount = String.format(getString(R.string.txt_account_echo), account);
            tv_wallet_account.setText(showAccount);
        }
        //状态
        int status = data.getBindingStatus();
        if (status == 1) {//已绑定
            tv_wallet_status.setVisibility(View.VISIBLE);
        } else {
            tv_wallet_status.setVisibility(View.GONE);
        }

        //设置事件
        //支付宝
        findViewById(R.id.ll_wallet_alipay)
                .setOnClickListener(v -> {
                            if (status == 1) {
                                //已绑定  提示解除绑定
                                new XPopup.Builder(WalletActivity.this)
                                        .asConfirm("确认解绑吗?",
                                                "解除后将不可恢复，如需启用，可选择重新添加支付宝",
                                                () -> getPresenter().unbindAliPay(SpUtil.getString(AppConstant.UID, "")))
                                        .show();
                            } else {
                                //进入绑定界面
                                BindAliActivity.startActivityForResult(WalletActivity.this, CODE_BIND_ALI);
                            }
                        }
                );
        //提现
        final double b = balance;
        findViewById(R.id.tv_wallet_withdraw).setOnClickListener(v -> {
            //判断是否绑定支付宝
            if (status == 1) {
                //已绑定
                //判断账户余额,不足100不能提现
                if (b < 100) {
                    new XPopup.Builder(WalletActivity.this)
                            .asConfirm(
                                    getString(R.string.txt_bind_tips_title),
                                    getString(R.string.txt_withdraw_insufficient_balance),
                                    () -> {
                                    }
                            )
                            .hideCancelBtn()
                            .show();
                } else {
                    //进入提现页面
                    WithdrawActivity.startActivityForResult(WalletActivity.this, CODE_BIND_ALI, bean.getData());
                }
            } else {
                //未绑定
                new XPopup.Builder(WalletActivity.this)
                        .asConfirm(getString(R.string.txt_bind_tips_title),
                                getString(R.string.txt_tip_unbind_alipay),
                                getString(R.string.cancel),
                                getString(R.string.ok),
                                () -> BindAliActivity.startActivityForResult(WalletActivity.this, CODE_BIND_ALI),
                                () -> {
                                },
                                false)
                        .show();
            }
        });

        hideLoading();
    }

    @Override
    public void onGetWalletInfoFail(String errorMsg) {
        ToastUtil.showToast(errorMsg);
    }
}
