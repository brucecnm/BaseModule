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

import java.util.List;

/**
 * TODO this class desription here
 * <p>
 * Created by ww on 2017/7/14.
 */
public class LksEventModel {
    String code;
    private String moduleName;
    private List<LksEventModel> groups;
    private Class aClass;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public List<LksEventModel> getGroups() {
        return groups;
    }

    public void setGroups(List<LksEventModel> groups) {
        this.groups = groups;
    }

    public Class getaClass() {
        return aClass;
    }

    public void setaClass(Class aClass) {
        this.aClass = aClass;
    }
}
