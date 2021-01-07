package com.itqf.dao;

import com.itqf.entity.Users;

import java.sql.SQLException;

/**
 * @author LiuCongYang
 * @Version 1.0.0
 * create at @date  2021/1/4 19:41
 * copyright Beijing Murong Information Technology Co.,Ltd.
 */
public interface UserDao {
    public Users findByUsername(String username) throws SQLException;
    public int insertUser(Users users) throws SQLException;
}
