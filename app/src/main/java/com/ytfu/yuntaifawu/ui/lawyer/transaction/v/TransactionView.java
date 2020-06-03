package com.ytfu.yuntaifawu.ui.lawyer.transaction.v;


import com.ytfu.yuntaifawu.base.BaseView;
import com.ytfu.yuntaifawu.ui.lawyer.transaction.bean.TransactionResponseBean;

public interface TransactionView extends BaseView {

    void onGetTransactionSuccess(TransactionResponseBean bean);

    void onGetTransactionFail(String msg);



}
