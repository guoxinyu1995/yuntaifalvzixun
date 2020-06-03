package com.ytfu.yuntaifawu.ui.falvguwen.presenter;

import android.content.Context;

import com.orhanobut.logger.Logger;
import com.ytfu.yuntaifawu.apis.HttpUtil;
import com.ytfu.yuntaifawu.app.AppConstant;
import com.ytfu.yuntaifawu.base.BasePresenter;
import com.ytfu.yuntaifawu.helper.BaseRxObserver;
import com.ytfu.yuntaifawu.ui.falvguwen.bean.LegalAdviserSecondClassifyBean;
import com.ytfu.yuntaifawu.ui.falvguwen.view.ILegalAdviserClassifyView;
import com.ytfu.yuntaifawu.utils.ToastUtil;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
*
*  @Auther  gxy
*
*  @Date    2019/11/22
*
*  @Des  法律顾问二级分类
*
*/
public class LegalAdviserSecondClassifyPresenter extends BasePresenter<ILegalAdviserClassifyView> {
    private Context mContext;

    public LegalAdviserSecondClassifyPresenter(Context mContext) {
        this.mContext = mContext;
    }

    public void getFlgwSecond(String id){
        HttpUtil.getInstance().getApiService().getFlgwSecondFl(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(bindLifecycle())
                .subscribe(new BaseRxObserver<LegalAdviserSecondClassifyBean>() {
                    @Override
                    public void onNextImpl(LegalAdviserSecondClassifyBean entity) {
                        if (AppConstant.STATUS_SUCCESS == entity.getStatus()) {
                            getView().onSuccess(entity);
                        }else{
                            getView().onSuccess(null);
                        }

                    }
                    @Override
                    public void onErrorImpl(String errorMessage) {
                        Logger.e("getFlgwSecond" + errorMessage);
                        ToastUtil.showToast("网络开小差了");
                        getView().showTimeout();
                        getView().onFiled(errorMessage);
                    }
                });
    }
}
