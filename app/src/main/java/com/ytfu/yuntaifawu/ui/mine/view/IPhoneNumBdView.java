package com.ytfu.yuntaifawu.ui.mine.view;

import com.ytfu.yuntaifawu.base.BaseView;
import com.ytfu.yuntaifawu.ui.mine.bean.PhoneNumBdBean;
import com.ytfu.yuntaifawu.ui.mine.bean.SendBdCodeBean;

/**
*
*  @Auther  gxy
*
*  @Date    2020/1/8
*
*  @Des 绑定手机号view
*
*/
public interface IPhoneNumBdView extends BaseView {
    void onSendCodeSuccess(SendBdCodeBean sendBdCodeBean);
    void onPhoneNumSuccess(PhoneNumBdBean phoneNumBdBean);
    void onBdFiled();
}
