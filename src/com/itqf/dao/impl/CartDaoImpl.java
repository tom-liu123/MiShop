package com.itqf.dao.impl;

import com.itqf.dao.CartDao;
import com.itqf.entity.Cart;
import com.itqf.entity.Product;
import com.itqf.utils.DruidUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Company: 刘先生
 * @Author: 刘先生
 * @Date: 2020/9/16
 * @Time: 上午11:17
 */
public class CartDaoImpl implements CartDao {
    QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
    @Override
    public Cart findCartByUIdAndPId(int uid, int pid) throws Exception {
        String sql = "select c_id as cid,u_id as uid,p_id as pid ,c_count as ccount," +
                "c_num as cnum from cart where u_id = ? and p_id=?";
        Cart cart = queryRunner.query(sql,new BeanHandler<>(Cart.class),uid,pid);

        return cart;
    }

    @Override
    public int update(Cart cart) throws Exception {
        //update cart set c_num=c_num+1,c_count=? where c_id=?";

        String sql = "update cart set c_num=?,c_count=? where c_id=?";

        return queryRunner.update(sql,cart.getCnum(),
                cart.getCcount(),cart.getCid());
    }

    @Override
    public int addCart(Cart cart) throws Exception {
        // value 单条
        //values 批量插入
        String sql = "insert into cart(u_id,p_id,c_count,c_num) value(?,?,?,?)";


        return queryRunner.update(sql,cart.getUid(),cart.getPid(),cart.getCcount(), cart.getCnum());
    }

    @Override
    public List<Cart> findAllCart(int uid) throws Exception {
        //p_price  :单价
        //c_count :总价
        String sql="select c.c_id  as cid ,c.p_id as pid,c.c_count as ccount,c.c_num as cnum,\n" +
                "p_image as pimage,p.p_price as pprice ,p.p_info as pinfo\n" +
                ",p_name as pname\n" +
                "from cart c \n" +
                "INNER JOIN product p\n" +
                "on c.p_id = p.p_id\n" +
                "where c.u_id = ?";
        List<Cart> carts = new ArrayList<>();
        //结果集
        //new BeanListHandler<>()  只能封装属于cart对象中的字段  product为null

        //new MapListHandler() 把每一行的所有字段和值存储到Map中，并把Map存到List中
        List<Map<String,Object>>  list =   queryRunner.query(sql,new MapListHandler(),uid);
        for (Map<String, Object> map : list) {
            //System.out.println(map);
            Cart cart = new Cart();
            Product product = new Product();
            //把map封装到cart中
            BeanUtils.populate(cart,map);
            BeanUtils.populate(product,map);

            cart.setProduct(product);
            carts.add(cart);
        }


        return carts;
    }

    @Override
    public int delCart(int uid, int pid) throws  Exception {
        String sql="delete from cart where u_id=? and p_id=?";

        return queryRunner.update(sql,uid,pid);
    }

    @Override
    public int delCartById(int uid) throws Exception {
        String sql="delete from cart where u_id=? ";

        return queryRunner.update(sql,uid);
    }

    public  static  void  main(String[]args) throws  Exception{

        CartDao cartDao = new CartDaoImpl();
        System.out.println(cartDao.findAllCart(1));
    }
}
