package com.ytfu.yuntaifawu.ui.mine.present;

import com.ytfu.yuntaifawu.apis.ResumeService;
import com.ytfu.yuntaifawu.base.BasePresenter;
import com.ytfu.yuntaifawu.helper.BaseRxObserver;
import com.ytfu.yuntaifawu.ui.mine.bean.ResumeResponseBean;
import com.ytfu.yuntaifawu.ui.mine.view.PersonalProfileView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import io.reactivex.Observable;

/**
 * 个人简历P成层代码实现
 */
public class PersonalProfilePresent extends BasePresenter<PersonalProfileView> {

    /**
     * 上一次选中的模板
     */
    private List<String> oldSelect = new ArrayList<>();

    /**
     * 获取简历模板
     */
    public void getResumeTemplate() {
        Observable<ResumeResponseBean> ob = createService(ResumeService.class)
                .getResumeTemplate();
        requestRemote(ob, new BaseRxObserver<ResumeResponseBean>() {
            @Override
            public void onNextImpl(ResumeResponseBean data) {
                if (null == data) {
                    getView().onGetResumeTemplateFail();
                    return;
                }
                int status = data.getStatus();
                if (status != 200) {
                    getView().onGetResumeTemplateFail();
                    return;
                }
                List<String> list = data.getList();
                getView().onGetResumeTemplateSuccess(list);
            }

            @Override
            public void onErrorImpl(String errorMessage) {
                getView().onGetResumeTemplateFail();
            }
        });
    }

    /**
     * 从data中获取needSize长度的随机模板
     */
    public void getRandomTemplate(List<String> data, int needSize) {
        if (needSize >= data.size()) {
            return;
        }

        Random random = new Random();
        List<String> select = new ArrayList<>();
        for (int i = 0; i < needSize; i++) {
            int index = random.nextInt(data.size());
            String selectItem = data.get(index);
            if (select.contains(selectItem)) {
                continue;
            }
            select.add(selectItem);
        }
        oldSelect.clear();
        oldSelect.addAll(select);
        getView().getRandomTemplateToShow(select);
    }
}
