package com.ytfu.yuntaifawu.ui.consult.presenter;

import android.content.Context;

import com.orhanobut.logger.Logger;
import com.ytfu.yuntaifawu.apis.HttpUtil;
import com.ytfu.yuntaifawu.app.AppConstant;
import com.ytfu.yuntaifawu.base.BasePresenter;
import com.ytfu.yuntaifawu.helper.BaseRxObserver;
import com.ytfu.yuntaifawu.ui.consult.bean.MIanFeiFaBuBean;
import com.ytfu.yuntaifawu.ui.consult.bean.ZiXunNavBean;
import com.ytfu.yuntaifawu.ui.consult.view.IZiXunNavView;
import com.ytfu.yuntaifawu.ui.home.bean.AudioNavBean;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ZiXunNavPresenter extends BasePresenter<IZiXunNavView> {
    private Context mContext;

    public ZiXunNavPresenter(Context mContext) {
        this.mContext = mContext;
    }

    //咨询类型分类
    public void setZiXunNav() {
        HttpUtil.getInstance().getApiService().getZiXunNav()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(bindLifecycle())
                .subscribe(new BaseRxObserver<ZiXunNavBean>() {
                    @Override
                    public void onNextImpl(ZiXunNavBean entity) {
                        if (AppConstant.CODE_SUCCESS == entity.getStatus()) {
                            getView().onZiXunNavSuccess(entity);
                        } else {
                            getView().onZiXunNavSuccess(null);
                        }

                    }

                    @Override
                    public void onErrorImpl(String errorMessage) {
                        Logger.e("getNavTitle" + errorMessage);
//                        ToastUtil.showToast("网络开小差了");
                        getView().showTimeout();
                        getView().onZiXunNavFiled();
                    }
                });
    }

    //免费发布咨询
    public void getMianFeiFabu(HashMap<String, String> map) {
        HttpUtil.getInstance().getApiService().setMianFeiFaBu(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(bindLifecycle())
                .subscribe(new BaseRxObserver<MIanFeiFaBuBean>() {
                    @Override
                    public void onNextImpl(MIanFeiFaBuBean entity) {
                        if (AppConstant.STATUS_SUCCESS == entity.getStatus()) {
                            getView().onMianFeiFaBuSuccess(entity);
                        } else {
                            getView().onMianFeiFaBuSuccess(null);
                        }
                    }

                    @Override
                    public void onErrorImpl(String errorMessage) {
                        Logger.e("getNavTitle" + errorMessage);
//                        ToastUtil.showToast("网络开小差了");
//                        getView().showTimeout();
                        getView().onZiXunNavFiled();
                    }
                });
    }
}
