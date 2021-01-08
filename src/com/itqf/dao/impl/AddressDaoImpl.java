package com.itqf.dao.impl;

import com.itqf.dao.AddressDao;
import com.itqf.entity.Address;
import com.itqf.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * @Description:
 * @Company: 刘先生
 * @Author: 刘先生
 * @Date: 2020/9/17
 * @Time: 上午10:04
 */
public class AddressDaoImpl implements AddressDao {

    QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());//connection

    @Override
    public List<Address> findAllAddress(int uid) throws SQLException {
        String sql="select a_id as aid,a_name as aname,a_phone as aphone ,a_detail as adetail" +
                ",a_state as astate from address where u_id=?";

        return queryRunner.query(sql,new BeanListHandler<Address>(Address.class),uid);//
    }
}
