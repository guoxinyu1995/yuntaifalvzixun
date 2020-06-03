package com.ytfu.yuntaifawu.ui.lvshiwenti.presenter;

import android.content.Context;

import com.orhanobut.logger.Logger;
import com.ytfu.yuntaifawu.apis.HttpUtil;
import com.ytfu.yuntaifawu.base.BasePresenter;
import com.ytfu.yuntaifawu.helper.BaseRxObserver;
import com.ytfu.yuntaifawu.ui.lvshiwenti.bean.LvShiZiXunListBean;
import com.ytfu.yuntaifawu.ui.lvshiwenti.view.IlvshiZiXunListView;
import com.ytfu.yuntaifawu.ui.mine.bean.MineZiXunBean;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
*
*  @Auther  gxy
*
*  @Date    2020/5/25
*
*  @Des 律师端 问题列表p
*
*/
public class LvShiZixunPresenter extends BasePresenter<IlvshiZiXunListView> {
    private Context mContext;

    public LvShiZixunPresenter(Context mContext) {
        this.mContext = mContext;
    }

    public void getLvShiZiXunList(int page){
        HttpUtil.getInstance().getApiService().setLvShiZiXunList(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(bindLifecycle())
                .subscribe(new BaseRxObserver<LvShiZiXunListBean>() {
                    @Override
                    public void onNextImpl(LvShiZiXunListBean lvShiZiXunListBean) {
                        if (lvShiZiXunListBean.getStatus() == 1) {
                            getView().onLvShiListSuccess(lvShiZiXunListBean);
                        } else {
                            getView().onLvShiListSuccess(lvShiZiXunListBean);
                        }
                    }

                    @Override
                    public void onErrorImpl(String errorMessage) {
                        Logger.e("getMineZiXunList" + errorMessage);
                        getView().onLvShiListFiled(errorMessage);
                    }
                });
    }
}
