package com.ytfu.yuntaifawu.utils;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @作者  gxy
 *
 * @创建时间  2019/11/9
 *
 * @描述
 */
public class MessageEvent {
    /**
     * Additional fields if needed
     */
    private int what;
    private String key;
    private String message;
    private List<String> list;
    private String[] params;
    private Serializable value;

    public MessageEvent(int what) {
        this.what = what;
    }

    public MessageEvent(String message) {
        this.message = message;
    }

    public MessageEvent(String key, String message) {
        this.key = key;
        this.message = message;
    }

    public MessageEvent(int what, String message) {
        this.what = what;
        this.message = message;
    }

    public MessageEvent(int what, List<String> list) {
        this.what = what;
        this.list = list;
    }

    public MessageEvent(int what, String[] params) {
        this.what = what;
        this.params = params;
    }

    public MessageEvent(int what, Serializable value) {
        this.what = what;
        this.value = value;
    }

    public int getWhat() {
        return what;
    }

    public void setWhat(int what) {
        this.what = what;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public String[] getParams() {
        return params;
    }

    public void setParams(String[] params) {
        this.params = params;
    }

    public Serializable getValue() {
        return value;
    }

    public void setValue(Serializable value) {
        this.value = value;
    }
}