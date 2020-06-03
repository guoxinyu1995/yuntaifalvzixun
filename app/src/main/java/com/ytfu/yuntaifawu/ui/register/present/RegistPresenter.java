package com.ytfu.yuntaifawu.ui.register.present;

import android.content.Context;

import com.orhanobut.logger.Logger;
import com.ytfu.yuntaifawu.apis.HttpUtil;
import com.ytfu.yuntaifawu.base.BasePresenter;
import com.ytfu.yuntaifawu.helper.BaseRxObserver;
import com.ytfu.yuntaifawu.ui.login.bean.CodeLoginBean;
import com.ytfu.yuntaifawu.ui.register.bean.RegistBean;
import com.ytfu.yuntaifawu.ui.register.view.IRegistView;
import com.ytfu.yuntaifawu.utils.ToastUtil;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
*
*  @Auther  gxy
*
*  @Date    2019/11/15
*
*  @Des  注册的present
*
*/
public class RegistPresenter extends BasePresenter<IRegistView> {
    private Context mContext;

    public RegistPresenter(Context mContext) {
        this.mContext = mContext;
    }

    public void getRegistNum(HashMap<String,String> map){
        HttpUtil.getInstance().getApiService().goRegist(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(bindLifecycle())
                .subscribe(new BaseRxObserver<RegistBean>() {
                    @Override
                    public void onNextImpl(RegistBean entity) {
                        getView().onRegistSussess(entity);
                    }
                    @Override
                    public void onErrorImpl(String errorMessage) {
                        Logger.e("getRegistNum" + errorMessage);
                        ToastUtil.showToast("网络开小差了");
                        getView().onRegistFiled();
                    }
                });
    }
}
