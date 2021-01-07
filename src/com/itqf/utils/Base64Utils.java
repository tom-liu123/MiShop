package com.itqf.utils;

import java.util.Base64;

/**
 * @Description:
 * @Company: 千锋互联
 * @Author: 李丽婷
 * @Date: 2020/9/14
 * @Time: 下午5:36
 */
public class Base64Utils {

    //加密
    public static String encode(String msg){
        return Base64.getEncoder().encodeToString(msg.getBytes());
    }
    //解密
    public static String decode(String msg){
        return new String(Base64.getDecoder().decode(msg));
    }

    public static void main(String[] args) {
        System.out.println(Base64Utils.decode("aaa"));
    }

}
