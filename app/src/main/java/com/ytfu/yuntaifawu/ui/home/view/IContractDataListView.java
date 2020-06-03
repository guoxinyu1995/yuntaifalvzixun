package com.ytfu.yuntaifawu.ui.home.view;

import com.ytfu.yuntaifawu.base.BaseView;
import com.ytfu.yuntaifawu.ui.lvshihetong.bean.ContractDatalistBean;

public interface IContractDataListView extends BaseView {
    void onDataListSuccess(ContractDatalistBean datalistBean);
    void onFiled();
}
