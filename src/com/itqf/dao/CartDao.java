package com.itqf.dao;

import com.itqf.entity.Cart;

import java.util.List;

/**
 * @Description:
 * @Company: 刘先生
 * @Author: 刘先生
 * @Date: 2020/9/16
 * @Time: 上午11:07
 */
public interface CartDao {

    //1,怎么判断用户购物车中有该商品
    public Cart  findCartByUIdAndPId(int uid,int pid) throws  Exception;

    //有，修改数量  和  小计（价格*数量）
    public int  update(Cart cart) throws  Exception;

    //没有，新增一条
    public int  addCart(Cart cart) throws  Exception;

    //查询当前登录用户的购物车
    public List<Cart> findAllCart(int uid) throws  Exception;


    //删除购物车
    public  int  delCart(int uid,int pid) throws  Exception;
    //当前用户的所有商品
    public  int  delCartById(int uid) throws  Exception;




}
