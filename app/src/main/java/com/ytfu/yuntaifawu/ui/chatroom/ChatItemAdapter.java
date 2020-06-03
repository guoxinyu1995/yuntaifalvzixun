package com.ytfu.yuntaifawu.ui.chatroom;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ytfu.yuntaifawu.ui.mseeage.bean.HistoryRecordResponseBean;

import java.util.ArrayList;
import java.util.List;

public class ChatItemAdapter extends BaseMultiItemQuickAdapter<HistoryRecordResponseBean.DataBean, BaseViewHolder> {


    public ChatItemAdapter() {
        super(new ArrayList<>());
    }

    @Override
    protected void convert(BaseViewHolder helper, HistoryRecordResponseBean.DataBean item) {

    }


    //    HistoryRecordResponseBean.DataBean
}
