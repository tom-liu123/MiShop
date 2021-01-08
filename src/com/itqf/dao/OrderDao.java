package com.itqf.dao;

import com.itqf.entity.Orders;

import java.util.List;

/**
 * @Description:
 * @Company: 刘先生
 * @Author: 刘先生
 * @Date: 2020/9/17
 * @Time: 上午11:13
 */
public interface OrderDao {

    public int addOrder(Orders orders) throws  Exception;

    public List<Orders> findAllOrderByUId(int uid) throws  Exception;

    public Orders findOrderDetailByOid(String oid) throws  Exception;

    public int updateOrderState(Orders orders) throws  Exception;



}
