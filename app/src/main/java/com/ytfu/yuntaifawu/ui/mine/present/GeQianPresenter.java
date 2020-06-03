package com.ytfu.yuntaifawu.ui.mine.present;

import android.content.Context;

import com.orhanobut.logger.Logger;
import com.ytfu.yuntaifawu.apis.HttpUtil;
import com.ytfu.yuntaifawu.app.AppConstant;
import com.ytfu.yuntaifawu.base.BasePresenter;
import com.ytfu.yuntaifawu.helper.BaseRxObserver;
import com.ytfu.yuntaifawu.ui.mine.bean.GeQianBean;
import com.ytfu.yuntaifawu.ui.mine.bean.SetNameBean;
import com.ytfu.yuntaifawu.ui.mine.view.IGeQianView;
import com.ytfu.yuntaifawu.utils.ToastUtil;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
*
*  @Auther  gxy
*
*  @Date    2020/1/6
*
*  @Des  个性签名presenter
*
*/
public class GeQianPresenter extends BasePresenter<IGeQianView> {
    private Context mContext;

    public GeQianPresenter(Context mContext) {
        this.mContext = mContext;
    }

    public void setGeQian(HashMap<String,String> map){
        HttpUtil.getInstance().getApiService().getGeQian(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(bindLifecycle())
                .subscribe(new BaseRxObserver<GeQianBean>() {
                    @Override
                    public void onNextImpl(GeQianBean entity) {
                        if (AppConstant.CODE_SUCCESS == entity.getStatus()) {
                            getView().onGqSuccess(entity);
                        } else {
                            getView().onGqSuccess(entity);
                        }

                    }

                    @Override
                    public void onErrorImpl(String errorMessage) {
                        Logger.e("setGeQian" + errorMessage);
                        ToastUtil.showToast("网络开小差了");
                        getView().onGqFiled();
                    }
                });
    }
}
