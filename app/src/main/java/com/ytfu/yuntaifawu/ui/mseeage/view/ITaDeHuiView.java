package com.ytfu.yuntaifawu.ui.mseeage.view;

import com.ytfu.yuntaifawu.base.BaseView;
import com.ytfu.yuntaifawu.ui.mseeage.bean.TaHuiDaBean;

/**
*
*  @Auther  gxy
*
*  @Date    2020/4/20
*
*  @Des Ta的回答view
*
*/
public interface ITaDeHuiView extends BaseView {
    void onTaHuiSuccess(TaHuiDaBean taHuiDaBean);
    void onTaHuiFiled();

}
