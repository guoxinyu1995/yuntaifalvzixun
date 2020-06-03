package com.ytfu.yuntaifawu.ui.qisuzhuang.view;

import com.ytfu.yuntaifawu.base.BaseView;
import com.ytfu.yuntaifawu.ui.kaitingzhushou.bean.SendEmailBean;
import com.ytfu.yuntaifawu.ui.mine.bean.BdEmailBean;

public interface IQszYlXqView extends BaseView {
    void onQszYlXqSendEmail(SendEmailBean sendEmailBean);
    void onQszYlXqBdEmailSuccess(BdEmailBean bdEmailBean);
    void onFiled();
}
