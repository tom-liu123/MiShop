package com.itqf.anno;


import java.lang.reflect.Method;

/**
 * @Description:
 * @Company: 刘先生
 * @Author: 刘先生
 * @Date: 2020/9/16
 * @Time: 上午9:15
 */
public class Test {

    @ContentType(value="小尝尝",id=2,name="xiaochangchang")
    public  void  testAnno(){
        System.out.println("ssss");
    }

    public  static  void  main(String[]args) throws  Exception{
        Class clazz = Test.class;

        Method method = clazz.getMethod("testAnno");

        System.out.println(method.isAnnotationPresent(ContentType.class));

        if (method.isAnnotationPresent(ContentType.class)){
            ContentType contentType = method.getAnnotation(ContentType.class);

            System.out.println(contentType.value()+"--"+contentType.id()+"---"+contentType.name());

            if (contentType.value().equals("application/json;charset=utf-8")){
                  //json
            }
        }
    }

}
