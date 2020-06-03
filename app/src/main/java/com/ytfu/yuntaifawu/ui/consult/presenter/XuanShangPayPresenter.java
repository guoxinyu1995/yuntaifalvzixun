package com.ytfu.yuntaifawu.ui.consult.presenter;

import android.content.Context;

import com.orhanobut.logger.Logger;
import com.ytfu.yuntaifawu.apis.HttpUtil;
import com.ytfu.yuntaifawu.app.AppConstant;
import com.ytfu.yuntaifawu.base.BasePresenter;
import com.ytfu.yuntaifawu.helper.BaseRxObserver;
import com.ytfu.yuntaifawu.ui.consult.bean.MIanFeiFaBuBean;
import com.ytfu.yuntaifawu.ui.consult.bean.XuanshangFaBuAliBean;
import com.ytfu.yuntaifawu.ui.consult.bean.XuanshangFaBuWatchBean;
import com.ytfu.yuntaifawu.ui.consult.view.IXuanShangPayView;
import com.ytfu.yuntaifawu.ui.pay.bean.WxPayBean;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @Auther gxy
 * @Date 2020/5/20
 * @Des 悬赏发布的presenter
 */
public class XuanShangPayPresenter extends BasePresenter<IXuanShangPayView> {
    private Context mContext;

    public XuanShangPayPresenter(Context mContext) {
        this.mContext = mContext;
    }

    //悬赏发布微信
    public void getXuanShangWatch(HashMap<String, String> map) {
        HttpUtil.getInstance().getApiService().setXuanShangWatch(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(bindLifecycle())
                .subscribe(new BaseRxObserver<WxPayBean>() {
                    @Override
                    public void onNextImpl(WxPayBean entity) {
                        if (AppConstant.CODE_SUCCESS == entity.getStatus()) {
                            getView().onXuanShangWatchPaySuccess(entity);
                        } else {
                            getView().onXuanShangWatchPaySuccess(null);
                        }
                    }

                    @Override
                    public void onErrorImpl(String errorMessage) {
                        Logger.e("getXuanShangWatch" + errorMessage);
//                        ToastUtil.showToast("网络开小差了");
//                        getView().showTimeout();
                        getView().onXuanShangFiled();
                    }
                });
    }


    //悬赏支付宝
    public void getXuanShangAli(HashMap<String, String> map) {
        HttpUtil.getInstance().getApiService().setXuanShangAli(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(bindLifecycle())
                .subscribe(new BaseRxObserver<XuanshangFaBuAliBean>() {
                    @Override
                    public void onNextImpl(XuanshangFaBuAliBean entity) {
                        if (AppConstant.CODE_SUCCESS == entity.getStatus()) {
                            getView().onXuanShangAliPaySuccess(entity);
                        } else {
                            getView().onXuanShangAliPaySuccess(null);
                        }
                    }

                    @Override
                    public void onErrorImpl(String errorMessage) {
                        Logger.e("getXuanShangAli" + errorMessage);
//                        ToastUtil.showToast("网络开小差了");
//                        getView().showTimeout();
                        getView().onXuanShangFiled();
                    }
                });
    }
}
