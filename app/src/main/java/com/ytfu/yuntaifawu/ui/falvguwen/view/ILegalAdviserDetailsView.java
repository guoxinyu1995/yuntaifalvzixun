package com.ytfu.yuntaifawu.ui.falvguwen.view;

import com.ytfu.yuntaifawu.base.BaseView;
import com.ytfu.yuntaifawu.ui.falvguwen.bean.LegalAdviserDetailsBean;
import com.ytfu.yuntaifawu.ui.home.bean.AudioShouCangBean;
import com.ytfu.yuntaifawu.ui.kaitingzhushou.bean.SendEmailBean;
import com.ytfu.yuntaifawu.ui.mine.bean.BdEmailBean;

import com.ytfu.yuntaifawu.ui.pay.bean.PayBean;
import com.ytfu.yuntaifawu.ui.pay.bean.WxPayBean;


public interface ILegalAdviserDetailsView extends BaseView {
    void onSuccess(LegalAdviserDetailsBean xinagQingBean);
    void onFiled(String error);
    void onShouCangSuccess(AudioShouCangBean shouCangBean);
    void onShouCangdelSuccess(AudioShouCangBean shouCangBean);
    void onFlgwPaySuccess(PayBean payBean);
    void onFlgwPayWxSuccess(WxPayBean wxPayBean);
    void onSendEmailSuccess(SendEmailBean emailBean);
    void onFlguBdEmail(BdEmailBean bdEmailBean);
}
