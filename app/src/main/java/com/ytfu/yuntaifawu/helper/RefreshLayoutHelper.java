package com.ytfu.yuntaifawu.helper;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.ytfu.yuntaifawu.app.AppConstant;
import com.ytfu.yuntaifawu.base.BaseActivity;
import com.ytfu.yuntaifawu.base.BaseFragment;
import com.ytfu.yuntaifawu.utils.ToastUtil;

import java.util.List;

/**
 *
 * @作者  gxy
 *
 * @创建时间  2019/11/9
 *
 * @描述
 */
public class RefreshLayoutHelper {

    private static RefreshLayoutHelper mInstance;

    public static RefreshLayoutHelper getInstance() {
        if (mInstance == null) {
            synchronized (RefreshLayoutHelper.class) {
                if (mInstance == null) {
                    mInstance = new RefreshLayoutHelper();
                }
            }
        }
        return mInstance;
    }

    private RefreshLayoutHelper() {

    }

    /**
     * 设置RefreshLayout刷新/加载状态
     *
     * @param list          数据源
     * @param page          page
     * @param refreshLayout RefreshLayout
     * @param activity      当前继承了BaseActivity的子Activity
     * @param showEmpty     是否显示空页面
     */
    public void setLoadedState(List<?> list, int page, RefreshLayout refreshLayout, BaseActivity activity, boolean showEmpty) {
        this.setLoadedState(list, page, refreshLayout, activity, null, showEmpty);
    }

    /**
     * 设置RefreshLayout刷新/加载状态
     *
     * @param list          数据源
     * @param page          page
     * @param refreshLayout RefreshLayout
     * @param activity      当前继承了BaseActivity的子Activity
     */
    public void setLoadedState(List<?> list, int page, RefreshLayout refreshLayout, BaseActivity activity) {
        this.setLoadedState(list, page, refreshLayout, activity, null, true);
    }

    /**
     * 设置RefreshLayout刷新/加载状态
     *
     * @param list          数据源
     * @param page          page
     * @param refreshLayout RefreshLayout
     * @param fragment      当前继承了BaseFragment的子Fragment
     * @param showEmpty     是否显示空页面
     */
    public void setLoadedState(List<?> list, int page, RefreshLayout refreshLayout, BaseFragment fragment, boolean showEmpty) {
        this.setLoadedState(list, page, refreshLayout, null, fragment, showEmpty);
    }

    /**
     * 设置RefreshLayout刷新/加载状态
     *
     * @param list          数据源
     * @param page          page
     * @param refreshLayout RefreshLayout
     * @param fragment      当前继承了BaseFragment的子Fragment
     */
    public void setLoadedState(List<?> list, int page, RefreshLayout refreshLayout, BaseFragment fragment) {
        this.setLoadedState(list, page, refreshLayout, null, fragment, true);
    }

    /**
     * 设置RefreshLayout刷新/加载状态
     *
     * @param list          数据源
     * @param page          page
     * @param refreshLayout RefreshLayout
     * @param activity      当前继承了BaseActivity的子Activity
     * @param fragment      当前继承了BaseFragment的子Fragment
     * @param showEmpty     是否显示空页面
     */
    private void setLoadedState(List<?> list, int page, RefreshLayout refreshLayout, BaseActivity activity, BaseFragment fragment, boolean showEmpty) {
        if (null == refreshLayout) {
            return;
        }
        if (null != list && !list.isEmpty()) {
            if (1 == page) {
                // 完成刷新
                refreshLayout.finishRefresh(true);
                if (list.size() < AppConstant.MAXPERPAGE) {
                    // 数据少于1页，禁用加载
                    refreshLayout.setEnableLoadMore(false);
                } else {
                    // 启用加载
                    refreshLayout.setEnableLoadMore(true);
                }
                if (null != activity) {
                    activity.hideLoading();
                } else if (null != fragment) {
                    fragment.hideLoading();
                }
            } else {
                if (list.size() < AppConstant.MAXPERPAGE) {
                    // 数据少于1页
                    // 完成并禁用加载
                    refreshLayout.finishLoadMoreWithNoMoreData();
                    refreshLayout.setEnableLoadMore(false);
                } else {
                    // 完成加载
                    refreshLayout.finishLoadMore();
                }
            }
        } else {
            if (1 == page) {
                // 第一页数据为空
                // 结束刷新
                refreshLayout.finishRefresh(false);

                if (showEmpty) {
                    // 显示空页面
                    if (null != activity) {
                        activity.showEmpty();
                    } else if (null != fragment) {
                        fragment.showEmpty();
                    }
                }
            } else {
                // 完成加载
                refreshLayout.finishLoadMoreWithNoMoreData();
                ToastUtil.showToast("暂无更多数据");
            }
            refreshLayout.setEnableLoadMore(false);
        }
    }
}
