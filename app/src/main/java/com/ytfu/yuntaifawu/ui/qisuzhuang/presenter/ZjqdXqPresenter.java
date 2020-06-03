package com.ytfu.yuntaifawu.ui.qisuzhuang.presenter;

import android.content.Context;

import com.orhanobut.logger.Logger;
import com.ytfu.yuntaifawu.apis.HttpUtil;
import com.ytfu.yuntaifawu.app.AppConstant;
import com.ytfu.yuntaifawu.base.BasePresenter;
import com.ytfu.yuntaifawu.helper.BaseRxObserver;
import com.ytfu.yuntaifawu.ui.mine.bean.BdEmailBean;
import com.ytfu.yuntaifawu.ui.qisuzhuang.bean.QszXqFlBean;
import com.ytfu.yuntaifawu.ui.qisuzhuang.bean.ZjqdXqBean;
import com.ytfu.yuntaifawu.ui.qisuzhuang.bean.ZjqdXqSendEmailBean;
import com.ytfu.yuntaifawu.ui.qisuzhuang.view.IZjqdXqView;
import com.ytfu.yuntaifawu.utils.ToastUtil;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
*
*  @Auther  gxy
*
*  @Date    2019/12/18
*
*  @Des  证据清单详情presenter
*
*/
public class ZjqdXqPresenter extends BasePresenter<IZjqdXqView> {
     private Context mContext;

    public ZjqdXqPresenter(Context mContext) {
        this.mContext = mContext;
    }
    public void setZjqdXq(HashMap<String,String> map){
        HttpUtil.getInstance().getApiService().getZjqdXq(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(bindLifecycle())
                .subscribe(new BaseRxObserver<ZjqdXqBean>() {
                    @Override
                    public void onNextImpl(ZjqdXqBean entity) {
                        if (AppConstant.STATUS_SUCCESS == entity.getStatus()) {
                            getView().onZjqdXqSuccess(entity);
                        } else {
                            getView().onZjqdXqSuccess(null);
                        }

                    }

                    @Override
                    public void onErrorImpl(String errorMessage) {
                        Logger.e("setQszXqFl" + errorMessage);
                        ToastUtil.showToast("网络开小差了");
                        getView().showTimeout();
                        getView().onZjqdXqFiled();
                    }
                });
    }

    //购买后
    public void setZjqdXqBuy(HashMap<String,String> map){
        HttpUtil.getInstance().getApiService().getZjqdXqBuy(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(bindLifecycle())
                .subscribe(new BaseRxObserver<ZjqdXqBean>() {
                    @Override
                    public void onNextImpl(ZjqdXqBean entity) {
                        if (AppConstant.STATUS_SUCCESS == entity.getStatus()) {
                            getView().onZjqdXqSuccess(entity);
                        } else {
                            getView().onZjqdXqSuccess(null);
                        }

                    }

                    @Override
                    public void onErrorImpl(String errorMessage) {
                        Logger.e("setQszXqFl" + errorMessage);
                        ToastUtil.showToast("网络开小差了");
                        getView().showTimeout();
                        getView().onZjqdXqFiled();
                    }
                });
    }

    //发送邮箱
    public void setZjqdXqSendEmail(HashMap<String,String> map){
        HttpUtil.getInstance().getApiService().getZjqdXqSendEmail(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(bindLifecycle())
                .subscribe(new BaseRxObserver<ZjqdXqSendEmailBean>() {
                    @Override
                    public void onNextImpl(ZjqdXqSendEmailBean entity) {
                        if (AppConstant.STATUS_SUCCESS == entity.getStatus()) {
                            getView().onZjqdXqSendSuccess(entity);
                        }
                        else {
                            getView().onZjqdXqSendSuccess(entity);
                        }

                    }

                    @Override
                    public void onErrorImpl(String errorMessage) {
                        Logger.e("setQszXqFl" + errorMessage);
                        ToastUtil.showToast("网络开小差了");
                        getView().onZjqdXqFiled();
                    }
                });
    }

    //绑定邮箱
    public void setBdEmail(HashMap<String,String> map){
        HttpUtil.getInstance().getApiService().getBdEmail(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(bindLifecycle())
                .subscribe(new BaseRxObserver<BdEmailBean>() {
                    @Override
                    public void onNextImpl(BdEmailBean entity) {
                        if (AppConstant.STATUS_SUCCESS == entity.getStatus()) {
                            getView().onBdEmailSuccess(entity);
                        }else{
                            getView().onBdEmailSuccess(entity);
                        }

                    }
                    @Override
                    public void onErrorImpl(String errorMessage) {
                        Logger.e("setBdEmail" + errorMessage);
                        ToastUtil.showToast("网络开小差了");
//                        getView().showTimeout();
                        getView().onZjqdXqFiled();
                    }
                });

    }
}
