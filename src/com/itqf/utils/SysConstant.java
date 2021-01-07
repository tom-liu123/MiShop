package com.itqf.utils;

/**
 * @Description:
 * @Company: 千锋互联
 * @Author: 李丽婷
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


}
