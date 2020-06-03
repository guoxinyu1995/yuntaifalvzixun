package com.ytfu.yuntaifawu.im;

public interface OnMessageStatusCallback {
    void onSuccess();

    void onError(int code, String msg);
}
