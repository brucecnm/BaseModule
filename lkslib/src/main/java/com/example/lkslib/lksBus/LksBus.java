/*
 * Copyright (C) 2017 Qi Cai Technology Co., Ltd. 
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at 
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and 
 * limitations under the License.
 */
package com.example.lkslib.lksBus;

import android.util.Log;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * 替代EventBus发送post事件，可以设置过滤器
 * <p>
 * Created by ww on 2017/7/5.
 */
public class LksBus {
    List<BusFilter> filters = new ArrayList<>();


    public LksBus addFilter(BusFilter filter) {
        this.filters.add(filter);
        return this;
    }

    public void post(UrlBean urlBean) {
        for (int i = 0; i < filters.size(); i++) {
            filters.get(i).verifyData(urlBean);
        }
        if (EventBusConstants.Code.CODE_ERR.equals(urlBean.getCode())) {
            TransmitData transmitData = urlBean.getI();
            if(null!=transmitData){
                Log.e("lks","err_code");
                transmitData .fail("请求码错误");
            }
            return;
        }
        doPost(urlBean);
    }

    private void doPost(UrlBean urlBean) {
        EventBus.getDefault().post(urlBean);
    }
}
