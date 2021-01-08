package com.itqf.service;

import com.itqf.entity.Users;

import java.sql.SQLException;

/**
 * @Description:  处理业务逻辑的  处理事务
 * @Company: 刘先生
 * @Author: 刘先生
 * @Date: 2020/9/14
 * @Time: 下午3:51
 */
public interface UserService {

    public Users login(String username,String password) throws SQLException;

    public int register(Users users) throws SQLException;

    public  int activeAccount(String code) throws SQLException;


    public boolean checkName(String username) throws  SQLException;


}
