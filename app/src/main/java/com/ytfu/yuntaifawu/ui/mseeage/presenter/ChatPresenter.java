package com.ytfu.yuntaifawu.ui.mseeage.presenter;

import android.content.Context;
import android.util.Log;

import com.orhanobut.logger.Logger;
import com.ytfu.yuntaifawu.apis.HttpUtil;
import com.ytfu.yuntaifawu.app.AppConstant;
import com.ytfu.yuntaifawu.base.BasePresenter;
import com.ytfu.yuntaifawu.helper.BaseRxObserver;
import com.ytfu.yuntaifawu.helper.RxLifecycleUtil;
import com.ytfu.yuntaifawu.ui.mseeage.bean.CheckWeixinBean;
import com.ytfu.yuntaifawu.ui.mseeage.bean.ConversationBean;
import com.ytfu.yuntaifawu.ui.mseeage.bean.ExChangeWeiXinBean;
import com.ytfu.yuntaifawu.ui.mseeage.bean.FirstMessageBean;
import com.ytfu.yuntaifawu.ui.mseeage.bean.LvShiCardBean;
import com.ytfu.yuntaifawu.ui.mseeage.bean.ToCheckPayBean;
import com.ytfu.yuntaifawu.ui.mseeage.bean.WhetherToPayBean;
import com.ytfu.yuntaifawu.ui.mseeage.view.IChatView;
import com.ytfu.yuntaifawu.ui.pay.bean.PayBean;
import com.ytfu.yuntaifawu.ui.pay.bean.WxPayBean;
import com.ytfu.yuntaifawu.utils.ToastUtil;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class ChatPresenter extends BasePresenter<IChatView> {
    private Context mContext;

    public ChatPresenter(Context mContext) {
        this.mContext = mContext;
    }

    //判断是否支付

    public void getToCheckPay(String user_id, String lv_id, String h_id) {
        HttpUtil.getInstance().getApiService().setToPay(user_id, lv_id, h_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(bindLifecycle())
                .subscribe(new BaseRxObserver<ToCheckPayBean>() {
                    @Override
                    public void onNextImpl(ToCheckPayBean checkPayBean) {
                        if (checkPayBean.getCode() == 200) {
                            getView().onToCheckPaySucccess(checkPayBean);
                        } else {
                            getView().onToCheckPaySucccess(checkPayBean);
                        }

                    }

                    @Override
                    public void onErrorImpl(String errorMessage) {
                        Logger.e("getToCheckPay" + errorMessage);
//                        ToastUtil.showToast("网络开小差了");
                        getView().onChatFiled();
                    }
                });
    }

    //微信支付
    public void setMessageWatchPay(HashMap<String, String> map) {
        HttpUtil.getInstance().getApiService().getWxPay(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(bindLifecycle())
                .subscribe(new BaseRxObserver<WxPayBean>() {
                    @Override
                    public void onNextImpl(WxPayBean wxPayBean) {
                        if (AppConstant.CODE_SUCCESS == wxPayBean.getStatus()) {
                            getView().onMessageWatchPaySucccess(wxPayBean);
                        } else {
                            getView().onMessageWatchPaySucccess(wxPayBean);
                        }
                    }

                    @Override
                    public void onErrorImpl(String errorMessage) {
                        Logger.e("setMessageWatchPay" + errorMessage);
//                        ToastUtil.showToast("网络开小差了");
                        getView().onChatFiled();
                    }
                });
    }

    //支付宝支付
    public void setMessageAliPay(HashMap<String, String> map) {
        HttpUtil.getInstance().getApiService().getPay(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(bindLifecycle())
                .subscribe(new BaseRxObserver<PayBean>() {
                    @Override
                    public void onNextImpl(PayBean payBean) {
                        if (AppConstant.CODE_SUCCESS == payBean.getStatus()) {
                            getView().onMessageAliPaySuccess(payBean);
                        } else {
                            getView().onMessageAliPaySuccess(payBean);
                        }
                    }

                    @Override
                    public void onErrorImpl(String errorMessage) {
                        Logger.e("setMessageAliPay" + errorMessage);
//                        ToastUtil.showToast("网络开小差了");
                        getView().onChatFiled();
                    }
                });
    }


    //律师卡片
    public void getLvShiCard(HashMap<String, String> map) {
        HttpUtil.getInstance().getApiService().setLvShiCard(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(bindLifecycle())
                .subscribe(new BaseRxObserver<LvShiCardBean>() {
                    @Override
                    public void onNextImpl(LvShiCardBean lvShiCardBean) {
                        if (AppConstant.STATUS_SUCCESS == lvShiCardBean.getCode()) {
                            getView().onLvShiCardSuccess(lvShiCardBean);
                        } else {
                            getView().onLvShiCardSuccess(lvShiCardBean);
                        }
                    }

                    @Override
                    public void onErrorImpl(String errorMessage) {
                        Logger.e("getLvShiCard" + errorMessage);
//                        ToastUtil.showToast("网络开小差了");
                        getView().onChatFiled();
                    }
                });
    }

    public void getFirstMessage(HashMap<String, String> map) {
        HttpUtil.getInstance().getApiService().setFirstMessage(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(bindLifecycle())
                .subscribe(new BaseRxObserver<FirstMessageBean>() {
                    @Override
                    public void onNextImpl(FirstMessageBean messageBean) {
                        getView().onFirstMessageSuccess(messageBean);
                    }

                    @Override
                    public void onErrorImpl(String errorMessage) {
                        Logger.e("setGrzx" + errorMessage);
//                        ToastUtil.showToast("网络开小差了");
                        getView().onChatFiled();
                    }
                });
    }

    //点击顶部判断是否支付
    public void getWhetherToPay(HashMap<String, String> map) {
        HttpUtil.getInstance().getApiService().setWhetherToPay(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(bindLifecycle())
                .subscribe(new BaseRxObserver<WhetherToPayBean>() {
                    @Override
                    public void onNextImpl(WhetherToPayBean whetherToPayBean) {
                        getView().onWhetherToPay(whetherToPayBean);
                    }

                    @Override
                    public void onErrorImpl(String errorMessage) {
                        Logger.e("setGrzx" + errorMessage);
//                        ToastUtil.showToast("网络开小差了");
                        getView().onChatFiled();
                    }
                });
    }

    //通知
    public void getNotice(HashMap<String, String> map) {
        HttpUtil.getInstance().getApiService().setFirstNotice(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(bindLifecycle())
                .subscribe(new BaseRxObserver<ResponseBody>() {
                    @Override
                    public void onNextImpl(ResponseBody ResponseBody) {
                        Log.e("onNextImpl", "------" + "success");
                    }

                    @Override
                    public void onErrorImpl(String errorMessage) {
                        Logger.e(errorMessage);
                    }
                });
    }


    //判断是否购买过微信
    public void getCheckWeiXin(HashMap<String, String> map) {
        HttpUtil.getInstance().getApiService().setCheckWeiXin(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(bindLifecycle())
                .subscribe(new BaseRxObserver<CheckWeixinBean>() {
                    @Override
                    public void onNextImpl(CheckWeixinBean checkWeixinBean) {
                        getView().onCheckWeiXinSuccess(checkWeixinBean);
                    }

                    @Override
                    public void onErrorImpl(String errorMessage) {
                        Logger.e(errorMessage);
                        getView().onChatFiled();
                    }
                });
    }

    //交换微信
    public void getExChangeWx(HashMap<String, String> map) {
        HttpUtil.getInstance().getApiService().setExChangeWx(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(bindLifecycle())
                .subscribe(new BaseRxObserver<ExChangeWeiXinBean>() {
                    @Override
                    public void onNextImpl(ExChangeWeiXinBean exChangeWeiXinBean) {
                        getView().onExChangeWx(exChangeWeiXinBean);
                    }

                    @Override
                    public void onErrorImpl(String errorMessage) {
                        Logger.e(errorMessage);
                        getView().onChatFiled();
                    }
                });
    }
}
