package com.ytfu.yuntaifawu.ui.lvshiwenti.view;

import com.ytfu.yuntaifawu.base.BaseView;
import com.ytfu.yuntaifawu.ui.lvshiwenti.bean.LvShiZiXunListBean;

/**
 * @Auther gxy
 * @Date 2020/5/25
 * @Des 律师端 问题列表view
 */
public interface IlvshiZiXunListView extends BaseView {
    void onLvShiListSuccess(LvShiZiXunListBean lvShiZiXunListBean);

    void onLvShiListFiled(String error);
}
