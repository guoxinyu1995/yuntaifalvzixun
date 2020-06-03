package com.ytfu.yuntaifawu.ui.lawyer.bind.p;

import com.ytfu.yuntaifawu.apis.HttpUtil;
import com.ytfu.yuntaifawu.apis.TransactionService;
import com.ytfu.yuntaifawu.base.BasePresenter;
import com.ytfu.yuntaifawu.helper.BaseRxObserver;
import com.ytfu.yuntaifawu.ui.lawyer.bind.v.BindAliView;
import com.ytfu.yuntaifawu.ui.lawyer.wallet.bean.UnbindAliResponse;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class BindAliPresenter extends BasePresenter<BindAliView> {

    /**
     * 绑定支付宝
     */
    public void bindAliPay(String uid, String account, String realName) {
        HttpUtil.getInstance().getService(TransactionService.class)
                .bindAliPay(uid, account, realName, 1)
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
                            getView().onBindAliPayFail("网络请求出现错误,请稍后重试");
                            return;
                        }
                        int code = unbindAliResponse.getCode();
                        if (code != 200) {
                            getView().onBindAliPayFail(unbindAliResponse.getMsg());
                            return;
                        }
                        getView().onBindAliPaySuccess();
                    }

                    @Override
                    public void onErrorImpl(String errorMessage) {
                        getView().hideProgress();
                        getView().onBindAliPayFail(errorMessage);
                    }
                });
    }


}
