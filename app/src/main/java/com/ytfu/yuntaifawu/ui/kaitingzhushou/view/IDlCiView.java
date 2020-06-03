package com.ytfu.yuntaifawu.ui.kaitingzhushou.view;

import com.ytfu.yuntaifawu.base.BaseView;
import com.ytfu.yuntaifawu.ui.kaitingzhushou.bean.DlcBean;
import com.ytfu.yuntaifawu.ui.kaitingzhushou.bean.DlcSendEmailBean;
import com.ytfu.yuntaifawu.ui.mine.bean.BdEmailBean;
import com.ytfu.yuntaifawu.ui.pay.bean.PayBean;
import com.ytfu.yuntaifawu.ui.pay.bean.WxPayBean;


/**
*
*  @Auther  gxy
*
*  @Date    2019/12/2
*
*  @Des  代理词view
*
*/
public interface IDlCiView extends BaseView {
    void onDlcSuccess(DlcBean dlcBean);
    void onDlicFiled();
    void onDlcSendEmailSuccess(DlcSendEmailBean sendEmailBean);
    void onDlcPaySuccess(PayBean payBean);
    void onDlcPayWxSuccess(WxPayBean wxPayBean);
    void onDlcBdEmail(BdEmailBean bdEmailBean);
}
