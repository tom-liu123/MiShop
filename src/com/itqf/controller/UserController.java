package com.itqf.controller;

import cn.dsna.util.images.ValidateCode;
import com.itqf.anno.ContentType;
import com.itqf.entity.Users;
import com.itqf.service.UserService;
import com.itqf.service.impl.UserServiceImpl;
import com.itqf.utils.ActiveCodeUtils;
import com.itqf.utils.Base64Utils;
import com.itqf.utils.SysConstant;
import org.apache.commons.beanutils.BeanUtils;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description:  处理用户所有业务
 * @Company: 刘先生
 * @Author: 刘先生
 * @Date: 2020/9/14
 * @Time: 下午3:36
 */
@WebServlet("/userController")
public class UserController extends BaseController{

    UserService userService = new UserServiceImpl();


    public String activeAccount(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //String url="http://"+ip+":8080/userController?method=activeAccount&c="+c;
        //激活账户
        //u_status  u_code
        String c = req.getParameter("c");//编码过
        //解码  因为数据库存储的是未编码过的
        c = Base64Utils.decode(c);

        try {
           int i =  userService.activeAccount(c);
           if(i>0){
               req.setAttribute("msg","激活成功，您可以登录了");
           }else{
               req.setAttribute("msg","激活失败！");
           }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  SysConstant.FORWARD+SysConstant.FLAG+"message.jsp";
    }

    public String register(HttpServletRequest req, HttpServletResponse resp) throws  Exception {
        //注册
        //1,接收用户信息
           Users users = new Users();

            BeanUtils.populate(users,req.getParameterMap());
            //2,给页面没有赋值的字段赋值
            users.setUstatus(SysConstant.Status.NOT_ACTIVE.getCode());
            users.setUcode(ActiveCodeUtils.getActiveCode());//激活码
            users.setUrole(SysConstant.CUSTOMER);

           int i =  userService.register(users);
            if (i>0){
                return SysConstant.REDIRECT+SysConstant.FLAG+"registerSuccess.jsp";
            }else{
                req.setAttribute("registerMsg","注册失败");
                return SysConstant.FORWARD+SysConstant.FLAG+"register.jsp";
            }



    }

    public  String login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //登录
        String name = req.getParameter("username");
        String password = req.getParameter("upassword");

        String code = req.getParameter("code");
        String scode = (String) req.getSession().getAttribute("code");
//        if (!code.equalsIgnoreCase(scode)) {
//            req.setAttribute("msg", "验证码错误");
//        } else {
            try {
                Users u = userService.login(name, password);
                //if(u!=null&&u.getUstatus()==SysConstant.NOT_ACTIVE){

                if (u != null && u.getUstatus() == SysConstant.Status.NOT_ACTIVE.getCode()) {
                    req.setAttribute("msg", "账号没激活");
                } else if (u != null && u.getUstatus() == SysConstant.Status.ACTIVE.getCode()) {
                    req.getSession().setAttribute("user", u);//redis
                    return SysConstant.FORWARD + SysConstant.FLAG + "index.jsp";
                } else {
                    req.setAttribute("msg", "用户名或者密码错误");
                }


            } catch (Exception e) {
                e.printStackTrace();
            }
        //}
        return SysConstant.FORWARD + SysConstant.FLAG + "login.jsp";

    }

    //@ContentType(value="image/jpg")
    @ContentType("image/jpg")
    public void  code(HttpServletRequest req, HttpServletResponse resp) throws  Exception{
        ValidateCode code = new ValidateCode(100,40,4,5);
        String textCode= code.getCode();
        req.getSession().setAttribute("code",textCode);

        ImageIO.write(code.getBuffImg(),"jpg",resp.getOutputStream());
    }



    //@ContentType(value = "application/json;charset=utf-8")
//    public void checkName(HttpServletRequest req, HttpServletResponse resp) throws  Exception{
//        String username = req.getParameter("username");
//        boolean f = userService.checkName(username);
//        Map<String,Object> map = new HashMap<>();
//
//        if (f){//有 {data:1}
//            map.put("data",1);
//        }else
//        {
//            map.put("data",0);
//        }
//        Gson gson = new Gson();
//        String json  = gson.toJson(map);
//        resp.getWriter().print(json);
//    }

    @ContentType(value = "application/json;charset=utf-8")
    public Map<String,Object> checkName(HttpServletRequest req, HttpServletResponse resp) throws  Exception{
        String username = req.getParameter("username");
        boolean f = userService.checkName(username);
        Map<String,Object> map = new HashMap<>();

        if (f){//有 {data:1}
            map.put("data",1);
        }else
        {
            map.put("data",0);
        }
        return  map;
    }




}
