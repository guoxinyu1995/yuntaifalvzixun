package com.ytfu.yuntaifawu.ui.lawyer.setting.p;

import com.ytfu.yuntaifawu.apis.HttpUtil;
import com.ytfu.yuntaifawu.apis.LawyerService;
import com.ytfu.yuntaifawu.base.BasePresenter;
import com.ytfu.yuntaifawu.helper.BaseRxObserver;
import com.ytfu.yuntaifawu.ui.lawyer.setting.v.LawyerSettingView;
import com.ytfu.yuntaifawu.ui.mine.bean.InformationBean;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class LawyerSettingPresenter extends BasePresenter<LawyerSettingView> {

    public void getInformation(String uid) {
        HttpUtil.getInstance().getService(LawyerService.class)
                .getInformation(uid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(bindLifecycle())
                .subscribe(new BaseRxObserver<InformationBean>() {

                    @Override
                    public void onNextImpl(InformationBean informationBean) {
                        if (null == informationBean) {
                            getView().onGetInformationFail("获取数据失败");
                            return;
                        }
                        int status = informationBean.getStatus();
                        if (status != 1) {
                            getView().onGetInformationFail("获取数据失败");
                            return;
                        }
                        getView().onGetInformationSuccess(informationBean);
                    }

                    @Override
                    public void onErrorImpl(String errorMessage) {
                        getView().onGetInformationFail(errorMessage);
                    }
                });

    }

}
