package com.ytfu.yuntaifawu.ui.home.view;

import com.ytfu.yuntaifawu.base.BaseView;
import com.ytfu.yuntaifawu.ui.home.bean.AudioShouCangBean;
import com.ytfu.yuntaifawu.ui.lvshihetong.bean.ContractDetailsBean;
import com.ytfu.yuntaifawu.ui.kaitingzhushou.bean.SendEmailBean;
import com.ytfu.yuntaifawu.ui.mine.bean.BdEmailBean;

import com.ytfu.yuntaifawu.ui.pay.bean.PayBean;
import com.ytfu.yuntaifawu.ui.pay.bean.WxPayBean;


/**
*
*  @Auther  gxy
*
*  @Date    2019/11/12
*
*  @Des 合同详情的view
*
*/
public interface IContractDetailsView extends BaseView {
    void onContractDetailsSuccess(ContractDetailsBean detailsBean);
    void onContractDetailFiled();
    void onShouCangSuccess(AudioShouCangBean shouCangBean);
    void onShouCangdelSuccess(AudioShouCangBean shouCangBean);
    void onContractPaySuccess(PayBean payBean);
    void onContractPayWxSuccess(WxPayBean wxPayBean);
    void onSendEmailSuccess(SendEmailBean emailBean);
    void onContractBdEmailSuccess(BdEmailBean bdEmailBean);
}
