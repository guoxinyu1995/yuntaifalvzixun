package com.ytfu.yuntaifawu.ui.chatroom.p;

import android.app.Activity;

import com.google.gson.Gson;
import com.hyphenate.EMCallBack;
import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chat.EMTextMessageBody;
import com.orhanobut.logger.Logger;
import com.ytfu.yuntaifawu.apis.ApiService;
import com.ytfu.yuntaifawu.apis.HttpUtil;
import com.ytfu.yuntaifawu.apis.LawyerService;
import com.ytfu.yuntaifawu.apis.MessageService;
import com.ytfu.yuntaifawu.apis.PayService;
import com.ytfu.yuntaifawu.base.BasePresenter;
import com.ytfu.yuntaifawu.helper.BaseRxObserver;
import com.ytfu.yuntaifawu.im.EmChatManager;
import com.ytfu.yuntaifawu.ui.chatroom.bean.AddMessageResponseBean;
import com.ytfu.yuntaifawu.ui.chatroom.bean.FeeWechatOrderResponse;
import com.ytfu.yuntaifawu.ui.chatroom.bean.RoomLawyerCardInfoResponse;
import com.ytfu.yuntaifawu.ui.chatroom.v.UserChatRoomView;
import com.ytfu.yuntaifawu.ui.lawyer.chat.bean.HistoryChatBodyBean;
import com.ytfu.yuntaifawu.ui.lawyer.chat.bean.HistoryChatExtBean;
import com.ytfu.yuntaifawu.ui.lawyer.chat.bean.HistoryChatItemBean;
import com.ytfu.yuntaifawu.ui.lawyer.chat.bean.HistoryChatItemMultiItem;
import com.ytfu.yuntaifawu.ui.lawyer.chat.bean.HistoryChatResponse;
import com.ytfu.yuntaifawu.ui.mseeage.bean.ExChangeWeiXinBean;
import com.ytfu.yuntaifawu.ui.mseeage.bean.ToCheckPayBean;
import com.ytfu.yuntaifawu.ui.mseeage.bean.WhetherToPayBean;
import com.ytfu.yuntaifawu.ui.mseeage.bean.ZiXunSendMessageBean;
import com.ytfu.yuntaifawu.ui.pay.PayHelper;
import com.ytfu.yuntaifawu.ui.pay.bean.PayBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class UserChatRoomPresenter extends BasePresenter<UserChatRoomView> implements EMMessageListener {


    public void getHistoryRecord(String toUserId, String selfId) {
        HttpUtil.getInstance().getService(MessageService.class)
                .userGetHistoryRecord(selfId, toUserId, 1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(bindLifecycle())
                .subscribe(new BaseRxObserver<HistoryChatResponse>() {
                    @Override
                    public void onNextImpl(HistoryChatResponse historyRecordResponseBean) {
                        if (null == historyRecordResponseBean) {
                            getView().onGetHistoryRecordFail("获取数据失败");
                            return;
                        }
                        if (!"200".equals(historyRecordResponseBean.getCode())) {
                            getView().onGetHistoryRecordFail("获取数据失败");
                            return;
                        }
                        List<HistoryChatItemMultiItem> data = new ArrayList<>();
                        List<HistoryChatItemBean> list = historyRecordResponseBean.getData();
                        for (HistoryChatItemBean item : list) {
                            HistoryChatItemMultiItem bean = new HistoryChatItemMultiItem(item);
                            data.add(bean);
                        }
                        getView().onGetHistoryRecordSuccess(data);
                    }

                    @Override
                    public void onErrorImpl(String errorMessage) {
                        getView().onGetHistoryRecordFail(errorMessage);
                    }
                });

    }

    /**
     * 发送文本聊天
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
     * 咨询付费获取支付订单
     *
     * @param isWechatPay 是否是微信支付
     */
    public void getFeePayOrder(boolean isWechatPay, String selfId, String toUserId, String redPackageId) {
        PayService service = HttpUtil.getInstance().getService(PayService.class);
        if (isWechatPay) {
            Observable<FeeWechatOrderResponse> ob = service.getFeeWechatPayOrder(selfId, toUserId, redPackageId, 12, 1);
            HttpUtil.getInstance().enqueue(ob, bindLifecycle(), new BaseRxObserver<FeeWechatOrderResponse>() {

                @Override
                public void onSubscribeImpl(Disposable d) {
                    super.onSubscribeImpl(d);
                    getView().showProgress();
                }

                @Override
                public void onNextImpl(FeeWechatOrderResponse feeWechatOrderResponse) {
                    if (null == feeWechatOrderResponse) {
                        getView().onGetFeeWechatOrderFail("获取数据失败,请稍后重试");
                        return;
                    }
                    getView().onGetFeeWechatOrderSuccess(feeWechatOrderResponse);
                    getView().hideProgress();
                }

                @Override
                public void onErrorImpl(String errorMessage) {
                    getView().onGetFeeWechatOrderFail(errorMessage);
                    getView().hideProgress();
                }
            });
        } else {
            Observable<PayBean> ob = service.getFeeAliPayOrder(selfId, toUserId, redPackageId, 12, 1);
            HttpUtil.getInstance().enqueue(ob, bindLifecycle(), new BaseRxObserver<PayBean>() {

                @Override
                public void onSubscribeImpl(Disposable d) {
                    super.onSubscribeImpl(d);
                    getView().showProgress();
                }

                @Override
                public void onNextImpl(PayBean payBean) {
                    if (null == payBean) {
                        getView().onGetFeeAliOrderFail("获取数据失败,请稍后重试");
                        return;
                    }
                    getView().onGetFeeAliOrderSuccess(payBean);
                    getView().hideProgress();
                }

                @Override
                public void onErrorImpl(String errorMessage) {
                    getView().onGetFeeAliOrderFail(errorMessage);
                    getView().hideProgress();
                }
            });

        }


    }


    /**
     * 通过获取的服务器支付宝订单唤起支付宝支付
     */
    public void arouseAlipay(Activity activity, String sign, PayHelper.IPayListener listener) {
        PayHelper.getInstance().setIPayListener(listener);
        PayHelper.getInstance().AliPay(activity, sign);
    }


    /**
     * 获取顶部律师卡片信息
     */
    public void getLawyerCardInfo(String lawyerId) {
        Observable<RoomLawyerCardInfoResponse> ob = HttpUtil.getInstance()
                .getService(LawyerService.class)
                .getLawyerCardInfo(lawyerId);
        HttpUtil.getInstance().enqueue(ob, bindLifecycle(), new BaseRxObserver<RoomLawyerCardInfoResponse>() {

            @Override
            public void onNextImpl(RoomLawyerCardInfoResponse lvShiCardBean) {
                if (null == lvShiCardBean) {
                    getView().onGetLawyerCardInfoFail("获取数据失败");
                    return;
                }
                int code = lvShiCardBean.getCode();
                if (code != 200) {
                    getView().onGetLawyerCardInfoFail(lvShiCardBean.getState());
                    return;
                }
                RoomLawyerCardInfoResponse.LawyerCardInfo data = lvShiCardBean.getData();
                if (null == data) {
                    getView().onGetLawyerCardInfoFail("数据解析失败");
                    return;
                }
                getView().onGetLawyerCardInfoSuccess(data);
            }

            @Override
            public void onErrorImpl(String errorMessage) {
                getView().onGetLawyerCardInfoFail(errorMessage);
            }
        });
    }

    /**
     * 获取服务号的顶部信息
     */
    public void getCustomerServiceMessage() {
        Observable<ZiXunSendMessageBean> ob = HttpUtil.getInstance().getApiService().setZiXunMessage();
        HttpUtil.getInstance().enqueue(ob, bindLifecycle(), new BaseRxObserver<ZiXunSendMessageBean>() {

            @Override
            public void onNextImpl(ZiXunSendMessageBean data) {
                if (null == data) {
                    getView().onGetCustomerServiceMessageFail("获取数据失败");
                    return;
                }
                if (data.getStatus() != 200) {
                    getView().onGetCustomerServiceMessageFail(data.getState());
                    return;
                }
                getView().onGetCustomerServiceMessageSuccess(data.getList());

            }

            @Override
            public void onErrorImpl(String errorMessage) {
                getView().onGetCustomerServiceMessageFail(errorMessage);

            }
        });
    }


    /**
     * 检测用户是否已经支付
     */
    public void checkPay(int requestCode, String selfId, String toUserId) {
        Observable<WhetherToPayBean> ob = HttpUtil.getInstance().getService(PayService.class)
                .checkPay(selfId, toUserId);
        HttpUtil.getInstance().enqueue(ob, bindLifecycle(), new BaseRxObserver<WhetherToPayBean>() {
            @Override
            public void onNextImpl(WhetherToPayBean data) {
                if (null == data) {
                    getView().onCheckPayFail(requestCode, "获取数据失败");
                    return;
                }
                getView().onCheckPaySuccess(requestCode, data);

            }

            @Override
            public void onErrorImpl(String errorMessage) {
                getView().onCheckPayFail(requestCode, errorMessage);
            }
        });
    }


    /**
     * 交换微信
     */
    public void exchangeWeChat(String selfId, String toUserId) {
        HashMap<String, String> map = new HashMap<>();
        map.put("userid", selfId);
        map.put("lvshiid", toUserId);
        Observable<ExChangeWeiXinBean> ob = HttpUtil.getInstance().getApiService().setExChangeWx(map);
        HttpUtil.getInstance().enqueue(ob, bindLifecycle(), new BaseRxObserver<ExChangeWeiXinBean>() {
            @Override
            public void onSubscribeImpl(Disposable d) {
                super.onSubscribeImpl(d);
                getView().showProgress();
            }

            @Override
            public void onNextImpl(ExChangeWeiXinBean data) {
                if (null == data) {
                    getView().onExchangeWeChatFail("获取数据失败");
                    getView().hideProgress();
                    return;
                }
                getView().onExchangeWeChatSuccess(data);
                getView().hideProgress();
            }

            @Override
            public void onErrorImpl(String errorMessage) {
                getView().onExchangeWeChatFail(errorMessage);
                getView().hideProgress();
            }
        });
    }

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

    ///////////////////////////////////////////////////////////////////////////
    // 环信接收消息的回调监听
    ///////////////////////////////////////////////////////////////////////////
    @Override
    public void onMessageReceived(List<EMMessage> messages) {
        for (EMMessage msg : messages) {
            Logger.e("!!!!!!!!!!!!!!!!!!!--->" + msg);

            Gson gson = new Gson();
            String s = gson.toJson(msg.ext());
            Logger.e("ext json = " + s);

            //接收到待支付订单
            //msg{from:3, to:4286 body:txt:"[支付]"
            //{"price":"0.01","lvshiid":"3","huashuid":"15910672274","type":1,"userid":"4286","jiluid":"15910672274"}

            //接收到已支付
            //msg{from:3, to:4286 body:txt:"已支付"
            //{"price":"0.01","jilu_id":0,"type":2,"huashu_id":"15910672274"}

        }
        //收到消息
        //收到已读回执
        List<HistoryChatItemMultiItem> data = new ArrayList<>();

        for (EMMessage msg : messages) {
            EmChatManager.getInstance().getConversationById(msg.conversationId())
                    .markAllMessagesAsRead();

            String from = msg.getFrom();
            String to = msg.getTo();
            String content = "";
            if (msg.getType() == EMMessage.Type.TXT) {
                EMTextMessageBody body = (EMTextMessageBody) msg.getBody();
                content = body.getMessage();
            }
            if ("已支付".equals(content)) {
                continue;
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

            //获取环信消息的ext字段  转换成HistoryChatExtBean
            Map<String, Object> extMap = msg.ext();
            Gson gson = new Gson();
            String json = gson.toJson(extMap);
            HistoryChatExtBean ext = gson.fromJson(json, HistoryChatExtBean.class);
            itemBean.setExt(ext);

            HistoryChatItemMultiItem bean = new HistoryChatItemMultiItem(itemBean);
            data.add(bean);
        }
        getView().onTextReceived(data);
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

    /**
     * 律師同步消息到服務器
     */
    public void userSyncMessageToService(String toUserId, String fromUserId, String content) {
        HttpUtil.getInstance().getService2(MessageService.class)
                .lawyerSyncMessageToService("", fromUserId, toUserId, content, "1")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(bindLifecycle())
                .subscribe(new BaseRxObserver<AddMessageResponseBean>() {
                    @Override
                    public void onNextImpl(AddMessageResponseBean response) {
//                        getView().onSyncMessageSuccess();
                    }

                    @Override
                    public void onErrorImpl(String errorMessage) {
//                        getView().onSyncMessageFail(errorMessage);
                    }
                });
    }

    /**
     * 用户点击支付订单判断当前订单是否支付
     */
    public void checkMessagePayed(String selfId, String toUserId, String feeId) {
        Observable<ToCheckPayBean> ob = createService(ApiService.class)
                .setToPay(selfId, toUserId, feeId);
        requestRemote(ob, new BaseRxObserver<ToCheckPayBean>() {
            @Override
            public void onSubscribeImpl(Disposable d) {
                super.onSubscribeImpl(d);
                getView().showProgress();
            }

            @Override
            public void onNextImpl(ToCheckPayBean data) {
                if (null == data) {
                    getView().showToast("获取数据失败,请稍后重试");
                    getView().hideProgress();
                    return;
                }
                getView().onCheckMessagePayedSuccess(data);
                getView().hideProgress();
            }

            @Override
            public void onErrorImpl(String errorMessage) {
                getView().showToast("网络链接失败");
                getView().hideProgress();
            }
        });
//        HttpUtil.getInstance().getApiService().setToPay(user_id, lv_id, h_id)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .as(bindLifecycle())
//                .subscribe(new BaseRxObserver<ToCheckPayBean>() {
//                    @Override
//                    public void onNextImpl(ToCheckPayBean checkPayBean) {
//                        if (checkPayBean.getCode() == 200) {
//                            getView().onToCheckPaySucccess(checkPayBean);
//                        } else {
//                            getView().onToCheckPaySucccess(checkPayBean);
//                        }
//
//                    }
//
//                    @Override
//                    public void onErrorImpl(String errorMessage) {
//                        Logger.e("getToCheckPay" + errorMessage);
////                        ToastUtil.showToast("网络开小差了");
//                        getView().onChatFiled();
//                    }
//                });
    }
}
