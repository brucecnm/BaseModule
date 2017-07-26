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
import android.util.Log;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * TODO this class desription here
 * <p>
 * Created by ww on 2017/7/12.
 */
public class EventHelper {
    static String TAG = EventHelper.class.getSimpleName();
    public static List<LksEventModel> lksServletList;

    public static void register(String key, String moduleName, Class value) {
        if (null == lksServletList) {
            lksServletList = new ArrayList<>();
        }

        if (!verifyData(key)) {
            return;
        }

        LksEventModel eventModelBean = getLksEventModelBean(key, moduleName, value);

        for (LksEventModel model : lksServletList) {
            if (model.getCode().contains(key)) {
                    List<LksEventModel> groups = model.getGroups();
                boolean hasSet=false;
               A: for (LksEventModel model1: groups) {
                    if(model1.getCode().equals(key)){
                        hasSet=true;
                        break A;
                    }
                }
                if(!hasSet){
                    groups.add(eventModelBean);
                    Collections.sort(groups, new Comparator<LksEventModel>(){

                        /*
                         * 返回负数表示：o1 小于o2，
                         * 返回正数表示：o1大于o2。
                         */
                        public int compare(LksEventModel o1, LksEventModel o2) {
                            if(o1.getCode().length() > o2.getCode().length()){
                                return 1;
                            }else {
                                return -1;
                            }
                        }
                    });

                    model.setGroups(groups);
                }
            }
        }
        lksServletList.add(eventModelBean);
        Log.d(TAG, "register............<" + key + "-----" + value + ">");
    }

    private static LksEventModel getLksEventModelBean(String key, String moduleName, Class value) {
        LksEventModel model = new LksEventModel();
        model.setCode(key);
        model.setaClass(value);
        model.setGroups(getGroups(key));
        model.setModuleName(moduleName);

        return model;
    }

    private static boolean verifyData(String key) {
        boolean pass = true;
        for (LksEventModel model :
                lksServletList) {
            if (model.getCode().equals(key)) {
                pass = false;
                Log.e(TAG, "verifyData:this key has register");
                break;
            }
        }
        return pass;
    }

    public static void handlerEvent(@NonNull UrlBean urlBean) throws NoSuchMethodException {
        String lksUrl = urlBean.getCode();
        try {
            A: for (LksEventModel model : lksServletList) {
                if (lksUrl.equals(model.getCode())) {
                    Class aClass = model.getaClass();
                    boolean post = ((LksServlet) (aClass.getConstructor().newInstance())).doPost(urlBean);
                    if (!post) {
                        List<LksEventModel> groups = model.getGroups();
                        if(null!=groups&&groups.size()>0){
                            for (int i = 0; i < groups.size(); i++) {
                                boolean post1 = ((LksServlet) (groups.get(i).getaClass().getConstructor().newInstance())).doPost(urlBean);
                                if(post1){
                                   break A;
                                }
                            }
                        }
                    }
                }
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            throw e;
        } catch (NullPointerException e) {

        }
    }

    private static List<LksEventModel> getGroups(String lksUrl) {
        List<LksEventModel> groups = new ArrayList<>();
        String temp = lksUrl;
        try {
            String replace = lksUrl.replace("lks://", "");
            String[] split = replace.split("/");
//            Main/Demo/DemoServlet/DemoServlet1"
            if (split.length > 2) {
//               有 group
                for (int i = split.length - 1; i > 0; i--) {
                    temp = temp.replace("/" + split[i], "");
                    if (null != lksServletList && lksServletList.size() > 0) {
                        for (LksEventModel model : lksServletList) {
                            if (model.getCode().equals(temp)) {
                                groups.add(model);
                            }
                        }
                    }
                }

            }
        } catch (Exception e) {
        } finally {
            Collections.sort(groups, new Comparator<LksEventModel>(){
                public int compare(LksEventModel o1, LksEventModel o2) {
                    if(o1.getCode().length() > o2.getCode().length()){
                        return 1;
                    }else {
                        return -1;
                    }
                }
            });
            return groups;
        }
    }
}
