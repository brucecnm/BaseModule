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

import android.content.Context;
import android.content.pm.PackageManager;

import com.example.lkslib.router.ClassUtils;
import com.example.lkslib.router.IRouterRoot;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * TODO this class desription here
 * <p>
 * Created by ww on 2017/7/7.
 */
public class LKsUrlInit {
    private static final String ROUTE_ROOT_PAKCAGE = "com.example.mylibrary.url";

    public static void init(Context context) {
        try {
            List<String> classFileNames = ClassUtils.getFileNameByPackageName(context, ROUTE_ROOT_PAKCAGE);
            for (String s : classFileNames) {
                ((ILksUrlHelper) (Class.forName(s).getConstructor().newInstance())).register();
            }

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
