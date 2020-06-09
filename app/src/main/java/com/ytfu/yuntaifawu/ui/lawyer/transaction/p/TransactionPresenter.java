package com.ytfu.yuntaifawu.ui.lawyer.transaction.p;

import com.ytfu.yuntaifawu.R;
import com.ytfu.yuntaifawu.apis.HttpUtil;
import com.ytfu.yuntaifawu.apis.TransactionService;
import com.ytfu.yuntaifawu.base.BaseRefreshPresenter;
import com.ytfu.yuntaifawu.base.BaseRefreshView;
import com.ytfu.yuntaifawu.helper.BaseRxObserver;
import com.ytfu.yuntaifawu.ui.lawyer.transaction.bean.TransactionResponseBean;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class TransactionPresenter extends BaseRefreshPresenter<BaseRefreshView<TransactionResponseBean.DataBean>> {

    /**
     * 获取交易流水
     */
    public void getTransaction(String uid, int page, int size, boolean isLoadMore) {
        HttpUtil.getInstance().getService(TransactionService.class)
                .getTransaction(uid, page, size)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(bindLifecycle())
                .subscribe(new BaseRxObserver<TransactionResponseBean>() {
                    @Override
                    public void onNextImpl(TransactionResponseBean transactionResponseBean) {
                        getView().stopRefresh();
                        if (null == transactionResponseBean) {
                            getView().showToast("获取数据失败");
                            getView().setEmptyView(R.layout.layout_empty);
                            getView().resetCurrentPage(isLoadMore, 1);
                            return;
                        }
                        int code = transactionResponseBean.getCode();
                        if (code == 200 || code == 202) {
                            List<TransactionResponseBean.DataBean> data = transactionResponseBean.getData();
                            if (isLoadMore) {
                                if (null == data || data.isEmpty()) {
                                    getView().resetCurrentPage(true, 1);
                                    getView().loadMoreEnd(false);
                                } else {
                                    getView().addData(data);
                                    getView().loadMoreComplete();
                                }
                            } else {
                                if (null == data || data.isEmpty()) {
                                    getView().resetCurrentPage(false, 1);
                                    getView().setEmptyView(R.layout.layout_empty);
                                } else {
                                    getView().setNewData(data);
                                }
                            }
                        } else {
                            getView().showToast("获取数据失败");
                            getView().resetCurrentPage(isLoadMore, 1);
                            getView().setEmptyView(R.layout.layout_empty);
                        }
                    }

                    @Override
                    public void onErrorImpl(String errorMessage) {
                        if (isLoadMore) {
                            getView().loadMoreComplete();
                        } else {
                            getView().stopRefresh();
                        }
                        getView().showToast(errorMessage);
                        getView().resetCurrentPage(isLoadMore, 1);
                        getView().setEmptyView(R.layout.layout_empty);
                    }
                });

    }
}
