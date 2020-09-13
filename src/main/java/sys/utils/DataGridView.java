package sys.utils;

import sys.domian.Menu;

import java.util.List;

/* 用来封装layui数据表格的数据据对象*/
public class DataGridView {

    private Integer code=0;
    private String mag="";
    private Long  count;
    private Object data;
/*Obiect是一个对象*/
    public DataGridView() {

    }

    public DataGridView(Object data) {
        this.data = data;
    }

    public DataGridView(long count, Object data) {
        this.count=count;
        this.data=data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMag() {
        return mag;
    }

    public void setMag(String mag) {
        this.mag = mag;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
