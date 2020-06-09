package com.ytfu.yuntaifawu.ui.lvshihetong.fragment;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ytfu.yuntaifawu.R;
import com.ytfu.yuntaifawu.base.BaseFragment;
import com.ytfu.yuntaifawu.ui.home.adaper.ContractSecondAdaper;
import com.ytfu.yuntaifawu.ui.lvshihetong.bean.ContractListSecondBean;
import com.ytfu.yuntaifawu.ui.home.presenter.ContractListSecondPresenter;
import com.ytfu.yuntaifawu.ui.home.view.IContractListSecondView;
import com.ytfu.yuntaifawu.utils.MyItemDecoration2;

import butterknife.BindView;

/**
 * @Auther gxy
 * @Date 2019/11/18
 * @Des 合同二级列表
 */
public class ContractListSecondFragment extends BaseFragment<IContractListSecondView, ContractListSecondPresenter> implements IContractListSecondView {

    @BindView(R.id.recycle_second)
    RecyclerView recycleSecond;
    private int indicator_tag;
    private String id;
    private int currentIndex = -1;
    private ContractSecondAdaper secondAdaper;

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_contract_list_second;
    }

    @Override
    protected ContractListSecondPresenter createPresenter() {
        return new ContractListSecondPresenter(getContext());
    }

    @Override
    public void onResume() {
        super.onResume();
        if (null != getArguments()) {
            indicator_tag = getArguments().getInt("indicator_tag");
            id = getArguments().getString("id");
        }
//        if (currentIndex != indicator_tag) {
            setData();
//        }
    }

    private void setData() {
//        HashMap<String,String> map = new HashMap<>();
//        map.put("id",id);
        getPresenter().secondList(id);
    }

    @Override
    protected void initView(View rootView) {
//        hideLoading();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycleSecond.addItemDecoration(new MyItemDecoration2(0f, 0f));
        recycleSecond.setLayoutManager(linearLayoutManager);
    }

    @Override
    protected void initData() {
        secondAdaper = new ContractSecondAdaper(getContext());
        recycleSecond.setAdapter(secondAdaper);
        setData();
    }

    @Override
    public void onSecondListSuccess(ContractListSecondBean secondBean) {
        hideLoading();
        if (secondBean == null || secondBean.getList() == null || secondBean.getList().isEmpty()) {
            showEmpty();
        } else {
            secondAdaper.setmList(secondBean.getList());
        }
    }

    @Override
    public void onFiled() {

    }
}
