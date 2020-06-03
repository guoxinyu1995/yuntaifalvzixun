package com.ytfu.yuntaifawu.ui.mseeage.presenter;

import android.content.Context;

import com.orhanobut.logger.Logger;
import com.ytfu.yuntaifawu.apis.HttpUtil;
import com.ytfu.yuntaifawu.base.BasePresenter;
import com.ytfu.yuntaifawu.helper.BaseRxObserver;
import com.ytfu.yuntaifawu.ui.mseeage.bean.HuiDaDetailsBean;
import com.ytfu.yuntaifawu.ui.mseeage.bean.PingJIaDetailsBean;
import com.ytfu.yuntaifawu.ui.mseeage.view.IEvaluateDetailsView;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class EvaluateDetailsPresenter extends BasePresenter<IEvaluateDetailsView> {
    private Context mContext;

    public EvaluateDetailsPresenter(Context mContext) {
        this.mContext = mContext;
    }

    public void getEvaluateDetalis(HashMap<String,String> map){
        HttpUtil.getInstance().getApiService().setEvaluateDetails(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(bindLifecycle())
                .subscribe(new BaseRxObserver<PingJIaDetailsBean>() {
                    @Override
                    public void onNextImpl(PingJIaDetailsBean pingJIaDetailsBean) {
                        if (pingJIaDetailsBean.getCode() == 200) {
                            getView().onEvaluateDetailsSuccess(pingJIaDetailsBean);
                        } else {
                            getView().onEvaluateDetailsSuccess(pingJIaDetailsBean);
                        }
                    }

                    @Override
                    public void onErrorImpl(String errorMessage) {
                        Logger.e("setMessageWatchPay" + errorMessage);
//                        ToastUtil.showToast("网络开小差了");
                        getView().onEvaluateDetailsFiled();
                    }
                });
    }
}
