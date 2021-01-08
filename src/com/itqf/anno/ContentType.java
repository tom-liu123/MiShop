package com.itqf.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description:
 * @Company: 刘先生
 * @Author: 刘先生
 * @Date: 2020/9/15
 * @Time: 下午2:32
 */
@Retention(RetentionPolicy.RUNTIME)  //被反射读取到  class
@Target(ElementType.METHOD)   //应用到方法上

public @interface ContentType {
    String  value();
    int id() default 1;
    String name() default  "Ellie";
}
