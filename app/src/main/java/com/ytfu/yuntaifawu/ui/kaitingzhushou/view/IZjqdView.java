package com.ytfu.yuntaifawu.ui.kaitingzhushou.view;

import com.ytfu.yuntaifawu.base.BaseView;
import com.ytfu.yuntaifawu.ui.kaitingzhushou.bean.ZjqdBean;
import com.ytfu.yuntaifawu.ui.pay.bean.PayBean;
import com.ytfu.yuntaifawu.ui.pay.bean.WxPayBean;


public interface IZjqdView extends BaseView {
    void onZjqdSuccess(ZjqdBean zjqdBean);
    void onZjqdBuySuccess(PayBean payBean);
    void onZjqdBuyWxSuccess(WxPayBean wxPayBean);
    void onZjqdFiled();
}
