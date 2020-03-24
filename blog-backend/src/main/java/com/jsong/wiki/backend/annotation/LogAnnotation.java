package com.jsong.wiki.backend.annotation;

import java.lang.annotation.*;

/**
 * @Author: Jsong
 * @Date: 2020/3/22 20:51
 * @Description:
 */
// 注解信息添加到java文档中
@Documented
// 注解生命周期，表示注解会被保留到什么阶段，可选择编译阶段，类加载阶段，运行阶段
@Retention(RetentionPolicy.RUNTIME)
// 注解作用的位置，ElementType.METHOD 作用在方法上
@Target(ElementType.METHOD)
public @interface LogAnnotation {
    String action() default "";
}
