package com.itqf.dao.impl;

import com.itqf.dao.OrderDao;
import com.itqf.entity.Address;
import com.itqf.entity.Orders;
import com.itqf.utils.DruidUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Company: 刘先生
 * @Author: 刘先生
 * @Date: 2020/9/17
 * @Time: 上午11:14
 */
public class OrderDaoImpl implements OrderDao {

    QueryRunner runner = new QueryRunner(DruidUtils.getDataSource());
    @Override
    public int addOrder(Orders orders) throws Exception {
        //UUID
        //snowflower 分布式
        String sql = "insert into orders(o_id,u_id,a_id,o_time,o_count,o_state) value(?,?,?,?,?,?)";


        return runner.update(sql,orders.getOid(),
                orders.getUid(),orders.getAid(),orders.getOtime(),orders.getOcount(),orders.getOstate());
    }

    @Override
    public List<Orders> findAllOrderByUId(int uid) throws Exception {
        String sql = "select o_id  as oid,a_detail as adetail,o_state as ostate ," +
                "o_time as otime,o_count as ocount from orders o inner join " +
                "address a on o.a_id = a.a_id where o.u_id=? ";
        List<Map<String,Object>> list = runner.query(sql,new MapListHandler(),uid);
        List<Orders> orders = new ArrayList<>();

        for (Map<String, Object> map : list) {
            Orders o = new Orders();
            Address address = new Address();
            BeanUtils.populate(o,map);
            BeanUtils.populate(address,map);

            o.setAddress(address);
            orders.add(o);
        }
        return orders;
    }

    /**
     * 根据订单编号查询订单和该订单的收货地址
     * @param oid
     * @return
     * @throws Exception
     */
    @Override
    public Orders findOrderDetailByOid(String oid) throws Exception {
        String sql = "select o_id  as oid,a_detail as adetail,a_name as aname,a_phone as aphone,o_state as ostate ," +
                "o_time as otime,o_count as ocount from orders o inner join "  +
                "address a on o.a_id = a.a_id where o.o_id=? ";

        //MapListHandler()  List<Map>
        //MapHandler  Map
        Map<String,Object> map = runner.query(sql,new MapHandler(),oid);
        List<Orders> orders = new ArrayList<>();
            Orders o = new Orders();
            Address address = new Address();
            BeanUtils.populate(o,map);
            BeanUtils.populate(address,map);
            o.setAddress(address);
       return  o;
    }

    @Override
    public int updateOrderState(Orders orders) throws Exception {
        String sql = "update  orders set o_state =?   where o_id=?";
        return runner.update(sql,orders.getOstate(),orders.getOid());
    }
}

