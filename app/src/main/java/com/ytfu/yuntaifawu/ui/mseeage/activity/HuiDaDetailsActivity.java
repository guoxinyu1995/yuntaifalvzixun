package com.ytfu.yuntaifawu.ui.mseeage.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ytfu.yuntaifawu.R;
import com.ytfu.yuntaifawu.base.BaseActivity;
import com.ytfu.yuntaifawu.base.BasePresenter;
import com.ytfu.yuntaifawu.ui.mseeage.adaper.HuiDaDetailsAdaper;
import com.ytfu.yuntaifawu.ui.mseeage.bean.HuiDaDetailsBean;
import com.ytfu.yuntaifawu.ui.mseeage.presenter.HuiDaDetailsPresenter;
import com.ytfu.yuntaifawu.ui.mseeage.view.IHuiDaDetailsView;
import com.ytfu.yuntaifawu.utils.CommonUtil;
import com.ytfu.yuntaifawu.utils.Eyes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HuiDaDetailsActivity extends BaseActivity<IHuiDaDetailsView, HuiDaDetailsPresenter> implements IHuiDaDetailsView {
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
    private String anid;
    private HuiDaDetailsAdaper daDetailsAdaper;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_huida_details;
    }

    @Override
    protected HuiDaDetailsPresenter createPresenter() {
        return new HuiDaDetailsPresenter(this);
    }
    @Override
    public void init() {
        super.init();
        Eyes.setStatusBarColor(this, CommonUtil.getColor(R.color.transparent_4c));
    }
    @Override
    protected void initView() {
        hideLoading();
        tvTopTitle.setText("问答详情");
        lid = getIntent().getStringExtra("lid");
        anid = getIntent().getStringExtra("anid");
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rlNum.setLayoutManager(layoutManager);
        daDetailsAdaper = new HuiDaDetailsAdaper(this);
        rlNum.setAdapter(daDetailsAdaper);
    }

    @Override
    protected void initData() {
        HashMap<String, String> map = new HashMap<>();
        map.put("lid", lid);
        map.put("anid", anid);
        mPresenter.getHuiDaDetails(map);
    }

    @OnClick(R.id.iv_fanhui)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void onHuiDaSuccess(HuiDaDetailsBean huiDaDetailsBean) {
        if (huiDaDetailsBean != null && huiDaDetailsBean.getData() != null) {
            HuiDaDetailsBean.DataBean data = huiDaDetailsBean.getData();
            tvName.setText(data.getUname());
            tvTime.setText(data.getDate());
            tvContent.setText(data.getContent());
            tvLiexing.setText(data.getCid());
            tvNum.setText(data.getSum() + "条回复");
            List<String> list = new ArrayList<>();
            for (int i = 0; i < Integer.valueOf(data.getSum()); i++) {
                list.add("");
            }
            daDetailsAdaper.setList(list);
        }
    }

    @Override
    public void onHuiDaFiled() {

    }
}
