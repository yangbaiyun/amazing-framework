package org.amazing.framework;

import org.amazing.framework.helper.*;
import org.amazing.framework.util.ClassUtil;

/**
 * Created by john on 2017/9/22.
 */
public class HelperLoader {

    public static void init()
    {

        Class<?>[] classList={ClassHelper.class, BeanHelper.class, ControllerHelper.class,AopHelper.class,IocHelper.class};
         for(Class<?> cls:classList)
         {
             ClassUtil.loadClass(cls.getName(),true);
         }

    }




}
