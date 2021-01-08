package com.itqf.service.impl;

import com.itqf.dao.UserDao;
import com.itqf.dao.impl.UserDaoImpl;
import com.itqf.entity.Users;
import com.itqf.service.UserService;
import com.itqf.utils.Base64Utils;
import com.itqf.utils.EmailUtils;
import com.itqf.utils.SysConstant;

import java.net.Inet4Address;
import java.sql.SQLException;

/**
 * @Description:
 * @Company: 刘先生
 * @Author: 刘先生
 * @Date: 2020/9/14
 * @Time: 下午3:54
 */
public class UserServiceImpl implements UserService {
    //dao
    UserDao userDao = new UserDaoImpl();;

    @Override
    public Users login(String username, String password) throws SQLException {
       Users u =  userDao.findByUsername(username);
        if (u!=null){
            if (u.getUpassword().equals(password)){
                return u;
            }
        }
        return null;
    }


    @Override
    public int register(Users users) throws SQLException {
        //1.新增数据库
        int i = userDao.insertUser(users);
       try {
           if (i > 0) {
               //2.给用户的邮箱发送激活码  确认邮箱是否可用
               String title = "小米商城激活账户";
               String ip = Inet4Address.getLocalHost().getHostAddress();
               // users.getUcode();  未编码的激活码  md5
               //
               String c = Base64Utils.encode(users.getUcode());
               //www.taobao.com/user
               String url="http://"+ip+":8080/userController?method=activeAccount&c="+c;
               String content = users.getUsername() + ":<br>您好,<a href='"+url+ "'>请点击该链接激活账户</a>";

               EmailUtils.sendEmail(title,content,users.getUemail());
           }
       }catch (Exception e){

       }

        return i;
    }

    @Override
    public int activeAccount(String code) throws SQLException {
        int status = userDao.findUStatusByCode(code);
        if (status==SysConstant.Status.NOT_ACTIVE.getCode()) {
          int i =   userDao.updateAccount(code);
          return  i;
        }

        return 0;
    }

    @Override
    public boolean checkName(String username) throws SQLException {
        Users u = userDao.findByUsername(username);
        if (u!=null){
            return true;
        }
        return false;
    }
}
