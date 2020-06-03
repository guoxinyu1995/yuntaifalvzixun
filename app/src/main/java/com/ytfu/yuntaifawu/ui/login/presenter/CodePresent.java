package com.ytfu.yuntaifawu.ui.login.presenter;

import android.content.Context;

import com.orhanobut.logger.Logger;
import com.ytfu.yuntaifawu.apis.HttpUtil;
import com.ytfu.yuntaifawu.base.BasePresenter;
import com.ytfu.yuntaifawu.helper.BaseRxObserver;
import com.ytfu.yuntaifawu.ui.login.bean.WxLoginBean;
import com.ytfu.yuntaifawu.ui.login.view.ICodeView;
import com.ytfu.yuntaifawu.ui.login.bean.CodeBean;
import com.ytfu.yuntaifawu.ui.login.bean.CodeLoginBean;
import com.ytfu.yuntaifawu.utils.ToastUtil;

import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
*
*  @Auther  gxy
*
*  @Date    2019/11/14
*
*  @Des  验证码登录的present
*
*/
public class CodePresent extends BasePresenter<ICodeView> {
    private Context mContext;

    public CodePresent(Context mContext) {
        this.mContext = mContext;
    }
    //获取验证码
    public void getSendCode(Map<String,String> map){
        HttpUtil.getInstance().getApiService().sendCode(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(bindLifecycle())
                .subscribe(new BaseRxObserver<CodeBean>() {
                    @Override
                    public void onNextImpl(CodeBean entity) {
                            getView().onCodeSuccess(entity);
                    }
                    @Override
                    public void onErrorImpl(String errorMessage) {
                        Logger.e("getSendCode" + errorMessage);
                        ToastUtil.showToast("网络开小差了");
                        getView().onCodeFiled();
                    }
                });
    }
    //验证码登录
    public void getLoginCode(Map<String,String> map){
        HttpUtil.getInstance().getApiService().loginCode(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(bindLifecycle())
                .subscribe(new BaseRxObserver<CodeLoginBean>() {
                    @Override
                    public void onNextImpl(CodeLoginBean entity) {
                        getView().onCodeLoginSuccess(entity);
                    }
                    @Override
                    public void onErrorImpl(String errorMessage) {
                        Logger.e("getLoginCode" + errorMessage);
                        ToastUtil.showToast("网络开小差了");
                        getView().onCodeFiled();
                    }
                });
    }
    //微信登录
    public void getWxLogin(Map<String,String> map){
        HttpUtil.getInstance().getApiService().getWxLogin(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(bindLifecycle())
                .subscribe(new BaseRxObserver<WxLoginBean>() {
                    @Override
                    public void onNextImpl(WxLoginBean entity) {
                        getView().onWxLoginSuccess(entity);
                    }
                    @Override
                    public void onErrorImpl(String errorMessage) {
                        Logger.e("getWxLogin" + errorMessage);
                        ToastUtil.showToast("网络开小差了");
                        getView().onCodeFiled();
                    }
                });
    }
}
