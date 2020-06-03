package com.ytfu.yuntaifawu.ui.kaitingzhushou.view;

import com.ytfu.yuntaifawu.base.BaseView;
import com.ytfu.yuntaifawu.ui.kaitingzhushou.bean.QylxtgBean;
import com.ytfu.yuntaifawu.ui.kaitingzhushou.bean.SendEmailBean;
import com.ytfu.yuntaifawu.ui.mine.bean.BdEmailBean;

/**
*
*  @Auther  gxy
*
*  @Date    2019/11/27
*
*  @Des  企业另需提供
*
*/
public interface IQylxtgView extends BaseView {
    void onQylxtgSuccess(QylxtgBean qylxtgBean);
    void onQylxtgFiled(String str);
    void onSendEmail(SendEmailBean emailBean);
    void onBdEmailSuccess(BdEmailBean bdEmailBean);
}
