package com.itqf.service.impl;

import com.itqf.dao.CartDao;
import com.itqf.dao.impl.CartDaoImpl;
import com.itqf.entity.Cart;
import com.itqf.service.CartService;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Description:
 * @Company: 刘先生
 * @Author: 刘先生
 * @Date: 2020/9/16
 * @Time: 上午11:25
 */
public class CartServiceImpl implements CartService {

    CartDao dao = new CartDaoImpl();

    @Override
    public int addCart(Cart cart) throws Exception {
        Cart ucart = dao.findCartByUIdAndPId(cart.getUid(),cart.getPid());
        int i = 0;
        if (ucart==null){//该商品没有添加过购物车
            i = dao.addCart(cart);
        }else{
            //ucart.getCnum()  得到原来的数量  u
            ucart.setCnum(ucart.getCnum()+1);
            // ucart.getCcount()  小计 ,因为是数据库查出来的
            // BigDecimal bigDecimal = ucart.getCcount();

            //cart.getCcount();  单价 是从页面传过来的
            BigDecimal bigDecimal = cart.getCcount();
            //单价* 数量
             //计算后赋值
             bigDecimal  =  bigDecimal.multiply(new BigDecimal(ucart.getCnum()));

            ucart.setCcount(bigDecimal);//总价

           i =  dao.update(ucart);
        }

        return i;
    }

    @Override
    public List<Cart> findAllCart(int uid) throws  Exception {
        return dao.findAllCart(uid);
    }

    @Override
    public int updateNum(int pid, int uid,BigDecimal price) throws Exception {

        //1.查出原来的数据
        Cart cart = dao.findCartByUIdAndPId(uid,pid);
        //把新值赋值给购物车对象
        cart.setCnum(cart.getCnum()-1);//数量-1

        BigDecimal bigDecimal = price;  //重新计算小计
        bigDecimal = bigDecimal.multiply(new BigDecimal(cart.getCnum()));
        cart.setCcount(bigDecimal);
        //更新数据库
        int i = dao.update(cart);

        return i;
    }

    @Override
    public boolean delete(int uid, int pid) throws Exception {

        return dao.delCart(uid,pid)>0;
    }
}
