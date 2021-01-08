package com.itqf.dao.impl;

import com.itqf.dao.UserDao;
import com.itqf.entity.Users;
import com.itqf.utils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;

/**
 * @Description:
 * @Company: 刘先生
 * @Author: 刘先生
 * @Date: 2020/9/14
 * @Time: 下午3:44
 */
public class UserDaoImpl implements UserDao {
    QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());

    @Override
    public Users findByUsername(String username) throws SQLException {
        //u_status 用户的状态  0  未激活  1  激活
        String sql = "select u_id as id ,u_name as username,u_password as upassword,u_status as ustatus from user where u_name=?";
        return queryRunner.query(sql,new BeanHandler<Users>(Users.class),username);
    }

    @Override
    public int insertUser(Users users) throws SQLException {
        String sql = "insert into user(u_name,u_password,u_sex," +
                "u_status,u_email,u_role,u_code) values(?,?,?,?,?,?,?)";

        int i = queryRunner.update(sql,users.getUsername(),
                users.getUpassword(),users.getUsex(),users.getUstatus(),users.getUemail(),
                users.getUrole(),users.getUcode());

        return  i;
    }

    @Override
    public int findUStatusByCode(String code) throws SQLException {
       String sql = "select u_status from user where u_code=?";
       Object o = queryRunner.query(sql,new ScalarHandler(),code);
       if (o==null){
           return -1;//
       }
        return  (Integer)o;//0 :未激活  1：激活
    }

    @Override
    public int updateAccount(String code) throws SQLException {
        String sql="update user set u_status=1,u_code=null where u_code=?";
        int i = queryRunner.update(sql,code);
        return i;
    }
}
