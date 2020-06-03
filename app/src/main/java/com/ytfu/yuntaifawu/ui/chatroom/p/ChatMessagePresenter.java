package com.ytfu.yuntaifawu.ui.chatroom.p;

import com.ytfu.yuntaifawu.apis.HttpUtil;
import com.ytfu.yuntaifawu.apis.MessageService;
import com.ytfu.yuntaifawu.base.BasePresenter;
import com.ytfu.yuntaifawu.helper.BaseRxObserver;
import com.ytfu.yuntaifawu.ui.chat.bean.LawyerListBean;
import com.ytfu.yuntaifawu.ui.chatroom.bean.AddMessageResponseBean;
import com.ytfu.yuntaifawu.ui.chatroom.v.IChatMessageView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 消息相关的业务层实现
 */
public class ChatMessagePresenter extends BasePresenter<IChatMessageView> {


    public void syncMessageToService(String uid,
                                     String lsid,
                                     String msg,
                                     int type) {
//        HttpUtil.getInstance().getService2(MessageService.class)
//                .syncMessageToService(uid, lsid, msg, type)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .as(bindLifecycle())
//                .subscribe(new BaseRxObserver<AddMessageResponseBean>() {
//                    @Override
//                    public void onNextImpl(AddMessageResponseBean response) {
//                        if (null == response) {
//                            getView().onSyncMessageToFail("服务器响应数据失败,请稍后重试");
//                        } else {
//                            String code = response.getCode();
//                            if (!"200".equals(code)) {
//                                getView().onSyncMessageToFail(response.getMsg());
//                            } else {
//                                getView().onSyncMessageToSuccess(msg);
//                            }
//                        }
//                    }
//
//                    @Override
//                    public void onErrorImpl(String errorMessage) {
//                        getView().onSyncMessageToFail(errorMessage);
//                    }
//                });

    }


    /**
     * @param uid    用户id
     * @param lsid   律师id
     * @param source 1 来自用户端 2 来自律师端
     */
    public void getHistoricalMessage(String uid, String lsid, int source) {

    }

}
