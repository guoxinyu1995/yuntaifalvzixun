package com.ytfu.yuntaifawu.helper;


import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 *
 * @作者  gxy
 *
 * @创建时间  2019/11/9
 *
 * @描述  自己的Observer，减少实现不必要的回调
 */
public abstract class BaseRxObserver<T> implements Observer<T> {

    @Override
    public void onSubscribe(Disposable d) {
        onSubscribeImpl(d);
    }

    @Override
    public void onNext(T t) {
        onNextImpl(t);
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        onErrorImpl(e.getMessage());
    }

    @Override
    public void onComplete() {
        onCompleteImpl();
    }

    public void onSubscribeImpl(Disposable d) {

    }

    public void onCompleteImpl() {

    }

    /**
     * onNext 抽象方法，必须实现
     *
     * @param data 接口返回数据
     */
    public abstract void onNextImpl(T data);

    /**
     * onError
     *
     * @param errorMessage 错误信息
     */
    public abstract void onErrorImpl(String errorMessage);

}