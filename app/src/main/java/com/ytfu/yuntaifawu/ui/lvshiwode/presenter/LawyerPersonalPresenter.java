package com.ytfu.yuntaifawu.ui.lvshiwode.presenter;

import com.ytfu.yuntaifawu.apis.HttpUtil;
import com.ytfu.yuntaifawu.apis.LawyerService;
import com.ytfu.yuntaifawu.base.BasePresenter;
import com.ytfu.yuntaifawu.helper.BaseRxObserver;
import com.ytfu.yuntaifawu.helper.RxLifecycleUtil;
import com.ytfu.yuntaifawu.ui.lvshiwode.view.LawyerPersonalView;
import com.ytfu.yuntaifawu.ui.mine.bean.MineBean;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 律师我的界面的P层
 */
public class LawyerPersonalPresenter extends BasePresenter<LawyerPersonalView> {

    /**
     * 律师获取个人数据
     */
    public void requestPersonal(String uid) {
        HttpUtil.getInstance().getService(LawyerService.class)
                .getPersonalData(uid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(bindLifecycle())
                .subscribe(new BaseRxObserver<MineBean>() {


                    @Override
                    public void onNextImpl(MineBean mineBean) {
                        if (null == mineBean) {
                            getView().onGetPersonalFail("获取数据失败");
                            return;
                        }
                        int status = mineBean.getStatus();
                        if (status != 200) {
                            getView().onGetPersonalFail("获取数据失败");
                            return;
                        }
                        if (null == mineBean.getFind()) {
                            getView().onGetPersonalFail("解析数据失败");
                            return;
                        }
                        getView().onGetPersonalSuccess(mineBean);
                    }

                    @Override
                    public void onErrorImpl(String errorMessage) {
                        getView().onGetPersonalFail(errorMessage);
                    }
                });

    }
}
