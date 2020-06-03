package com.ytfu.yuntaifawu.utils;

import android.os.CountDownTimer;

/**
 * 发送短信验证码倒计时类
 * @author gxy
 */
public class MyCountDownTimer extends CountDownTimer {

    private CountListener countListener;

    /**
     * @param millisInFuture    The number of millis in the future from the call
     *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
     *                          is called.
     * @param countDownInterval The interval along the way to receive
     *                          {@link #onTick(long)} callbacks.
     */
    public MyCountDownTimer(long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
    }

    @Override
    public void onFinish() {
        countListener.onFinish();
    }

    @Override
    public void onTick(long millisUntilFinished) {
        countListener.onTick(millisUntilFinished);
    }

    public void setCountListener(CountListener countListener) {
        this.countListener = countListener;
    }

    public interface CountListener {
        void onTick(Long millisUntilFinished);

        void onFinish();
    }
}
