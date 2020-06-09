package com.github.lee.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自动注入布局,目前先使用运行时反射,后期更换成编译时自动生成,提升性能
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface InjectLayout {
    /**
     * 显示的布局的id
     */
    int value() default -1;

    /**
     * 需要显示的Toolbar的布局id
     */
    int toolbarLayoutId() default -1;

    /**
     * 加载中的布局id
     */
    int loadingLayoutId() default -1;

    /**
     * 加载数据为空的布局id
     */
    int emptyLayoutId() default -1;

    /**
     * 加载失败的布局id
     */
    int errorLayoutId() default -1;
}
