package com.itqf.service;

import com.itqf.entity.Product;
import com.itqf.utils.PageBean;

import java.sql.SQLException;

/**
 * @Description:
 * @Company: 刘先生
 * @Author: 刘先生
 * @Date: 2020/9/15
 * @Time: 下午4:32
 */
public interface ProductService {

    public PageBean findProductByTypeId(int typeId,int startIndex,int pageSize) throws SQLException;

    public Product  findProductDetailById(int id) throws SQLException;
}
