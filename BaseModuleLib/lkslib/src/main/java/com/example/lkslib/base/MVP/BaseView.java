package com.example.lkslib.base.MVP;

/**
 * Created by Administrator on 2017/3/28.
 */

public interface BaseView {
    void showProgress( String s) ;

    void hintProgress() ;

    void showToast(String s) ;
    void finishView();
}
