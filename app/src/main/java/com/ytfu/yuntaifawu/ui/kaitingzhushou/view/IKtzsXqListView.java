package com.ytfu.yuntaifawu.ui.kaitingzhushou.view;

import com.ytfu.yuntaifawu.base.BaseView;
import com.ytfu.yuntaifawu.ui.kaitingzhushou.bean.KtzsXqListBean;
import com.ytfu.yuntaifawu.ui.kaitingzhushou.bean.SendEmailBean;
import com.ytfu.yuntaifawu.ui.mine.bean.BdEmailBean;

/**
*
*  @Auther  gxy
*
*  @Date    2019/11/24
*
*  @Des  开庭助手详情list  view
*
*/
public interface IKtzsXqListView extends BaseView {
    void onSuccess(KtzsXqListBean xqListBean);
    void onFiled();
    void onSendEmail(SendEmailBean emailBean);
    void onKtzsBdEmail(BdEmailBean bdEmailBean);
}
