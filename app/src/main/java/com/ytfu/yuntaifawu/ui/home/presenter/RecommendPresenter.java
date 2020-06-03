package com.ytfu.yuntaifawu.ui.home.presenter;

import android.content.Context;

import com.orhanobut.logger.Logger;
import com.ytfu.yuntaifawu.apis.HttpUtil;
import com.ytfu.yuntaifawu.app.AppConstant;
import com.ytfu.yuntaifawu.base.BasePresenter;
import com.ytfu.yuntaifawu.helper.BaseRxObserver;
import com.ytfu.yuntaifawu.ui.home.bean.HomeBannerBean;
import com.ytfu.yuntaifawu.ui.home.bean.HomeLvShiBean;
import com.ytfu.yuntaifawu.ui.home.bean.HomePingJIaBean;
import com.ytfu.yuntaifawu.ui.home.bean.RecommendListBean;
import com.ytfu.yuntaifawu.ui.home.view.IRecommendView;
import com.ytfu.yuntaifawu.utils.ToastUtil;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @Auther gxy
 * @Date 2019/11/13
 * @Des 首页present
 */
public class RecommendPresenter extends BasePresenter<IRecommendView> {
    private Context mContext;

    public RecommendPresenter(Context mContext) {
        this.mContext = mContext;
    }

    //    public void getRecommendList(){
//        HttpUtil.getInstance().getApiService().recommendList()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .as(bindLifecycle())
//                .subscribe(new BaseRxObserver<ResultEntity<RecommendListBean>>() {
//                    @Override
//                    public void onNextImpl(ResultEntity<RecommendListBean> entity) {
//                        if (AppConstant.CODE_SUCCESS == entity.getStatus()) {
//                            getView().onRecommendSuccess(entity.getList());
//                        }else{
//                            getView().onRecommendSuccess(null);
//                        }
//
//                    }
//                    @Override
//                    public void onErrorImpl(String errorMessage) {
//                        Logger.e("getRecommendList" + errorMessage);
//                        ToastUtil.showToast("网络开小差了");
//                        getView().onRecommendFiled();
//                        getView().showTimeout();
//                    }
//                });
//    }
    public void getRecommendList() {
        HttpUtil.getInstance().getApiService().recommendList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(bindLifecycle())
                .subscribe(new BaseRxObserver<RecommendListBean>() {
                    @Override
                    public void onNextImpl(RecommendListBean entity) {
                        if (AppConstant.CODE_SUCCESS == entity.getStatus()) {
                            getView().onRecommendSuccess(entity);
                        } else {
                            getView().onRecommendSuccess(null);
                        }

                    }

                    @Override
                    public void onErrorImpl(String errorMessage) {
                        Logger.e("getRecommendList" + errorMessage);
                        ToastUtil.showToast("网络开小差了");
                        getView().onRecommendFiled();
                        getView().showTimeout();
                    }
                });
    }

    //    public void getBannerList(){
//        HttpUtil.getInstance().getApiService().homeBanner()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .as(bindLifecycle())
//                .subscribe(new BaseRxObserver<ResultEntity<HomeBannerBean>>() {
//                    @Override
//                    public void onNextImpl(ResultEntity<HomeBannerBean> entity) {
//                        if (AppConstant.CODE_SUCCESS == entity.getStatus()) {
//                            getView().onBannerSuccess(entity.getList());
//                        }else{
//                            getView().onBannerSuccess(null);
//                        }
//
//                    }
//                    @Override
//                    public void onErrorImpl(String errorMessage) {
//                        Logger.e("getBannerList" + errorMessage);
//                        ToastUtil.showToast("网络开小差了");
//                        getView().onRecommendFiled();
//                    }
//                });
//    }
    //首页轮播图
    public void getBannerList() {
        HttpUtil.getInstance().getApiService().homeBanner()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(bindLifecycle())
                .subscribe(new BaseRxObserver<HomeBannerBean>() {
                    @Override
                    public void onNextImpl(HomeBannerBean entity) {
                        if (AppConstant.CODE_SUCCESS == entity.getStatus()) {
                            getView().onBannerSuccess(entity);
                        } else {
                            getView().onBannerSuccess(null);
                        }

                    }

                    @Override
                    public void onErrorImpl(String errorMessage) {
                        Logger.e("getBannerList" + errorMessage);
                        ToastUtil.showToast("网络开小差了");
                        getView().onRecommendFiled();
                    }
                });
    }

    //首页推荐律师
    public void getHomeLvShi() {
        HttpUtil.getInstance().getApiService().setHomeLvShi()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(bindLifecycle())
                .subscribe(new BaseRxObserver<HomeLvShiBean>() {
                    @Override
                    public void onNextImpl(HomeLvShiBean homeLvShiBean) {
                        if (AppConstant.CODE_SUCCESS == homeLvShiBean.getStatus()) {
                            getView().onHomeLvShiSuccess(homeLvShiBean);
                        } else {
                            getView().onHomeLvShiSuccess(homeLvShiBean);
                        }

                    }

                    @Override
                    public void onErrorImpl(String errorMessage) {
                        Logger.e("getHomeLvShi" + errorMessage);
                        ToastUtil.showToast("网络开小差了");
                        getView().onRecommendFiled();
                    }
                });
    }

    //首页律师评价
    public void getHomePingJia(int page) {
        HttpUtil.getInstance().getApiService().setHomePingJIa(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(bindLifecycle())
                .subscribe(new BaseRxObserver<HomePingJIaBean>() {
                    @Override
                    public void onNextImpl(HomePingJIaBean homePingJIaBean) {
                        if (AppConstant.CODE_SUCCESS == homePingJIaBean.getStatus()) {
                            getView().onHomePingJiaSuccess(homePingJIaBean);
                        } else {
                            getView().onHomePingJiaSuccess(homePingJIaBean);
                        }

                    }

                    @Override
                    public void onErrorImpl(String errorMessage) {
                        Logger.e("getHomeLvShi" + errorMessage);
                        ToastUtil.showToast("网络开小差了");
                        getView().onRecommendFiled();
                    }
                });
    }
}
