package com.ytfu.yuntaifawu.ui.lawyer.chat.p;

import com.google.gson.Gson;
import com.hyphenate.EMCallBack;
import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMTextMessageBody;
import com.orhanobut.logger.Logger;
import com.ytfu.yuntaifawu.apis.HttpUtil;
import com.ytfu.yuntaifawu.apis.MessageService;
import com.ytfu.yuntaifawu.apis.TransactionService;
import com.ytfu.yuntaifawu.base.BasePresenter;
import com.ytfu.yuntaifawu.helper.BaseRxObserver;
import com.ytfu.yuntaifawu.helper.RxLifecycleUtil;
import com.ytfu.yuntaifawu.im.EmChatManager;
import com.ytfu.yuntaifawu.ui.chatroom.bean.AddMessageResponseBean;
import com.ytfu.yuntaifawu.ui.lawyer.chat.bean.FeeResponse;
import com.ytfu.yuntaifawu.ui.lawyer.chat.bean.HistoryChatBodyBean;
import com.ytfu.yuntaifawu.ui.lawyer.chat.bean.HistoryChatExtBean;
import com.ytfu.yuntaifawu.ui.lawyer.chat.bean.HistoryChatItemBean;
import com.ytfu.yuntaifawu.ui.lawyer.chat.bean.HistoryChatItemMultiItem;
import com.ytfu.yuntaifawu.ui.lawyer.chat.v.LawyerChatRoomView;
import com.ytfu.yuntaifawu.ui.lawyer.withdraw.bean.WithdrawResponseBean;
import com.ytfu.yuntaifawu.ui.mseeage.fragment.ChatFragment;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 律师端聊天界面业务层实现
 */
public class LawyerChatRoomPresenter extends BasePresenter<LawyerChatRoomView> implements EMMessageListener {

    /**
     * 发送按钮发送消息给对方
     */
    public void sendTextMessage(String selfId, String toUserId, String content) {
        EMMessage message = EmChatManager.getInstance().createTxtMessage(toUserId, content);
        HistoryChatItemBean itemBean = new HistoryChatItemBean();
        itemBean.setMessageId(message.getMsgId());
        itemBean.setDirection(0);
        itemBean.setFrom(selfId);
        itemBean.setTo(toUserId);
        itemBean.setTimestamp(System.currentTimeMillis() / 1000);
        itemBean.setChatType(0);
        itemBean.setStatus(2);
        itemBean.setIsRead(0);

        HistoryChatBodyBean body = new HistoryChatBodyBean();
        body.setType(1);
        body.setText(content);
        itemBean.setBody(body);

        HistoryChatExtBean ext = new HistoryChatExtBean();
        itemBean.setExt(ext);

        getView().onSendTxtPre(itemBean);

        message.setMessageStatusCallback(new EMCallBack() {
            @Override
            public void onSuccess() {
                getView().onSendTxtSuccess(toUserId, selfId, itemBean);
            }

            @Override
            public void onError(int code, String errorMessage) {
                getView().onSendTxtFail(itemBean);
            }

            @Override
            public void onProgress(int i, String s) {

            }
        });
        //发送消息
        EMClient.getInstance().chatManager().sendMessage(message);
    }

    /**
     * 发送收取服务费的消息
     */
    public void sendFeeMessage(String toUserId, String selfId, String price) {
        //        EMMessage message = EMMessage.createSendMessage(EMMessage.Type.CUSTOM);
        HistoryChatItemBean itemBean = new HistoryChatItemBean();
        //        itemBean.setMessageId(message.getMsgId());
        itemBean.setDirection(0);
        itemBean.setFrom(selfId);
        itemBean.setTo(toUserId);
        itemBean.setTimestamp(System.currentTimeMillis() / 1000);
        itemBean.setChatType(0);
        itemBean.setStatus(2);
        itemBean.setIsRead(0);

        itemBean.setBody(null);

        HistoryChatExtBean ext = new HistoryChatExtBean();
        ext.setJiluid(String.valueOf(System.currentTimeMillis() / 1000));
        ext.setLvshiid(selfId);
        ext.setUserid(toUserId);
        ext.setPrice(price);
        ext.setType(1);

        itemBean.setExt(ext);

        HttpUtil.getInstance().getService(TransactionService.class)
                .chargeFee(toUserId, selfId, price)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(bindLifecycle())
                .subscribe(new BaseRxObserver<FeeResponse>() {
                    @Override
                    public void onSubscribeImpl(Disposable d) {
                        super.onSubscribeImpl(d);
                        getView().showProgress();
                        getView().onSendFeePre(itemBean);
                    }

                    @Override
                    public void onNextImpl(FeeResponse response) {
                        getView().hideProgress();
                        if (null == response) {
                            getView().onSendFeeFail(itemBean);
                            return;
                        }
                        if (response.getStatus() != 200) {
                            getView().onSendFeeFail(itemBean);
                            return;
                        }
                        getView().onSendFeeSuccess(toUserId, selfId, itemBean);

                    }

                    @Override
                    public void onErrorImpl(String errorMessage) {
                        getView().hideProgress();
                        getView().onSendFeeFail(itemBean);
                    }
                });
    }

