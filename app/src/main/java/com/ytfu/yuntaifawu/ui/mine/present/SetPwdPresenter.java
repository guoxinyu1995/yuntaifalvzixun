package com.ytfu.yuntaifawu.ui.mine.present;

import android.content.Context;

import com.orhanobut.logger.Logger;
import com.ytfu.yuntaifawu.apis.HttpUtil;
import com.ytfu.yuntaifawu.app.AppConstant;
import com.ytfu.yuntaifawu.base.BasePresenter;
import com.ytfu.yuntaifawu.helper.BaseRxObserver;
import com.ytfu.yuntaifawu.ui.mine.bean.SetNameBean;
import com.ytfu.yuntaifawu.ui.mine.bean.SetPwdBean;
import com.ytfu.yuntaifawu.ui.mine.view.ISetPwdView;
import com.ytfu.yuntaifawu.utils.ToastUtil;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
*
*  @Auther  gxy
*
*  @Date    2020/1/7
*
*  @Des 设置密码presenter
*
*/
public class SetPwdPresenter extends BasePresenter<ISetPwdView> {
    private Context mContext;

    public SetPwdPresenter(Context mContext) {
        this.mContext = mContext;
    }

    public void setPwd(HashMap<String,String> map){
        HttpUtil.getInstance().getApiService().getSetPwd(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(bindLifecycle())
                .subscribe(new BaseRxObserver<SetPwdBean>() {
                    @Override
                    public void onNextImpl(SetPwdBean entity) {
                        if (AppConstant.CODE_SUCCESS == entity.getStatus()) {
                            getView().onSetPwdSuccess(entity);
                        } else {
                            getView().onSetPwdSuccess(entity);
                        }

                    }

                    @Override
                    public void onErrorImpl(String errorMessage) {
                        Logger.e("setName" + errorMessage);
                        ToastUtil.showToast("网络开小差了");
                        getView().onSetPwdFiled();
                    }
                });
    }
}
