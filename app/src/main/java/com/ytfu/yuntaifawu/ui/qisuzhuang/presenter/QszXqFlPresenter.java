package com.ytfu.yuntaifawu.ui.qisuzhuang.presenter;

import android.content.Context;

import com.orhanobut.logger.Logger;
import com.ytfu.yuntaifawu.apis.HttpUtil;
import com.ytfu.yuntaifawu.app.AppConstant;
import com.ytfu.yuntaifawu.base.BasePresenter;
import com.ytfu.yuntaifawu.helper.BaseRxObserver;
import com.ytfu.yuntaifawu.ui.qisuzhuang.bean.QszFenLeiBean;
import com.ytfu.yuntaifawu.ui.qisuzhuang.bean.QszXqFlBean;
import com.ytfu.yuntaifawu.ui.qisuzhuang.view.IQszXqFlView;
import com.ytfu.yuntaifawu.utils.ToastUtil;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class QszXqFlPresenter extends BasePresenter<IQszXqFlView> {
private Context mContext;

    public QszXqFlPresenter(Context mContext) {
        this.mContext = mContext;
    }

    public void setQszXqFl(HashMap<String,String> map){
        HttpUtil.getInstance().getApiService().getQszXqFl(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(bindLifecycle())
                .subscribe(new BaseRxObserver<QszXqFlBean>() {
                    @Override
                    public void onNextImpl(QszXqFlBean entity) {
                        if (AppConstant.STATUS_SUCCESS == entity.getStatus()) {
                            getView().onSuccess(entity);
                        } else {
                            getView().onSuccess(null);
                        }

                    }

                    @Override
                    public void onErrorImpl(String errorMessage) {
                        Logger.e("setQszXqFl" + errorMessage);
                        ToastUtil.showToast("网络开小差了");
                        getView().showTimeout();
                        getView().onFiled();
                    }
                });
    }
}
