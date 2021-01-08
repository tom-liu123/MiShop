package com.itqf.service.impl;

import com.itqf.dao.UserDao;
import com.itqf.dao.impl.UserDaoImpl;
import com.itqf.entity.Users;
import com.itqf.service.UserService;
import com.itqf.utils.Base64Utils;
import com.itqf.utils.EmailUtils;
import com.itqf.utils.SysConstant;

import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.sql.SQLException;

/**
 * @author LiuCongYang
 * @Version 1.0.0
 * create at @date  2021/1/4 19:53
 * copyright Beijing Murong Information Technology Co.,Ltd.
 */
public class UserServiceImpl implements UserService {
    //dao
    UserDao userDao = new UserDaoImpl();
    @Override
    public Users login(String username, String password) throws SQLException {
        Users u =userDao.findByUsername(username);
        if (u!= null){
            if (u.getUpassword().equals(password)){
                return u;
            }
        }

        return null;
    }

    @Override
    public int register(Users users) throws SQLException, UnknownHostException {
        //�������ݿ�
        int i = userDao.insertUser(users);
        if (i>0){
            //���û������䷢��֤��,ȷ�������Ƿ����
            String title ="С���̳��û�";
            String ip = Inet4Address.getLocalHost().getHostAddress();
            //δ����ļ�����   md5
            String c = Base64Utils.encode(users.getUcode());
            String url = "http://"+ip+":8080/userController?method=activeAccount&c="+c;
            String content = users.getUsername()+"<br>���ã�<a herf='"+url+"'>�뵥��������</a>�����˻�";


            EmailUtils.sendEmail(title,content,users.getUemail());
        }
        return i;
    }

    @Override
    public int activeAccount(String code) throws SQLException {
        int status = userDao.findUstatus(code);
        if (status == SysConstant.Status.NOT_ACTIVE.getCode()){
           int i = userDao.updateAccount(code);
            return i;
        }
        return 0;
    }
}
