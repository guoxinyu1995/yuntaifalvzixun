package com.ytfu.yuntaifawu.ui.qisuzhuang.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ytfu.yuntaifawu.R;
import com.ytfu.yuntaifawu.base.BaseActivity;
import com.ytfu.yuntaifawu.ui.qisuzhuang.adaper.QszFenLeiAdaper;
import com.ytfu.yuntaifawu.ui.qisuzhuang.bean.QszFenLeiBean;
import com.ytfu.yuntaifawu.ui.qisuzhuang.presenter.QszPresenter;
import com.ytfu.yuntaifawu.ui.qisuzhuang.view.IQszView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @Auther gxy
 * @Date 2019/11/19
 * @Des 起诉状
 */
public class ActivityQiSuZhuang extends BaseActivity<IQszView, QszPresenter> implements IQszView {
    @BindView(R.id.iv_fanhui)
    ImageView ivFanhui;
    @BindView(R.id.tv_top_title)
    TextView tvTopTitle;
    @BindView(R.id.recycle_qsz)
    RecyclerView recycleQsz;
    @BindView(R.id.tv_qsz_history)
    TextView tvQszHistory;
    @BindView(R.id.tv_tishi)
    TextView tvTishi;
    private QszFenLeiAdaper fenLeiAdaper;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_qsz;
    }

    @Override
    protected QszPresenter createPresenter() {
        return new QszPresenter(this);
    }

    @Override
    public void init() {
        super.init();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
//      window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.transparent_half));

        }
    }

    @Override
    protected void initView() {
//        hideLoading();
        tvTopTitle.setText("起诉状");
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        layoutManager.setOrientation(RecyclerView.VERTICAL);
//        recycleQsz.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
//        recycleQsz.addItemDecoration(new MyItemDecoration2(0f, 0f));
        recycleQsz.setLayoutManager(new GridLayoutManager(this, 3));
    }

    @Override
    protected void onMyReload(View v) {
        super.onMyReload(v);
        mPresenter.getQiSuZhuang();
    }

    @Override
    protected void initData() {
        fenLeiAdaper = new QszFenLeiAdaper(this);
        recycleQsz.setAdapter(fenLeiAdaper);
        mPresenter.getQiSuZhuang();
    }


    @Override
    public void onQszSuccess(QszFenLeiBean fenLeiBean) {
        hideLoading();
        if (fenLeiBean.getList() == null || fenLeiBean.getList().isEmpty()) {
            showEmpty();
        } else {
            tvTishi.setText("已免费代写"+fenLeiBean.getRand()+"份");
            fenLeiAdaper.setmList(fenLeiBean.getList());
        }
    }

    @Override
    public void onQszFiled() {

    }


    @OnClick({R.id.iv_fanhui, R.id.tv_qsz_history})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_fanhui:
                finish();
                break;
            case R.id.tv_qsz_history:
                Intent intent = new Intent(ActivityQiSuZhuang.this, ActivityQszHistoryList.class);
                intent.putExtra("type", 1);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

}
