package com.itqf.utils;

/**
 * @Description:生成一个唯一的订单编号
 * @Company: 刘先生
 * @Author: 刘先生
 * @Date: 2020/9/17
 * @Time: 上午11:31
 */
public class OrderIdUtils {

    public  static String getOrderId(int uid){
        //需求号 机器码
        long time = System.currentTimeMillis();

        String orderId = time+""+uid;
        return  orderId;
    }


}
