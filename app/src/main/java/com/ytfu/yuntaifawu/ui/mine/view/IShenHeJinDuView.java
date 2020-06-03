package com.ytfu.yuntaifawu.ui.mine.view;

import com.ytfu.yuntaifawu.base.BaseView;
import com.ytfu.yuntaifawu.ui.mine.bean.ShenHeJInduBean;

/**
 * @Auther gxy
 * @Date 2020/5/21
 * @Des 审核进度view
 */
public interface IShenHeJinDuView extends BaseView {
    void onShenHeJinSuccess(ShenHeJInduBean shenHeJInduBean);

    void onShengheJinduFiled();
}
