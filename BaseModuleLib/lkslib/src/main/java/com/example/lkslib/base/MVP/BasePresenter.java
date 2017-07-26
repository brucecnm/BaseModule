package com.example.lkslib.base.MVP;

/**
 * Created by Administrator on 2017/3/28.
 */

public interface BasePresenter {
    /**
     * 初始化
     */
     void onCreate();

    /**
     * 注销需要注销的东西 eg：eventBus
     */
     void onDestroy();
}
