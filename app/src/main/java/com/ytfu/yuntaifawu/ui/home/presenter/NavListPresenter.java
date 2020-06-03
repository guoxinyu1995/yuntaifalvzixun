package com.ytfu.yuntaifawu.ui.home.presenter;

import android.content.Context;

import com.orhanobut.logger.Logger;
import com.ytfu.yuntaifawu.apis.HttpUtil;
import com.ytfu.yuntaifawu.app.AppConstant;
import com.ytfu.yuntaifawu.base.BasePresenter;
import com.ytfu.yuntaifawu.helper.BaseRxObserver;
import com.ytfu.yuntaifawu.ui.home.bean.AudioListBean;
import com.ytfu.yuntaifawu.ui.home.bean.AudioTopImageBean;
import com.ytfu.yuntaifawu.ui.home.view.INavListView;
import com.ytfu.yuntaifawu.utils.ToastUtil;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
/**
*
*  @Auther  gxy
*
*  @Date    2019/11/13
*
*  @Des  音频列表presenter
*
*/
public class NavListPresenter extends BasePresenter<INavListView> {
    private Context mContext;

    public NavListPresenter(Context mContext) {
        this.mContext = mContext;
    }

//    public void getNavList(String id,int page,String keyword){
//        HttpUtil.getInstance().getApiService().navAudioList(id,page,keyword)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .as(bindLifecycle())
//                .subscribe(new BaseRxObserver<ResultEntity<AudioListBean>>() {
//                    @Override
//                    public void onNextImpl(ResultEntity<AudioListBean> resultEntity) {
//                        if (AppConstant.CODE_SUCCESS == resultEntity.getStatus()) {
//                            getView().onNavListSuccess(resultEntity.getList());
//                        }else{
//                            getView().onNavListSuccess(null);
//                        }
//                    }
//
//                    @Override
//                    public void onErrorImpl(String errorMessage) {
//                        Logger.e("getNavList" + errorMessage);
//                        ToastUtil.showToast("网络错误");
//                        getView().showTimeout();
//                        getView().onNavListFiled();
//                    }
//                });
//    }
public void getNavList(String id,int page,String keyword){
    HttpUtil.getInstance().getApiService().navAudioList(id,page,keyword)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .as(bindLifecycle())
            .subscribe(new BaseRxObserver<AudioListBean>() {
                @Override
                public void onNextImpl(AudioListBean resultEntity) {
                    if (AppConstant.CODE_SUCCESS == resultEntity.getStatus()) {
                        getView().onNavListSuccess(resultEntity);
                    }else{
//                        getView().onNavListSuccess(null);
                        ToastUtil.showCenterToast("暂无相关音频");
                        getView().showEmpty();
                    }
                }

                @Override
                public void onErrorImpl(String errorMessage) {
                    Logger.e("getNavList" + errorMessage);
                    ToastUtil.showToast("网络错误");
                    getView().showTimeout();
                    getView().onNavListFiled();
                }
            });
}
//    //音频列表顶部图片
//    public void getNavTopImage(){
//        HttpUtil.getInstance().getApiService().navTopImage()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .as(bindLifecycle())
//                .subscribe(new BaseRxObserver<ResultEntity<AudioTopImageBean>>() {
//                    @Override
//                    public void onNextImpl(ResultEntity<AudioTopImageBean> entity) {
//                        if (AppConstant.CODE_SUCCESS == entity.getStatus()) {
//                            getView().onNavImageSuccess(entity.getList());
//                        }else{
//                            getView().onNavImageSuccess(null);
//                        }
//
//                    }
//                    @Override
//                    public void onErrorImpl(String errorMessage) {
//                        Logger.e("getNavTopImage" + errorMessage);
//                        ToastUtil.showToast("网络开小差了");
//                        getView().onNavListFiled();
//                    }
//                });
//    }
//音频列表顶部图片
public void getNavTopImage(String id){
    HttpUtil.getInstance().getApiService().navTopImage(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .as(bindLifecycle())
            .subscribe(new BaseRxObserver<AudioTopImageBean>() {
                @Override
                public void onNextImpl(AudioTopImageBean entity) {
                    if (AppConstant.CODE_SUCCESS == entity.getStatus()) {
                        getView().onNavImageSuccess(entity);
                    }else{
                        getView().onNavImageSuccess(null);
                    }

                }
                @Override
                public void onErrorImpl(String errorMessage) {
                    Logger.e("getNavTopImage" + errorMessage);
                    ToastUtil.showToast("网络开小差了");
                    getView().onNavListFiled();
                }
            });
}
}
