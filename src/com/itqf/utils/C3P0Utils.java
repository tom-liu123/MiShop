package com.itqf.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @Description: 获得DataSource对象
 * @Company: 刘先生
 * @Author: 刘先生
 * @Date: 2020/9/14
 * @Time: 下午3:14
 */
public class C3P0Utils {

    static DataSource dataSource;

    public static DataSource getDataSource() {
        //dataSource = new DruidDataSource();
        dataSource = new ComboPooledDataSource();
        return dataSource;
    }

    public  static  void  main(String[]args) throws SQLException {
        System.out.println(getDataSource().getConnection());
    }
}
