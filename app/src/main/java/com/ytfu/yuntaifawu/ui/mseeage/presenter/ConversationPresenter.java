package com.ytfu.yuntaifawu.ui.mseeage.presenter;

import android.content.Context;

import com.orhanobut.logger.Logger;
import com.ytfu.yuntaifawu.apis.ChatService;
import com.ytfu.yuntaifawu.apis.HttpUtil;
import com.ytfu.yuntaifawu.app.AppConstant;
import com.ytfu.yuntaifawu.base.BasePresenter;
import com.ytfu.yuntaifawu.helper.BaseRxObserver;
import com.ytfu.yuntaifawu.helper.RxLifecycleUtil;
import com.ytfu.yuntaifawu.ui.chat.bean.LawyerListBean;
import com.ytfu.yuntaifawu.ui.mine.bean.InformationBean;
import com.ytfu.yuntaifawu.ui.mseeage.bean.ConversationBean;
import com.ytfu.yuntaifawu.ui.mseeage.bean.FirstMessageBean;
import com.ytfu.yuntaifawu.ui.mseeage.bean.LvShiCardBean;
import com.ytfu.yuntaifawu.ui.mseeage.view.IConversationView;
import com.ytfu.yuntaifawu.utils.ToastUtil;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.http.Query;

public class ConversationPresenter extends BasePresenter<IConversationView> {
    private Context mContext;

    public ConversationPresenter(Context mContext) {
        this.mContext = mContext;
    }

    public void getConversationList() {
        HttpUtil.getInstance().getApiService().setConversationList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(bindLifecycle())
                .subscribe(new BaseRxObserver<ConversationBean>() {
                    @Override
                    public void onNextImpl(ConversationBean conversationBean) {
                        if (AppConstant.STATUS_SUCCESS == conversationBean.getStatus()) {
                            getView().onCoversationSuccess(conversationBean);
                        } else {
                            getView().onCoversationSuccess(conversationBean);
                        }

                    }

                    @Override
                    public void onErrorImpl(String errorMessage) {
                        Logger.e("setGrzx" + errorMessage);
                        ToastUtil.showToast("网络开小差了");
                        getView().onConversationFiled();
                    }
                });
    }


    /**
     * 获取律师聊天列表
     */
    public void getLawyerChatList(String uid) {
        HttpUtil.getInstance().getService(ChatService.class)
                .getLawyerChatList(uid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(bindLifecycle())
                .subscribe(new BaseRxObserver<LawyerListBean>() {

                    @Override
                    public void onNextImpl(LawyerListBean lawyerListBean) {
                        if (String.valueOf(AppConstant.STATUS_SUCCESS).equals(lawyerListBean.getCode())) {
                            getView().onGetLawyerChatListSuccess(lawyerListBean.getData());
                        } else {
                            getView().onGetLawyerChatListFail("获取数据失败,请稍后重试");
                        }
                    }

                    @Override
                    public void onErrorImpl(String errorMessage) {
                        getView().onGetLawyerChatListFail(errorMessage);
                        //  ToastUtil.showToast("网络开小差了");
                        //                        getView().onConversationFiled();
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
                        getView().onConversationFiled();
                    }
                });
    }
}
