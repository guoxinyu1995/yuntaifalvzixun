package com.ytfu.yuntaifawu.ui.kaitingzhushou.presenter;

import android.content.Context;

import com.orhanobut.logger.Logger;
import com.ytfu.yuntaifawu.apis.HttpUtil;
import com.ytfu.yuntaifawu.app.AppConstant;
import com.ytfu.yuntaifawu.base.BasePresenter;
import com.ytfu.yuntaifawu.helper.BaseRxObserver;
import com.ytfu.yuntaifawu.ui.kaitingzhushou.bean.DlcBean;
import com.ytfu.yuntaifawu.ui.kaitingzhushou.bean.DlcSendEmailBean;
import com.ytfu.yuntaifawu.ui.kaitingzhushou.view.IDlCiView;
import com.ytfu.yuntaifawu.ui.mine.bean.BdEmailBean;

import com.ytfu.yuntaifawu.ui.pay.bean.PayBean;
import com.ytfu.yuntaifawu.ui.pay.bean.WxPayBean;

import com.ytfu.yuntaifawu.utils.ToastUtil;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
*
*  @Auther  gxy
*
*  @Date    2019/12/2
*
*  @Des  代理词presenter
*
*/
public class DlcPresenter extends BasePresenter<IDlCiView> {
    private Context mContext;

    public DlcPresenter(Context mContext) {
        this.mContext = mContext;
    }

    public void setDlc(HashMap<String,String> map){
        HttpUtil.getInstance().getApiService().getDlc(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(bindLifecycle())
                .subscribe(new BaseRxObserver<DlcBean>() {
                    @Override
                    public void onNextImpl(DlcBean dlcBean) {
                        if(AppConstant.CODE_SUCCESS == dlcBean.getStatus()){
                            getView().onDlcSuccess(dlcBean);
                        }else{
                            getView().onDlcSuccess(null);
                        }
                    }

                    @Override
                    public void onErrorImpl(String errorMessage) {
                        Logger.e("setDlc" + errorMessage);
                        ToastUtil.showToast("网络开小差了");
                        getView().showTimeout();
                    }
                });
    }

    //发送邮箱
    public void setDlcSendEmail(HashMap<String,String> map){
        HttpUtil.getInstance().getApiService().getDlcSendEmail(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(bindLifecycle())
                .subscribe(new BaseRxObserver<DlcSendEmailBean>() {
                    @Override
                    public void onNextImpl(DlcSendEmailBean sendEmailBean) {
                        if(AppConstant.STATUS_SUCCESS == sendEmailBean.getStatus()){
                            getView().onDlcSendEmailSuccess(sendEmailBean);
                        }else{
                            getView().onDlcSendEmailSuccess(sendEmailBean);
                        }
                    }

                    @Override
                    public void onErrorImpl(String errorMessage) {
                        Logger.e("setDlcSendEmail" + errorMessage);
                        ToastUtil.showToast("网络开小差了");
                        getView().onDlicFiled();
                    }
                });
    }

    //支付
    public void setDlcPay(HashMap<String,String> map){
        HttpUtil.getInstance().getApiService().getPay(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(bindLifecycle())
                .subscribe(new BaseRxObserver<PayBean>() {
                    @Override
                    public void onNextImpl(PayBean payBean) {
                        if(AppConstant.CODE_SUCCESS == payBean.getStatus()){
                            getView().onDlcPaySuccess(payBean);
                        }else{
                            getView().onDlcPaySuccess(null);
                        }
                    }

                    @Override
                    public void onErrorImpl(String errorMessage) {
                        Logger.e("setDlcPay" + errorMessage);
                        ToastUtil.showToast("网络开小差了");
                        getView().onDlicFiled();
                    }
                });
    }
    //微信支付
    public void setDlcPayWx(HashMap<String,String> map){
        HttpUtil.getInstance().getApiService().getWxPay(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(bindLifecycle())
                .subscribe(new BaseRxObserver<WxPayBean>() {
                    @Override
                    public void onNextImpl(WxPayBean payBean) {
                        if(AppConstant.CODE_SUCCESS == payBean.getStatus()){
                            getView().onDlcPayWxSuccess(payBean);
                        }else{
                            getView().onDlcPayWxSuccess(null);
                        }
                    }

                    @Override
                    public void onErrorImpl(String errorMessage) {
                        Logger.e("setDlcPayWx" + errorMessage);
                        ToastUtil.showToast("网络开小差了");
                        getView().onDlicFiled();
                    }
                });
    }
    //绑定邮箱
    public void setDlcBdEmail(HashMap<String,String> map){
        HttpUtil.getInstance().getApiService().getBdEmail(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(bindLifecycle())
                .subscribe(new BaseRxObserver<BdEmailBean>() {
                    @Override
                    public void onNextImpl(BdEmailBean entity) {
                        if (AppConstant.STATUS_SUCCESS == entity.getStatus()) {
                            getView().onDlcBdEmail(entity);
                        }else{
                            getView().onDlcBdEmail(entity);
                        }

                    }
                    @Override
                    public void onErrorImpl(String errorMessage) {
                        Logger.e("setBdEmail" + errorMessage);
                        ToastUtil.showToast("网络开小差了");
//                        getView().showTimeout();
                        getView().onDlicFiled();
                    }
                });

    }

}
