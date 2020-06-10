package com.ytfu.yuntaifawu.ui.users.p;

import com.orhanobut.logger.Logger;
import com.ytfu.yuntaifawu.apis.HttpUtil;
import com.ytfu.yuntaifawu.app.AppConstant;
import com.ytfu.yuntaifawu.base.BasePresenter;
import com.ytfu.yuntaifawu.base.BaseRefreshPresenter;
import com.ytfu.yuntaifawu.base.BaseRefreshView;
import com.ytfu.yuntaifawu.helper.BaseRxObserver;
import com.ytfu.yuntaifawu.ui.home.bean.HomeBannerBean;
import com.ytfu.yuntaifawu.ui.users.v.IUserHomeView;
import com.ytfu.yuntaifawu.utils.ToastUtil;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @Auther gxy
 * @Date 2020/6/9
 * @Des* 用户端首页presenter
 */
public class UserHomePresenter extends BaseRefreshPresenter<IUserHomeView> {
    //首页轮播
    public void getHomeBannerList() {
        HttpUtil.getInstance().getApiService().homeBanner()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(bindLifecycle())
                .subscribe(new BaseRxObserver<HomeBannerBean>() {
                    @Override
                    public void onNextImpl(HomeBannerBean entity) {
                        if (AppConstant.CODE_SUCCESS == entity.getStatus()) {
                            getView().userHomeBannerList(entity);
                        } else {
                            getView().userHomeBannerList(null);
                        }

                    }

                    @Override
                    public void onErrorImpl(String errorMessage) {
                        Logger.e("getBannerList" + errorMessage);
                        ToastUtil.showToast("网络开小差了");
                        getView().userHomeFiled(errorMessage);
                    }
                });
    }
}
