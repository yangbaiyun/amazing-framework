package org.amazing.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by john on 2017/9/22.
 */
public class ReflectionUtil {


    public static final Logger LOGGER= LoggerFactory.getLogger(ReflectionUtil.class);

    /**
     * create new instance
     * @param cls
     * @return
     */
    public static Object getInstance(Class<?> cls)
    {
        Object o=null;
        try
        {
            o=cls.newInstance();
        }
        catch (Exception e)
        {
            LOGGER.error("new instance failed",e);
            throw new RuntimeException(e);
        }
        return o;
    }

    public static Object invokeMethod(Object obj, Method method,Object... args)
    {
          Object result;
          try
          {
              method.setAccessible(true);
              result=method.invoke(obj,args);
          }
          catch (Exception e)
          {
              LOGGER.error("method invoke failed",e);
              throw new RuntimeException(e);
          }

     return result;
    }

    /**
     * set the value of field
     */
    public static void setField(Object obj, Field field,Object value)
    {
        try
        {
           field.setAccessible(true);
           field.set(obj,value);
        }
        catch (Exception e)
        {
            LOGGER.error("set the value of field failed",e);
            throw new RuntimeException(e);
        }
    }





}
