package com.ytfu.yuntaifawu.ui.mine.present;

import android.content.Context;

import com.orhanobut.logger.Logger;
import com.ytfu.yuntaifawu.apis.HttpUtil;
import com.ytfu.yuntaifawu.app.AppConstant;
import com.ytfu.yuntaifawu.base.BasePresenter;
import com.ytfu.yuntaifawu.helper.BaseRxObserver;
import com.ytfu.yuntaifawu.ui.mine.bean.InvitationRecordListBean;
import com.ytfu.yuntaifawu.ui.mine.bean.PurchaseRecordBean;
import com.ytfu.yuntaifawu.ui.mine.view.IInvitionRecordView;
import com.ytfu.yuntaifawu.utils.ToastUtil;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @Auther gxy
 * @Date 2019/11/17
 * @Des 邀请记录presenter
 */
public class InvitionRecordPresenter extends BasePresenter<IInvitionRecordView> {
    private Context mContext;

    public InvitionRecordPresenter(Context mContext) {
        this.mContext = mContext;
    }

    public void invitationRecord(HashMap<String, String> map) {
        HttpUtil.getInstance().getApiService().getInvitationRecord(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(bindLifecycle())
                .subscribe(new BaseRxObserver<InvitationRecordListBean>() {
                    @Override
                    public void onNextImpl(InvitationRecordListBean entity) {
                        if (AppConstant.CODE_SUCCESS == entity.getStatus() && entity.getState() != null) {
                            getView().onInvitionSuccess(entity);
                        } else {
                            getView().onFiled();
                        }

                    }

                    @Override
                    public void onErrorImpl(String errorMessage) {
                        Logger.e("getNavTitle" + errorMessage);
                        ToastUtil.showToast("网络开小差了");
                        getView().onFiled();
                    }
                });
    }
}
