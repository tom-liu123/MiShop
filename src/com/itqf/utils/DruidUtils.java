package com.itqf.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.util.Properties;

/**
 * @Description:  获得一个激活码  用随机数  或者  随机字母串
 * @Company: 刘先生
 * @Author: 刘先生
 * @Date: 2020/9/14
 * @Time: 下午5:01
 */
public class DruidUtils {

    //连接池
    private static DruidDataSource ds=null;
    static {
        try {
            InputStream is= DruidUtils.class.getClassLoader().getResourceAsStream("database.properties");
            Properties properties=new Properties();
            properties.load(is);
            ds= (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            System.out.println("创建数据源失败:"+e.getMessage());
        }
    }
    public static DataSource getDataSource(){
        return  ds;
    }

}
