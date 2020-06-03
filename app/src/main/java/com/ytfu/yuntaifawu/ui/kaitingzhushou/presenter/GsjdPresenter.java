package com.ytfu.yuntaifawu.ui.kaitingzhushou.presenter;

import android.content.Context;

import com.orhanobut.logger.Logger;
import com.ytfu.yuntaifawu.apis.HttpUtil;
import com.ytfu.yuntaifawu.app.AppConstant;
import com.ytfu.yuntaifawu.base.BasePresenter;
import com.ytfu.yuntaifawu.helper.BaseRxObserver;
import com.ytfu.yuntaifawu.ui.kaitingzhushou.bean.GsjdBean;
import com.ytfu.yuntaifawu.ui.kaitingzhushou.bean.ScjdBean;
import com.ytfu.yuntaifawu.ui.kaitingzhushou.bean.SendEmailBean;
import com.ytfu.yuntaifawu.ui.kaitingzhushou.view.IGsjdView;
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
*  @Des  工伤鉴定presenter
*
*/
public class GsjdPresenter extends BasePresenter<IGsjdView> {
    private Context mContext;

    public GsjdPresenter(Context mContext) {
        this.mContext = mContext;
    }

    public void setGsjd(){
        HttpUtil.getInstance().getApiService().getGsjd()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(bindLifecycle())
                .subscribe(new BaseRxObserver<GsjdBean>() {
                    @Override
                    public void onNextImpl(GsjdBean gsjdBean) {
                        if(AppConstant.STATUS_SUCCESS == gsjdBean.getStatus()){
                            getView().onGsjdSuccess(gsjdBean);
                        }else{
                            getView().onGsjdSuccess(null);
                        }
                    }

                    @Override
                    public void onErrorImpl(String errorMessage) {
                        Logger.e("setGsjd :" + errorMessage);
                        ToastUtil.showToast("网络开小差了");
                        getView().showTimeout();
                        getView().onGsjdFiled(errorMessage);
                    }
                });
    }


    public void setSendEmail(HashMap<String,String> map){
        HttpUtil.getInstance().getApiService().getSendEmails(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(bindLifecycle())
                .subscribe(new BaseRxObserver<SendEmailBean>() {
                    @Override
                    public void onNextImpl(SendEmailBean emailBean) {
                        if(AppConstant.STATUS_SUCCESS == emailBean.getStatus()){
                            getView().onSendEmailSuccess(emailBean);
                        }else{
                            getView().onSendEmailSuccess(emailBean);
                        }
                    }

                    @Override
                    public void onErrorImpl(String errorMessage) {
                        Logger.e("setSendEmail :" + errorMessage);
                        ToastUtil.showToast("网络开小差了");
                        getView().onGsjdFiled(errorMessage);
                    }
                });
    }


    //绑定邮箱
    public void setGsjdBdEmail(HashMap<String,String> map){
        HttpUtil.getInstance().getApiService().getBdEmail(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(bindLifecycle())
                .subscribe(new BaseRxObserver<BdEmailBean>() {
                    @Override
                    public void onNextImpl(BdEmailBean entity) {
                        if (AppConstant.STATUS_SUCCESS == entity.getStatus()) {
                            getView().onGsjdBdEmailSuccess(entity);
                        }else{
                            getView().onGsjdBdEmailSuccess(entity);
                        }

                    }
                    @Override
                    public void onErrorImpl(String errorMessage) {
                        Logger.e("setBdEmail" + errorMessage);
                        ToastUtil.showToast("网络开小差了");
//                        getView().showTimeout();
                        getView().onGsjdFiled(errorMessage);
                    }
                });

    }
}
