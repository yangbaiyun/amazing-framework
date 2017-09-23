package org.amazing.framework.helper;

import org.amazing.framework.util.ReflectionUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by john on 2017/9/22.
 */
public class BeanHelper {


    /**
     * definition the mapping of Bean
     */
    private static final Map<Class<?>,Object> BEAN_MAP=new HashMap<Class<?>, Object>();

    static
    {
        Set<Class<?>> beanSet=ClassHelper.getAllBeanClassSet();
        for(Class<?> cls:beanSet)
        {
            Object obj= ReflectionUtil.getInstance(cls);
            BEAN_MAP.put(cls,obj);
        }
    }

    /**
     * get mapping of bean
     */
    public static Map<Class<?>,Object> getBeanMap()
    {
        return BEAN_MAP;
    }

    /**
     * get bean instance by bean class
     */
    public static <T> T getBean(Class<T> cls)
    {
        if(!BEAN_MAP.containsKey(cls))
            throw new RuntimeException("can not find key"+cls);
        return (T)BEAN_MAP.get(cls);
    }


    public static void setBean(Class<?> cls,Object obj)
    {
         BEAN_MAP.put(cls,obj);
    }




}
