package com.itqf.dao.impl;

import com.itqf.dao.UserDao;
import com.itqf.entity.Users;
import com.itqf.utils.C3P0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import javax.sql.rowset.spi.SyncResolver;
import java.sql.SQLException;

/**
 * @author LiuCongYang
 * @Version 1.0.0
 * create at @date  2021/1/4 19:43
 * copyright Beijing Murong Information Technology Co.,Ltd.
 */
public class UserDaoImpl implements UserDao {
    QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());

    @Override
    public Users findByUsername(String username) throws SQLException {

        String sql = "select u_name as username,u_password as upassword,u_status as ustatus from user where u_name=?";

        return queryRunner.query(sql,new BeanHandler<Users>(Users.class),username);
    }

    @Override
    public int insertUser(Users users) throws SQLException {
        String sql = "insert into user(u_name,u_password,u_sex,u_status,u_email,u_role,u_code) value(?,?,?,?,?,?,?)";
        int i = queryRunner.update(sql,users.getUsername(),users.getUpassword(),
                users.getUsex(),users.getUstatus(),users.getUemail(),
                users.getUrole(), users.getUcode());



        return i;
    }
}
