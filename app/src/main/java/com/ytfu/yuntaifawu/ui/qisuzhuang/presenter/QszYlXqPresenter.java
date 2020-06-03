package com.ytfu.yuntaifawu.ui.qisuzhuang.presenter;

import android.content.Context;

import com.orhanobut.logger.Logger;
import com.ytfu.yuntaifawu.apis.HttpUtil;
import com.ytfu.yuntaifawu.app.AppConstant;
import com.ytfu.yuntaifawu.base.BasePresenter;
import com.ytfu.yuntaifawu.helper.BaseRxObserver;
import com.ytfu.yuntaifawu.ui.kaitingzhushou.bean.SendEmailBean;
import com.ytfu.yuntaifawu.ui.mine.bean.BdEmailBean;
import com.ytfu.yuntaifawu.ui.qisuzhuang.view.IQszYlXqView;
import com.ytfu.yuntaifawu.utils.ToastUtil;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class QszYlXqPresenter extends BasePresenter<IQszYlXqView> {
    private Context mContext;

    public QszYlXqPresenter(Context mContext) {
        this.mContext = mContext;
    }

    //发送邮箱
    public void setQszYlSendEmail(HashMap<String, String> map) {
        HttpUtil.getInstance().getApiService().getSendEmails(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(bindLifecycle())
                .subscribe(new BaseRxObserver<SendEmailBean>() {
                    @Override
                    public void onNextImpl(SendEmailBean emailBean) {
                        if (AppConstant.STATUS_SUCCESS == emailBean.getStatus()) {
                            getView().onQszYlXqSendEmail(emailBean);
                        } else {
                            getView().onQszYlXqSendEmail(emailBean);
                        }
                    }

                    @Override
                    public void onErrorImpl(String errorMessage) {
                        Logger.e("setQszYlSendEmail :" + errorMessage);
                        ToastUtil.showToast("网络开小差了");
                        getView().onFiled();
                    }
                });
    }


    //绑定邮箱
    public void setQszYlBdEmail(HashMap<String,String> map){
        HttpUtil.getInstance().getApiService().getBdEmail(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(bindLifecycle())
                .subscribe(new BaseRxObserver<BdEmailBean>() {
                    @Override
                    public void onNextImpl(BdEmailBean entity) {
                        if (AppConstant.STATUS_SUCCESS == entity.getStatus()) {
                            getView().onQszYlXqBdEmailSuccess(entity);
                        }else{
                            getView().onQszYlXqBdEmailSuccess(entity);
                        }

                    }
                    @Override
                    public void onErrorImpl(String errorMessage) {
                        Logger.e("setBdEmail" + errorMessage);
                        ToastUtil.showToast("网络开小差了");
//                        getView().showTimeout();
                        getView().onFiled();
                    }
                });

    }
}
