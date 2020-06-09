package com.ytfu.yuntaifawu.ui.home.fragment;

import android.content.Intent;
import android.view.View;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ytfu.yuntaifawu.R;
import com.ytfu.yuntaifawu.app.AppConstant;
import com.ytfu.yuntaifawu.base.BaseFragment;
import com.ytfu.yuntaifawu.ui.home.adaper.RelevantContractAdaper;
import com.ytfu.yuntaifawu.ui.home.bean.AudioDetailsBean;
import com.ytfu.yuntaifawu.ui.home.bean.AudioListerBean;
import com.ytfu.yuntaifawu.ui.home.bean.AudioShouCangBean;
import com.ytfu.yuntaifawu.ui.home.presenter.AudioDetailsPresenter;
import com.ytfu.yuntaifawu.ui.home.view.IAudioDetailsView;

import com.ytfu.yuntaifawu.ui.pay.bean.PayBean;
import com.ytfu.yuntaifawu.ui.pay.bean.WxPayBean;

import com.ytfu.yuntaifawu.utils.SpUtil;

import java.util.HashMap;
import java.util.List;

/**
 * @Auther gxy
 * @Date 2019/11/12
 * @Des 相关合同fragment
 */
public class RelevantContractFragment extends BaseFragment<IAudioDetailsView, AudioDetailsPresenter> implements IAudioDetailsView {

    private RecyclerView recyclerView;
    private RelevantContractAdaper contractAdaper;
    private String uid;
    private String id;

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_relevant_contract;
    }

    @Override
    protected AudioDetailsPresenter createPresenter() {
        return new AudioDetailsPresenter(getContext());
    }

    @Override
    protected void initView(View rootView) {
//        hideLoading();
        recyclerView = rootView.findViewById(R.id.recyele_contract);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setNestedScrollingEnabled(true);
    }

    @Override
    protected void initData() {
        Intent intent = getActivity().getIntent();
        id = intent.getStringExtra("id");
        uid = SpUtil.getString(AppConstant.UID, "");
        contractAdaper = new RelevantContractAdaper(getContext());
        recyclerView.setAdapter(contractAdaper);
        HashMap<String,String> map = new HashMap<>();
        map.put("uid",uid);
        map.put("id", id);
        getPresenter().getAudioDetails(map);
    }

    @Override
    protected void onMyReload(View v) {
        super.onMyReload(v);
        showLoading();
        HashMap<String,String> map = new HashMap<>();
        map.put("uid",uid);
        map.put("id",id);
        getPresenter().getAudioDetails(map);
    }

    @Override
    public void onDetailsSuccess(AudioDetailsBean detailsBean) {
        hideLoading();
        if (detailsBean == null && detailsBean.getContract_list().isEmpty()) {
            showEmpty();
        } else {
            List<AudioDetailsBean.ContractListBean> contract_list = detailsBean.getContract_list();
            contractAdaper.setmList(contract_list);
        }
    }

    @Override
    public void onDetailFiled() {

    }

    @Override
    public void onShouCangSuccess(AudioShouCangBean shouCangBean) {
        hideLoading();
    }

    @Override
    public void onShouCangdelSuccess(AudioShouCangBean shouCangBean) {
        hideLoading();
    }

    @Override
    public void onAudioPaySuccess(PayBean payBean) {

    }

    @Override
    public void onAudioPayWxSuccess(WxPayBean wxPayBean) {

    }

    @Override
    public void onListenerSuccess(AudioListerBean listerBean) {

    }
}
