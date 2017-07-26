package com.example.lkslib.net;


/**
 * 网络返回基类
 */
public class LKSResponse {
    /**
     * 错误码
     */
    private String errorcode;
    /**
     * 数据
     */
//    private String data;
    private Object data;
    /**
     * 错误信息
     */
    private String errormsg;
    /**
     * 返回码 0：正确  1：有错误"
     */
    private String errorno;

    public String getErrorno() {
        return errorno;
    }

    public void setErrorno(String errorno) {
        this.errorno = errorno;
    }

    public String getErrorcode() {
        return errorcode;
    }

    public void setErrorcode(String errorcode) {
        this.errorcode = errorcode;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getErrormsg() {
        return errormsg;
    }

    public void setErrormsg(String errormsg) {
        this.errormsg = errormsg;
    }

}
