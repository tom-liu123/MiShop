package com.itqf.entity;

import java.math.BigDecimal;

/**
 * @Description: 订单明细表
 * @Company: 刘先生
 * @Author: 刘先生
 * @Date: 2020/9/17
 * @Time: 上午11:06
 */
public class Item {

    private int iid;
    private String oid;//订单编号
    private int pid;//商品编号
    private BigDecimal icount;//订单项的小计
    private int inum;//数量

    //一条订单明细对应一个商品对象
    private  Product product;

    public int getIid() {
        return iid;
    }

    public void setIid(int iid) {
        this.iid = iid;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public BigDecimal getIcount() {
        return icount;
    }

    public void setIcount(BigDecimal icount) {
        this.icount = icount;
    }

    public int getInum() {
        return inum;
    }

    public void setInum(int inum) {
        this.inum = inum;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
