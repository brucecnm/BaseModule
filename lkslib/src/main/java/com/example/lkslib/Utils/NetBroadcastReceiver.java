package com.example.lkslib.Utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;


import java.util.ArrayList;
import java.util.List;

/**
 * 自定义检查手机网络状态是否切换的广播接受器
 *
 * @author cj
 */
public class NetBroadcastReceiver extends BroadcastReceiver {

    static List<NetEvevt> evevts = new ArrayList<>();
//    public NetEvevt evevt = BaseActivity.evevt;

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub
        // 如果相等的话就说明网络状态发生了变化
        if (intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
            int netWorkState = NetUtil.getNetWorkState(context);
            // 接口回调传过去状态的类型
            for (NetEvevt netEvevt:evevts) {
                netEvevt.onNetChange(netWorkState);
            }
//            evevt.onNetChange(netWorkState);
        }
    }

    public static void addEvevt(NetEvevt evevt) {
        evevts.add(evevt);
    }
    public static void reMoveEvevt(NetEvevt evevt) {
        evevts.remove(evevt);
    }
    // 自定义接口
    public interface NetEvevt {
        public void onNetChange(int netMobile);
    }
}