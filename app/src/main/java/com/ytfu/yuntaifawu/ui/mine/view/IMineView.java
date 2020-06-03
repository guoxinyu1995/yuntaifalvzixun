package com.ytfu.yuntaifawu.ui.mine.view;

import com.ytfu.yuntaifawu.base.BaseView;
import com.ytfu.yuntaifawu.ui.mine.bean.MineBean;
import com.ytfu.yuntaifawu.ui.mine.bean.ShenHeJInduBean;

public interface IMineView extends BaseView {
    void onMineSuccess(MineBean mineBean);
    void onShenHeJinSuccess(ShenHeJInduBean shenHeJInduBean);
    void onMineFiled();
}
