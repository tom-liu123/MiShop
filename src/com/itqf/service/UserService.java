package com.itqf.service;

import com.itqf.entity.Users;

import java.net.UnknownHostException;
import java.sql.SQLException;

/**
 * @author LiuCongYang
 * @Version 1.0.0
 * create at @date  2021/1/4 19:51
 * copyright Beijing Murong Information Technology Co.,Ltd.
 */
public interface UserService {
    public Users login(String username,String password) throws SQLException;
    public int register(Users users) throws SQLException, UnknownHostException;
}
