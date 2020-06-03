package com.ytfu.yuntaifawu.ui.falvguwen.view;

import com.ytfu.yuntaifawu.base.BaseView;
import com.ytfu.yuntaifawu.ui.falvguwen.bean.LegalAdviserListBean;
/**
*
*  @Auther  gxy
*
*  @Date    2019/11/22
*
*  @Des  法律顾问列表view
*
*/
public interface ILegalAdviserListView extends BaseView {
    void onSuccess(LegalAdviserListBean liebiaoBean);
    void onFiled(String errorMessage);
}
