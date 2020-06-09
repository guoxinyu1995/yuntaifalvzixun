package com.ytfu.yuntaifawu.base;

public class BaseRefreshPresenter<V extends BaseRefreshView<?>> extends BasePresenter<V> {

    /**
     * 刷新
     */
    public void refresh() {
        getView().showRefresh();
        getView().onRefresh();
    }

    /**
     * 加载更多
     */
    public void loadMore() {
        getView().onLoadMore();

    }
}
