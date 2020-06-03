package com.ytfu.yuntaifawu.ui.consult.view;

import com.ytfu.yuntaifawu.base.BaseView;
import com.ytfu.yuntaifawu.ui.consult.bean.MIanFeiFaBuBean;
import com.ytfu.yuntaifawu.ui.consult.bean.ZiXunNavBean;

public interface IZiXunNavView extends BaseView {
    //咨询分类
    void onZiXunNavSuccess(ZiXunNavBean ziXunNavBean);
    //免费发布
    void onMianFeiFaBuSuccess(MIanFeiFaBuBean mIanFeiFaBuBean);
    void onZiXunNavFiled();
}
