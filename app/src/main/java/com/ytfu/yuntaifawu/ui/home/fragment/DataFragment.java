package com.ytfu.yuntaifawu.ui.home.fragment;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.ytfu.yuntaifawu.R;
import com.ytfu.yuntaifawu.app.App;
import com.ytfu.yuntaifawu.app.AppConstant;
import com.ytfu.yuntaifawu.base.BaseFragment;
import com.ytfu.yuntaifawu.helper.RefreshLayoutHelper;
import com.ytfu.yuntaifawu.ui.home.adaper.DataAdaper;
import com.ytfu.yuntaifawu.ui.home.bean.AudioListBean;
import com.ytfu.yuntaifawu.ui.home.bean.AudioTopImageBean;
import com.ytfu.yuntaifawu.ui.home.presenter.NavListPresenter;
import com.ytfu.yuntaifawu.ui.home.view.INavListView;
import com.ytfu.yuntaifawu.utils.CommonUtil;
import com.ytfu.yuntaifawu.utils.MessageEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * @Auther gxy
 * @Date 2019/11/13
 * @Des 音频列表fragment
 */
public class DataFragment extends BaseFragment<INavListView, NavListPresenter> implements INavListView {
    private SmartRefreshLayout refreshLayout;
    private RecyclerView recyclerView;
    private int indicator_tag;
    private String song_id;
    private DataAdaper dataAdaper;
    private int currentIndex = -1;
    private int page = -1;
    private String message;

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_data;
    }

    @Override
    protected NavListPresenter createPresenter() {
        return new NavListPresenter(getContext());
    }

    @Override
    public void onResume() {
        super.onResume();
        if (null != getArguments()) {
            indicator_tag = getArguments().getInt("indicator_tag");
            song_id = getArguments().getString("song_id");
        }
        if (currentIndex != indicator_tag) {
            setData();
        }
    }

    private void setData() {
        page = 1;
        getPresenter().getNavList(song_id, page, message);
    }

    private void setDatas() {
        page++;
        getPresenter().getNavList(song_id, page, message);
    }

    @Override
    protected void initView(View rootView) {
        EventBus.getDefault().register(this);
        refreshLayout = rootView.findViewById(R.id.refresh_layout);
        recyclerView = rootView.findViewById(R.id.recyele_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        refreshLayout.setEnableLoadMore(false);
        refreshLayout.setRefreshHeader(new MaterialHeader(App.getContext()).setColorSchemeResources(R.color.primaryColor, R.color.blue_3C, R.color.green_59));
        refreshLayout.setRefreshFooter(new BallPulseFooter(App.getContext()).setAnimatingColor(CommonUtil.getColor(R.color.primaryColor)));
    }

    @Override
    protected void initData() {
        dataAdaper = new DataAdaper(getContext());
        recyclerView.setAdapter(dataAdaper);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                setData();
                refreshLayout.finishRefresh();
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                setDatas();
                refreshLayout.finishLoadMore();      //加载完成
                refreshLayout.finishLoadMoreWithNoMoreData();
            }
        });
    }

    @Override
    public void onNavListSuccess(AudioListBean beanList) {
        hideLoading();
        if (null != beanList || beanList !=null || !beanList.getList().isEmpty()) {
            if (page == 1) {
                dataAdaper.setList(beanList.getList());
            } else {
                dataAdaper.addList(beanList.getList());
            }
        }
        RefreshLayoutHelper.getInstance().setLoadedState(beanList.getList(), page, refreshLayout, this);
    }

    @Override
    public void onNavListFiled() {

    }

    @Override
    public void onNavImageSuccess(AudioTopImageBean beanList) {
        hideLoading();
    }

    //接收事件
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void DataEvent(MessageEvent eventMessage) {
        switch (eventMessage.getWhat()) {
            case AppConstant.EVENT_BUS_DATAFRAGMENT:
                message = eventMessage.getMessage();
                page = 1;
                getPresenter().getNavList(song_id, page, message);
                break;
            default:
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
