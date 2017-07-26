package com.example.lkslib.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * TODO this class desription here
 * <p>
 * Created by ww on 2017/6/14.
 */
public class AutoHeightListView extends ListView {

    boolean isAutoHight = true;

    public AutoHeightListView(Context context) {
        super(context);
    }

    public AutoHeightListView(Context context, AttributeSet attrs) {
        super(context, attrs);


    }

    public AutoHeightListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }



    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

//        ViewGroup.LayoutParams params = this.getLayoutParams();
//        if(params.height == ViewGroup.LayoutParams.WRAP_CONTENT) {
//            isAutoHight = true;
//        }
//        else{
//            isAutoHight = false;
//        }


        if(isAutoHight) {
            int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                    MeasureSpec.AT_MOST);

            setOverScrollMode(OVER_SCROLL_NEVER);
            super.onMeasure(widthMeasureSpec, expandSpec);
            return;
        }

        setOverScrollMode(OVER_SCROLL_IF_CONTENT_SCROLLS);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public boolean isAutoHight() {
        return isAutoHight;
    }

    public void setIsAutoHight(boolean isAutoHight) {

        this.isAutoHight = isAutoHight;
        this.requestLayout();
    }
}
