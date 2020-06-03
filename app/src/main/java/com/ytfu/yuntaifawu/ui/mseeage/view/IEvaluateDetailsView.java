package com.ytfu.yuntaifawu.ui.mseeage.view;

import com.ytfu.yuntaifawu.base.BaseView;
import com.ytfu.yuntaifawu.ui.mseeage.bean.PingJIaDetailsBean;

public interface IEvaluateDetailsView extends BaseView {
    void onEvaluateDetailsSuccess(PingJIaDetailsBean pingJIaDetailsBean);
    void onEvaluateDetailsFiled();
}
