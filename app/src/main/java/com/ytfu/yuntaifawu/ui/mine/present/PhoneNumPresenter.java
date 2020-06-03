package com.ytfu.yuntaifawu.ui.mine.present;

import android.content.Context;

import com.orhanobut.logger.Logger;
import com.ytfu.yuntaifawu.apis.HttpUtil;
import com.ytfu.yuntaifawu.app.AppConstant;
import com.ytfu.yuntaifawu.base.BasePresenter;
import com.ytfu.yuntaifawu.helper.BaseRxObserver;
import com.ytfu.yuntaifawu.ui.mine.bean.PhoneNumBdBean;
import com.ytfu.yuntaifawu.ui.mine.bean.SendBdCodeBean;
import com.ytfu.yuntaifawu.ui.mine.bean.SetNameBean;
import com.ytfu.yuntaifawu.ui.mine.view.IPhoneNumBdView;
import com.ytfu.yuntaifawu.utils.ToastUtil;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
*
*  @Auther  gxy
*
*  @Date    2020/1/8
*
*  @Des  绑定手机号presenter
*
*/
public class PhoneNumPresenter extends BasePresenter<IPhoneNumBdView> {
    private Context mContext;

    public PhoneNumPresenter(Context mContext) {
        this.mContext = mContext;
    }

    public void setSendCode(HashMap<String,String> map){
        HttpUtil.getInstance().getApiService().getSendCode(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(bindLifecycle())
                .subscribe(new BaseRxObserver<SendBdCodeBean>() {
                    @Override
                    public void onNextImpl(SendBdCodeBean entity) {
                        if (AppConstant.CODE_SUCCESS == entity.getStatus()) {
                            getView().onSendCodeSuccess(entity);
                        } else {
                            getView().onSendCodeSuccess(entity);
                        }

                    }

                    @Override
                    public void onErrorImpl(String errorMessage) {
                        Logger.e("setName" + errorMessage);
                        ToastUtil.showToast("网络开小差了");
                        getView().onBdFiled();
                    }
                });
    }

    public void setPhoneNum(HashMap<String,String> map){
        HttpUtil.getInstance().getApiService().getPhoneNum(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(bindLifecycle())
                .subscribe(new BaseRxObserver<PhoneNumBdBean>() {
                    @Override
                    public void onNextImpl(PhoneNumBdBean entity) {
                        if (AppConstant.CODE_SUCCESS == entity.getStatus()) {
                            getView().onPhoneNumSuccess(entity);
                        } else {
                            getView().onPhoneNumSuccess(entity);
                        }

                    }

                    @Override
                    public void onErrorImpl(String errorMessage) {
                        Logger.e("setName" + errorMessage);
                        ToastUtil.showToast("网络开小差了");
                        getView().onBdFiled();
                    }
                });
    }
}
