package com.ytfu.yuntaifawu.ui.kaitingzhushou.presenter;

import android.content.Context;

import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.ytfu.yuntaifawu.apis.HttpUtil;
import com.ytfu.yuntaifawu.app.AppConstant;
import com.ytfu.yuntaifawu.base.BasePresenter;
import com.ytfu.yuntaifawu.helper.BaseRxObserver;
import com.ytfu.yuntaifawu.ui.kaitingzhushou.bean.KtzsListBean;
import com.ytfu.yuntaifawu.ui.kaitingzhushou.bean.KtzsXqListBean;
import com.ytfu.yuntaifawu.ui.kaitingzhushou.bean.SendEmailBean;
import com.ytfu.yuntaifawu.ui.kaitingzhushou.view.IKtzsXqListView;
import com.ytfu.yuntaifawu.ui.mine.bean.BdEmailBean;
import com.ytfu.yuntaifawu.utils.ToastUtil;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
/**
*
*  @Auther  gxy
*
*  @Date    2019/11/24
*
*  @Des  开庭助手详情list   presenter
*
*/
public class KtzsXqListPresenter extends BasePresenter<IKtzsXqListView> {
    private Context mContext;

    public KtzsXqListPresenter(Context mContext) {
        this.mContext = mContext;
    }

    public void setKtzsZqList(HashMap<String,String> map){
        HttpUtil.getInstance().getApiService().getKtzsXqList(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(bindLifecycle())
                .subscribe(new BaseRxObserver<KtzsXqListBean>() {
                    @Override
                    public void onNextImpl(KtzsXqListBean entity) {
                        if (AppConstant.CODE_SUCCESS == entity.getStatus()) {
                            getView().onSuccess(entity);
                        }else{
                            getView().onSuccess(null);
                        }

                    }
                    @Override
                    public void onErrorImpl(String errorMessage) {
                        Logger.e("setKtzsZqList" + errorMessage);
                        ToastUtil.showToast("网络开小差了");
                        getView().showTimeout();
                        getView().onFiled();
                    }
                });
    }

    //发送到邮箱
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
                        getView().onFiled();
                    }
                });

    }

    //绑定邮箱
    public void setKtzsBdEmail(HashMap<String,String> map){
        HttpUtil.getInstance().getApiService().getBdEmail(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(bindLifecycle())
                .subscribe(new BaseRxObserver<BdEmailBean>() {
                    @Override
                    public void onNextImpl(BdEmailBean entity) {
                        if (AppConstant.STATUS_SUCCESS == entity.getStatus()) {
                            getView().onKtzsBdEmail(entity);
                        }else{
                            getView().onKtzsBdEmail(entity);
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
