package com.ytfu.yuntaifawu.ui.kaitingzhushou.presenter;

import android.content.Context;

import com.orhanobut.logger.Logger;
import com.ytfu.yuntaifawu.apis.HttpUtil;
import com.ytfu.yuntaifawu.app.AppConstant;
import com.ytfu.yuntaifawu.base.BasePresenter;
import com.ytfu.yuntaifawu.helper.BaseRxObserver;
import com.ytfu.yuntaifawu.ui.kaitingzhushou.bean.KtzsListBean;
import com.ytfu.yuntaifawu.ui.kaitingzhushou.bean.SelectZhuShouBean;
import com.ytfu.yuntaifawu.ui.kaitingzhushou.view.IKtzsListView;
import com.ytfu.yuntaifawu.ui.mine.bean.BdEmailBean;
import com.ytfu.yuntaifawu.ui.mine.bean.MyLibraryBean;
import com.ytfu.yuntaifawu.utils.ToastUtil;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class KtzsListPresenter extends BasePresenter<IKtzsListView> {
    private Context mContext;

    public KtzsListPresenter(Context mContext) {
        this.mContext = mContext;
    }

    public void setKtzsList(HashMap<String,String> map){
        HttpUtil.getInstance().getApiService().getKtzs(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(bindLifecycle())
                .subscribe(new BaseRxObserver<KtzsListBean>() {
                    @Override
                    public void onNextImpl(KtzsListBean entity) {
                        if (AppConstant.CODE_SUCCESS == entity.getStatus()) {
                            getView().onSuccess(entity);
                        }else{
                            getView().onSuccess(null);
                        }

                    }
                    @Override
                    public void onErrorImpl(String errorMessage) {
                        Logger.e("setKtzsList" + errorMessage);
//                        ToastUtil.showToast("网络开小差了");
                        getView().showTimeout();
                        getView().onFiled(errorMessage);
                    }
                });

    }
}
