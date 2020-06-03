package com.ytfu.yuntaifawu.ui.chatroom.v;

import com.ytfu.yuntaifawu.base.BaseView;
import com.ytfu.yuntaifawu.ui.chatroom.bean.FeeWechatOrderResponse;
import com.ytfu.yuntaifawu.ui.chatroom.bean.RoomLawyerCardInfoResponse;
import com.ytfu.yuntaifawu.ui.lawyer.chat.bean.HistoryChatItemBean;
import com.ytfu.yuntaifawu.ui.lawyer.chat.bean.HistoryChatItemMultiItem;
import com.ytfu.yuntaifawu.ui.mseeage.bean.ExChangeWeiXinBean;
import com.ytfu.yuntaifawu.ui.mseeage.bean.ToCheckPayBean;
import com.ytfu.yuntaifawu.ui.mseeage.bean.WhetherToPayBean;
import com.ytfu.yuntaifawu.ui.mseeage.bean.ZiXunSendMessageBean;
import com.ytfu.yuntaifawu.ui.pay.bean.PayBean;

import java.util.List;

public interface UserChatRoomView extends BaseView {

    /**
     * 获取历史记录成功
     */
    void onGetHistoryRecordSuccess(List<HistoryChatItemMultiItem> data);

    void onGetHistoryRecordFail(String errorMsg);

    //===Desc:=================================================================

    void onSendTxtPre(HistoryChatItemBean itemBean);

    void onSendTxtSuccess(String toUserId, String fromUserId, HistoryChatItemBean itemBean);

    void onSendTxtFail(HistoryChatItemBean itemBean);

    //===Desc:=================================================================

    void onGetFeeWechatOrderSuccess(FeeWechatOrderResponse bean);

    void onGetFeeWechatOrderFail(String errorMsg);

    void onGetFeeAliOrderSuccess(PayBean bean);

    void onGetFeeAliOrderFail(String errorMsg);

    //===Desc:=================================================================

    void onGetLawyerCardInfoSuccess(RoomLawyerCardInfoResponse.LawyerCardInfo info);

    void onGetLawyerCardInfoFail(String errorMsg);

    //===Desc:=================================================================

    void onGetCustomerServiceMessageSuccess(List<ZiXunSendMessageBean.ListBean> list);

    void onGetCustomerServiceMessageFail(String errorMsg);

    //===Desc:=================================================================

    void onCheckPaySuccess(int requestCode, WhetherToPayBean bean);

    void onCheckPayFail(int requestCode, String errorMsg);

    //===Desc:=================================================================
    void onExchangeWeChatSuccess(ExChangeWeiXinBean data);

    void onExchangeWeChatFail(String errorMsg);

    ///////////////////////////////////////////////////////////////////////////
    //
    ///////////////////////////////////////////////////////////////////////////


    void onTextReceived(List<HistoryChatItemMultiItem> list);

    void onMessageRead(List<HistoryChatItemMultiItem> list);

    ///////////////////////////////////////////////////////////////////////////
    //
    ///////////////////////////////////////////////////////////////////////////

    void onCheckMessagePayedSuccess(ToCheckPayBean data);



}
