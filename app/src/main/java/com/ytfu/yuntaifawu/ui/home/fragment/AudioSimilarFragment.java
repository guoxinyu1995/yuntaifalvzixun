package com.ytfu.yuntaifawu.ui.home.fragment;

import android.content.Intent;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ytfu.yuntaifawu.R;
import com.ytfu.yuntaifawu.app.AppConstant;
import com.ytfu.yuntaifawu.base.BaseFragment;
import com.ytfu.yuntaifawu.ui.home.adaper.AudioSimilarAdaper;
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
 * @Des 同类音频fragment
 */
public class AudioSimilarFragment extends BaseFragment<IAudioDetailsView, AudioDetailsPresenter> implements IAudioDetailsView {

    private RecyclerView recyclerView;
    private AudioSimilarAdaper similarAdaper;
    private String id;
    private String uid;

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_similar_audio;
    }

    @Override
    protected AudioDetailsPresenter createPresenter() {
        return new AudioDetailsPresenter(getContext());
    }

    @Override
    protected void initView(View rootView) {
//        hideLoading();
        recyclerView = rootView.findViewById(R.id.recycle_tl_audio);
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
        similarAdaper = new AudioSimilarAdaper(getContext());
        recyclerView.setAdapter(similarAdaper);
        HashMap<String,String> map = new HashMap<>();
        map.put("uid",uid);
        map.put("id",id);
        mPresenter.getAudioDetails(map);
    }

    @Override
    protected void onMyReload(View v) {
        super.onMyReload(v);
        showLoading();
        HashMap<String,String> map = new HashMap<>();
        map.put("uid",uid);
        map.put("id",id);
        mPresenter.getAudioDetails(map);
    }

    @Override
    public void onDetailsSuccess(AudioDetailsBean detailsBean) {
        hideLoading();
        if (detailsBean != null && !detailsBean.getAudio_list().isEmpty()) {
            List<AudioDetailsBean.AudioListBean> audio_list = detailsBean.getAudio_list();
            similarAdaper.setList(audio_list);
        } else {
            showEmpty();
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

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}
