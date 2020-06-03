package com.ytfu.yuntaifawu.ui.mine.present;

import android.content.Context;

import com.orhanobut.logger.Logger;
import com.ytfu.yuntaifawu.apis.HttpUtil;
import com.ytfu.yuntaifawu.app.AppConstant;
import com.ytfu.yuntaifawu.base.BasePresenter;
import com.ytfu.yuntaifawu.helper.BaseRxObserver;
import com.ytfu.yuntaifawu.ui.mine.bean.SetPwdBean;
import com.ytfu.yuntaifawu.ui.mine.bean.ShenHeJInduBean;
import com.ytfu.yuntaifawu.ui.mine.view.IShenHeJinDuView;
import com.ytfu.yuntaifawu.utils.ToastUtil;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @Auther gxy
 * @Date 2020/5/21
 * @Des 审核进度presenter
 */
public class ShengheJinduPresenter extends BasePresenter<IShenHeJinDuView> {
    private Context mContext;

    public ShengheJinduPresenter(Context mContext) {
        this.mContext = mContext;
    }


    public void getShengHeJIndu(HashMap<String, String> map) {
        HttpUtil.getInstance().getApiService().setShengHeJindu(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(bindLifecycle())
                .subscribe(new BaseRxObserver<ShenHeJInduBean>() {
                    @Override
                    public void onNextImpl(ShenHeJInduBean entity) {
                        if (AppConstant.CODE_SUCCESS == entity.getStatus()) {
                            getView().onShenHeJinSuccess(entity);
                        } else {
                            getView().onShenHeJinSuccess(entity);
                        }

                    }

                    @Override
                    public void onErrorImpl(String errorMessage) {
                        Logger.e("setName" + errorMessage);
                        ToastUtil.showToast("网络开小差了");
                        getView().onShengheJinduFiled();
                    }
                });
    }
}
