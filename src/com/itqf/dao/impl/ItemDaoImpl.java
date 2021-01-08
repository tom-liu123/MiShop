package com.itqf.dao.impl;

import com.itqf.dao.ItemDao;
import com.itqf.entity.Item;
import com.itqf.entity.Product;
import com.itqf.utils.DruidUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Company: 刘先生
 * @Author: 刘先生
 * @Date: 2020/9/17
 * @Time: 上午11:23
 */
public class ItemDaoImpl implements ItemDao {
    QueryRunner queryRunner = new QueryRunner(DruidUtils.getDataSource());
    @Override
    public int addItem(Item item) throws Exception {
        String sql = "insert into item(o_id,p_id,i_count,i_num) value(?,?,?,?)";
        return queryRunner.update(sql,item.getOid(),item.getPid(),item.getIcount(),item.getInum());
    }

    @Override
    public List<Item> findItemByOid(String oid)  throws  Exception{
        String sql="select i_id as iid,o_id as oid,i.p_id as pid,i_count as icount ,p_name as pname\n" +
                ",p_time as ptime,p_image as pimage ,p_price as pprice ,p_state as pstate,\n" +
                "p_info as pinfo from item i" +
                " INNER JOIN product p on i.p_id = p.p_id\n" +
                "where o_id = ?";
        List<Map<String,Object>> list = queryRunner.query(sql,new MapListHandler(),oid);
        List<Item> itemList = new ArrayList<>();
        for (Map<String, Object> map : list) {
            Item item = new Item();
            Product product = new Product();
            BeanUtils.populate(item,map);
            BeanUtils.populate(product,map);

            item.setProduct(product);

            itemList.add(item);
        }

        return itemList;
    }
}
