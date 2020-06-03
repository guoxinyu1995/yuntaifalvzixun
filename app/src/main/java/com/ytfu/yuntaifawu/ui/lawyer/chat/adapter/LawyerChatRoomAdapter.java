package com.ytfu.yuntaifawu.ui.lawyer.chat.adapter;

import android.graphics.Color;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hyphenate.util.DateUtils;
import com.ytfu.yuntaifawu.R;
import com.ytfu.yuntaifawu.ui.lawyer.chat.bean.HistoryChatItemBean;
import com.ytfu.yuntaifawu.ui.lawyer.chat.bean.HistoryChatItemMultiItem;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LawyerChatRoomAdapter extends
        BaseMultiItemQuickAdapter<HistoryChatItemMultiItem, BaseViewHolder> {
    private String toUserAvatar;
    private String selfAvatar;


    public LawyerChatRoomAdapter(String toUserAvatar, String selfAvatar) {
        super(new ArrayList<>());
        this.toUserAvatar = toUserAvatar;
        this.selfAvatar = selfAvatar;
        addItemType(HistoryChatItemMultiItem.TYPE_SEND_MSG, R.layout.item_chat_send_msg);
        addItemType(HistoryChatItemMultiItem.TYPE_SEND_FEE, R.layout.item_chat_send_fee);
        addItemType(HistoryChatItemMultiItem.TYPE_RECEIVE_MSG, R.layout.item_chat_receive_msg);
        addItemType(HistoryChatItemMultiItem.TYPE_RECEIVE_FEE, R.layout.item_chat_receive_fee);
    }


    @Override
    protected void convert(BaseViewHolder helper, HistoryChatItemMultiItem item) {
        int type = helper.getItemViewType();

        switch (type) {
            case HistoryChatItemMultiItem.TYPE_SEND_MSG:
                handleSelfMsg(helper, item);
                break;
            case HistoryChatItemMultiItem.TYPE_RECEIVE_MSG:
                handleReceiveMsg(helper, item);
                break;
            case HistoryChatItemMultiItem.TYPE_SEND_FEE:
                handleSelfFee(helper, item);
                break;
            case HistoryChatItemMultiItem.TYPE_RECEIVE_FEE:
                handleReceiveFee(helper, item);
                break;
            default:
                break;
        }
        helper.addOnClickListener(R.id.iv_chat_item_avatar);
    }


    //===Desc:=================================================================

    /**
     * 处理自己发送的文本
     */
    private void handleSelfMsg(BaseViewHolder helper, HistoryChatItemMultiItem item) {
        HistoryChatItemBean chatItem = item.getChatItem();
        int position = getData().indexOf(item);
        //设置时间显示
        if (position == 0) {
            helper.setGone(R.id.tv_chat_item_time, true);
            String timeStr = DateUtils.getTimestampString(new Date(chatItem.getTimestamp() * 1000));
            helper.setText(R.id.tv_chat_item_time, timeStr);
        } else {
            HistoryChatItemMultiItem preItem = getData().get(position - 1);
            if (null != preItem && DateUtils.isCloseEnough(chatItem.getTimestamp() * 1000, preItem.getChatItem().getTimestamp() * 1000)) {
                helper.setGone(R.id.tv_chat_item_time, false);
            } else {
                helper.setGone(R.id.tv_chat_item_time, true);
                String timeStr = DateUtils.getTimestampString(new Date(chatItem.getTimestamp() * 1000));
                helper.setText(R.id.tv_chat_item_time, timeStr);
            }
        }
        //设置消息
        helper.setText(R.id.tv_chat_item_content, chatItem.getBody().getText());
        //设置头像
        ImageView ivAvatar = helper.getView(R.id.iv_chat_item_avatar);
        CircleCrop cc = new CircleCrop();
        RequestOptions options = RequestOptions.bitmapTransform(cc)
                .placeholder(R.drawable.touxiang)//图片加载出来前，显示的图片
                .fallback(R.drawable.touxiang) //url为空的时候,显示的图片
                .error(R.drawable.touxiang);//图片加载失败后，显示的图片
        Glide.with(mContext).load(selfAvatar)
                .apply(options)
                .into(ivAvatar);

        //状态回显
        helper.setGone(R.id.tv_chat_item_status, false);
        if (chatItem.getIsRead() == 1) {
            helper.setText(R.id.tv_chat_item_status, "已读");
        } else {
            helper.setText(R.id.tv_chat_item_status, "未读");
        }
        helper.addOnClickListener(R.id.iv_chat_item_avatar);
        //
    }

    private void handleSelfFee(BaseViewHolder helper, HistoryChatItemMultiItem item) {
        HistoryChatItemBean chatItem = item.getChatItem();
        int position = getData().indexOf(item);
        //设置时间显示
        if (position == 0) {
            helper.setGone(R.id.tv_chat_item_time, true);
            String timeStr = DateUtils.getTimestampString(new Date(chatItem.getTimestamp() * 1000));
            helper.setText(R.id.tv_chat_item_time, timeStr);
        } else {
            HistoryChatItemMultiItem preItem = getData().get(position - 1);
            if (null != preItem && DateUtils.isCloseEnough(chatItem.getTimestamp() * 1000, preItem.getChatItem().getTimestamp() * 1000)) {
                helper.setGone(R.id.tv_chat_item_time, false);
            } else {
                helper.setGone(R.id.tv_chat_item_time, true);
                String timeStr = DateUtils.getTimestampString(new Date(chatItem.getTimestamp() * 1000));
                helper.setText(R.id.tv_chat_item_time, timeStr);
            }
        }
        //设置金额
        helper.setText(R.id.tv_chat_item_money, chatItem.getExt().getPrice());
        //设置头像
        ImageView ivAvatar = helper.getView(R.id.iv_chat_item_avatar);
        CircleCrop cc = new CircleCrop();
        RequestOptions options = RequestOptions.bitmapTransform(cc)
                .placeholder(R.drawable.touxiang)//图片加载出来前，显示的图片
                .fallback(R.drawable.touxiang) //url为空的时候,显示的图片
                .error(R.drawable.touxiang);//图片加载失败后，显示的图片
        Glide.with(mContext).load(selfAvatar)
                .apply(options)
                .into(ivAvatar);

        //设置支付状态 1 未支付 2 已支付
        int type = chatItem.getExt().getType();
        if (type == 2) {
            helper.setText(R.id.tv_chat_item_pay_status, "已支付");
            helper.setTextColor(R.id.tv_chat_item_pay_status, Color.parseColor("#E1504E"));
            //已支付图标显示
            helper.setGone(R.id.iv_chat_item_payed, true);
        } else {
            helper.setText(R.id.tv_chat_item_pay_status, "待支付");
            helper.setTextColor(R.id.tv_chat_item_pay_status, Color.parseColor("#44A2F7"));
            //已支付图标显示
            helper.setGone(R.id.iv_chat_item_payed, false);
        }
        helper.addOnClickListener(R.id.iv_chat_item_avatar);
    }

    /**
     * 处理接收的文本
     */
    private void handleReceiveMsg(BaseViewHolder helper, HistoryChatItemMultiItem item) {
        HistoryChatItemBean chatItem = item.getChatItem();
        int position = getData().indexOf(item);
        //设置时间显示
        if (position == 0) {
            helper.setGone(R.id.tv_chat_item_time, true);
            String timeStr = DateUtils.getTimestampString(new Date(chatItem.getTimestamp() * 1000));
            helper.setText(R.id.tv_chat_item_time, timeStr);
        } else {
            HistoryChatItemMultiItem preItem = getData().get(position - 1);
            if (null != preItem && DateUtils.isCloseEnough(chatItem.getTimestamp() * 1000, preItem.getChatItem().getTimestamp() * 1000)) {
                helper.setGone(R.id.tv_chat_item_time, false);
            } else {
                helper.setGone(R.id.tv_chat_item_time, true);
                String timeStr = DateUtils.getTimestampString(new Date(chatItem.getTimestamp() * 1000));
                helper.setText(R.id.tv_chat_item_time, timeStr);
            }
        }
        //设置消息
        helper.setText(R.id.tv_chat_item_content, chatItem.getBody().getText());
        //设置头像
        ImageView ivAvatar = helper.getView(R.id.iv_chat_item_avatar);
        CircleCrop cc = new CircleCrop();
        RequestOptions options = RequestOptions.bitmapTransform(cc)
                .placeholder(R.drawable.touxiang)//图片加载出来前，显示的图片
                .fallback(R.drawable.touxiang) //url为空的时候,显示的图片
                .error(R.drawable.touxiang);//图片加载失败后，显示的图片
        Glide.with(mContext).load(toUserAvatar)
                .apply(options)
                .into(ivAvatar);
        helper.addOnClickListener(R.id.iv_chat_item_avatar);
    }

    private void handleReceiveFee(BaseViewHolder helper, HistoryChatItemMultiItem item) {
        HistoryChatItemBean chatItem = item.getChatItem();
        int position = getData().indexOf(item);
        //设置时间显示
        if (position == 0) {
            helper.setGone(R.id.tv_chat_item_time, true);
            String timeStr = DateUtils.getTimestampString(new Date(chatItem.getTimestamp() * 1000));
            helper.setText(R.id.tv_chat_item_time, timeStr);
        } else {
            HistoryChatItemMultiItem preItem = getData().get(position - 1);
            if (null != preItem && DateUtils.isCloseEnough(chatItem.getTimestamp() * 1000, preItem.getChatItem().getTimestamp() * 1000)) {
                helper.setGone(R.id.tv_chat_item_time, false);
            } else {
                helper.setGone(R.id.tv_chat_item_time, true);
                String timeStr = DateUtils.getTimestampString(new Date(chatItem.getTimestamp() * 1000));
                helper.setText(R.id.tv_chat_item_time, timeStr);
            }
        }
        //设置金额
        helper.setText(R.id.tv_chat_item_money, chatItem.getExt().getPrice());
        //设置头像
        ImageView ivAvatar = helper.getView(R.id.iv_chat_item_avatar);
        CircleCrop cc = new CircleCrop();
        RequestOptions options = RequestOptions.bitmapTransform(cc)
                .placeholder(R.drawable.touxiang)//图片加载出来前，显示的图片
                .fallback(R.drawable.touxiang) //url为空的时候,显示的图片
                .error(R.drawable.touxiang);//图片加载失败后，显示的图片
        Glide.with(mContext).load(toUserAvatar)
                .apply(options)
                .into(ivAvatar);

        //判断接收到的服务费的支付状态  type =2 已支付
        int payType = chatItem.getExt().getType();
        if (payType == 2) {//已经支付
            helper.setGone(R.id.tv_chat_item_tip1, false);
            helper.setGone(R.id.tv_chat_item_tip2, true);
            //底部支付状态
            helper.setGone(R.id.ll_chat_item_status_unpay, false);
            helper.setGone(R.id.tv_chat_item_pay_status, true);
            //顶部图标显示
            helper.setGone(R.id.iv_chat_item_payed, true);

        } else {
            helper.setGone(R.id.tv_chat_item_tip1, true);
            helper.setGone(R.id.tv_chat_item_tip2, false);
            //底部支付状态
            helper.setGone(R.id.ll_chat_item_status_unpay, true);
            helper.setGone(R.id.tv_chat_item_pay_status, false);
            //顶部图标显示
            helper.setGone(R.id.iv_chat_item_payed, false);
            //未支付状态下才能添加点击事件
            helper.addOnClickListener(R.id.rl_chat_item_pay);//点击支付
        }
    }


    //===Desc:=================================================================
    public int indexOfByMessageId(String messageId) {
        List<HistoryChatItemMultiItem> data = getData();
        for (HistoryChatItemMultiItem item : data) {
            String itemMessageId = item.getChatItem().getMessageId();
            if (messageId.equals(itemMessageId)) {
                return data.indexOf(item);
            }
        }
        return -1;
    }
}
