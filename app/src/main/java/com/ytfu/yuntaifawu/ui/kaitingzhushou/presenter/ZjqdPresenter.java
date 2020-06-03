package com.ytfu.yuntaifawu.ui.kaitingzhushou.presenter;

import android.content.Context;

import com.orhanobut.logger.Logger;
import com.ytfu.yuntaifawu.apis.HttpUtil;
import com.ytfu.yuntaifawu.app.AppConstant;
import com.ytfu.yuntaifawu.base.BasePresenter;
import com.ytfu.yuntaifawu.helper.BaseRxObserver;
import com.ytfu.yuntaifawu.ui.kaitingzhushou.bean.ZjqdBean;
import com.ytfu.yuntaifawu.ui.kaitingzhushou.view.IZjqdView;
import com.ytfu.yuntaifawu.ui.pay.bean.PayBean;
import com.ytfu.yuntaifawu.ui.pay.bean.WxPayBean;

import com.ytfu.yuntaifawu.utils.ToastUtil;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ZjqdPresenter extends BasePresenter<IZjqdView> {
    private Context mContext;

    public ZjqdPresenter(Context mContext) {
        this.mContext = mContext;
    }

    public void setZjqd(HashMap<String,String> map){
        HttpUtil.getInstance().getApiService().getZjqd(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(bindLifecycle())
                .subscribe(new BaseRxObserver<ZjqdBean>() {
                    @Override
                    public void onNextImpl(ZjqdBean titleBean) {
                        if(AppConstant.STATUS_SUCCESS == titleBean.getStatus()){
                            getView().onZjqdSuccess(titleBean);
                        }else{
                            getView().onZjqdSuccess(null);
                        }
                    }

                    @Override
                    public void onErrorImpl(String errorMessage) {
                        Logger.e("setZjqd" + errorMessage);
                        ToastUtil.showToast("网络开小差了");
                        getView().showTimeout();
                        getView().onZjqdFiled();
                    }
                });
    }


    //支付
    public void setZjqdPay(HashMap<String,String> map){
        HttpUtil.getInstance().getApiService().getPay(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(bindLifecycle())
                .subscribe(new BaseRxObserver<PayBean>() {
                    @Override
                    public void onNextImpl(PayBean payBean) {
                        if(AppConstant.CODE_SUCCESS == payBean.getStatus()){
                            getView().onZjqdBuySuccess(payBean);
                        }else{
                            getView().onZjqdBuySuccess(null);
                        }
                    }

                    @Override
                    public void onErrorImpl(String errorMessage) {
                        Logger.e("setDlcPay" + errorMessage);
                        ToastUtil.showToast("网络开小差了");
                        getView().onZjqdFiled();
                    }
                });
    }
    //微信支付
    public void setZjqdPayWx(HashMap<String,String> map){
        HttpUtil.getInstance().getApiService().getWxPay(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(bindLifecycle())
                .subscribe(new BaseRxObserver<WxPayBean>() {
                    @Override
                    public void onNextImpl(WxPayBean payBean) {
                        if(AppConstant.CODE_SUCCESS == payBean.getStatus()){
                            getView().onZjqdBuyWxSuccess(payBean);
                        }else{
                            getView().onZjqdBuyWxSuccess(null);
                        }
                    }

                    @Override
                    public void onErrorImpl(String errorMessage) {
                        Logger.e("setZjqdPayWx" + errorMessage);
                        ToastUtil.showToast("网络开小差了");
                        getView().onZjqdFiled();
                    }
                });
    }
}
