package com.itqf.service.impl;

import com.itqf.dao.ProductDao;
import com.itqf.dao.impl.ProductDaoImpl;
import com.itqf.entity.Product;
import com.itqf.service.ProductService;
import com.itqf.utils.PageBean;

import java.sql.SQLException;
import java.util.List;

/**
 * @Description:
 * @Company: 刘先生
 * @Author: 刘先生
 * @Date: 2020/9/15
 * @Time: 下午4:41
 */
public class ProductServiceImpl  implements ProductService {

    ProductDao productDao = new ProductDaoImpl();

    @Override
    public PageBean findProductByTypeId(int typeId, int startIndex, int pageSize) throws SQLException {
      long totalCount = productDao.findTotalProductByTypeId(typeId);

      List<Product> list = productDao.findProductByTypeId(startIndex,pageSize,typeId);

      PageBean bean = new PageBean();
      bean.setList(list);
      bean.setTotalCount(totalCount);

      return bean;
    }

    @Override
    public Product findProductDetailById(int id) throws SQLException {
        Product p = productDao.findProductById(id);

        return p;
    }
}


