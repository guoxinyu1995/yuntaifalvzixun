package com.ytfu.yuntaifawu.ui.qisuzhuang.view;

import com.ytfu.yuntaifawu.base.BaseView;
import com.ytfu.yuntaifawu.ui.qisuzhuang.bean.QszFenLeiBean;

public interface IQszView extends BaseView {
    void onQszSuccess(QszFenLeiBean fenLeiBean);
    void onQszFiled();
}
