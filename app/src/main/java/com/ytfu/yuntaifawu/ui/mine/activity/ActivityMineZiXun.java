package com.ytfu.yuntaifawu.ui.mine.activity;

import android.os.Bundle;
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
import com.ytfu.yuntaifawu.app.AppConstant;
import com.ytfu.yuntaifawu.base.BaseActivity;
import com.ytfu.yuntaifawu.helper.RefreshLayoutHelper;
import com.ytfu.yuntaifawu.ui.mine.adaper.MineZiXunAdaper;
import com.ytfu.yuntaifawu.ui.mine.bean.MineZiXunBean;
import com.ytfu.yuntaifawu.ui.mine.present.MineZiXunPresenter;
import com.ytfu.yuntaifawu.ui.mine.view.IMineZiXunView;
import com.ytfu.yuntaifawu.utils.CommonUtil;
import com.ytfu.yuntaifawu.utils.Eyes;
import com.ytfu.yuntaifawu.utils.SpUtil;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Auther gxy
 * @Date 2020/5/19
 * @Des 我的咨询
 */
public class ActivityMineZiXun extends BaseActivity<IMineZiXunView, MineZiXunPresenter> implements IMineZiXunView {
    @BindView(R.id.iv_fanhui)
    ImageView ivFanhui;
    @BindView(R.id.tv_top_title)
    TextView tvTopTitle;
    @BindView(R.id.rl_zixun_list)
    RecyclerView rlZixunList;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout refreshLayout;
    private MineZiXunAdaper mineZiXunAdaper;
    private int page = 1;
    private String uid;
    private String shenfen;
    private String id;

    @Override
    public void init() {
        super.init();
        Eyes.setStatusBarColor(this, CommonUtil.getColor(R.color.transparent_4c));
    }

    @Override
    protected View provideLoadServiceRootView() {
        return refreshLayout;
    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_mine_zixun;
    }


    @Override
    protected MineZiXunPresenter createPresenter() {
        return new MineZiXunPresenter(this);
    }

    @Override
    protected void initView() {
        shenfen = SpUtil.getString(AppConstant.SHENFEN, "1");
        uid = SpUtil.getString(AppConstant.UID, "");
        //律师端他的咨询
        id = getIntent().getStringExtra("id");
        if("1".equals(shenfen)){
            tvTopTitle.setText("我的咨询");
        }else{
            tvTopTitle.setText("他的咨询");
        }
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
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
    protected void initData() {
        mineZiXunAdaper = new MineZiXunAdaper(this);
        rlZixunList.setAdapter(mineZiXunAdaper);
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
        if("1".equals(shenfen)){
            page = 1;
            HashMap<String, String> map = new HashMap<>();
            map.put("uid", uid);
            map.put("page", String.valueOf(page));
            mPresenter.getMineZiXunList(map);
        }else{
            page = 1;
            HashMap<String, String> map = new HashMap<>();
            map.put("uid", id);
            map.put("page", String.valueOf(page));
            mPresenter.getMineZiXunList(map);
        }

    }

    private void getDatas() {
        if("1".equals(shenfen)){
            page++;
            HashMap<String, String> map = new HashMap<>();
            map.put("uid", uid);
            map.put("page", String.valueOf(page));
            mPresenter.getMineZiXunList(map);
        }else{
            page++;
            HashMap<String, String> map = new HashMap<>();
            map.put("uid", id);
            map.put("page", String.valueOf(page));
            mPresenter.getMineZiXunList(map);
        }

    }

    @OnClick(R.id.iv_fanhui)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void onMineZiXunSuccess(MineZiXunBean mineZiXunBean) {
        hideLoading();
        if (mineZiXunBean == null || mineZiXunBean.getList() == null) {
            if(page == 1){
                showEmpty();
            }
        } else {
            if (page == 1) {
                mineZiXunAdaper.setListBeans(mineZiXunBean.getList());
            } else {
                mineZiXunAdaper.addListBeans(mineZiXunBean.getList());
            }
        }
        RefreshLayoutHelper.getInstance().setLoadedState(mineZiXunBean.getList(), page, refreshLayout, this);
    }

    @Override
    public void onMineZiXunFiled() {

    }

}
