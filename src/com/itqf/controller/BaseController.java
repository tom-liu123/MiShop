package com.itqf.controller;

import com.google.gson.Gson;
import com.itqf.anno.ContentType;
import com.itqf.utils.SysConstant;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @Description: 调用相关方法处理业务  不需要判断method的值
 * 用反射
 * @Company: 刘先生
 * @Author: 刘先生
 * @Date: 2020/9/15
 * @Time: 上午10:57
 */
public class BaseController extends HttpServlet {//--->springmvc: DispacherServlet

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //method参数的值对应的就是controller的方法名
        String  methodStr = req.getParameter("method");
        if(methodStr==null){
            //不处理业务  去首页
            resp.sendRedirect("/index.jsp");
        }
        //login  register  activeAccount  .......
        //public  String  login(...){}
        try {

            //1.得到类的Class对象
            Class clazz = this.getClass();
            //System.out.println(clazz);
            //2.构建对象
            Object o =  clazz.newInstance();
            //3.得到要处理该业务的方法的Method对象//userLogin
            //org.apache.catalina.connector.RequestFacade, org.apache.catalina.connector.ResponseFacade
           //MethodNotFound   method=login
            Method method = clazz.getMethod(methodStr,HttpServletRequest.class,HttpServletResponse.class);
            //contentType("text/html;charset=utf-8"); html
            //contentType("image/jpeg")//图片
            //contentType("application/json;charset=utf-8")
            //判断方法是否被自定义注解修饰
            if (method.isAnnotationPresent(ContentType.class)){
                ContentType contentType =   method.getAnnotation(ContentType.class);
                String type = contentType.value();//image/jpeg  application/json;charset=utf-8

                resp.setContentType(type);
            }else{
                resp.setContentType("text/html;charset=utf-8");
            }
            //4.调用方法
            Object result = method.invoke(o,req,resp);
            //List<Types>
            //controller的方法返回值都加上一个前缀
            //return  "forward:index.jsp"
            //return "redirect:index.jsp"
            if(result!=null){
                //返回值是否是String类型  是String类型  就跳转页面
                if (result.getClass().getSimpleName().equalsIgnoreCase("String")){
                    //跳页面
                   String s =( String)result;
                    //怎么得到:后的字符串
                    String suffix = s.substring(s.indexOf(":")+1);//跳转的页面
                    if (s.startsWith(SysConstant.FORWARD)){
                        req.getRequestDispatcher(suffix).forward(req,resp);
                    }else if(s.startsWith(SysConstant.REDIRECT)) {
                        resp.sendRedirect(suffix);
                    }

                }
                else{
                    //返回json  前端请求ajax
                    // public  List<Users>  findAll()
                    //public  Map<String,Object>  checkName()
                    //转json
                    String s = new Gson().toJson(result);
                    resp.getWriter().print(s);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
