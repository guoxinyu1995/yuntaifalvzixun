package com.ytfu.yuntaifawu.ui.mine.present;

import android.content.Context;

import com.orhanobut.logger.Logger;
import com.ytfu.yuntaifawu.apis.HttpUtil;
import com.ytfu.yuntaifawu.app.AppConstant;
import com.ytfu.yuntaifawu.base.BasePresenter;
import com.ytfu.yuntaifawu.helper.BaseRxObserver;
import com.ytfu.yuntaifawu.ui.mine.bean.InvitationRecordListBean;
import com.ytfu.yuntaifawu.ui.mine.bean.LvShiRenZhengCommitBean;
import com.ytfu.yuntaifawu.ui.mine.view.ILvShiRenZhengCommitView;
import com.ytfu.yuntaifawu.utils.ToastUtil;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;

/**
 * @Auther gxy
 * @Date 2020/5/22
 * @Des 律师认证presenter
 */
public class LvShiRenZhengCommitPresenter extends BasePresenter<ILvShiRenZhengCommitView> {
    private Context mContext;

    public LvShiRenZhengCommitPresenter(Context mContext) {
        this.mContext = mContext;
    }

    public void getLvShiRenZhengCommit(HashMap<String, RequestBody> map) {
        HttpUtil.getInstance().getApiService().setLvShiRenZhengCommit(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(bindLifecycle())
                .subscribe(new BaseRxObserver<LvShiRenZhengCommitBean>() {
                    @Override
                    public void onNextImpl(LvShiRenZhengCommitBean entity) {

                        getView().onLvShiCommitSuccess(entity);

                    }

                    @Override
                    public void onErrorImpl(String errorMessage) {
                        Logger.e("getNavTitle" + errorMessage);
                        ToastUtil.showToast("网络开小差了");
                        getView().onLvShiCommitFiled();
                    }
                });
    }
}
