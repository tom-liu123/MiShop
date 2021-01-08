package com.itqf.service.impl;

import com.itqf.dao.ItemDao;
import com.itqf.dao.OrderDao;
import com.itqf.dao.impl.ItemDaoImpl;
import com.itqf.dao.impl.OrderDaoImpl;
import com.itqf.entity.Item;
import com.itqf.entity.Orders;
import com.itqf.service.OrderDetailService;

import java.util.List;

/**
 * @Description:
 * @Company: 刘先生
 * @Author: 刘先生
 * @Date: 2020/9/17
 * @Time: 下午4:44
 */
public class OrderDetailServiceImpl implements OrderDetailService {

    OrderDao orderDao = new OrderDaoImpl();
    ItemDao itemDao = new ItemDaoImpl();

    @Override
    public Orders findOrderAndDetail(String oid) throws  Exception{
        //订单信息   收货地址
        //该订单已经包含了收货地址信息
        Orders orders =  orderDao.findOrderDetailByOid(oid);

        //订单明细  商品信息
        List<Item> list = itemDao.findItemByOid(oid);

        //把明细赋值给orders 的
        orders.setItemList(list);

        return orders;
    }
}
