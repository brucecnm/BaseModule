package com.example.lkslib.net;

import android.text.TextUtils;


import com.example.lkslib.Utils.GsonUtils;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


/**
 * LkSSubscriber
 */
public abstract class LkSSubscriber<T> implements Observer<T> {

    private boolean isNeedCahe;

    public LkSSubscriber() {
    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(T t) {
        LKSResponse lksResponse = null;
        try {
            lksResponse = (LKSResponse) t;
        } catch (Exception e) {
            if (null == lksResponse) {
                throw new IllegalArgumentException("传入的类型不匹配！请传入LKSResponse");
            }
        }
        if ("0".equals(lksResponse.getErrorno())) {
            if (null==lksResponse.getData()||TextUtils.isEmpty(lksResponse.getData().toString())) {
                onSuccess("");
            } else {
                if (lksResponse.getData() instanceof String) {
                    onSuccess(lksResponse.getData().toString());
                } else {
                    onSuccess(GsonUtils.getJsonString(lksResponse.getData()));
                }
            }
        } else {
            onFail(lksResponse.getErrormsg());
        }
    }

    @Override
    public void onError(Throwable e) {

        if (e instanceof ExceptionHandle.ResponeThrowable) {
            onError((ExceptionHandle.ResponeThrowable) e);
        } else {
            onError(new ExceptionHandle.ResponeThrowable(e, ExceptionHandle.ERROR.UNKNOWN));
        }
    }


    @Override
    public void onComplete() {
        // todo some common as  dismiss loadding
    }

    /**
     * 连接错误
     *
     * @param e
     */
    public abstract void onError(ExceptionHandle.ResponeThrowable e);

    /**
     * 服务器错误
     *
     * @param s
     */
    public abstract void onFail(String s);

    /**
     * 成功
     *
     * @param result
     */
    public abstract void onSuccess(String result);

}
