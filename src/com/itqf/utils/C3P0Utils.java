package com.itqf.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.sql.SQLException;

/**
 * @author LiuCongYang
 * @Version 1.0.0
 * create at @date  2021/1/4 19:14
 * copyright Beijing Murong Information Technology Co.,Ltd.
 */
public class C3P0Utils {
    static DataSource dataSource;
    public static DataSource getDataSource(){
        dataSource = new ComboPooledDataSource();
        return dataSource;
    }

}
