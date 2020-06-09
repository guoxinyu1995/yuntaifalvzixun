package com.ytfu.yuntaifawu.ui.lawyer.transaction.v;


import com.ytfu.yuntaifawu.base.BaseRefreshView;
import com.ytfu.yuntaifawu.ui.lawyer.transaction.bean.TransactionResponseBean;

public interface TransactionView extends BaseRefreshView<TransactionResponseBean.DataBean> {

    void onGetTransactionSuccess(TransactionResponseBean bean);

    void onGetTransactionFail(String msg);


}
