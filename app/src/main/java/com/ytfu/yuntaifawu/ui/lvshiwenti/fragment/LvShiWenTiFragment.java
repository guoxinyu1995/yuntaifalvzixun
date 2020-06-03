package com.ytfu.yuntaifawu.ui.lvshiwenti.fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;

import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.ytfu.yuntaifawu.R;
import com.ytfu.yuntaifawu.app.App;
import com.ytfu.yuntaifawu.base.BaseFragment;
import com.ytfu.yuntaifawu.base.BasePresenter;
import com.ytfu.yuntaifawu.helper.RefreshLayoutHelper;
import com.ytfu.yuntaifawu.ui.lvshiwenti.adaper.LvShiZiXunAdaper;
import com.ytfu.yuntaifawu.ui.lvshiwenti.bean.LvShiZiXunListBean;
import com.ytfu.yuntaifawu.ui.lvshiwenti.presenter.LvShiZixunPresenter;
import com.ytfu.yuntaifawu.ui.lvshiwenti.view.IlvshiZiXunListView;
import com.ytfu.yuntaifawu.utils.CommonUtil;

import java.util.HashMap;

import butterknife.BindView;

/**
 * @Auther gxy
 * @Date 2020/5/25
 * @Des 律师端 问题fragment
 */
public class LvShiWenTiFragment extends BaseFragment<IlvshiZiXunListView, LvShiZixunPresenter> implements IlvshiZiXunListView {

    @BindView(R.id.iv_fanhui)
    ImageView ivFanhui;
    @BindView(R.id.tv_top_title)
    TextView tvTopTitle;
    @BindView(R.id.rl_zixun_list)
    RecyclerView rlZixunList;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout refreshLayout;
    private int page = 1;
    private LvShiZiXunAdaper lvShiZiXunAdaper;

    @Override
    protected View provideLoadServiceRootView() {
        return refreshLayout;
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.lvshi_wenti_fragment;
    }

    @Override
    protected LvShiZixunPresenter createPresenter() {
        return new LvShiZixunPresenter(getContext());
    }

    public static LvShiWenTiFragment newInstance() {
        return new LvShiWenTiFragment();
    }

    @Override
    protected void initView(View rootView) {
        tvTopTitle.setText("咨询问题");
        ivFanhui.setVisibility(View.GONE);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rlZixunList.setLayoutManager(layoutManager);
        // 解决调用 notifyItemChanged 闪烁问题,取消默认动画
        ((SimpleItemAnimator) rlZixunList.getItemAnimator())
                .setSupportsChangeAnimations(false);
        refreshLayout.setEnableLoadMore(true);
        refreshLayout.setRefreshHeader(new MaterialHeader(App.getContext()).setColorSchemeResources(R.color.primaryColor, R.color.blue_3C, R.color.green_59));
        refreshLayout.setRefreshFooter(new BallPulseFooter(App.getContext()).setAnimatingColor(CommonUtil.getColor(R.color.primaryColor)));
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    @Override
    protected void initData() {
        lvShiZiXunAdaper = new LvShiZiXunAdaper(getContext());
        rlZixunList.setAdapter(lvShiZiXunAdaper);
        getData();
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getData();
                refreshLayout.finishRefresh();
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                getDatas();
                refreshLayout.finishLoadMore();
            }
        });
    }

    private void getData() {
        page = 1;
        mPresenter.getLvShiZiXunList(page);
    }

    private void getDatas() {
        page++;
        mPresenter.getLvShiZiXunList(page);
    }

    @Override
    public void onLvShiListSuccess(LvShiZiXunListBean lvShiZiXunListBean) {
        hideLoading();
        if (lvShiZiXunListBean == null || lvShiZiXunListBean.getList() == null) {
            if(page == 1){
                showEmpty();
            }
        }else{
            if (page == 1) {
                lvShiZiXunAdaper.setListBeans(lvShiZiXunListBean.getList());
            } else {
                lvShiZiXunAdaper.addListBeans(lvShiZiXunListBean.getList());
            }
        }
        RefreshLayoutHelper.getInstance().setLoadedState(lvShiZiXunListBean.getList(), page, refreshLayout, this);
    }

    @Override
    public void onLvShiListFiled(String error) {

    }
}
