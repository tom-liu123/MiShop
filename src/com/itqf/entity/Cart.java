package com.itqf.entity;

import java.math.BigDecimal;

/**
 * @Description:
 * @Company: 刘先生
 * @Author: 刘先生
 * @Date: 2020/9/16
 * @Time: 上午11:12
 */
public class Cart {

    private  int cid;
    private int uid;
    private  int pid;
    private int cnum;
    private BigDecimal ccount;    //  999.99      99999(long)  单位分

    private  Product product;//一条购物车信息对应一个商品


    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getCnum() {
        return cnum;
    }

    public void setCnum(int cnum) {
        this.cnum = cnum;
    }

    public BigDecimal getCcount() {
        return ccount;
    }

    public void setCcount(BigDecimal ccount) {
        this.ccount = ccount;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "cid=" + cid +
                ", uid=" + uid +
                ", pid=" + pid +
                ", cnum=" + cnum +
                ", ccount=" + ccount +
                ", product=" + product +
                '}';
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
