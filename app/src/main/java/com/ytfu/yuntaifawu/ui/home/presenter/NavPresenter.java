package com.ytfu.yuntaifawu.ui.home.presenter;

import android.content.Context;

import com.orhanobut.logger.Logger;
import com.ytfu.yuntaifawu.apis.HttpUtil;
import com.ytfu.yuntaifawu.app.AppConstant;
import com.ytfu.yuntaifawu.base.BasePresenter;
import com.ytfu.yuntaifawu.helper.BaseRxObserver;
import com.ytfu.yuntaifawu.ui.home.bean.AudioNavBean;
import com.ytfu.yuntaifawu.ui.home.view.INavView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
/**
*
*  @Auther  gxy
*
*  @Date    2019/11/13
*
*  @Des  音频分类
*
*/
public class NavPresenter extends BasePresenter<INavView> {
    private Context mContext;

    public NavPresenter(Context mContext) {
        this.mContext = mContext;
    }
    //咨询分类
    public void getNavTitle() {
        HttpUtil.getInstance().getApiService().navTitle()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(bindLifecycle())
                .subscribe(new BaseRxObserver<AudioNavBean>() {
                    @Override
                    public void onNextImpl(AudioNavBean entity) {
                        if (AppConstant.CODE_SUCCESS == entity.getStatus()) {
                            getView().onNavSuccess(entity);
                        }else{
                            getView().onNavSuccess(null);
                        }

                    }
                    @Override
                    public void onErrorImpl(String errorMessage) {
                        Logger.e("getNavTitle" + errorMessage);
//                        ToastUtil.showToast("网络开小差了");
                        getView().showTimeout();
                        getView().onNavFalied();
                    }
                });
    }
}
