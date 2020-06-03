package com.ytfu.yuntaifawu.ui.kaitingzhushou.presenter;

import android.content.Context;

import com.orhanobut.logger.Logger;
import com.ytfu.yuntaifawu.apis.HttpUtil;
import com.ytfu.yuntaifawu.app.AppConstant;
import com.ytfu.yuntaifawu.base.BasePresenter;
import com.ytfu.yuntaifawu.helper.BaseRxObserver;
import com.ytfu.yuntaifawu.ui.kaitingzhushou.bean.QylxtgBean;
import com.ytfu.yuntaifawu.ui.kaitingzhushou.bean.ScjdBean;
import com.ytfu.yuntaifawu.ui.kaitingzhushou.bean.SendEmailBean;
import com.ytfu.yuntaifawu.ui.kaitingzhushou.view.IScjdView;
import com.ytfu.yuntaifawu.ui.mine.bean.BdEmailBean;
import com.ytfu.yuntaifawu.utils.ToastUtil;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
*
*  @Auther  gxy
*
*  @Date    2019/11/27
*
*  @Des   伤残鉴定presenter
*
*/
public class ScjdPresenter extends BasePresenter<IScjdView> {
    private Context mContext;

    public ScjdPresenter(Context mContext) {
        this.mContext = mContext;
    }

    public void setScjd(){
        HttpUtil.getInstance().getApiService().getScjd()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(bindLifecycle())
                .subscribe(new BaseRxObserver<ScjdBean>() {
                    @Override
                    public void onNextImpl(ScjdBean scjdBean) {
                        if(AppConstant.STATUS_SUCCESS == scjdBean.getStatus()){
                            getView().onScjdSuccess(scjdBean);
                        }else{
                            getView().onScjdSuccess(null);
                        }
                    }

                    @Override
                    public void onErrorImpl(String errorMessage) {
                        Logger.e("setScjd" + errorMessage);
                        ToastUtil.showToast("网络开小差了");
                        getView().showTimeout();
                        getView().onScjdFiled(errorMessage);
                    }
                });
    }

    //发送邮件
    public void setSendEmail(HashMap<String,String> map){
        HttpUtil.getInstance().getApiService().getSendEmails(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(bindLifecycle())
                .subscribe(new BaseRxObserver<SendEmailBean>() {
                    @Override
                    public void onNextImpl(SendEmailBean emailBean) {
                        if(AppConstant.STATUS_SUCCESS == emailBean.getStatus()){
                            getView().onSendEmail(emailBean);
                        }else{
                            getView().onSendEmail(emailBean);
                        }
                    }

                    @Override
                    public void onErrorImpl(String errorMessage) {
                        Logger.e("setSendEmail :" + errorMessage);
                        ToastUtil.showToast("网络开小差了");
                        getView().onScjdFiled(errorMessage);
                    }
                });
    }

    //绑定邮箱
    public void setScjdBdEmail(HashMap<String,String> map){
        HttpUtil.getInstance().getApiService().getBdEmail(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(bindLifecycle())
                .subscribe(new BaseRxObserver<BdEmailBean>() {
                    @Override
                    public void onNextImpl(BdEmailBean entity) {
                        if (AppConstant.STATUS_SUCCESS == entity.getStatus()) {
                            getView().onScjdBdEmail(entity);
                        }else{
                            getView().onScjdBdEmail(entity);
                        }

                    }
                    @Override
                    public void onErrorImpl(String errorMessage) {
                        Logger.e("setBdEmail" + errorMessage);
                        ToastUtil.showToast("网络开小差了");
//                        getView().showTimeout();
                        getView().onScjdFiled(errorMessage);
                    }
                });

    }
}

