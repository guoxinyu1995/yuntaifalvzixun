package com.ytfu.yuntaifawu.ui.mseeage.presenter;

import android.content.Context;

import com.orhanobut.logger.Logger;
import com.ytfu.yuntaifawu.apis.HttpUtil;
import com.ytfu.yuntaifawu.base.BasePresenter;
import com.ytfu.yuntaifawu.helper.BaseRxObserver;
import com.ytfu.yuntaifawu.ui.mseeage.bean.ToCheckPayBean;
import com.ytfu.yuntaifawu.ui.mseeage.bean.UserEvaluateBean;
import com.ytfu.yuntaifawu.ui.mseeage.bean.UserEvaluateBean1;
import com.ytfu.yuntaifawu.ui.mseeage.bean.UserEvaluateClassBean;
import com.ytfu.yuntaifawu.ui.mseeage.view.IUserEvaluateView;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class UserEvaluatePresenter extends BasePresenter<IUserEvaluateView> {
    private Context mContext;

    public UserEvaluatePresenter(Context mContext) {
        this.mContext = mContext;
    }

    public void getUserEvaluate(HashMap<String,String> map){
        HttpUtil.getInstance().getApiService().setUserEvaluate(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(bindLifecycle())
                .subscribe(new BaseRxObserver<UserEvaluateBean>() {
                    @Override
                    public void onNextImpl(UserEvaluateBean userEvaluateBean) {
                        if (userEvaluateBean.getCode() == 200) {
                            getView().onUserEvaluateSuccess(userEvaluateBean);
                        } else {
                            getView().onUserEvaluateSuccess(userEvaluateBean);
                        }

                    }

                    @Override
                    public void onErrorImpl(String errorMessage) {
                        Logger.e("getToCheckPay" + errorMessage);
//                        ToastUtil.showToast("网络开小差了");
                        getView().onUserEvaluateFiled();
                    }
                });
    }

    public void getUserEvaluateClass(HashMap<String,String> map){
        HttpUtil.getInstance().getApiService().setUserEvaluateClass(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(bindLifecycle())
                .subscribe(new BaseRxObserver<UserEvaluateClassBean>() {
                    @Override
                    public void onNextImpl(UserEvaluateClassBean userEvaluateBean) {
                        if (userEvaluateBean.getStatus() == 1) {
                            getView().onUserEvaluateClassSuccess(userEvaluateBean);
                        } else {
                            getView().onUserEvaluateClassSuccess(userEvaluateBean);
                        }

                    }

                    @Override
                    public void onErrorImpl(String errorMessage) {
                        Logger.e("getToCheckPay" + errorMessage);
//                        ToastUtil.showToast("网络开小差了");
                        getView().onUserEvaluateFiled();
                    }
                });
    }
}
