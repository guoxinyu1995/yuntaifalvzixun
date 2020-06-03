package com.ytfu.yuntaifawu.ui.mine.present;

import android.content.Context;

import com.orhanobut.logger.Logger;
import com.ytfu.yuntaifawu.apis.HttpUtil;
import com.ytfu.yuntaifawu.app.AppConstant;
import com.ytfu.yuntaifawu.base.BasePresenter;
import com.ytfu.yuntaifawu.helper.BaseRxObserver;
import com.ytfu.yuntaifawu.ui.mine.bean.InformationBean;
import com.ytfu.yuntaifawu.ui.mine.bean.ShangChangLingYuBean;
import com.ytfu.yuntaifawu.ui.mine.view.IShanchanglingyuView;
import com.ytfu.yuntaifawu.utils.ToastUtil;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
*
*  @Auther  gxy
*
*  @Date    2020/5/21
*
*  @Des  擅长领域presenter
*
*/
public class ShangChangLingYuPresenter extends BasePresenter<IShanchanglingyuView> {
  private Context mContext;

    public ShangChangLingYuPresenter(Context mContext) {
        this.mContext = mContext;
    }

    public void getShangChangLingYu(){
        HttpUtil.getInstance().getApiService().setShangChangLingYu()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(bindLifecycle())
                .subscribe(new BaseRxObserver<ShangChangLingYuBean>() {
                    @Override
                    public void onNextImpl(ShangChangLingYuBean entity) {
                        if (AppConstant.CODE_SUCCESS == entity.getStatus()) {
                            getView().onShangChangLingYuSuccess(entity);
                        } else {
                            getView().onShangChangLingYuSuccess(entity);
                        }

                    }

                    @Override
                    public void onErrorImpl(String errorMessage) {
                        Logger.e("getShangChangLingYu" + errorMessage);
                        ToastUtil.showToast("网络开小差了");
                        getView().onShangChangLingYuFiled();
                    }
                });
    }
}
