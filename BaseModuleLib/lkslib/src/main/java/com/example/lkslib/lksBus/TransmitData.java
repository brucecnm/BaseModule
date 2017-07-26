package com.example.lkslib.lksBus;

/**
 * Created by Administrator on 2017/6/9.
 * 不同module之间传递数据
 */

public interface TransmitData {
    void success(EventResponse o);
    void fail(String s);
}
