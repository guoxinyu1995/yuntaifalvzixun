package com.ytfu.yuntaifawu.base;


import androidx.lifecycle.LifecycleOwner;

import com.github.lee.mvp.base.BasicPresenter;
import com.github.lee.mvp.base.BasicView;
import com.uber.autodispose.AutoDisposeConverter;
import com.ytfu.yuntaifawu.apis.HttpUtil;
import com.ytfu.yuntaifawu.helper.BaseRxObserver;
import com.ytfu.yuntaifawu.helper.RxLifecycleUtil;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author lqm
 * desc BasePresenter
 */

public abstract class BasePresenter<V extends BasicView> extends BasicPresenter<V> {

    private LifecycleOwner mOwner;


    public void attachLifecycle(LifecycleOwner owner) {
        mOwner = owner;
    }

    protected LifecycleOwner getLifecycle() {
        return mOwner;
    }

    protected <T> AutoDisposeConverter<T> bindLifecycle() {
        return RxLifecycleUtil.bindLifecycle(getLifecycle());
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
