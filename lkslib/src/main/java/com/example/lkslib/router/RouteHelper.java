package com.example.lkslib.router;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/6.
 * 控制项目跳转的帮助类
 * 1 传递数据
 * 2 界面跳转 （目标界面）
 */

public class RouteHelper {
    private static Map<String, Class> activityMap;//全局的管理有交互的界面
    public static final String ROUTE_KEY = "ROUTE_KEY";

    /**
     * 注册需要跳转的Activity 不用手动注册 注解框架完成注册
     */
    public static void register(String key, Class value) {
        if (null == activityMap) {
            activityMap = new HashMap<>();
        }
        Log.d("RouteHelper", "register............<" + key + "," + value + ">");
        activityMap.put(key, value);
    }

    public static void openActivity(Intent intent, Context context, int requestCode) {
        if (null == intent) {
            throw new IllegalArgumentException("intent cannot be null");
        }
        if (!intent.hasExtra(ROUTE_KEY)) {
            throw new IllegalArgumentException("you must set ROUTE_KEY in intent");
        }
        if (null == activityMap) {
//            throw new IllegalArgumentException("you should init Router");
            Router.init(context);
        }
        if (null == activityMap) {
            throw new IllegalArgumentException("init Router Err");
        }
        Class aClass = activityMap.get(intent.getStringExtra(ROUTE_KEY));
        if (null == aClass || null == context) {
            throw new IllegalArgumentException("The target class cannot be found");
        }
        if (requestCode > 0 && !(context instanceof Activity)) {
            throw new IllegalArgumentException("this context cannot startActivityForResult");
        }
        intent.setClass(context, aClass);
        if (!(context instanceof Activity)) {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        if (requestCode > 0) {
            Activity activity = (Activity) context;
            activity.startActivityForResult(intent, requestCode);
            return;
        }
        context.startActivity(intent);
    }
}
