package com.example.lkslib.base.MVP;

/**
 * Created by Administrator on 2017/4/12.
 * 用于Model层和Presenter层传递数据传递数据
 */

public interface BaseBus {
    /**
     * 传递事件
     * @param busBean
     */
    void transmitData(BusBean busBean);
}
