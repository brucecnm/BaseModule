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

import android.support.annotation.NonNull;

/**
 * TODO this class desription here
 * <p>
 * Created by ww on 2017/7/5.
 */
public class DefaultFilter implements BusFilter {
    @Override
    public UrlBean verifyData(@NonNull UrlBean urlBean) {
        if(null==EventHelper.lksServletList){
            urlBean.setCode(EventBusConstants.Code.CODE_ERR);
        }else {
            boolean contains=false;
            for (LksEventModel lksEventModel:EventHelper.lksServletList) {
                if(lksEventModel.getCode().equals(urlBean.getCode())){
                    contains=true;
                    break;
                }
            }
            if(!contains){
                urlBean.setCode(EventBusConstants.Code.CODE_ERR);
            }
        }
        return urlBean;
    }
}
