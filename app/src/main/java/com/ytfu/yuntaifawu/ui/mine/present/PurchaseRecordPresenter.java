package com.ytfu.yuntaifawu.ui.mine.present;

import android.content.Context;

import com.orhanobut.logger.Logger;
import com.ytfu.yuntaifawu.apis.HttpUtil;
import com.ytfu.yuntaifawu.app.AppConstant;
import com.ytfu.yuntaifawu.base.BasePresenter;
import com.ytfu.yuntaifawu.helper.BaseRxObserver;
import com.ytfu.yuntaifawu.ui.mine.bean.CollectionListBean;
import com.ytfu.yuntaifawu.ui.mine.bean.PurchaseRecordBean;
import com.ytfu.yuntaifawu.ui.mine.view.IPurchassRecordView;
import com.ytfu.yuntaifawu.utils.ToastUtil;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @Auther gxy
 * @Date 2019/11/20
 * @Des 购买记录
 */
public class PurchaseRecordPresenter extends BasePresenter<IPurchassRecordView> {
    private Context mContext;

    public PurchaseRecordPresenter(Context mContext) {
        this.mContext = mContext;
    }

    public void purchaseRecord(HashMap<String, String> map) {
        HttpUtil.getInstance().getApiService().getPurchaseRecord(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(bindLifecycle())
                .subscribe(new BaseRxObserver<PurchaseRecordBean>() {
                    @Override
                    public void onNextImpl(PurchaseRecordBean entity) {
                        if (AppConstant.CODE_SUCCESS == entity.getStatus() && entity.getState() != null) {
                            getView().onPurcheRecordSuccess(entity);
                        } else {
                            getView().onPurcheRecordSuccess(null);
                        }

                    }

                    @Override
                    public void onErrorImpl(String errorMessage) {
                        Logger.e("getNavTitle" + errorMessage);
                        ToastUtil.showToast("网络开小差了");
                        getView().showTimeout();
                        getView().onFiled();
                    }
                });
    }
}
