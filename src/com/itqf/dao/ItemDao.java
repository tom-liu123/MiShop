package com.itqf.dao;

import com.itqf.entity.Item;

import java.util.List;

/**
 * @Description:
 * @Company: 刘先生
 * @Author: 刘先生
 * @Date: 2020/9/17
 * @Time: 上午11:23
 */
public interface ItemDao {

    //新增一条订单明细记录
    public  int addItem(Item item) throws  Exception;

    /**
     * 查询某个订单的明细和该明细下的商品信息
     */
    public List<Item> findItemByOid(String oid) throws  Exception;//



}
