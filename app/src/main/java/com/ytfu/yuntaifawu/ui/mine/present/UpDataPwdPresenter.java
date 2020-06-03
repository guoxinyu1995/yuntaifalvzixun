package com.ytfu.yuntaifawu.ui.mine.present;

import android.content.Context;

import com.orhanobut.logger.Logger;
import com.ytfu.yuntaifawu.apis.HttpUtil;
import com.ytfu.yuntaifawu.app.AppConstant;
import com.ytfu.yuntaifawu.base.BasePresenter;
import com.ytfu.yuntaifawu.helper.BaseRxObserver;
import com.ytfu.yuntaifawu.ui.mine.bean.SetEmailBean;
import com.ytfu.yuntaifawu.ui.mine.bean.UpdataPwdBean;
import com.ytfu.yuntaifawu.ui.mine.view.IUpdataPwdView;
import com.ytfu.yuntaifawu.utils.ToastUtil;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @Auther gxy
 * @Date 2020/1/6
 * @Des 修改密码presenter
 */
public class UpDataPwdPresenter extends BasePresenter<IUpdataPwdView> {
    private Context mContext;

    public UpDataPwdPresenter(Context mContext) {
        this.mContext = mContext;
    }

    public void setUpdataPwd(HashMap<String,String> map){
        HttpUtil.getInstance().getApiService().getUpDataPwd(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(bindLifecycle())
                .subscribe(new BaseRxObserver<UpdataPwdBean>() {
                    @Override
                    public void onNextImpl(UpdataPwdBean entity) {
                        if (AppConstant.CODE_SUCCESS == entity.getStatus()) {
                            getView().onUpDataSuccess(entity);
                        } else {
                            getView().onUpDataSuccess(entity);
                        }

                    }

                    @Override
                    public void onErrorImpl(String errorMessage) {
                        Logger.e("setEmail" + errorMessage);
                        ToastUtil.showToast("网络开小差了");
                        getView().onUpDataFiled();
                    }
                });
    }
}
