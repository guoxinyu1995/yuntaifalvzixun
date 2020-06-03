package com.ytfu.yuntaifawu.ui.home.view;

import com.ytfu.yuntaifawu.base.BaseView;
import com.ytfu.yuntaifawu.ui.home.bean.AudioDetailsBean;
import com.ytfu.yuntaifawu.ui.home.bean.AudioListerBean;
import com.ytfu.yuntaifawu.ui.home.bean.AudioShouCangBean;
import com.ytfu.yuntaifawu.ui.pay.bean.PayBean;
import com.ytfu.yuntaifawu.ui.pay.bean.WxPayBean;


/**
*
*  @Auther  gxy
*
*  @Date    2019/11/12
*
*  @Des 音频详情的view
*
*/
public interface IAudioDetailsView extends BaseView {
    void onDetailsSuccess(AudioDetailsBean detailsBean);
    void onDetailFiled();
    void onShouCangSuccess(AudioShouCangBean shouCangBean);
    void onShouCangdelSuccess(AudioShouCangBean shouCangBean);
    void onAudioPaySuccess(PayBean payBean);
    void onAudioPayWxSuccess(WxPayBean wxPayBean);
    void onListenerSuccess(AudioListerBean listerBean);
}
