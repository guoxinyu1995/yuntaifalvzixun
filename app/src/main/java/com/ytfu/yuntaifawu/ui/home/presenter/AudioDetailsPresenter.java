package com.ytfu.yuntaifawu.ui.home.presenter;

import android.content.Context;

import com.orhanobut.logger.Logger;
import com.ytfu.yuntaifawu.apis.HttpUtil;
import com.ytfu.yuntaifawu.app.AppConstant;
import com.ytfu.yuntaifawu.base.BasePresenter;
import com.ytfu.yuntaifawu.helper.BaseRxObserver;
import com.ytfu.yuntaifawu.ui.home.bean.AudioDetailsBean;
import com.ytfu.yuntaifawu.ui.home.bean.AudioListerBean;
import com.ytfu.yuntaifawu.ui.home.bean.AudioShouCangBean;
import com.ytfu.yuntaifawu.ui.home.view.IAudioDetailsView;
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
*  @Date    2019/11/12
*
*  @Des 音频详情的presenter
*
*/
public class AudioDetailsPresenter extends BasePresenter<IAudioDetailsView> {
    private Context mContext;

    public AudioDetailsPresenter(Context mContext) {
        this.mContext = mContext;
    }

    public void getAudioDetails(HashMap<String,String> map){
        HttpUtil.getInstance().getApiService().audioDetails(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(bindLifecycle())
                .subscribe(new BaseRxObserver<AudioDetailsBean>() {
                    @Override
                    public void onNextImpl(AudioDetailsBean detailsBean) {
                        if(AppConstant.CODE_SUCCESS == detailsBean.getStatus()){
                            getView().onDetailsSuccess(detailsBean);
                        }else{
                            getView().onDetailsSuccess(null);
                        }
                    }

                    @Override
                    public void onErrorImpl(String errorMessage) {
                        Logger.e("getAudioDetails" + errorMessage);
                        ToastUtil.showToast("网络开小差了");
                        getView().onDetailFiled();
                        getView().showTimeout();
                    }
                });
    }


        //收藏
    public void audioShouCang(HashMap<String,String> map){
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
                            getView().onDetailsSuccess(null);
                        }
                    }

                    @Override
                    public void onErrorImpl(String errorMessage) {
                        Logger.e("audioShouCang" + errorMessage);
                        ToastUtil.showToast("网络开小差了");
                        getView().onDetailFiled();
                    }
                });
    }
    //取消收藏
    public void audioShouCangDel(HashMap<String,String> map){
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
                        Logger.e("audioShouCangDel" + errorMessage);
                        ToastUtil.showToast("网络开小差了");
                        getView().onDetailFiled();
                    }
                });
    }

    //支付
    public void setAudioPay(HashMap<String,String> map){
        HttpUtil.getInstance().getApiService().getPay(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(bindLifecycle())
                .subscribe(new BaseRxObserver<PayBean>() {
                    @Override
                    public void onNextImpl(PayBean payBean) {
                        if(AppConstant.CODE_SUCCESS == payBean.getStatus()){
                            getView().onAudioPaySuccess(payBean);
                        }else{
                            getView().onAudioPaySuccess(payBean);
                        }
                    }

                    @Override
                    public void onErrorImpl(String errorMessage) {
                        Logger.e("setAudioPay" + errorMessage);
                        ToastUtil.showToast("网络开小差了");
                        getView().onDetailFiled();
                    }
                });
    }

    //微信支付
    public void setAudioPayWx(HashMap<String,String> map){
        HttpUtil.getInstance().getApiService().getWxPay(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(bindLifecycle())
                .subscribe(new BaseRxObserver<WxPayBean>() {
                    @Override
                    public void onNextImpl(WxPayBean wxPayBean) {
                        if(AppConstant.CODE_SUCCESS == wxPayBean.getStatus()){
                            getView().onAudioPayWxSuccess(wxPayBean);
                        }else{
                            getView().onAudioPayWxSuccess(wxPayBean);
                        }
                    }

                    @Override
                    public void onErrorImpl(String errorMessage) {
                        Logger.e("setAudioPayWx" + errorMessage);
                        ToastUtil.showToast("网络开小差了");
                        getView().onDetailFiled();
                    }
                });
    }

    //音频时长
    public void setAudioLisener(HashMap<String,String> map){
        HttpUtil.getInstance().getApiService().getAudioListener(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(bindLifecycle())
                .subscribe(new BaseRxObserver<AudioListerBean>() {
                    @Override
                    public void onNextImpl(AudioListerBean listerBean) {
                        if(AppConstant.CODE_SUCCESS == listerBean.getStatus()){
                            getView().onListenerSuccess(listerBean);
                        }else{
                            getView().onListenerSuccess(listerBean);
                        }
                    }

                    @Override
                    public void onErrorImpl(String errorMessage) {
                        Logger.e("setAudioPayWx" + errorMessage);
                        ToastUtil.showToast("网络开小差了");
                        getView().onDetailFiled();
                    }
                });
    }
}
