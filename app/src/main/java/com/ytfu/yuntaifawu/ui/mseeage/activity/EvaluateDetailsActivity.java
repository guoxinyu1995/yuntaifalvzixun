package com.ytfu.yuntaifawu.ui.mseeage.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ytfu.yuntaifawu.R;
import com.ytfu.yuntaifawu.base.BaseActivity;
import com.ytfu.yuntaifawu.base.BasePresenter;
import com.ytfu.yuntaifawu.ui.mseeage.adaper.EvaluateDetailsAdaper;
import com.ytfu.yuntaifawu.ui.mseeage.bean.PingJIaDetailsBean;
import com.ytfu.yuntaifawu.ui.mseeage.presenter.EvaluateDetailsPresenter;
import com.ytfu.yuntaifawu.ui.mseeage.view.IEvaluateDetailsView;
import com.ytfu.yuntaifawu.utils.CommonUtil;
import com.ytfu.yuntaifawu.utils.Eyes;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Auther gxy
 * @Date 2020/4/22
 * @Des 评价详情
 */
public class EvaluateDetailsActivity extends BaseActivity<IEvaluateDetailsView, EvaluateDetailsPresenter> implements IEvaluateDetailsView {
    @BindView(R.id.iv_fanhui)
    ImageView ivFanhui;
    @BindView(R.id.tv_top_title)
    TextView tvTopTitle;
    @BindView(R.id.iv)
    ImageView iv;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.tv_liexing)
    TextView tvLiexing;
    @BindView(R.id.tv_num)
    TextView tvNum;
    @BindView(R.id.rl_num)
    RecyclerView rlNum;
    private String lid;
    private String id;
    private EvaluateDetailsAdaper evaluateDetailsAdaper;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_evaluate_detalis;
    }

    @Override
    protected View provideLoadServiceRootView() {
        return rlNum;
    }

    @Override
    protected EvaluateDetailsPresenter createPresenter() {
        return new EvaluateDetailsPresenter(this);
    }

    @Override
    public void init() {
        super.init();
        Eyes.setStatusBarColor(this, CommonUtil.getColor(R.color.transparent_4c));
    }

    @Override
    protected void initView() {
        hideLoading();
        tvTopTitle.setText("评价详情");
        lid = getIntent().getStringExtra("lid");
        id = getIntent().getStringExtra("id");
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rlNum.setLayoutManager(layoutManager);
        evaluateDetailsAdaper = new EvaluateDetailsAdaper(this);
        rlNum.setAdapter(evaluateDetailsAdaper);
    }

    @Override
    protected void initData() {
        HashMap<String, String> map = new HashMap<>();
        map.put("lid", lid);
        map.put("pingjia_id", id);
        mPresenter.getEvaluateDetalis(map);
    }


    @OnClick(R.id.iv_fanhui)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void onEvaluateDetailsSuccess(PingJIaDetailsBean pingJIaDetailsBean) {
        if (pingJIaDetailsBean != null) {
            tvName.setText(pingJIaDetailsBean.getNickname());
            tvTime.setText(pingJIaDetailsBean.getYhaddtime());
            tvContent.setText(pingJIaDetailsBean.getName());
            tvLiexing.setText(pingJIaDetailsBean.getCid());
            tvNum.setText(pingJIaDetailsBean.getCount() + "条回复");
            if (pingJIaDetailsBean.getTalkdetail() != null) {
                evaluateDetailsAdaper.setBeanList(pingJIaDetailsBean.getTalkdetail());
            } else {
                showEmpty();
            }
        }
    }

    @Override
    public void onEvaluateDetailsFiled() {

    }
}
