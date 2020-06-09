package com.github.lee.mvp.base;


import java.lang.ref.WeakReference;


/**
 * @author lqm
 * desc BasePresenter
 */

public abstract class BasicPresenter<V extends BasicView> {

    private WeakReference<V> mViewRef;

    public void attachView(V view) {
        mViewRef = new WeakReference<V>(view);
    }


    protected V getView() {
        return mViewRef.get();
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
}
