package com.ytfu.yuntaifawu.ui.mine.present;

import android.content.Context;

import com.orhanobut.logger.Logger;
import com.ytfu.yuntaifawu.apis.HttpUtil;
import com.ytfu.yuntaifawu.app.AppConstant;
import com.ytfu.yuntaifawu.base.BasePresenter;
import com.ytfu.yuntaifawu.helper.BaseRxObserver;
import com.ytfu.yuntaifawu.ui.mine.bean.CollectionListBean;
import com.ytfu.yuntaifawu.ui.mine.view.ICollectionView;
import com.ytfu.yuntaifawu.utils.ToastUtil;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
*
*  @Auther  gxy
*
*  @Date    2019/11/16
*
*  @Des  我的收藏p
*
*/
public class CollectionPresenter extends BasePresenter<ICollectionView> {

    private Context mContext;

    public CollectionPresenter(Context mContext) {
        this.mContext = mContext;
    }

    public void collectionAudio(HashMap<String,String> map){
        HttpUtil.getInstance().getApiService().getCollectionAudio(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(bindLifecycle())
                .subscribe(new BaseRxObserver<CollectionListBean>() {
                    @Override
                    public void onNextImpl(CollectionListBean entity) {
                        if (AppConstant.CODE_SUCCESS == entity.getStatus()) {
                            getView().onCollectionSuccess(entity);
                        }else{
                            getView().onCollectionSuccess(null);
                        }

                    }
                    @Override
                    public void onErrorImpl(String errorMessage) {
                        Logger.e("collectionAudio" + errorMessage);
                        ToastUtil.showToast("网络开小差了");
                        getView().showTimeout();
                        getView().onFiled();
                    }
                });
    }
}
