package com.itqf.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description:
 * @Company: 刘先生
 * @Author: 刘先生
 * @Date: 2020/9/15
 * @Time: 下午3:55
 */
public class Product {

    private int pid;
    private int tid;
    private String pname;
    private Date ptime;
    private String  pinfo;
    private BigDecimal pprice;
    private int pstate;
    private String pimage;//

    //private Types types;//一个产品只有一个类型
    //该字段属于Type表  接收product和type表连接查询结果集
    private  String tname;

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public Date getPtime() {
        return ptime;
    }

    public void setPtime(Date ptime) {
        this.ptime = ptime;
    }

    public String getPinfo() {
        return pinfo;
    }

    public void setPinfo(String pinfo) {
        this.pinfo = pinfo;
    }

    public BigDecimal getPprice() {
        return pprice;
    }

    public void setPprice(BigDecimal pprice) {
        this.pprice = pprice;
    }

    public int getPstate() {
        return pstate;
    }

    public void setPstate(int pstate) {
        this.pstate = pstate;
    }

    public String getPimage() {
        return pimage;
    }

    public void setPimage(String pimage) {
        this.pimage = pimage;
    }

    @Override
    public String toString() {
        return "Product{" +
                "pid=" + pid +
                ", tid=" + tid +
                ", pname='" + pname + '\'' +
                ", ptime=" + ptime +
                ", pinfo='" + pinfo + '\'' +
                ", pprice=" + pprice +
                ", pstate=" + pstate +
                ", pimage='" + pimage + '\'' +
                ", tname='" + tname + '\'' +
                '}';
    }
}
