package com.example.lkslib.base.MVP;

/**
 * Created by Administrator on 2017/4/12.
 * 传递数据的实体类
 */

public class BusBean {
    private int code;//传递的代码
    private Object data;//传递的数据

    public BusBean(int code, Object data) {
        this.code = code;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
