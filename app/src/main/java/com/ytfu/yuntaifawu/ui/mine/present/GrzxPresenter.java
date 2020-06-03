package com.ytfu.yuntaifawu.ui.mine.present;

import android.content.Context;

import com.orhanobut.logger.Logger;
import com.ytfu.yuntaifawu.apis.HttpUtil;
import com.ytfu.yuntaifawu.app.AppConstant;
import com.ytfu.yuntaifawu.base.BasePresenter;
import com.ytfu.yuntaifawu.helper.BaseRxObserver;
import com.ytfu.yuntaifawu.helper.RxLifecycleUtil;
import com.ytfu.yuntaifawu.ui.mine.bean.InformationBean;
import com.ytfu.yuntaifawu.ui.mine.bean.PictureUploadBean;
import com.ytfu.yuntaifawu.ui.mine.bean.SetBirthdayBean;
import com.ytfu.yuntaifawu.ui.mine.view.IGrzxView;
import com.ytfu.yuntaifawu.utils.ToastUtil;

import org.openxmlformats.schemas.spreadsheetml.x2006.main.STRef;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * @Auther gxy
 * @Date 2020/1/3
 * @Des 个人中心presenter
 */
public class GrzxPresenter extends BasePresenter<IGrzxView> {
    private Context mContext;

    public GrzxPresenter(Context mContext) {
        this.mContext = mContext;
    }

    public void setGrzx(HashMap<String, String> map) {
        HttpUtil.getInstance().getApiService().getInformation(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(bindLifecycle())
                .subscribe(new BaseRxObserver<InformationBean>() {
                    @Override
                    public void onNextImpl(InformationBean entity) {
                        if (AppConstant.CODE_SUCCESS == entity.getStatus()) {
                            getView().onGrzxSuccess(entity);
                        } else {
                            getView().onGrzxSuccess(entity);
                        }

                    }

                    @Override
                    public void onErrorImpl(String errorMessage) {
                        Logger.e("setGrzx" + errorMessage);
                        ToastUtil.showToast("网络开小差了");
                        getView().onFiled();
                    }
                });
    }
    //修改生日
    public void setBirthday(HashMap<String, String> map){
        HttpUtil.getInstance().getApiService().getBirthday(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(bindLifecycle())
                .subscribe(new BaseRxObserver<SetBirthdayBean>() {
                    @Override
                    public void onNextImpl(SetBirthdayBean entity) {
                        if (AppConstant.CODE_SUCCESS == entity.getStatus()) {
                            getView().onBirthdaySuccess(entity);
                        } else {
                            getView().onBirthdaySuccess(entity);
                        }

                    }

                    @Override
                    public void onErrorImpl(String errorMessage) {
                        Logger.e("setBirthday" + errorMessage);
                        ToastUtil.showToast("网络开小差了");
                        getView().onFiled();
                    }
                });
    }

    //头像上传
    public void upLoadImage(RequestBody uid, MultipartBody.Part body) {
        HttpUtil.getInstance().getApiService().getUpLoadImage(uid, body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(bindLifecycle())
                .subscribe(new BaseRxObserver<PictureUploadBean>() {
                    @Override
                    public void onNextImpl(PictureUploadBean entity) {
                        if (AppConstant.CODE_SUCCESS == entity.getStatus()) {
                            getView().onPictureUpLoadSuccess(entity);
                        } else {
                            getView().onPictureUpLoadSuccess(entity);
                        }

                    }

                    @Override
                    public void onErrorImpl(String errorMessage) {
                        Logger.e("upLoadImage" + errorMessage);
                        ToastUtil.showToast("网络开小差了");
//                        getView().showTimeout();
                        getView().onFiled();
                    }
                });
    }
}
