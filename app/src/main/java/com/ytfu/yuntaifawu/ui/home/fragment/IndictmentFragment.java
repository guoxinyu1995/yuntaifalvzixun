package com.ytfu.yuntaifawu.ui.home.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ytfu.yuntaifawu.R;
import com.ytfu.yuntaifawu.base.BaseFragment;
import com.ytfu.yuntaifawu.ui.qisuzhuang.activity.ActivityQszHistoryList;
import com.ytfu.yuntaifawu.ui.qisuzhuang.adaper.QszFenLeiAdaper;
import com.ytfu.yuntaifawu.ui.qisuzhuang.bean.QszFenLeiBean;
import com.ytfu.yuntaifawu.ui.qisuzhuang.presenter.QszPresenter;
import com.ytfu.yuntaifawu.ui.qisuzhuang.view.IQszView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @Auther gxy
 * @Date 2020/3/20
 * @Des 诉状fragment
 */
public class IndictmentFragment extends BaseFragment<IQszView, QszPresenter> implements IQszView {
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
        return R.layout.fragment_suzhuang;
    }

    @Override
    protected QszPresenter createPresenter() {
        return new QszPresenter(getContext());
    }

//    @Override
//    public void onHiddenChanged(boolean hidden) {
//        super.onHiddenChanged(hidden);
//        if(!hidden){
//            mPresenter.getQiSuZhuang();
//        }
//    }
    public static IndictmentFragment newInstance() {
        return new IndictmentFragment();
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    @Override
    protected void initView(View rootView) {
        ivFanhui.setVisibility(View.GONE);
        tvTopTitle.setText("起诉状");
//        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
//        layoutManager.setOrientation(RecyclerView.VERTICAL);
//        recycleQsz.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
//        recycleQsz.addItemDecoration(new MyItemDecoration2(0f, 0f));
        recycleQsz.setLayoutManager(new GridLayoutManager(getContext(), 3));
    }

    @Override
    protected void initData() {
        fenLeiAdaper = new QszFenLeiAdaper(getContext());
        recycleQsz.setAdapter(fenLeiAdaper);
        mPresenter.getQiSuZhuang();
    }

    @OnClick(R.id.tv_qsz_history)
    public void onViewClicked() {
        Intent intent = new Intent(getActivity(), ActivityQszHistoryList.class);
        intent.putExtra("type", 1);
        startActivity(intent);
    }

    @Override
    public void onQszSuccess(QszFenLeiBean fenLeiBean) {
        hideLoading();
        if (fenLeiBean.getList() == null || fenLeiBean.getList().isEmpty()) {
            showEmpty();
        } else {
            tvTishi.setText("已免费代写" + fenLeiBean.getRand() + "份");
            fenLeiAdaper.setmList(fenLeiBean.getList());
        }
    }

    @Override
    public void onQszFiled() {

    }
}
