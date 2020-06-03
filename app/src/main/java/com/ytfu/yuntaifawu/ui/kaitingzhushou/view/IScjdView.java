package com.ytfu.yuntaifawu.ui.kaitingzhushou.view;

import com.ytfu.yuntaifawu.base.BaseView;
import com.ytfu.yuntaifawu.ui.kaitingzhushou.bean.ScjdBean;
import com.ytfu.yuntaifawu.ui.kaitingzhushou.bean.SendEmailBean;
import com.ytfu.yuntaifawu.ui.mine.bean.BdEmailBean;

/**
*
*  @Auther  gxy
*
*  @Date    2019/11/27
*
*  @Des   伤残鉴定view
*
*/
public interface IScjdView extends BaseView {
    void onScjdSuccess(ScjdBean scjdBean);
    void onScjdFiled(String str);
    void onSendEmail(SendEmailBean emailBean);
    void onScjdBdEmail(BdEmailBean bdEmailBean);
}
