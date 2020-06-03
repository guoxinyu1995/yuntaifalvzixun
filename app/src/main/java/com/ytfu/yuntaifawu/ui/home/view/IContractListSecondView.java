package com.ytfu.yuntaifawu.ui.home.view;

import com.ytfu.yuntaifawu.base.BaseView;
import com.ytfu.yuntaifawu.ui.lvshihetong.bean.ContractListSecondBean;

public interface IContractListSecondView extends BaseView {
    void onSecondListSuccess(ContractListSecondBean secondBean);
    void onFiled();
}
