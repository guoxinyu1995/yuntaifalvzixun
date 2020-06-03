package com.ytfu.yuntaifawu.ui.falvguwen.presenter;

import android.content.Context;

import com.orhanobut.logger.Logger;
import com.ytfu.yuntaifawu.apis.HttpUtil;
import com.ytfu.yuntaifawu.app.AppConstant;
import com.ytfu.yuntaifawu.base.BasePresenter;
import com.ytfu.yuntaifawu.helper.BaseRxObserver;
import com.ytfu.yuntaifawu.ui.falvguwen.bean.LegalAdviserDetailsBean;
import com.ytfu.yuntaifawu.ui.falvguwen.view.ILegalAdviserDetailsView;
import com.ytfu.yuntaifawu.ui.home.bean.AudioShouCangBean;
import com.ytfu.yuntaifawu.ui.kaitingzhushou.bean.SendEmailBean;
import com.ytfu.yuntaifawu.ui.mine.bean.BdEmailBean;
import com.ytfu.yuntaifawu.ui.pay.bean.PayBean;
import com.ytfu.yuntaifawu.ui.pay.bean.WxPayBean;

import com.ytfu.yuntaifawu.utils.ToastUtil;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class LegalAdviserDetailsPresenter extends BasePresenter<ILegalAdviserDetailsView> {
    private Context mContext;

    public LegalAdviserDetailsPresenter(Context mContext) {
        this.mContext = mContext;
    }

    public void flgwXQ(HashMap<String,String> map){
        HttpUtil.getInstance().getApiService().getFlgwXq(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(bindLifecycle())
                .subscribe(new BaseRxObserver<LegalAdviserDetailsBean>() {
                    @Override
                    public void onNextImpl(LegalAdviserDetailsBean entity) {
                        if (AppConstant.STATUS_SUCCESS == entity.getStatus()) {
                            getView().onSuccess(entity);
                        }else{
                            getView().onSuccess(null);
                        }
                    }
                    @Override
                    public void onErrorImpl(String errorMessage) {
                        Logger.e("flgwXQ" + errorMessage);
                        ToastUtil.showToast("网络开小差了");
                        getView().showTimeout();
                        getView().onFiled(errorMessage);
                    }
                });
    }

    //收藏
    public void flgwShouCang(HashMap<String,String> map){
        HttpUtil.getInstance().getApiService().getShouCang(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(bindLifecycle())
                .subscribe(new BaseRxObserver<AudioShouCangBean>() {
                    @Override
                    public void onNextImpl(AudioShouCangBean detailsBean) {
                        if(AppConstant.CODE_SUCCESS == detailsBean.getStatus()){
                            getView().onShouCangSuccess(detailsBean);
                        }else{
                            getView().onShouCangSuccess(null);
                        }
                    }

                    @Override
                    public void onErrorImpl(String errorMessage) {
                        Logger.e("flgwShouCang" + errorMessage);
                        ToastUtil.showToast("网络开小差了");
                        getView().onFiled(errorMessage);
                    }
                });
    }

    //取消收藏
    public void flgwShouCangDel(HashMap<String,String> map){
        HttpUtil.getInstance().getApiService().getShouCangdel(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(bindLifecycle())
                .subscribe(new BaseRxObserver<AudioShouCangBean>() {
                    @Override
                    public void onNextImpl(AudioShouCangBean detailsBean) {
                        if(AppConstant.CODE_SUCCESS == detailsBean.getStatus()){
                            getView().onShouCangdelSuccess(detailsBean);
                        }else{
                            getView().onShouCangdelSuccess(null);
                        }
                    }

                    @Override
                    public void onErrorImpl(String errorMessage) {
                        Logger.e("flgwShouCangDel" + errorMessage);
                        ToastUtil.showToast("网络开小差了");
                        getView().onFiled(errorMessage);
                    }
                });
    }

    //支付
    public void setFlgwPay(HashMap<String,String> map){
        HttpUtil.getInstance().getApiService().getPay(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(bindLifecycle())
                .subscribe(new BaseRxObserver<PayBean>() {
                    @Override
                    public void onNextImpl(PayBean payBean) {
                        if(AppConstant.CODE_SUCCESS == payBean.getStatus()){
                            getView().onFlgwPaySuccess(payBean);
                        }else{
                            getView().onFlgwPaySuccess(null);
                        }
                    }

                    @Override
                    public void onErrorImpl(String errorMessage) {
                        Logger.e("setFlgwPay" + errorMessage);
                        ToastUtil.showToast("网络开小差了");
                        getView().onFiled(errorMessage);
                    }
                });
    }
    //微信支付
    public void setFlgwPayWx(HashMap<String,String> map){
        HttpUtil.getInstance().getApiService().getWxPay(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(bindLifecycle())
                .subscribe(new BaseRxObserver<WxPayBean>() {
                    @Override
                    public void onNextImpl(WxPayBean payBean) {
                        if(AppConstant.CODE_SUCCESS == payBean.getStatus()){
                            getView().onFlgwPayWxSuccess(payBean);
                        }else{
                            getView().onFlgwPayWxSuccess(null);
                        }
                    }

                    @Override
                    public void onErrorImpl(String errorMessage) {
                        Logger.e("setFlgwPayWx" + errorMessage);
                        ToastUtil.showToast("网络开小差了");
                        getView().onFiled(errorMessage);
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
                            getView().onSendEmailSuccess(emailBean);
                        }else{
                            getView().onSendEmailSuccess(emailBean);
                        }
                    }
                    @Override
                    public void onErrorImpl(String errorMessage) {
                        Logger.e("setSendEmail :" + errorMessage);
                        ToastUtil.showToast("网络开小差了");
                        getView().onFiled(errorMessage);
                    }
                });
    }

    //绑定邮箱
    public void setFlgwBdEmail(HashMap<String,String> map){
        HttpUtil.getInstance().getApiService().getBdEmail(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(bindLifecycle())
                .subscribe(new BaseRxObserver<BdEmailBean>() {
                    @Override
                    public void onNextImpl(BdEmailBean entity) {
                        if (AppConstant.STATUS_SUCCESS == entity.getStatus()) {
                            getView().onFlguBdEmail(entity);
                        }else{
                            getView().onFlguBdEmail(entity);
                        }

                    }
                    @Override
                    public void onErrorImpl(String errorMessage) {
                        Logger.e("setBdEmail" + errorMessage);
                        ToastUtil.showToast("网络开小差了");
//                        getView().showTimeout();
                        getView().onFiled(errorMessage);
                    }
                });

    }
}
