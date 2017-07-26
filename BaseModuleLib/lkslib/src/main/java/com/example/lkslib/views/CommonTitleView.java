package com.example.lkslib.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lkslib.R;


/**
 * 项目公共的标题栏
 * * @attr ref  R.styleable.CommonTitileView
 *
 * @attr ref R.styleable.CommonTitileView_title 标题
 * @attr ref R.styleable.CommonTitileView_rightTitle 右边文字
 * @attr ref  R.styleable.CommonTitileView_showRight, false 是否显示右边文字
 */
public class CommonTitleView extends LinearLayout {
    public int mTitleBarID = 1;
    //正标题
    private String mTitleString;
    //右边文字
    private String mRightString;
    //是否显示右边文字
    private boolean showRight;
    TextView tv_title, tv_right;
    ImageView iv_back;

    public CommonTitleView(Context context) {
        super(context);
        init(null, 0);
    }

    public CommonTitleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public CommonTitleView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        // Load attributes
        setId(mTitleBarID);
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.CommonTitleView, defStyle, 0);
        if (null != a) {
            //       mTitleString = a.getString(R.styleable.CommonTitleView_title);
            mRightString = a.getString(
                    R.styleable.CommonTitleView_rightTitle);
            showRight = a.getBoolean(
                    R.styleable.CommonTitleView_showRight, false);
            a.recycle();
        }
        View view = View.inflate(getContext(), R.layout.common_titile_view, this);
        tv_title = (TextView) view.findViewById(R.id.tv_base_title);
        tv_right = (TextView) view.findViewById(R.id.tv_right);
        iv_back = (ImageView) view.findViewById(R.id.iv_base_back);
        if (!TextUtils.isEmpty(mTitleString)) {
            tv_title.setText(mTitleString);
        }
        if (showRight && !TextUtils.isEmpty(mRightString)) {
            tv_right.setText(mRightString);
        }

    }

    public void setTitileString(String title) {
        mTitleString = title;
        tv_title.setText(title);
    }

    public void setRightString(String string) {
        mRightString = string;
        tv_right.setText(mRightString);
    }

    public void setBackListener(OnClickListener clickListener) {
        if (null != clickListener && null != iv_back) {
            iv_back.setOnClickListener(clickListener);
        }
    }
}
