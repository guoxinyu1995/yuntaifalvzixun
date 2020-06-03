package com.ytfu.yuntaifawu.ui.lawyer.wallet.p;

import com.ytfu.yuntaifawu.apis.HttpUtil;
import com.ytfu.yuntaifawu.apis.TransactionService;
import com.ytfu.yuntaifawu.base.BasePresenter;
import com.ytfu.yuntaifawu.helper.BaseRxObserver;
import com.ytfu.yuntaifawu.ui.lawyer.wallet.bean.UnbindAliResponse;
import com.ytfu.yuntaifawu.ui.lawyer.wallet.bean.WalletResponseBean;
import com.ytfu.yuntaifawu.ui.lawyer.wallet.v.WalletView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class WalletPresenter extends BasePresenter<WalletView> {

    /**
     * 支付宝绑定
     */
    public void unbindAliPay(String uid) {
        HttpUtil.getInstance().getService(TransactionService.class)
                .unbindAliPay(uid, 2)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(bindLifecycle())
                .subscribe(new BaseRxObserver<UnbindAliResponse>() {
                    @Override
                    public void onSubscribeImpl(Disposable d) {
                        super.onSubscribeImpl(d);
                        getView().showProgress();
                    }

                    @Override
                    public void onNextImpl(UnbindAliResponse unbindAliResponse) {
                        getView().hideProgress();
                        if (null == unbindAliResponse) {
                            getView().onUnbindAliPayFail("网络请求出现,请稍后重试");
                            return;
                        }
                        int code = unbindAliResponse.getCode();
                        if (code != 200) {
                            getView().onUnbindAliPayFail(unbindAliResponse.getMsg());
                            return;
                        }
                        getView().onUnbindAliPaySuccess();
                    }

                    @Override
                    public void onErrorImpl(String errorMessage) {
                        getView().hideProgress();
                        getView().onUnbindAliPayFail(errorMessage);
                    }
                });
    }

    /**
     * 获取个人账户钱包信息
     */
    public void getWalletInfo(String uid) {
        HttpUtil.getInstance().getService(TransactionService.class)
                .getWalletInfo(uid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(bindLifecycle())
                .subscribe(new BaseRxObserver<WalletResponseBean>() {

                    @Override
                    public void onNextImpl(WalletResponseBean bean) {
                        if (null == bean) {
                            getView().onGetWalletInfoFail("获取数据失败");
                            return;
                        }
                        int code = bean.getCode();
                        if (code != 200) {
                            getView().onGetWalletInfoFail(bean.getMsg());
                            return;
                        }
                        WalletResponseBean.DataBean data = bean.getData();
                        if (null == data) {
                            getView().onGetWalletInfoFail("解析数据失败");
                            return;
                        }

                        getView().onGetWalletInfoSuccess(bean);
                    }

                    @Override
                    public void onErrorImpl(String errorMessage) {
                        getView().onGetWalletInfoFail(errorMessage);
                    }
                });


    }
}
