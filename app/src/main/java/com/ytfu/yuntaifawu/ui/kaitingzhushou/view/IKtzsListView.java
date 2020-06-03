package com.ytfu.yuntaifawu.ui.kaitingzhushou.view;

import com.ytfu.yuntaifawu.base.BaseView;
import com.ytfu.yuntaifawu.ui.kaitingzhushou.bean.KtzsListBean;
import com.ytfu.yuntaifawu.ui.kaitingzhushou.bean.SelectZhuShouBean;
import com.ytfu.yuntaifawu.ui.mine.bean.BdEmailBean;

/**
*
*  @Auther  gxy
*
*  @Date    2019/11/23
*
*  @Des  开庭助手列表view
*
*/
public interface IKtzsListView extends BaseView {
    void onSuccess(KtzsListBean listBean);
    void onFiled(String error);
}
