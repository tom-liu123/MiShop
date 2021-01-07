package com.itqf.utils;

import java.util.Random;

/**
 * @Description:  获得一个激活码  用随机数  或者  随机字母串
 * @Company: 千锋互联
 * @Author: 李丽婷
 * @Date: 2020/9/14
 * @Time: 下午5:01
 */
public class ActiveCodeUtils {

    public  static String   getActiveCode(){
        StringBuilder s = new StringBuilder();
        for(int i = 0;i<20;i++){
            char a = (char)(new Random().nextInt(26)+'A');
            s.append(a);
        }
        return s.toString();
    }

    public  static  void  main(String[]args){
        System.out.println(getActiveCode());
    }

}
