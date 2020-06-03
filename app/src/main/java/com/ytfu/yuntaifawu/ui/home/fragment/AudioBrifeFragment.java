package com.ytfu.yuntaifawu.ui.home.fragment;

import android.content.Intent;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

import androidx.core.widget.NestedScrollView;

import com.ytfu.yuntaifawu.R;
import com.ytfu.yuntaifawu.app.AppConstant;
import com.ytfu.yuntaifawu.base.BaseFragment;
import com.ytfu.yuntaifawu.ui.home.bean.AudioDetailsBean;
import com.ytfu.yuntaifawu.ui.home.bean.AudioListerBean;
import com.ytfu.yuntaifawu.ui.home.bean.AudioShouCangBean;
import com.ytfu.yuntaifawu.ui.home.presenter.AudioDetailsPresenter;
import com.ytfu.yuntaifawu.ui.home.view.IAudioDetailsView;

import com.ytfu.yuntaifawu.ui.pay.bean.PayBean;
import com.ytfu.yuntaifawu.ui.pay.bean.WxPayBean;
import com.ytfu.yuntaifawu.utils.SpUtil;

import java.util.HashMap;

/**
*
*  @Auther  gxy
*
*  @Date    2019/11/12
*
*  @Des  音频简介fragment
*
*/
public class AudioBrifeFragment extends BaseFragment<IAudioDetailsView, AudioDetailsPresenter> implements IAudioDetailsView {

    private TextView tv_audio_brife,tv_audio_yuanwen,tv_zheng_qu;
    private String uid;
    private String id;
    private NestedScrollView scroll_view;
    private View v_1;

    @Override
    protected int provideContentViewId() {
        return R.layout.fragment_audio_brife;
    }

    @Override
    protected AudioDetailsPresenter createPresenter() {
        return new AudioDetailsPresenter(getContext());
    }

    @Override
    public void onResume() {
        super.onResume();
        uid = SpUtil.getString(AppConstant.UID, "");
        HashMap<String,String> map = new HashMap<>();
        map.put("uid",uid);
        map.put("id", id);
        mPresenter.getAudioDetails(map);
    }

    @Override
    protected void initView(View rootView) {
//        hideLoading();
        uid = SpUtil.getString(AppConstant.UID, "");
        scroll_view = rootView.findViewById(R.id.scroll_view);
        scroll_view.setFocusable(false);
        tv_audio_brife = rootView.findViewById(R.id.tv_audio_brife);
        tv_zheng_qu = rootView.findViewById(R.id.tv_zheng_qu);
//        tv_audio_brife.setSelected(true);
        tv_audio_brife.setMovementMethod(new ScrollingMovementMethod());
        tv_audio_yuanwen = rootView.findViewById(R.id.tv_audio_yuanwen);
//        tv_audio_yuanwen.setSelected(true);
        tv_audio_yuanwen.setMovementMethod(new ScrollingMovementMethod());
//        tv_audio_brife.getSettings().setDefaultTextEncodingName("UTF -8");
//        tv_audio_yuanwen.getSettings().setDefaultTextEncodingName("UTF -8");
        v_1 = rootView.findViewById(R.id.v_1);
    }

    @Override
    protected void initData() {
        Intent intent = getActivity().getIntent();
        id = intent.getStringExtra("id");
        HashMap<String,String> map = new HashMap<>();
        map.put("uid",uid);
        map.put("id", id);
        mPresenter.getAudioDetails(map);
    }

    @Override
    protected void onMyReload(View v) {
        super.onMyReload(v);
        showLoading();
        HashMap<String,String> map = new HashMap<>();
        map.put("uid",uid);
        map.put("id", id);
        mPresenter.getAudioDetails(map);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetailsSuccess(AudioDetailsBean detailsBean) {
        hideLoading();
        if(detailsBean == null){
            showEmpty();
        }else{
            tv_audio_brife.setText("\u3000\u3000"+Html.fromHtml(detailsBean.getList().getPost_content()));
//            tv_audio_brife.loadData(detailsBean.getList().getPost_content(), "text/html; charset=UTF-8", null);
            int buy = detailsBean.getBuy();
            if(buy == 1){
//                tv_audio_brife.loadData(detailsBean.getList().getPost_yuanwen(), "text/html; charset=UTF-8", null);
                tv_zheng_qu.setVisibility(View.VISIBLE);
                v_1.setVisibility(View.VISIBLE);
                tv_audio_yuanwen.setText("\u3000\u3000"+Html.fromHtml(detailsBean.getList().getPost_yuanwen()));
            }else if(buy == 0){
//                tv_audio_brife.loadData("暂无原文", "text/html; charset=UTF-8", null);
                tv_zheng_qu.setVisibility(View.GONE);
                tv_audio_yuanwen.setVisibility(View.GONE);
                v_1.setVisibility(View.GONE);
                tv_audio_yuanwen.setText("暂无原文");
            }
        }
    }

    @Override
    public void onDetailFiled() {

    }

    @Override
    public void onShouCangSuccess(AudioShouCangBean shouCangBean) {

    }

    @Override
    public void onShouCangdelSuccess(AudioShouCangBean shouCangBean) {

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
