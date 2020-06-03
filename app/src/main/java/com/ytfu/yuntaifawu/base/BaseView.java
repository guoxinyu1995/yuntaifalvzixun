package com.ytfu.yuntaifawu.base;

/**
 * @作者 gxy
 * @创建时间 2019/11/9
 * @描述
 */
public interface BaseView {

    void showToast(String msg);

    void showLoading();

    void hideLoading();

    void showProgress();

    void hideProgress();

    void showEmpty();

    void showTimeout();

    void showError();

}
