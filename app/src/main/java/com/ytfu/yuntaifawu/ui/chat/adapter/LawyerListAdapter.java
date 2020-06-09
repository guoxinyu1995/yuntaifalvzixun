package com.ytfu.yuntaifawu.ui.chat.adapter;

import android.text.TextUtils;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.module.LoadMoreModule;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.util.DateUtils;
import com.ytfu.yuntaifawu.R;
import com.ytfu.yuntaifawu.im.EmChatManager;
import com.ytfu.yuntaifawu.ui.chat.bean.LawyerListBean;
import com.ytfu.yuntaifawu.utils.GlideManager;

import java.util.Date;

public class LawyerListAdapter
        extends BaseQuickAdapter<LawyerListBean.LawyerItemBean, BaseViewHolder>
        implements LoadMoreModule {
    public LawyerListAdapter() {
        super(R.layout.item_lawyer_list);
    }

    @Override
    protected void convert(BaseViewHolder helper, LawyerListBean.LawyerItemBean item) {
        ImageView ivHead = helper.getView(R.id.iv_lawyer_head);
        GlideManager.loadRadiusImage(getContext(), item.getHeaderImage(), ivHead, 4);
        helper.setText(R.id.tv_lawyer_name, item.getNickName());
        String time = item.getTime();
        long t;
        try {
            t = Long.parseLong(time);
        } catch (Exception e) {
            t = 0L;
        }
        String timeStr = "";
        try {
            timeStr = DateUtils.getTimestampString(new Date(t * 1000));
        } catch (Exception e) {
            timeStr = "";
        }
        if (TextUtils.isEmpty(timeStr)) {
            helper.setVisible(R.id.tv_lawyer_time, false);
        } else {
            helper.setText(R.id.tv_lawyer_time, timeStr);
            helper.setVisible(R.id.tv_lawyer_time, true);
        }


        int count;
        EMConversation conversation = EmChatManager.getInstance().getConversationById(item.getConversationId());
        if (null != conversation) {
            count = conversation.getUnreadMsgCount();
        } else {
            count = item.getMessagesCount();
        }
        if (count > 0) {
            helper.setVisible(R.id.tv_lawyer_unread, true);
            helper.setText(R.id.tv_lawyer_unread, String.valueOf(count));
        } else {
            helper.setVisible(R.id.tv_lawyer_unread, false);
        }

        String text = item.getText();
        if (TextUtils.isEmpty(text)) {
            helper.setVisible(R.id.tv_lawyer_last, false);
        } else {
            helper.setText(R.id.tv_lawyer_last, text);
            helper.setVisible(R.id.tv_lawyer_last, true);
        }
    }
}
