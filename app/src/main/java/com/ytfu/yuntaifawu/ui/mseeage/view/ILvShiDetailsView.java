package com.ytfu.yuntaifawu.ui.mseeage.view;

import com.ytfu.yuntaifawu.base.BaseView;
import com.ytfu.yuntaifawu.ui.mseeage.bean.LvShiDetailsBean;
import com.ytfu.yuntaifawu.ui.mseeage.bean.LvShiDetailsBean1;

public interface ILvShiDetailsView extends BaseView {
    void onLvShiDetailsSuccess(LvShiDetailsBean1 lvShiDetailsBean);

    void onLvShiDetailsFiled();
}
