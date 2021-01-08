package com.itqf.dao;

import com.itqf.entity.Product;

import java.sql.SQLException;
import java.util.List;

/**
 * @Description:
 * @Company: 刘先生
 * @Author: 刘先生
 * @Date: 2020/9/15
 * @Time: 下午4:22
 */
public interface ProductDao {

    //某类型下商品数量
    public  long findTotalProductByTypeId(int typeId) throws SQLException;
    //某类型下的商品集合  分页显示
    public List<Product>  findProductByTypeId(int startIndex,int pageSize,int typeId) throws SQLException;

    public  Product findProductById(int id) throws  SQLException;


}
