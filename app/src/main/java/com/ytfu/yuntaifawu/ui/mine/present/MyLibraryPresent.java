package com.ytfu.yuntaifawu.ui.mine.present;

import android.content.Context;

import com.orhanobut.logger.Logger;
import com.ytfu.yuntaifawu.apis.HttpUtil;
import com.ytfu.yuntaifawu.app.AppConstant;
import com.ytfu.yuntaifawu.base.BasePresenter;
import com.ytfu.yuntaifawu.helper.BaseRxObserver;
import com.ytfu.yuntaifawu.ui.kaitingzhushou.bean.SendEmailBean;
import com.ytfu.yuntaifawu.ui.mine.bean.BdEmailBean;
import com.ytfu.yuntaifawu.ui.mine.bean.MyLibraryBean;
import com.ytfu.yuntaifawu.ui.mine.bean.PurchaseRecordBean;
import com.ytfu.yuntaifawu.ui.mine.view.IMyLibraryView;
import com.ytfu.yuntaifawu.utils.CommonUtil;
import com.ytfu.yuntaifawu.utils.ToastUtil;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MyLibraryPresent extends BasePresenter<IMyLibraryView> {
    private Context mContext;

    public MyLibraryPresent(Context mContext) {
        this.mContext = mContext;
    }

    public void myLibrary(HashMap<String,String> map){
        HttpUtil.getInstance().getApiService().getMyLibrary(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(bindLifecycle())
                .subscribe(new BaseRxObserver<MyLibraryBean>() {
                    @Override
                    public void onNextImpl(MyLibraryBean entity) {
                        if (AppConstant.CODE_SUCCESS == entity.getStatus()) {
                            getView().onMyLibrarySuccess(entity);
                        }else{
                            getView().onMyLibrarySuccess(null);
                        }

                    }
                    @Override
                    public void onErrorImpl(String errorMessage) {
                        Logger.e("myLibrary" + errorMessage);
                        ToastUtil.showToast("网络开小差了");
                        getView().showTimeout();
                        getView().onFiled();
                    }
                });
    }

    //下载发送邮箱
    public void setSendEmail(HashMap<String,String> map){
        HttpUtil.getInstance().getApiService().getSendEmails(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(bindLifecycle())
                .subscribe(new BaseRxObserver<SendEmailBean>() {
                    @Override
                    public void onNextImpl(SendEmailBean emailBean) {
                        if (AppConstant.STATUS_SUCCESS == emailBean.getStatus()) {
                            getView().onSendEmail(emailBean);
                        }else{
                            getView().onSendEmail(emailBean);
                        }
                    }
                    @Override
                    public void onErrorImpl(String errorMessage) {
                        Logger.e("setSendEmail" + errorMessage);
//                        ToastUtil.showToast("网络开小差了");
                        getView().onFiled();
                    }
                });
    }

    //绑定邮箱
    public void setLibraryBdEmail(HashMap<String,String> map){
        HttpUtil.getInstance().getApiService().getBdEmail(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(bindLifecycle())
                .subscribe(new BaseRxObserver<BdEmailBean>() {
                    @Override
                    public void onNextImpl(BdEmailBean entity) {
                        if (AppConstant.STATUS_SUCCESS == entity.getStatus()) {
                            getView().onLibraryBdEmailSuccess(entity);
                        }else{
                            getView().onLibraryBdEmailSuccess(entity);
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
