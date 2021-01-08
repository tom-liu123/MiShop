package com.itqf.controller;

import com.itqf.entity.Address;
import com.itqf.entity.Cart;
import com.itqf.entity.Orders;
import com.itqf.entity.Users;
import com.itqf.service.AddressService;
import com.itqf.service.CartService;
import com.itqf.service.OrderDetailService;
import com.itqf.service.OrderService;
import com.itqf.service.impl.AddressServiceImpl;
import com.itqf.service.impl.CartServiceImpl;
import com.itqf.service.impl.OrderDetailServiceImpl;
import com.itqf.service.impl.OrderServiceImpl;
import com.itqf.utils.SysConstant;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.List;

/**
 * @Description:
 * @Company: 刘先生
 * @Author: 刘先生
 * @Date: 2020/9/17
 * @Time: 上午9:56
 */
@WebServlet("/orderController")
public class OrderController  extends  BaseController{

    CartService cartService = new CartServiceImpl();
    AddressService addressService = new AddressServiceImpl();
    OrderService orderService = new OrderServiceImpl();
    OrderDetailService orderDetailService = new OrderDetailServiceImpl();


    public  String getOrderView(HttpServletRequest request, HttpServletResponse resp) throws Exception {
        Object users = request.getSession().getAttribute("user");
        Users u = (Users) users;
        //1，查询当前用户的购物车
        List<Cart> list = cartService.findAllCart(u.getId());
        //2,当前用户的收货地址列表
        List<Address> addresses = addressService.findAllAddress(u.getId());

        //存储到域对象
        request.setAttribute("carts",list);
        request.setAttribute("addresses",addresses);

        return SysConstant.FORWARD+SysConstant.FLAG+"order.jsp";
    }

    public String createOrder(HttpServletRequest request, HttpServletResponse resp) throws Exception {
        int aid = Integer.parseInt(request.getParameter("aid"));
        Object users = request.getSession().getAttribute("user");
        //总金额
        BigDecimal sum = new BigDecimal(request.getParameter("sum"));

        Users u = (Users) users;
        int uid = u.getId();
        Orders orders  = new Orders();
        orders.setUid(uid);
        orders.setAid(aid);
        orders.setOcount(sum);

        int  i = orderService.createOrder(orders);
        if (i>1){
            //跳转到一个查询订单列表的方法
            //
            return SysConstant.FORWARD+SysConstant.FLAG+"orderController?method=showAllOrder";
        }
        return SysConstant.REDIRECT+SysConstant.FLAG+"orderController?method=getOrderView";
    }


    public  String showAllOrder(HttpServletRequest request, HttpServletResponse resp) throws  Exception{
        Object users = request.getSession().getAttribute("user");
        Users u = (Users) users;
        List<Orders> list = orderService.findAllOrderByUId(u.getId());
        request.setAttribute("ordersList",list);
        return SysConstant.FORWARD+SysConstant.FLAG+"orderList.jsp";
    }

    public String getOrderDetail(HttpServletRequest request, HttpServletResponse resp) throws  Exception{
        String oid = request.getParameter("oid");

        Orders orders =  orderDetailService.findOrderAndDetail(oid);


        request.setAttribute("orders",orders);
        return SysConstant.FORWARD+SysConstant.FLAG+"orderDetail.jsp";
    }

    //修改订单状态
    public  String updateState(HttpServletRequest request, HttpServletResponse resp) throws  Exception{
        String oid = request.getParameter("oid");
        Orders orders  =new Orders();
        orders.setOid(oid);
        orders.setOstate(1);//1

        int i = orderService.updateOrderState(orders);
        if (i>0){
             request.setAttribute("msg","支付成功");
             return SysConstant.FORWARD+SysConstant.FLAG+"message.jsp";

        }else{
            request.setAttribute("msg","订单异常");
            return SysConstant.FORWARD+SysConstant.FLAG+"message.jsp";

        }

    }


}
