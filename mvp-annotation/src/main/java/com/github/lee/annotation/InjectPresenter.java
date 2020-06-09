package com.github.lee.annotation;

import com.github.lee.mvp.base.BasicPresenter;
import com.github.lee.mvp.base.BasicView;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface InjectPresenter {
    Class<? extends BasicPresenter<? extends BasicView>> value();
}