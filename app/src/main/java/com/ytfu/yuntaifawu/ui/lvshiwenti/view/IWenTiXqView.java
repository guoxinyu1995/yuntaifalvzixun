package com.ytfu.yuntaifawu.ui.lvshiwenti.view;

import com.ytfu.yuntaifawu.base.BaseView;
import com.ytfu.yuntaifawu.ui.lvshiwenti.bean.WenTiXiangQingBean;

/**
*
*  @Auther  gxy
*
*  @Date    2020/5/26
*
*  @Des  问题详情view
*
*/
public interface IWenTiXqView extends BaseView {
    void onWenTiXqSuccess(WenTiXiangQingBean wenTiXiangQingBean);
    void onWenTiXqFiled(String error);
}
