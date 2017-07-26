package com.example.lkslib.base;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.lkslib.Utils.NetBroadcastReceiver;
import com.example.lkslib.views.CommonTitleView;
import com.umeng.analytics.MobclickAgent;



public abstract class LksActivity extends AppCompatActivity implements NetBroadcastReceiver.NetEvevt {
    RelativeLayout baseView;//主布局
    RelativeLayout contentView;//内容布局
    CommonTitleView titleView;//标题布局
    public static final int FLAG_HOMEKEY_DISPATCHED = 0x80000000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        NetBroadcastReceiver.addEvevt(this);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(FLAG_HOMEKEY_DISPATCHED, FLAG_HOMEKEY_DISPATCHED);
        //去掉Activity上面的状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        ----------------------------
        baseView = new RelativeLayout(this);
        baseView.setBackgroundColor(Color.rgb(255, 255, 255));
        //内容布局
        contentView = new RelativeLayout(this);
        contentView.setPadding(0, 0, 0, 0);
        // 标题
        titleView = new CommonTitleView(this);
        //填入View
        baseView.addView(titleView, new LinearLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
        titleView.setVisibility(View.GONE);

        RelativeLayout.LayoutParams layoutParamsContent = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParamsContent.addRule(RelativeLayout.BELOW, titleView.getId());
        baseView.addView(contentView, layoutParamsContent);
//        ---------------------------
        setContentView(baseView, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));


    }

    /**
     * 描述：用指定的View填充主界面.
     *
     * @param content 指定的View
     */
    public void setAbContentView(View content) {
        contentView.removeAllViews();
        contentView.addView(content, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
    }

    /**
     * 描述：用指定资源ID表示的View填充主界面.
     */
    public void setAbContentView(int resId) {
        View view = View.inflate(this, resId, null);
        setAbContentView(view);

    }

    /**
     * 获取标题栏布局.
     */
    public CommonTitleView getTitleBar() {
        titleView.setVisibility(View.VISIBLE);
        return titleView;
    }


    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        NetBroadcastReceiver.reMoveEvevt(this);
    }

    private boolean isDialogShowing;//
    private AlertDialog netDialog;

    private void showDialog() {
        if (isDialogShowing) {
            return;
        }
        isDialogShowing = true;
        if (null == netDialog) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("网络监测");
            builder.setMessage("当前没有网络，离线使用！");
            netDialog = builder.setNeutralButton("关闭", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    isDialogShowing = false;
                }
            }).create();
        }
        netDialog.show();

        Log.e("============>", "showDialog");
    }

    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha
     */
    public void backgroundAlpha(Activity context, float bgAlpha) {
        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
        lp.alpha = bgAlpha;
        context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        context.getWindow().setAttributes(lp);
    }

    /**
     * 网络类型
     */
    protected int netMobile;

    @Override
    public void onNetChange(int netMobile) {
        this.netMobile = netMobile;
        if (!isNetConnect()) {
            try {
                showDialog();
            } catch (Exception e) {

            }

        } else {
            if (isDialogShowing) {
                netDialog.dismiss();
            }
        }
    }

    /**
     * 判断有无网络 。
     *
     * @return true 有网, false 没有网络.
     */
    public boolean isNetConnect() {
        if (netMobile == 1) {
            return true;
        } else if (netMobile == 0) {
            return true;
        } else if (netMobile == -1) {
            return false;
        }
        return false;
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_HOME) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}