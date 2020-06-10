package com.ytfu.yuntaifawu.base;

import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.LayoutRes;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.github.lee.annotation.InjectLayout;
import com.ytfu.yuntaifawu.R;
import com.ytfu.yuntaifawu.utils.CommonUtil;
import com.ytfu.yuntaifawu.utils.ItemDecoration;

import java.util.List;

import butterknife.BindView;
import io.reactivex.annotations.Nullable;


@InjectLayout(R.layout.fragment_base_recycler)
public abstract class BaseRecyclerViewFragment
        <T, V extends BaseRefreshView<T>, P extends BaseRefreshPresenter<V>>
        extends BaseFragment<V, P> implements BaseRefreshView<T> {
    /**
     * 分页使用,page初始化0  开始页面自己设置
     */
    protected int currentPage = 0;
    protected int pageSize = 10;

    protected BaseQuickAdapter<T, BaseViewHolder> mAdapter;
    /**
     * 进入页面时候默认进行刷新
     */
    protected boolean autoRefresh = true;

    @BindView(R.id.srl_refresh_refresh)
    SwipeRefreshLayout srl_refresh_refresh;
    @BindView(R.id.rv_refresh_content)
    RecyclerView rv_refresh_content;
    @BindView(R.id.fl_refresh_top_suspension)
    FrameLayout fl_refresh_top_suspension;//顶部悬浮容器
    @BindView(R.id.fl_refresh_bottom_suspension)
    FrameLayout fl_refresh_bottom_suspension;//底部悬浮容器


    @Override
    public void init() {
        super.init();
        mAdapter = createAdapter();
    }

    @Override
    protected void initView(View rootView) {
        super.initView(rootView);
        hideTopSuspensionView();
        hideBottomSuspensionView();
    }

    @Override
    protected void setViewListener(View rootView) {
        super.setViewListener(rootView);
        srl_refresh_refresh.setOnRefreshListener(() -> getPresenter().refresh());
        mAdapter.getLoadMoreModule().setOnLoadMoreListener(() -> getPresenter().loadMore());
    }

    @Override
    protected void initData() {
        super.initData();
        //创建LayoutManager
        RecyclerView.LayoutManager lm = createLayoutManager();
        if (null == lm) {
            lm = new LinearLayoutManager(mContext);
        }
        rv_refresh_content.setLayoutManager(lm);
        //设置adapter
        rv_refresh_content.setAdapter(mAdapter);

        //设置刷新初始化颜色
        srl_refresh_refresh.setColorSchemeColors(
                CommonUtil.generateBeautifulColor(),
                CommonUtil.generateBeautifulColor(),
                CommonUtil.generateBeautifulColor(),
                CommonUtil.generateBeautifulColor()
        );
        //默认初始化进行刷新操作
        if (autoRefresh) {
            srl_refresh_refresh.setRefreshing(true);
            getPresenter().refresh();
        }
        hideLoading();
    }

    ///////////////////////////////////////////////////////////////////////////
    //
    ///////////////////////////////////////////////////////////////////////////

    /**
     * 子类创建adapter
     */
    protected abstract BaseQuickAdapter<T, BaseViewHolder> createAdapter();


    protected void addItemDecoration(ItemDecoration itemDecoration) {
        rv_refresh_content.addItemDecoration(itemDecoration);
    }

    /**
     * 添加顶部悬浮容器
     */
    protected void addTopSuspensionView(View view) {
        fl_refresh_top_suspension.removeAllViews();
        fl_refresh_top_suspension.addView(view);
        fl_refresh_top_suspension.setVisibility(View.VISIBLE);
    }

    /*
     * 获取列表控件
     * */
    protected RecyclerView getRecycleView() {
        return rv_refresh_content;
    }
    /*
     * 获取列表控件
     * */
    protected SwipeRefreshLayout getSwipeRefreshLayout() {
        return srl_refresh_refresh;
    }

    /**
     * 隐藏顶部悬浮容器
     */
    protected void hideTopSuspensionView() {
        fl_refresh_top_suspension.removeAllViews();
        fl_refresh_top_suspension.setVisibility(View.GONE);
    }

    /**
     * 隐藏底部悬浮容器
     */
    protected void hideBottomSuspensionView() {
        fl_refresh_bottom_suspension.removeAllViews();
        fl_refresh_bottom_suspension.setVisibility(View.GONE);
    }

    /**
     * 添加底部悬浮容器
     */
    protected void addBottomSuspensionView(View view) {
        fl_refresh_bottom_suspension.removeAllViews();
        fl_refresh_bottom_suspension.addView(view);
        fl_refresh_bottom_suspension.setVisibility(View.VISIBLE);
    }

    ///////////////////////////////////////////////////////////////////////////
    //
    ///////////////////////////////////////////////////////////////////////////
    @Nullable
    protected RecyclerView.LayoutManager createLayoutManager() {
        return null;
    }

    @Override
    public void enableRefresh(boolean enable) {
        srl_refresh_refresh.setEnabled(enable);
    }

    @Override
    public void enableLoadMore(boolean enable) {
        mAdapter.getLoadMoreModule().setEnableLoadMore(enable);
    }

    @Override
    public void addData(T data) {
        mAdapter.addData(data);
    }

    @Override
    public void addData(List<T> data) {
        mAdapter.addData(data);
    }

    @Override
    public void setNewData(List<T> data) {
        mAdapter.setNewInstance(data);
    }

    @Override
    public void setEmptyView(@LayoutRes int layoutId) {
        mAdapter.setEmptyView(layoutId);
    }

    @Override
    public void setEmptyView(View emptyView) {
        mAdapter.setEmptyView(emptyView);
    }

    @Override
    public BaseQuickAdapter<T, ?> getAdapter() {
        return mAdapter;
    }

    @Override
    public void addHeaderView(View view) {
        mAdapter.addHeaderView(view);
    }

    @Override
    public void addHeaderView(View view, int index) {
        mAdapter.addHeaderView(view, index);
    }

    @Override
    public void addHeaderView(View view, int index, int orientation) {
        mAdapter.addHeaderView(view, index, orientation);
    }

    @Override
    public void removeHeader(View header) {
        getAdapter().removeHeaderView(header);
    }

    @Override
    public void removeAllHeader() {
        getAdapter().removeAllHeaderView();
    }

    @Override
    public void addFooterView(View view) {
        getAdapter().addFooterView(view);
    }

    @Override
    public void addFooterView(View view, int index) {
        getAdapter().addFooterView(view, index);
    }

    @Override
    public void addFooterView(View view, int index, int orientation) {
        getAdapter().addFooterView(view, index, orientation);
    }

    @Override
    public void removeFooter(View footer) {
        getAdapter().removeFooterView(footer);
    }

    @Override
    public void removeAllFooter() {
        getAdapter().removeAllFooterView();
    }

    @Override
    public int getCurrentPage() {
        return currentPage;
    }

    @Override
    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    @Override
    public void resetCurrentPage(boolean isLoadMore, int defaultPage) {
        if (isLoadMore) {
            currentPage--;
            if (currentPage < defaultPage) {
                currentPage = defaultPage;
            }
        } else {
            currentPage = defaultPage;
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    //
    ///////////////////////////////////////////////////////////////////////////

    @Override
    public void showRefresh() {
        srl_refresh_refresh.setRefreshing(true);
    }

    @Override
    public void loadMoreComplete() {
        mAdapter.getLoadMoreModule().loadMoreComplete();
    }

    @Override
    public void loadMoreEnd(boolean gone) {
        mAdapter.getLoadMoreModule().loadMoreEnd(gone);
    }

    @Override
    public void loadMoreFail() {
        mAdapter.getLoadMoreModule().loadMoreFail();
    }

    @Override
    public void stopRefresh() {
        srl_refresh_refresh.setRefreshing(false);
        srl_refresh_refresh.setColorSchemeColors(
                CommonUtil.generateBeautifulColor(),
                CommonUtil.generateBeautifulColor(),
                CommonUtil.generateBeautifulColor(),
                CommonUtil.generateBeautifulColor()
        );
    }

    ///////////////////////////////////////////////////////////////////////////
    //
    ///////////////////////////////////////////////////////////////////////////


    @Override
    public void onRefresh() {
        onLoadMoreOrRefresh(false);
    }

    @Override
    public void onLoadMore() {
        onLoadMoreOrRefresh(true);
    }

    /**
     * 子类重写该方法进行刷新或者加载更多操作
     */
    protected abstract void onLoadMoreOrRefresh(boolean isLoadMore);
}
