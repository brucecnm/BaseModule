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
 * Created by ww on 2017/7/12.
 */
public interface LksServlet {
    /**
     *
     * @param urlBean
     * @return 是否消耗掉请求 true 消耗请求，不再向下传递
     */
    boolean doPost(@NonNull UrlBean urlBean);
}