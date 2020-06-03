package com.ytfu.yuntaifawu.ui.mine.view;

import com.ytfu.yuntaifawu.base.BaseView;

import java.util.List;

/**
 * 个人简历 view
 */
public interface PersonalProfileView extends BaseView {

    /**
     * 获取服务器推荐简历成功
     */
    void onGetResumeTemplateSuccess(List<String> list);

    void onGetResumeTemplateFail();


    void getRandomTemplateToShow(List<String> list);

}
