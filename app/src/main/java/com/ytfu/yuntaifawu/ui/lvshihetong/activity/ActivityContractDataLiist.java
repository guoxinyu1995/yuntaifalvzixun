package com.ytfu.yuntaifawu.ui.lvshihetong.activity;

import android.content.Intent;
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
import com.ytfu.yuntaifawu.ui.lvshihetong.adaper.ContractDataListAdaper;
import com.ytfu.yuntaifawu.ui.lvshihetong.bean.ContractDatalistBean;
import com.ytfu.yuntaifawu.ui.home.presenter.ContractDataListPresenter;
import com.ytfu.yuntaifawu.ui.home.view.IContractDataListView;
import com.ytfu.yuntaifawu.utils.CommonUtil;
import com.ytfu.yuntaifawu.utils.Eyes;
import com.ytfu.yuntaifawu.utils.MyItemDecoration2;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @Auther gxy
 * @Date 2019/11/18
 * @Des 合同列表数据
 */
public class ActivityContractDataLiist extends BaseActivity<IContractDataListView, ContractDataListPresenter> implements IContractDataListView {
    @BindView(R.id.recycle_view)
    RecyclerView recycleView;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.iv_fanhui)
    ImageView ivFanhui;
    @BindView(R.id.tv_top_title)
    TextView tvTopTitle;
    private ContractDataListAdaper dataListAdaper;
    private String id;
    private int page = 1;
    private String lable;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_contract_data_list;
    }

    @Override
    protected ContractDataListPresenter createPresenter() {
        return new ContractDataListPresenter(this);
    }
    @Override
    public void init() {
        super.init();
        Eyes.setStatusBarColor(this, CommonUtil.getColor(R.color.transparent_4c));
    }

    @Override
    protected void initView() {
//        hideLoading();
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recycleView.addItemDecoration(new MyItemDecoration2(0f, 0f));
        recycleView.setLayoutManager(layoutManager);
        refreshLayout.setEnableLoadMore(false);
        refreshLayout.setRefreshHeader(new MaterialHeader(App.getContext()).setColorSchemeResources(R.color.primaryColor, R.color.blue_3C, R.color.green_59));
        refreshLayout.setRefreshFooter(new BallPulseFooter(App.getContext()).setAnimatingColor(CommonUtil.getColor(R.color.primaryColor)));
    }
    @Override
    protected void initData() {
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        lable = intent.getStringExtra("lable");
        tvTopTitle.setText(lable);
        dataListAdaper = new ContractDataListAdaper(this);
        recycleView.setAdapter(dataListAdaper);
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
        setData();
    }

    private void setData() {
        HashMap<String, String> map = new HashMap<>();
        page = 1;
        map.put("id", id);
        map.put("page", String.valueOf(page));
        map.put("keyword", "");
        getPresenter().getDataList(map);
    }

    private void setDatas() {
        HashMap<String, String> map = new HashMap<>();
        page++;
        map.put("id", id);
        map.put("page", String.valueOf(page));
        map.put("keyword", "");
        getPresenter().getDataList(map);
    }

    @Override
    public void onDataListSuccess(ContractDatalistBean datalistBean) {
        hideLoading();
        if (datalistBean.getList() == null || datalistBean.getList().isEmpty()) {
            showEmpty();
        } else {

            if (page == 1) {
                dataListAdaper.setmList(datalistBean.getList());
            } else {
                dataListAdaper.addmlist(datalistBean.getList());
            }
        }
        RefreshLayoutHelper.getInstance().setLoadedState(datalistBean.getList(), page, refreshLayout, this);
    }

    @Override
    public void onFiled() {

    }
    @OnClick(R.id.iv_fanhui)
    public void onViewClicked() {
        finish();
    }
}
