package com.itqf.dao;

import com.itqf.entity.Users;

import java.sql.SQLException;

/**
 * @Description:
 * @Company: 刘先生
 * @Author: 刘先生
 * @Date: 2020/9/14
 * @Time: 下午3:43
 */
public interface UserDao {

    public Users findByUsername(String username) throws SQLException;
    public int insertUser(Users users) throws SQLException;

    public   int   findUStatusByCode(String code) throws SQLException ;

    public  int  updateAccount(String code) throws SQLException;




}
