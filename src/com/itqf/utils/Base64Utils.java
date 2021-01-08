package com.itqf.utils;

import java.util.Base64;

public class Base64Utils {
    //加密
    public static String encode(String msg){
        return Base64.getEncoder().encodeToString(msg.getBytes());
    }
    //解密
    public static String decode(String msg){
        return new String(Base64.getDecoder().decode(msg));
    }

    public  static  void  main(String[]args){
        System.out.println(Base64Utils.encode("HFURHNUUEGMZYJTWJBEW"));

    }
}
