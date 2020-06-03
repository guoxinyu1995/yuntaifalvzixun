package com.ytfu.yuntaifawu.base;


import androidx.lifecycle.LifecycleOwner;

import com.uber.autodispose.AutoDisposeConverter;
import com.ytfu.yuntaifawu.apis.ChatService;
import com.ytfu.yuntaifawu.apis.HttpUtil;
import com.ytfu.yuntaifawu.helper.BaseRxObserver;
import com.ytfu.yuntaifawu.helper.RxLifecycleUtil;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author lqm
 * desc BasePresenter
 */

public abstract class BasePresenter<V> {

    private WeakReference<V> mViewRef;
    private LifecycleOwner mOwner;

    public void attachView(V view) {
        mViewRef = new WeakReference<V>(view);
    }

    public void attachLifecycle(LifecycleOwner owner) {
        mOwner = owner;
    }

    protected V getView() {
        return mViewRef.get();
    }

    protected LifecycleOwner getLifecycle() {
        return mOwner;
    }

    protected <T> AutoDisposeConverter<T> bindLifecycle() {
        return RxLifecycleUtil.bindLifecycle(getLifecycle());
    }

    public boolean isViewAttached() {
        return mViewRef != null && mViewRef.get() != null;
    }

    public void detachView() {
        if (mViewRef != null) {
            mViewRef.clear();
            mViewRef = null;
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    //
    ///////////////////////////////////////////////////////////////////////////

    protected <T> T createService(Class<T> clazz) {
        return HttpUtil.getInstance().getService(clazz);
    }

    /**
     * 请求服务器的方法
     */
    protected <T> void requestRemote(Observable<T> ob, BaseRxObserver<T> cb) {
        ob.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(bindLifecycle())
                .subscribe(cb);
    }

}
