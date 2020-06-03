package com.ytfu.yuntaifawu.ui.mine.view;

import com.ytfu.yuntaifawu.base.BaseView;
import com.ytfu.yuntaifawu.ui.mine.bean.GeQianBean;

/**
*
*  @Auther  gxy
*
*  @Date    2020/1/6
*
*  @Des 个性签名view
*
*/
public interface IGeQianView extends BaseView {
    void onGqSuccess(GeQianBean geQianBean);
    void onGqFiled();
}
