package com.ytfu.yuntaifawu.ui.mseeage.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

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
import com.ytfu.yuntaifawu.base.BaseActivity;
import com.ytfu.yuntaifawu.helper.RefreshLayoutHelper;
import com.ytfu.yuntaifawu.ui.mseeage.adaper.TaHuiDaAdaper;
import com.ytfu.yuntaifawu.ui.mseeage.bean.TaHuiDaBean;
import com.ytfu.yuntaifawu.ui.mseeage.presenter.TaHuiDaPresenter;
import com.ytfu.yuntaifawu.ui.mseeage.view.ITaDeHuiView;
import com.ytfu.yuntaifawu.utils.CommonUtil;
import com.ytfu.yuntaifawu.utils.Eyes;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Auther gxy
 * @Date 2020/4/20
 * @Des Ta的回答
 */
public class TaHuiDaActivity extends BaseActivity<ITaDeHuiView, TaHuiDaPresenter> implements ITaDeHuiView {
    @BindView(R.id.iv_fanhui)
    ImageView ivFanhui;
    @BindView(R.id.tv_top_title)
    TextView tvTopTitle;
    @BindView(R.id.rl_huida)
    RecyclerView rlHuida;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout refreshLayout;
    private String lid;
    private String userName;
    private TaHuiDaAdaper taHuiDaAdaper;
    private int page = 1;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_ta_huida;
    }

    @Override
    protected View provideLoadServiceRootView() {
        return refreshLayout;
    }

    @Override
    protected TaHuiDaPresenter createPresenter() {
        return new TaHuiDaPresenter(this);
    }

    @Override
    public void init() {
        super.init();
        Eyes.setStatusBarColor(this, CommonUtil.getColor(R.color.transparent_4c));
    }

    @Override
    protected void initView() {
//        hideLoading();
        lid = getIntent().getStringExtra("lid");
        userName = getIntent().getStringExtra("userName");
        tvTopTitle.setText(userName + "的回答");
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rlHuida.setLayoutManager(layoutManager);
        taHuiDaAdaper = new TaHuiDaAdaper(this);
        rlHuida.setAdapter(taHuiDaAdaper);
        refreshLayout.setEnableLoadMore(true);
        refreshLayout.setRefreshHeader(new MaterialHeader(App.getContext()).setColorSchemeResources(R.color.primaryColor, R.color.blue_3C, R.color.green_59));
        refreshLayout.setRefreshFooter(new BallPulseFooter(App.getContext()).setAnimatingColor(CommonUtil.getColor(R.color.primaryColor)));
    }

    @Override
    protected void initData() {
        setData();
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
//                refreshLayout.finishLoadMoreWithNoMoreData();
            }
        });
    }

    private void setData() {
        page = 1;
//        HashMap<String, String> map = new HashMap<>();
//        map.put("lid", lid);
        getPresenter().getTaHuiDa(lid,page);
    }

    private void setDatas() {
        page++;
//        HashMap<String, String> map = new HashMap<>();
//        map.put("lid", lid);
        getPresenter().getTaHuiDa(lid,page);
    }

    @OnClick(R.id.iv_fanhui)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void onTaHuiSuccess(TaHuiDaBean taHuiDaBean) {
        hideLoading();
        if (taHuiDaBean == null || taHuiDaBean.getData() == null) {
            if(page == 1){
                showEmpty();
            }
        } else {
            if(page==1){
                taHuiDaAdaper.setDataBeans(taHuiDaBean.getData());
            }else{
                taHuiDaAdaper.addDataBeans(taHuiDaBean.getData());
            }
        }
        RefreshLayoutHelper.getInstance().setLoadedState(taHuiDaBean.getData(), page, refreshLayout, this);
    }

    @Override
    public void onTaHuiFiled() {

    }

}
