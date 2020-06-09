package com.ytfu.yuntaifawu.ui.lawyer.transaction.adapter;

import com.chad.library.adapter.base.module.LoadMoreModule;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.ytfu.yuntaifawu.R;
import com.ytfu.yuntaifawu.base.adapter.QuickAdapter;
import com.ytfu.yuntaifawu.ui.lawyer.transaction.bean.TransactionResponseBean;


public class TransactionAdapter
        extends QuickAdapter<TransactionResponseBean.DataBean>
        implements LoadMoreModule {

    public TransactionAdapter() {
        super(R.layout.item_transaction);
    }

    @Override
    protected void convert(BaseViewHolder helper, TransactionResponseBean.DataBean item) {
        helper.setText(R.id.tv_transaction_name, item.getTitle());
        helper.setText(R.id.tv_transaction_time, item.getAddtime());
        helper.setText(R.id.tv_transaction_money, item.getMoney());
    }
}
