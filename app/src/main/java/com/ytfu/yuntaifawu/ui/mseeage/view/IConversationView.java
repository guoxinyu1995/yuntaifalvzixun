package com.ytfu.yuntaifawu.ui.mseeage.view;

import com.ytfu.yuntaifawu.base.BaseView;
import com.ytfu.yuntaifawu.ui.chat.bean.LawyerListBean;
import com.ytfu.yuntaifawu.ui.mseeage.bean.ConversationBean;
import com.ytfu.yuntaifawu.ui.mseeage.bean.FirstMessageBean;
import com.ytfu.yuntaifawu.ui.mseeage.bean.LvShiCardBean;

import java.util.List;

public interface IConversationView extends BaseView {
    void onCoversationSuccess(ConversationBean conversationBean);

    //律师卡片
    void onLvShiCardSuccess(LvShiCardBean lvShiCardBean);
    void onConversationFiled();


    void onGetLawyerChatListSuccess(List<LawyerListBean.LawyerItemBean> data);

    void onGetLawyerChatListFail(String msg);


}
