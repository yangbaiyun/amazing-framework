package org.amazing.framework.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by john on 2017/9/22.
 */
public class View {

    /**
     * view path
     */
    private String path;

    /**
     * model data
     */
    private Map<String,Object> model;

    public View(String path)
    {
        this.path=path;
        this.model=new HashMap<String, Object>();
    }


    public View addModel(String key,Object value)
    {
        model.put(key,value);
        return this;
    }

    public String getPath() {
        return path;
    }

    public Map<String, Object> getModel() {
        return model;
    }
}
