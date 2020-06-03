package com.ytfu.yuntaifawu.ui.home.fragment;

import android.content.Intent;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ytfu.yuntaifawu.R;
import com.ytfu.yuntaifawu.app.AppConstant;
import com.ytfu.yuntaifawu.base.BaseFragment;
import com.ytfu.yuntaifawu.ui.home.adaper.AudioRelevationAdaper;
import com.ytfu.yuntaifawu.ui.home.adaper.AudioSimilarAdaper;
import com.ytfu.yuntaifawu.ui.home.bean.AudioShouCangBean;
import com.ytfu.yuntaifawu.ui.lvshihetong.bean.ContractDetailsBean;
import com.ytfu.yuntaifawu.ui.home.presenter.ContractDetailsPresenter;
import com.ytfu.yuntaifawu.ui.home.view.IContractDetailsView;
import com.ytfu.yuntaifawu.ui.kaitingzhushou.bean.SendEmailBean;
import com.ytfu.yuntaifawu.ui.mine.bean.BdEmailBean;
import com.ytfu.yuntaifawu.ui.pay.bean.PayBean;
import com.ytfu.yuntaifawu.ui.pay.bean.WxPayBean;

import com.ytfu.yuntaifawu.utils.SpUtil;

import java.util.HashMap;
import java.util.List;

/**
 * @Auther gxy
 * @Date 2019/11/13
 * @Des 相关音频frgament
 */
public class RelevantAudioFragment extends BaseFragment<IContractDetailsView, ContractDetailsPresenter> implements IContractDetailsView {

    private RecyclerView recyclerView;
    private AudioSimilarAdaper similarAdaper;
    private AudioRelevationAdaper audioRelevationAdaper;
    private String uid;
    private String id;

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_relevant_audio;
    }

    @Override
    protected ContractDetailsPresenter createPresenter() {
        return new ContractDetailsPresenter(getContext());
    }

    @Override
    protected void initView(View rootView) {
//        hideLoading();
        recyclerView = rootView.findViewById(R.id.recyele_audio);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setNestedScrollingEnabled(true);
    }

    @Override
    protected void initData() {
        Intent intent = getActivity().getIntent();
        id = intent.getStringExtra("id");
        uid = SpUtil.getString(AppConstant.UID, "");
//        similarAdaper = new AudioSimilarAdaper(getContext());
        audioRelevationAdaper = new AudioRelevationAdaper(getContext());
        recyclerView.setAdapter(audioRelevationAdaper);
        HashMap<String,String> map = new HashMap<>();
        map.put("uid", uid);
        map.put("id", id);
        mPresenter.getContractDetails(map);
    }

    @Override
    protected void onMyReload(View v) {
        super.onMyReload(v);
        showLoading();
        HashMap<String,String> map = new HashMap<>();
        map.put("uid", uid);
        map.put("id",id);
        mPresenter.getContractDetails(map);
    }

    @Override
    public void onContractDetailsSuccess(ContractDetailsBean detailsBean) {
        hideLoading();
        if (detailsBean == null && detailsBean.getAudio_list().isEmpty()) {
            showEmpty();
        } else {
            List<ContractDetailsBean.AudioListBean> audio_list = detailsBean.getAudio_list();
            audioRelevationAdaper.setList(audio_list);
        }
    }

    @Override
    public void onContractDetailFiled() {

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
    public void onContractPaySuccess(PayBean payBean) {

    }

    @Override
    public void onContractPayWxSuccess(WxPayBean wxPayBean) {

    }

    @Override
    public void onSendEmailSuccess(SendEmailBean emailBean) {

    }

    @Override
    public void onContractBdEmailSuccess(BdEmailBean bdEmailBean) {

    }
}
