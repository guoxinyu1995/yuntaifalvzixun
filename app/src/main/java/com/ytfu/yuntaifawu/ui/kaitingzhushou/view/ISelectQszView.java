package com.ytfu.yuntaifawu.ui.kaitingzhushou.view;

import com.ytfu.yuntaifawu.base.BaseView;
import com.ytfu.yuntaifawu.ui.kaitingzhushou.bean.AddQszBean;
import com.ytfu.yuntaifawu.ui.kaitingzhushou.bean.KtzsListBean;
import com.ytfu.yuntaifawu.ui.kaitingzhushou.bean.SelectZhuShouBean;

/**
*
*  @Auther  gxy
*
*  @Date    2019/11/23
*
*  @Des  开庭助手列表view
*
*/
public interface ISelectQszView extends BaseView {
    void onSelectZhuShouSuccess(SelectZhuShouBean zhuShouBean);
    void onAddQszSuccess(AddQszBean addQszBean);
    void onFiled(String error);
}
