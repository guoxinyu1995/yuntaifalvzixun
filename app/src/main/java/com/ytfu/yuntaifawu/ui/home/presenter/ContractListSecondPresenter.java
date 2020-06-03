package com.ytfu.yuntaifawu.ui.home.presenter;

import android.content.Context;

import com.orhanobut.logger.Logger;
import com.ytfu.yuntaifawu.apis.HttpUtil;
import com.ytfu.yuntaifawu.app.AppConstant;
import com.ytfu.yuntaifawu.base.BasePresenter;
import com.ytfu.yuntaifawu.helper.BaseRxObserver;
import com.ytfu.yuntaifawu.ui.lvshihetong.bean.ContractListSecondBean;
import com.ytfu.yuntaifawu.ui.home.view.IContractListSecondView;
import com.ytfu.yuntaifawu.utils.ToastUtil;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ContractListSecondPresenter extends BasePresenter<IContractListSecondView> {
    private Context mContext;

    public ContractListSecondPresenter(Context mContext) {
        this.mContext = mContext;
    }

    public void secondList(String id){
        HttpUtil.getInstance().getApiService().getListSecond(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(bindLifecycle())
                .subscribe(new BaseRxObserver<ContractListSecondBean>() {
                    @Override
                    public void onNextImpl(ContractListSecondBean resultEntity) {
                        if (AppConstant.CODE_SUCCESS == resultEntity.getStatus()) {
                            getView().onSecondListSuccess(resultEntity);
                        }else{
                            getView().onSecondListSuccess(null);
                        }
                    }

                    @Override
                    public void onErrorImpl(String errorMessage) {
                        Logger.e("secondList" + errorMessage);
                        ToastUtil.showToast("网络错误");
                        getView().onFiled();
                    }
                });
    }
}
