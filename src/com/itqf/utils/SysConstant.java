package com.itqf.utils;

/**
 * @Description:
 * @Company: 刘先生
 * @Author: 刘先生
 * @Date: 2020/9/14
 * @Time: 下午4:01
 */
public class SysConstant {

    public  enum  Status{
         NOT_ACTIVE(0),ACTIVE(1);
         int code ;
         Status(int code){
            this.code = code;
         }

        public int  getCode(){
             return  code;
        }

    }


    public  static final int NOT_ACTIVE=0;
    public  static final  int CUSTOMER=0;


    public  static final String FORWARD="forward";
    public  static final String REDIRECT="redirect";
    public  static final String FLAG=":";

    //订单状态 0 未付款，1已经付款未发货 2发货待收货 3 收货待评价 4订单完成 5 退货状态



}
