package com.wuju.model;

import java.util.List;

/**
 * Created by 11929 on 2019/7/19.
 */
public class Page<T> {
    private int pageNo;//当前页数
    private int totalRows;//总记录数
    private int pageSize=3;//页容量：一页显示多少条数据
    private List<T> list;//当前页显示的集合

    public int getPageNo() {
        if(pageNo<1){
            pageNo=1;//如果当前小于第一页，则就显示为第一页
        }
        if(pageNo>getTotalPage()){
            pageNo=getTotalPage();//如果当前大于最大页，则就显示为最大页
        }
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    //得到总页数
    public int getTotalPage(){
        return totalRows%pageSize==0?totalRows/pageSize:totalRows/pageSize+1;
    }

    //判断是否有上一页
    public boolean hasPrev(){
        if(pageNo<=1){
            return false;
        }
        return true;
    }

    //判断是否有下一页
    public boolean hasNext(){
        if(pageNo>=getTotalPage()){
            return false;
        }
        return true;
    }

    //得到上一页
    public int getPrevPage(){
        if(hasPrev()){
            return pageNo-1;//如果有上一页，则当前页数-1
        }
        return pageNo;
    }
    //得到下一页
    public int getNextPage(){
        if(hasNext()){
            return pageNo+1;//如果有下一页，则当前页数+1
        }
        return pageNo;
    }
}
