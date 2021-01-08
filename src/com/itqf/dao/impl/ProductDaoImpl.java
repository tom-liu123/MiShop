package com.itqf.dao.impl;

import com.itqf.dao.ProductDao;
import com.itqf.entity.Product;
import com.itqf.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * @Description:
 * @Company: 刘先生
 * @Author: 刘先生
 * @Date: 2020/9/15
 * @Time: 下午4:24
 */
public class ProductDaoImpl implements ProductDao {

    QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());

    @Override
    public long findTotalProductByTypeId(int typeId) throws SQLException {
        //select price from
        String sql="select count(1) from product where t_id=?";
        long count = (Long) runner.query(sql,new ScalarHandler(),typeId);
        return count;
    }

    @Override
    public List<Product> findProductByTypeId(int startIndex, int pageSize, int typeId) throws SQLException {
        String sql = "select p_id as pid,p_name as pname" +
                ",p_price as pprice,p_image as pimage ," +
                "p_info as pinfo,p_time as ptime,p_state as pstate from product where t_id=? limit ?,?";


        return runner.query(sql,new BeanListHandler<Product>(Product.class),typeId,startIndex,pageSize);
    }

    @Override
    public Product findProductById(int id) throws SQLException {
        String sql = "select p_id as pid,p_name as pname" +
                ",p_price as pprice,p_image as pimage ," +
                "p_info as pinfo,p_time as ptime,p_state as pstate " +
                ",t.t_id as tid,t.t_name as tname  from product p" +
                " inner  join type t on p.t_id = t.t_id where p.p_id=? ";
        Product product = runner.query(sql,new BeanHandler<Product>(Product.class),id
        );

        return  product;

    }
}
