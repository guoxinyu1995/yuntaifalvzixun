package com.ytfu.yuntaifawu.ui.lawyer.chat.bean;


import androidx.annotation.Keep;

@Keep
public class HistoryChatBodyBean {

    /**
     * type : 1
     * text : 123
     */
    private int type;
    private String text;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
