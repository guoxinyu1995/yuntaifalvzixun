package com.ytfu.yuntaifawu.ui.register.view;

import com.ytfu.yuntaifawu.base.BaseView;
import com.ytfu.yuntaifawu.ui.register.bean.RegistBean;

public interface IRegistView extends BaseView {
    void onRegistSussess(RegistBean registBean);
    void onRegistFiled();
}
