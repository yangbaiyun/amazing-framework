package org.amazing.framework.bean;

import java.util.Map;

/**
 * Created by john on 2017/9/22.
 */
public class Param {

    private Map<String,Object> paramMap;

    public Param(Map<String, Object> paramMap) {
        this.paramMap = paramMap;
    }

    public long getLong(String name)
    {
            return (Long)paramMap.get(name);
    }

    public Map<String, Object> getParamMap() {
        return paramMap;
    }
}
