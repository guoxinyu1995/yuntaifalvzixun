package com.ytfu.yuntaifawu.ui.qisuzhuang.presenter;

import android.content.Context;

import com.orhanobut.logger.Logger;
import com.ytfu.yuntaifawu.apis.HttpUtil;
import com.ytfu.yuntaifawu.app.AppConstant;
import com.ytfu.yuntaifawu.base.BasePresenter;
import com.ytfu.yuntaifawu.helper.BaseRxObserver;
import com.ytfu.yuntaifawu.ui.qisuzhuang.bean.AddZjqdBean;
import com.ytfu.yuntaifawu.ui.qisuzhuang.bean.QszXqFlBean;
import com.ytfu.yuntaifawu.ui.qisuzhuang.view.IAddZjqdView;
import com.ytfu.yuntaifawu.utils.ToastUtil;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @Auther gxy
 * @Date 2019/12/18
 * @Des 添加证据清单persenter
 */
public class AddZjqdPresenter extends BasePresenter<IAddZjqdView> {
    private Context mContext;

    public AddZjqdPresenter(Context mContext) {
        this.mContext = mContext;
    }

    public void setAddZjqd(HashMap<String, String> map) {
        HttpUtil.getInstance().getApiService().getAddZjqd(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(bindLifecycle())
                .subscribe(new BaseRxObserver<AddZjqdBean>() {
                    @Override
                    public void onNextImpl(AddZjqdBean entity) {
                        if (AppConstant.STATUS_SUCCESS == entity.getStatus()) {
                            getView().onAssSuccess(entity);
                        } else {
                            getView().onAssSuccess(null);
                        }

                    }

                    @Override
                    public void onErrorImpl(String errorMessage) {
                        Logger.e("setQszXqFl" + errorMessage);
                        ToastUtil.showToast("网络开小差了");
                        getView().showTimeout();
                        getView().onAddFiled();
                    }
                });
    }
}