    /**
     * 律師同步消息到服務器
     */
    public void lawyerSyncMessageToService(String consultId, String toUserId, String fromUserId, String content) {
        HttpUtil.getInstance().getService2(MessageService.class)
                .lawyerSyncMessageToService(consultId, toUserId, fromUserId, content, "2")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(bindLifecycle())
                .subscribe(new BaseRxObserver<AddMessageResponseBean>() {
                    @Override
                    public void onNextImpl(AddMessageResponseBean response) {
                        getView().onSyncMessageSuccess();
                    }

                    @Override
                    public void onErrorImpl(String errorMessage) {
                        getView().onSyncMessageFail(errorMessage);
                    }
                });
    }

    //===Desc:=================================================================

    /**
     * 注册消息接收
     */
    public void registerMessageListener() {
        EmChatManager.getInstance().registerMessageListener(this);
    }

    /**
     * 注册消息接收
     */
    public void unregisterMessageListener() {
        EmChatManager.getInstance().unRegisterMessageListener(this);
    }

    //===Desc:环信消息监听相关=================================================================

    @Override
    public void onMessageReceived(List<EMMessage> messages) {
        //收到消息
        //收到已读回执
        List<HistoryChatItemMultiItem> list = new ArrayList<>();

        for (EMMessage msg : messages) {
            Logger.e("msg ----->" + msg);
            Map<String, Object> extMap1 = msg.ext();
            String s = new Gson().toJson(extMap1);
            Logger.e("ext json----->" + s);

            String from = msg.getFrom();
            String to = msg.getTo();
            String content = "";
            if (msg.getType() == EMMessage.Type.TXT) {
                EMTextMessageBody body = (EMTextMessageBody) msg.getBody();
                content = body.getMessage();
            }

            HistoryChatItemBean itemBean = new HistoryChatItemBean();
            itemBean.setMessageId(msg.getMsgId());
            itemBean.setDirection(1);
            itemBean.setFrom(from);
            itemBean.setTo(to);
            itemBean.setTimestamp(System.currentTimeMillis() / 1000);
            itemBean.setChatType(0);
            itemBean.setStatus(2);
            itemBean.setIsRead(0);

            HistoryChatBodyBean body = new HistoryChatBodyBean();
            body.setType(1);
            body.setText(content);
            itemBean.setBody(body);
            //接受文本
            //msg{from:4286, to:3 body:txt:"1"
            //ext json----->{}
            //接受红包
            //msg{from:4286, to:3 body:txt:"已支付律师咨询费0.01元"
            //{"price":"0.01","jilu_id":0,"type":2,"huashu_id":"15910784817"}


            //获取环信消息的ext字段  转换成HistoryChatExtBean
            Map<String, Object> extMap = msg.ext();
            if (extMap.size() >0) {
                Gson gson = new Gson();
                String json = gson.toJson(extMap);
                HistoryChatExtBean ext = gson.fromJson(json, HistoryChatExtBean.class);
                ext.setJiluid(String.valueOf(System.currentTimeMillis() / 1000));
                itemBean.setExt(ext);
            }


            HistoryChatItemMultiItem bean = new HistoryChatItemMultiItem(itemBean);
            list.add(bean);
        }
        getView().onTextReceived(list);

    }

    @Override
    public void onCmdMessageReceived(List<EMMessage> messages) {
        //收到透传消息
    }

    @Override
    public void onMessageRead(List<EMMessage> messages) {
        //收到已读回执
        List<HistoryChatItemMultiItem> list = new ArrayList<>();

        for (EMMessage msg : messages) {
            String from = msg.getFrom();
            String to = msg.getTo();
            String content = "";
            if (msg.getType() == EMMessage.Type.TXT) {
                EMTextMessageBody body = (EMTextMessageBody) msg.getBody();
                content = body.getMessage();
            }

            HistoryChatItemBean itemBean = new HistoryChatItemBean();
            itemBean.setMessageId(msg.getMsgId());
            itemBean.setDirection(0);
            itemBean.setFrom(from);
            itemBean.setTo(to);
            itemBean.setTimestamp(System.currentTimeMillis() / 1000);
            itemBean.setChatType(0);
            itemBean.setStatus(2);
            itemBean.setIsRead(1);

            HistoryChatBodyBean body = new HistoryChatBodyBean();
            body.setType(1);
            body.setText(content);
            itemBean.setBody(body);

            HistoryChatExtBean ext = new HistoryChatExtBean();
            itemBean.setExt(ext);

            HistoryChatItemMultiItem bean = new HistoryChatItemMultiItem(itemBean);
            list.add(bean);
        }
        getView().onMessageRead(list);
    }

    @Override
    public void onMessageDelivered(List<EMMessage> message) {
        //收到已送达回执
    }

    @Override
    public void onMessageRecalled(List<EMMessage> messages) {
        //消息被撤回
    }

    @Override
    public void onMessageChanged(EMMessage message, Object change) {
        //消息状态变动
    }
}
