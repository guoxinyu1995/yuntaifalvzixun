package com.ytfu.yuntaifawu.ui.home.presenter;

import android.content.Context;

import com.orhanobut.logger.Logger;
import com.ytfu.yuntaifawu.apis.HttpUtil;
import com.ytfu.yuntaifawu.app.AppConstant;
import com.ytfu.yuntaifawu.base.BasePresenter;
import com.ytfu.yuntaifawu.helper.BaseRxObserver;
import com.ytfu.yuntaifawu.ui.lvshihetong.bean.ContractDatalistBean;
import com.ytfu.yuntaifawu.ui.home.view.IContractDataListView;
import com.ytfu.yuntaifawu.utils.ToastUtil;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ContractDataListPresenter extends BasePresenter<IContractDataListView> {
    private Context mContext;

    public ContractDataListPresenter(Context mContext) {
        this.mContext = mContext;
    }

    public void getDataList(HashMap<String,String> map){
        HttpUtil.getInstance().getApiService().getDataList(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(bindLifecycle())
                .subscribe(new BaseRxObserver<ContractDatalistBean>() {
                    @Override
                    public void onNextImpl(ContractDatalistBean detailsBean) {
                        if(AppConstant.CODE_SUCCESS == detailsBean.getStatus()){
                            getView().onDataListSuccess(detailsBean);
                        }else{
                            getView().onDataListSuccess(null);
                        }
                    }

                    @Override
                    public void onErrorImpl(String errorMessage) {
                        Logger.e("getDataList" + errorMessage);
                        ToastUtil.showToast("网络开小差了");
                        getView().onFiled();
                    }
                });
    }
}
