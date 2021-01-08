package com.itqf.controller;

import com.itqf.entity.Users;
import com.itqf.service.UserService;
import com.itqf.service.impl.UserServiceImpl;
import com.itqf.utils.ActiveCodeUtils;
import com.itqf.utils.Base64Utils;
import com.itqf.utils.SysConstant;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

/**
 * @author LiuCongYang
 * @Version 1.0.0
 * create at @date  2021/1/4 19:31
 * copyright Beijing Murong Information Technology Co.,Ltd.
 */
@WebServlet("/userController")
public class UserController extends HttpServlet {
    UserService userService = new UserServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

         String method = req.getParameter("method");
         if (method.equals("userLogin")){
             login(req, resp);
         }else if (method.equals("register")){
             //ע��
             //�����û���Ϣ
             Users users = new Users();
             try {
                 BeanUtils.populate(users,req.getParameterMap());
                 //2.��ҳ��û�и�ֵ���ֶθ�ֵ
                 users.setUstatus(SysConstant.Status.NOT_ACTIVE.getCode());
                 users.setUcode(ActiveCodeUtils.getActiveCode());//������
                 users.setUrole(SysConstant.CUSTOMER);

                 int i =userService.register(users);
                 if (i>0){
                     resp.sendRedirect("registerSuccess.jsp");
                 }else {
                     req.setAttribute("registerMsg","ע��ʧ��");
                     req.getRequestDispatcher("register.jsp").forward(req,resp);
                 }

             } catch (Exception e) {
                 e.printStackTrace();
             }

         }else if ("activeAccount".equals(method)){
             String c = req.getParameter("c");
             c = Base64Utils.decode(c);
             try {
                int i = userService.activeAccount(c);
                if (i>0){
                    req.setAttribute("msg","����ɹ�");
                }else{
                    req.setAttribute("msg","����ʧ��");

                }
                req.getRequestDispatcher("message.jsp").forward(req,resp);
             } catch (SQLException throwables) {
                 throwables.printStackTrace();
             }
         }
    }

    private void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("username");
        String password = req.getParameter("upassword");
        String code = req.getParameter("code");
        String scode = (String) req.getSession().getAttribute("code");
        if (code.equalsIgnoreCase(scode)){
            req.setAttribute("msg","��֤�����");
            req.getRequestDispatcher("index.jsp").forward(req, resp);

        }else{
            try {
                Users u = userService.login(name,password);
                if (u!=null && u.getUstatus() == SysConstant.Status.NOT_ACTIVE.getCode()){
                    req.setAttribute("msg","�˺�û����");
                    req.getRequestDispatcher("/login.jsp").forward(req, resp);

                }else if (u!=null && u.getUstatus() == SysConstant.Status.ACTIVE.getCode()){
                    req.setAttribute("user",u);
                    req.getRequestDispatcher("/login.jsp").forward(req, resp);
                }else {
                    req.setAttribute("msg","�û����������");
                    req.getRequestDispatcher("/login.jsp").forward(req, resp);
                }


            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
