package com.ytfu.yuntaifawu.ui.lawyer.transaction.p;

import com.ytfu.yuntaifawu.apis.HttpUtil;
import com.ytfu.yuntaifawu.apis.LawyerService;
import com.ytfu.yuntaifawu.apis.TransactionService;
import com.ytfu.yuntaifawu.base.BasePresenter;
import com.ytfu.yuntaifawu.helper.BaseRxObserver;
import com.ytfu.yuntaifawu.ui.lawyer.transaction.bean.TransactionResponseBean;
import com.ytfu.yuntaifawu.ui.lawyer.transaction.v.TransactionView;
import com.ytfu.yuntaifawu.ui.mine.bean.InformationBean;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class TransactionPresenter extends BasePresenter<TransactionView> {

    /**
     * 获取交易流水
     */
    public void getTransaction(String uid, int page, int size) {
        HttpUtil.getInstance().getService(TransactionService.class)
                .getTransaction(uid, page, size)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(bindLifecycle())
                .subscribe(new BaseRxObserver<TransactionResponseBean>() {


                    @Override
                    public void onNextImpl(TransactionResponseBean transactionResponseBean) {
                        if (null == transactionResponseBean) {
                            getView().onGetTransactionFail("获取数据失败");
                            return;
                        }
                        int code = transactionResponseBean.getCode();
                        if (code != 200) {
                            if (code == 202) {
                                getView().onGetTransactionSuccess(transactionResponseBean);
                                return;
                            }
                            getView().onGetTransactionFail("获取数据失败");
                            return;
                        }
                        getView().onGetTransactionSuccess(transactionResponseBean);
                    }

                    @Override
                    public void onErrorImpl(String errorMessage) {
                        getView().onGetTransactionFail(errorMessage);
                    }
                });

    }
}
