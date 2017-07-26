package com.example.lkslib.common;

import android.app.Application;

import com.example.lkslib.lksBus.LksBus;

import org.greenrobot.eventbus.EventBus;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/12.
 */

public abstract class LksApplication {

    public static Application application;
    public  void onCreate(Application application){
        this.application = application;
    }

    public  void onTerminate(){
    }
    public abstract LksBus getLksBus();
}
