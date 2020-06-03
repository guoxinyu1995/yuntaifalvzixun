package com.ytfu.yuntaifawu.ui.login.presenter;

import android.content.Context;

import com.orhanobut.logger.Logger;
import com.ytfu.yuntaifawu.apis.HttpUtil;
import com.ytfu.yuntaifawu.base.BasePresenter;
import com.ytfu.yuntaifawu.helper.BaseRxObserver;
import com.ytfu.yuntaifawu.ui.login.bean.ForgectDosePwdBean;
import com.ytfu.yuntaifawu.ui.login.bean.ForgetSendBean;
import com.ytfu.yuntaifawu.ui.login.view.IForgetPwdView;
import com.ytfu.yuntaifawu.utils.ToastUtil;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ForgectPwdPresenter extends BasePresenter<IForgetPwdView> {
    private Context mContext;

    public ForgectPwdPresenter(Context mContext) {
        this.mContext = mContext;
    }
    //重置密码获取验证码
    public void getForgectSend(HashMap<String,String> map){
        HttpUtil.getInstance().getApiService().goForgectSend(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(bindLifecycle())
                .subscribe(new BaseRxObserver<ForgetSendBean>() {
                    @Override
                    public void onNextImpl(ForgetSendBean entity) {
                        getView().onForgetPwdSuccess(entity);
                    }
                    @Override
                    public void onErrorImpl(String errorMessage) {
                        Logger.e("getRegistNum" + errorMessage);
                        ToastUtil.showToast("网络开小差了");
                        getView().onForgetPweFilded();
                    }
                });
    }
    //去重置
    public void getForgectPwd(HashMap<String,String> map){
        HttpUtil.getInstance().getApiService().goForgectPwd(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(bindLifecycle())
                .subscribe(new BaseRxObserver<ForgectDosePwdBean>() {
                    @Override
                    public void onNextImpl(ForgectDosePwdBean entity) {
                        getView().onForgetDosetPwd(entity);
                    }
                    @Override
                    public void onErrorImpl(String errorMessage) {
                        Logger.e("getRegistNum" + errorMessage);
                        ToastUtil.showToast("网络开小差了");
                        getView().onForgetPweFilded();
                    }
                });
    }
}
