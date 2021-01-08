package com.itqf.dao.impl;

import com.itqf.dao.TypesDao;
import com.itqf.entity.Types;
import com.itqf.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * @Description:
 * @Company: 刘先生
 * @Author: 刘先生
 * @Date: 2020/9/15
 * @Time: 下午3:38
 */
public class TypesDaoImpl implements TypesDao {
    QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
    @Override
    public List<Types> findAll() throws SQLException {
        String sql = "select t_id as tid,t_name as tname,t_info as tinfo from  `type`";
        List<Types> list = runner.query(sql,new BeanListHandler<Types>(Types.class));


        return list;
    }
}
