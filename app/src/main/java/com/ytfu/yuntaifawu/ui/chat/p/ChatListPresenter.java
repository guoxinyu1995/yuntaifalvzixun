package com.ytfu.yuntaifawu.ui.chat.p;

import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMMessage;
import com.ytfu.yuntaifawu.apis.ChatService;
import com.ytfu.yuntaifawu.apis.HttpUtil;
import com.ytfu.yuntaifawu.base.BasePresenter;
import com.ytfu.yuntaifawu.helper.BaseRxObserver;
import com.ytfu.yuntaifawu.im.EmChatManager;
import com.ytfu.yuntaifawu.ui.chat.bean.LawyerListBean;
import com.ytfu.yuntaifawu.ui.chat.v.IChatListView;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ChatListPresenter extends BasePresenter<IChatListView> implements EMMessageListener {
    /**
     * 获取律师列表
     */
    public void loadLawyerList(String uid) {
        HttpUtil.getInstance().getService(ChatService.class)
                .getLawyerChatList(uid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(bindLifecycle())
                .subscribe(new BaseRxObserver<LawyerListBean>() {
                    @Override
                    public void onNextImpl(LawyerListBean lawyerListBean) {
                        if (null == lawyerListBean) {
                            getView().onLawyerListFail("加载失败");
                        } else {
                            if ("200".equals(lawyerListBean.getCode()) || "202".equals(lawyerListBean.getCode())) {
                                getView().onLawyerListSuccess(lawyerListBean.getData());
                            } else {
                                getView().onLawyerListFail("错误码:" + lawyerListBean.getCode() + "  " + lawyerListBean.getMsg());
                            }
                        }
                    }

                    @Override
                    public void onErrorImpl(String errorMessage) {
                        getView().onLawyerListFail(errorMessage);
                    }
                });
    }


    ///////////////////////////////////////////////////////////////////////////
    //
    ///////////////////////////////////////////////////////////////////////////

    public void registerMessageListener() {
        EmChatManager.getInstance().registerMessageListener(this);
    }

    public void unRegisterMessageListener() {
        EmChatManager.getInstance().unRegisterMessageListener(this);
    }


    ///////////////////////////////////////////////////////////////////////////
    // 环信接收消息的监听
    ///////////////////////////////////////////////////////////////////////////
    @Override
    public void onMessageReceived(List<EMMessage> list) {
        getView().reLoadList();
    }

    @Override
    public void onCmdMessageReceived(List<EMMessage> list) {

    }

    @Override
    public void onMessageRead(List<EMMessage> list) {

    }

    @Override
    public void onMessageDelivered(List<EMMessage> list) {

    }

    @Override
    public void onMessageRecalled(List<EMMessage> list) {

    }

    @Override
    public void onMessageChanged(EMMessage emMessage, Object o) {

    }
}
