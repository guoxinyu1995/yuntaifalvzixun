package com.ytfu.yuntaifawu.ui.lawyer.wallet.v;

import com.ytfu.yuntaifawu.base.BaseView;
import com.ytfu.yuntaifawu.ui.lawyer.wallet.bean.WalletResponseBean;

public interface WalletView extends BaseView {

    /**
     * 支付宝解除绑定成功
     */
    void onUnbindAliPaySuccess();

    /**
     * 支付宝解除绑定失败
     */
    void onUnbindAliPayFail(String errorMsg);

    void onGetWalletInfoSuccess(WalletResponseBean bean);

    void onGetWalletInfoFail(String errorMsg);

}
