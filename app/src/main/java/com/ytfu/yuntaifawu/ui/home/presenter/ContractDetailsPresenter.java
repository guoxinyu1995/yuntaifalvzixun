package com.ytfu.yuntaifawu.ui.home.presenter;

import android.content.Context;

import com.orhanobut.logger.Logger;
import com.ytfu.yuntaifawu.apis.HttpUtil;
import com.ytfu.yuntaifawu.app.AppConstant;
import com.ytfu.yuntaifawu.base.BasePresenter;
import com.ytfu.yuntaifawu.helper.BaseRxObserver;
import com.ytfu.yuntaifawu.ui.home.bean.AudioShouCangBean;
import com.ytfu.yuntaifawu.ui.lvshihetong.bean.ContractDetailsBean;
import com.ytfu.yuntaifawu.ui.home.view.IContractDetailsView;
import com.ytfu.yuntaifawu.ui.kaitingzhushou.bean.SendEmailBean;
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
*  @Date    2019/11/13
*
*  @Des  合同详情presenter
*
*/
public class ContractDetailsPresenter extends BasePresenter<IContractDetailsView> {
    private Context mContext;

    public ContractDetailsPresenter(Context mContext) {
        this.mContext = mContext;
    }

    public void getContractDetails(HashMap<String,String> map){
        HttpUtil.getInstance().getApiService().contractDetails(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(bindLifecycle())
                .subscribe(new BaseRxObserver<ContractDetailsBean>() {
                    @Override
                    public void onNextImpl(ContractDetailsBean detailsBean) {
                        if(AppConstant.CODE_SUCCESS == detailsBean.getStatus()){
                            getView().onContractDetailsSuccess(detailsBean);
                        }else{
                            getView().onContractDetailsSuccess(null);
                        }
                    }

                    @Override
                    public void onErrorImpl(String errorMessage) {
                        Logger.e("getContractDetails" + errorMessage);
                        ToastUtil.showToast("网络开小差了");
                        getView().onContractDetailFiled();
                    }
                });
    }

    //收藏
    public void contractShouCang(HashMap<String,String> map){
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
                            getView().onContractDetailsSuccess(null);
                        }
                    }

                    @Override
                    public void onErrorImpl(String errorMessage) {
                        Logger.e("contractShouCang" + errorMessage);
                        ToastUtil.showToast("网络开小差了");
                        getView().onContractDetailFiled();
                        getView().showTimeout();
                    }
                });
    }

    //取消收藏
    public void contractShouCangDel(HashMap<String,String> map){
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
                        Logger.e("contractShouCangDel" + errorMessage);
                        ToastUtil.showToast("网络开小差了");
                        getView().onContractDetailFiled();
                    }
                });
    }

    //支付
    public void setContractPay(HashMap<String,String> map){
        HttpUtil.getInstance().getApiService().getPay(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(bindLifecycle())
                .subscribe(new BaseRxObserver<PayBean>() {
                    @Override
                    public void onNextImpl(PayBean payBean) {
                        if(AppConstant.CODE_SUCCESS == payBean.getStatus()){
                            getView().onContractPaySuccess(payBean);
                        }else{
                            getView().onContractPaySuccess(null);
                        }
                    }

                    @Override
                    public void onErrorImpl(String errorMessage) {
                        Logger.e("setContractPay" + errorMessage);
                        ToastUtil.showToast("网络开小差了");
                        getView().onContractDetailFiled();
                    }
                });
    }
    //微信支付
    public void setContractPayWx(HashMap<String,String> map){
        HttpUtil.getInstance().getApiService().getWxPay(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(bindLifecycle())
                .subscribe(new BaseRxObserver<WxPayBean>() {
                    @Override
                    public void onNextImpl(WxPayBean wxPayBean) {
                        if(AppConstant.CODE_SUCCESS == wxPayBean.getStatus()){
                            getView().onContractPayWxSuccess(wxPayBean);
                        }else{
                            getView().onContractPayWxSuccess(wxPayBean);
                        }
                    }

                    @Override
                    public void onErrorImpl(String errorMessage) {
                        Logger.e("setContractPayWx" + errorMessage);
                        ToastUtil.showToast("网络开小差了");
                        getView().onContractDetailFiled();
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
                        getView().onContractDetailFiled();
                    }
                });
    }

    //绑定邮箱
    public void setHtBdEmail(HashMap<String,String> map){
        HttpUtil.getInstance().getApiService().getBdEmail(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(bindLifecycle())
                .subscribe(new BaseRxObserver<BdEmailBean>() {
                    @Override
                    public void onNextImpl(BdEmailBean entity) {
                        if (AppConstant.STATUS_SUCCESS == entity.getStatus()) {
                            getView().onContractBdEmailSuccess(entity);
                        }else{
                            getView().onContractBdEmailSuccess(entity);
                        }

                    }
                    @Override
                    public void onErrorImpl(String errorMessage) {
                        Logger.e("setBdEmail" + errorMessage);
                        ToastUtil.showToast("网络开小差了");
//                        getView().showTimeout();
                        getView().onContractDetailFiled();
                    }
                });

    }
}
