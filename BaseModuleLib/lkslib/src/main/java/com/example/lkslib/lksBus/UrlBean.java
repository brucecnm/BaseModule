package com.example.lkslib.lksBus;

/**
 * Created by Administrator on 2017/4/12.
 * 传递数据的实体类
 */

public class UrlBean {
    protected String code;//传递的代码
    protected Object data;//传递的数据
    private TransmitData i;//回掉的接口

    public UrlBean(String code) {
        this.code = code;
    }

    public UrlBean(String code, Object data) {
        this.code = code;
        this.data = data;
    }
    public UrlBean(String code, Object data, TransmitData i) {
        this.code = code;
        this.data = data;
        this.i=i;
    }

    public TransmitData getI() {
        return i;
    }

    public void setI(TransmitData i) {
        this.i = i;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
