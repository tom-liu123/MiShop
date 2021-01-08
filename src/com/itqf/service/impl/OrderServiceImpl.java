package com.itqf.service.impl;

import com.itqf.dao.CartDao;
import com.itqf.dao.ItemDao;
import com.itqf.dao.OrderDao;
import com.itqf.dao.impl.CartDaoImpl;
import com.itqf.dao.impl.ItemDaoImpl;
import com.itqf.dao.impl.OrderDaoImpl;
import com.itqf.entity.Cart;
import com.itqf.entity.Item;
import com.itqf.entity.Orders;
import com.itqf.service.OrderService;
import com.itqf.utils.OrderIdUtils;

import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @Company: 刘先生
 * @Author: 刘先生
 * @Date: 2020/9/17
 * @Time: 上午11:28
 */
public class OrderServiceImpl implements OrderService {
    OrderDao orderDao = new OrderDaoImpl();
    ItemDao itemDao = new ItemDaoImpl();
    CartDao cartDao = new CartDaoImpl();

    @Override
    public int createOrder(Orders orders) throws Exception {
        //1.新增订单记录
        String orderId = OrderIdUtils.getOrderId(orders.getUid());//hdiwhfoiwqfhoiewfhowei
        orders.setOid(orderId);
        orders.setOtime(new Date());
        orders.setOstate(0);
        int i = orderDao.addOrder(orders);
        //2.订单明细
        /*
      iid   oid;//订单编号pid;//商品编号 icount;//订单项的小计  inum;//数量
         */
        //2.1 先查当前用户的购物车数据
        List<Cart> list =  cartDao.findAllCart(orders.getUid());
        for (Cart cart : list) {
            //2.2 通过购物车数据存储到订单明细表
            Item item = new Item();
            item.setOid(orderId);
            item.setPid(cart.getPid());
            item.setInum(cart.getCnum());
            item.setIcount(cart.getCcount());

            //新增到数据库
            itemDao.addItem(item);
        }
        //3.清空用户的购物车
        i+=cartDao.delCartById(orders.getUid());

        return i;
    }

    @Override
    public List<Orders> findAllOrderByUId(int uid) throws Exception {


        return orderDao.findAllOrderByUId(uid);
    }

    @Override
    public int updateOrderState(Orders orders) throws Exception {
        return orderDao.updateOrderState(orders);
    }
}
