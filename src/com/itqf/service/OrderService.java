package com.itqf.service;

import com.itqf.entity.Orders;

import java.util.List;

/**
 * @Description:
 * @Company: 刘先生
 * @Author: 刘先生
 * @Date: 2020/9/17
 * @Time: 上午10:13
 */
public interface OrderService {


    //创建订单
    public  int createOrder(Orders orders) throws  Exception;

    public List<Orders> findAllOrderByUId( int uid) throws  Exception;

    public  int updateOrderState(Orders orders) throws  Exception;



}
