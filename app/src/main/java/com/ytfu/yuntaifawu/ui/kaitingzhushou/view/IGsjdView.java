package com.ytfu.yuntaifawu.ui.kaitingzhushou.view;

import com.ytfu.yuntaifawu.base.BaseView;
import com.ytfu.yuntaifawu.ui.kaitingzhushou.bean.GsjdBean;
import com.ytfu.yuntaifawu.ui.kaitingzhushou.bean.SendEmailBean;
import com.ytfu.yuntaifawu.ui.mine.bean.BdEmailBean;

/**
*
*  @Auther  gxy
*
*  @Date    2019/11/27
*
*  @Des  工伤鉴定view
*
*/
public interface IGsjdView extends BaseView {
    void onGsjdSuccess(GsjdBean gsjdBean);
    void onGsjdFiled(String str);
    void onSendEmailSuccess(SendEmailBean emailBean);
    void onGsjdBdEmailSuccess(BdEmailBean bdEmailBean);
}
