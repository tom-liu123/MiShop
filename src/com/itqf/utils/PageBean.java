package com.itqf.utils;

import java.util.List;

/**
 * @Description:
 * @Company: 刘先生
 * @Author: 刘先生
 * @Date: 2020/9/15
 * @Time: 下午4:33
 */
public class PageBean {
   private long pageSize = 5;
   private long nowPage;
   private long totalCount;//总的记录数
    private long totalPage;//总的页数
    private long startIndex;//起始记录

    //数字分页
    private  long start;
    private long end;

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    //  10  3
    //limit 0,3  (1-1)*3
    //limit 3,3  (2-1)*3

    private List<?>  list;

    public long getPageSize() {
        return pageSize;
    }

    public void setPageSize(long pageSize) {
        this.pageSize = pageSize;
    }

    public long getNowPage() {
        return nowPage;
    }

    public void setNowPage(long nowPage) {
        this.nowPage = nowPage;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public long getTotalPage() {
        return this.totalCount%pageSize==0?this.totalCount/pageSize:this.totalCount/pageSize+1;
    }

    public void setTotalPage(long totalPage) {
        this.totalPage = totalPage;
    }

    public List<?> getList() {
        return list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }

    public long getStartIndex() {
        return (nowPage-1)*pageSize;
    }

    public void setStartIndex(long startIndex) {
        this.startIndex = startIndex;
    }

    public  void cal(){

        start =nowPage-3;  //7 8 9 10 11 12 13
        end = nowPage+3;

        if (this.getTotalPage()<=7){//总的页数小于等于7的情况   3
            start = 1;
            end =this.getTotalPage();
        }
        else if(nowPage<=this.getTotalPage()-3){//   //100 页  当前99   93-100
            start = this.getTotalPage()-7;
            end = this.getTotalPage();
        }



    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }
}
