package com.itqf.controller;

import com.itqf.anno.ContentType;
import com.itqf.entity.Cart;
import com.itqf.entity.Users;
import com.itqf.service.CartService;
import com.itqf.service.impl.CartServiceImpl;
import com.itqf.utils.SysConstant;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Company: 刘先生
 * @Author: 刘先生
 * @Date: 2020/9/16
 * @Time: 上午11:00
 */
@WebServlet(value = "/cartController")
public class CartController  extends  BaseController{

    CartService cartService = new CartServiceImpl();

    public  String addCard(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //1,判断用户是否登录
        Object users = request.getSession().getAttribute("user");
        if (users==null){
            //redirect
            return SysConstant.REDIRECT+SysConstant.FLAG+"login.jsp";
        }
        Users u = (Users) users;
        int pid = Integer.parseInt(request.getParameter("goodsId"));
        BigDecimal bigDecimalPrice =
                new BigDecimal(request.getParameter("price"));

        //  1）用户购物车中已经有该商品  修改购物车中商品数量+1
        // 2) 购物车中没有该商品  新增一条
        Cart cart = new Cart();
        cart.setUid(u.getId());
        //method=addCard&goodsId=${goods.pid}&price=${goods.pprice}
        cart.setPid(pid);
        cart.setCcount(bigDecimalPrice);
        cart.setCnum(1);

        int i = cartService.addCart(cart);
        if (i>0){
            //添加成功
            return SysConstant.REDIRECT+SysConstant.FLAG+"cartSuccess.jsp";
        }else{
            request.setAttribute("msg","添加失败");
           return  SysConstant.FORWARD+SysConstant.FLAG+"message.jsp";
        }

    }

    public  String getCart(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Object users = request.getSession().getAttribute("user");
        if (users==null){
            //redirect
            return SysConstant.REDIRECT+SysConstant.FLAG+"login.jsp";
        }
        Users u = (Users) users;
        List<Cart> list = cartService.findAllCart(u.getId());
        request.setAttribute("carts",list);

        return  SysConstant.FORWARD+SysConstant.FLAG +"cart.jsp";
        }

        //修改购物车数量
    @ContentType("application/json;charset=utf-8")
    //updateCartNum
    public Map<String,Object> updateCartNum(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Object users = request.getSession().getAttribute("user");
        int pid = Integer.parseInt(request.getParameter("pid"));
        Users u = (Users) users;
        BigDecimal price = new BigDecimal(request.getParameter("price"));
        int i = cartService.updateNum(pid,((Users) users).getId(),price);
        Map<String,Object> map = new HashMap<>();
        if (i>0){
            map.put("code",1);
        }else{
            map.put("code",0);
        }
      return  map;
    }

    //删除购物车中某条记录
    @ContentType("application/json;charset=utf-8")
    public  Map<String,Object> delCart(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int pid = Integer.parseInt(request.getParameter("pid"));
        Object users = request.getSession().getAttribute("user");
        Map<String,Object> map = new HashMap<>();
        if (users==null){
            map.put("code",2);
        }
        Users u = (Users) users;
        if (cartService.delete(u.getId(),pid)){
            map.put("code",1);
        }else{
            map.put("code",0);
        }

        return  map;
    }

}
