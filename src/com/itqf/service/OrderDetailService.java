package com.itqf.service;

import com.itqf.entity.Orders;

/**
 * @Description:
 * @Company: 刘先生
 * @Author: 刘先生
 * @Date: 2020/9/17
 * @Time: 下午4:42
 */
public interface OrderDetailService {


    public Orders findOrderAndDetail(String oid) throws  Exception;


}
