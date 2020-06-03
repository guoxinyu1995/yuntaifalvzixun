package com.ytfu.yuntaifawu.ui.qisuzhuang.view;

import com.ytfu.yuntaifawu.base.BaseView;
import com.ytfu.yuntaifawu.ui.mine.bean.BdEmailBean;
import com.ytfu.yuntaifawu.ui.qisuzhuang.bean.ZjqdXqBean;
import com.ytfu.yuntaifawu.ui.qisuzhuang.bean.ZjqdXqSendEmailBean;

/**
*
*  @Auther  gxy
*
*  @Date    2019/12/18
*
*  @Des  证据清单详情view
*
*/
public interface IZjqdXqView extends BaseView {
    void onZjqdXqSuccess(ZjqdXqBean zjqdXqBean);
    void onZjqdXqFiled();
    void onZjqdXqSendSuccess(ZjqdXqSendEmailBean sendEmailBean);
    void onBdEmailSuccess(BdEmailBean bdEmailBean);
}
