package com.ytfu.yuntaifawu.ui.kaitingzhushou.presenter;

import android.content.Context;

import com.orhanobut.logger.Logger;
import com.ytfu.yuntaifawu.apis.HttpUtil;
import com.ytfu.yuntaifawu.app.AppConstant;
import com.ytfu.yuntaifawu.base.BasePresenter;
import com.ytfu.yuntaifawu.helper.BaseRxObserver;
import com.ytfu.yuntaifawu.ui.kaitingzhushou.bean.KtzsXqTitleBean;
import com.ytfu.yuntaifawu.ui.kaitingzhushou.bean.QylxtgBean;
import com.ytfu.yuntaifawu.ui.kaitingzhushou.bean.SendEmailBean;
import com.ytfu.yuntaifawu.ui.kaitingzhushou.view.IQylxtgView;
import com.ytfu.yuntaifawu.ui.mine.bean.BdEmailBean;
import com.ytfu.yuntaifawu.utils.ToastUtil;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @Auther gxy
 * @Date 2019/11/27
 * @Des 企业另需提供presenter
 */
public class QylxtgPresenter extends BasePresenter<IQylxtgView> {
    private Context mContext;

    public QylxtgPresenter(Context mContext) {
        this.mContext = mContext;
    }

    public void setQylxtg(){
        HttpUtil.getInstance().getApiService().getQylxtg()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(bindLifecycle())
                .subscribe(new BaseRxObserver<QylxtgBean>() {
                    @Override
                    public void onNextImpl(QylxtgBean qylxtgBean) {
                        if(AppConstant.STATUS_SUCCESS == qylxtgBean.getStatus()){
                            getView().onQylxtgSuccess(qylxtgBean);
                        }else{
                            getView().onQylxtgFiled(qylxtgBean.getMsg());
                        }
                    }

                    @Override
                    public void onErrorImpl(String errorMessage) {
                        Logger.e("setQylxtg" + errorMessage);
                        ToastUtil.showToast("网络开小差了");
                        getView().onQylxtgFiled(errorMessage);
                        getView().showTimeout();
                    }
                });
    }
    //发送邮箱
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
                        Logger.e("setSendEmail" + errorMessage);
                        ToastUtil.showToast("网络开小差了");
                        getView().onQylxtgFiled(errorMessage);
                    }
                });
    }

    //绑定邮箱
    public void setBdEmail(HashMap<String,String> map){
        HttpUtil.getInstance().getApiService().getBdEmail(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(bindLifecycle())
                .subscribe(new BaseRxObserver<BdEmailBean>() {
                    @Override
                    public void onNextImpl(BdEmailBean entity) {
                        if (AppConstant.STATUS_SUCCESS == entity.getStatus()) {
                            getView().onBdEmailSuccess(entity);
                        }else{
                            getView().onBdEmailSuccess(entity);
                        }

                    }
                    @Override
                    public void onErrorImpl(String errorMessage) {
                        Logger.e("setBdEmail" + errorMessage);
                        ToastUtil.showToast("网络开小差了");
//                        getView().showTimeout();
                        getView().onQylxtgFiled(errorMessage);
                    }
                });

    }
}
