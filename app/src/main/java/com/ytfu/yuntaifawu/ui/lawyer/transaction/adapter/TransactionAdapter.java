package com.ytfu.yuntaifawu.ui.lawyer.transaction.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ytfu.yuntaifawu.R;
import com.ytfu.yuntaifawu.ui.lawyer.transaction.bean.TransactionResponseBean;

import java.util.ArrayList;
import java.util.List;

public class TransactionAdapter extends BaseQuickAdapter<TransactionResponseBean.DataBean, BaseViewHolder> {

    public TransactionAdapter() {
        super(R.layout.item_transaction, new ArrayList<>());
    }

    @Override
    protected void convert(BaseViewHolder helper, TransactionResponseBean.DataBean item) {
        helper.setText(R.id.tv_transaction_name, item.getTitle());
        helper.setText(R.id.tv_transaction_time, item.getAddtime());
        helper.setText(R.id.tv_transaction_money, item.getMoney());
    }
}
