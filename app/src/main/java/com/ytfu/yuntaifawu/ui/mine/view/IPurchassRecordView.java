package com.ytfu.yuntaifawu.ui.mine.view;

import com.ytfu.yuntaifawu.base.BaseView;
import com.ytfu.yuntaifawu.ui.mine.bean.PurchaseRecordBean;

public interface IPurchassRecordView extends BaseView {
    void onPurcheRecordSuccess(PurchaseRecordBean recordBean);
    void onFiled();
}
