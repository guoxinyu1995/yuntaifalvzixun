package com.ytfu.yuntaifawu.ui.kaitingzhushou.presenter;

import android.content.Context;

import com.orhanobut.logger.Logger;
import com.ytfu.yuntaifawu.apis.HttpUtil;
import com.ytfu.yuntaifawu.app.AppConstant;
import com.ytfu.yuntaifawu.base.BasePresenter;
import com.ytfu.yuntaifawu.helper.BaseRxObserver;
import com.ytfu.yuntaifawu.ui.kaitingzhushou.bean.AddQszBean;
import com.ytfu.yuntaifawu.ui.kaitingzhushou.bean.KtzsListBean;
import com.ytfu.yuntaifawu.ui.kaitingzhushou.bean.SelectZhuShouBean;
import com.ytfu.yuntaifawu.ui.kaitingzhushou.view.IKtzsListView;
import com.ytfu.yuntaifawu.ui.kaitingzhushou.view.ISelectQszView;
import com.ytfu.yuntaifawu.utils.ToastUtil;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SelectQszPresenter extends BasePresenter<ISelectQszView> {
    private Context mContext;

    public SelectQszPresenter(Context mContext) {
        this.mContext = mContext;
    }

    //选择起诉状
    public void setSelectZhuShou(HashMap<String,String> map){
        HttpUtil.getInstance().getApiService().getSelectZhuShuo(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(bindLifecycle())
                .subscribe(new BaseRxObserver<SelectZhuShouBean>() {
                    @Override
                    public void onNextImpl(SelectZhuShouBean entity) {
                        if (AppConstant.STATUS_SUCCESS == entity.getStatus()) {
                            getView().onSelectZhuShouSuccess(entity);
                        }else{
                            getView().onSelectZhuShouSuccess(null);
                        }
                    }
                    @Override
                    public void onErrorImpl(String errorMessage) {
                        Logger.e("setSelectZhuShou" + errorMessage);
                        ToastUtil.showToast("网络开小差了");
                        getView().showTimeout();
                        getView().onFiled(errorMessage);
                    }
                });

    }
    //添加起诉状
    public void setAddZhuShou(HashMap<String,String> map){
        HttpUtil.getInstance().getApiService().getAddQsz(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(bindLifecycle())
                .subscribe(new BaseRxObserver<AddQszBean>() {
                    @Override
                    public void onNextImpl(AddQszBean entity) {
                        if (AppConstant.STATUS_SUCCESS == entity.getStatus()) {
                            getView().onAddQszSuccess(entity);
                        }else{
                            getView().onAddQszSuccess(null);
                        }

                    }
                    @Override
                    public void onErrorImpl(String errorMessage) {
                        Logger.e("setAddZhuShou" + errorMessage);
                        ToastUtil.showToast("网络开小差了");
                        getView().showTimeout();
                        getView().onFiled(errorMessage);
                    }
                });

    }
}
