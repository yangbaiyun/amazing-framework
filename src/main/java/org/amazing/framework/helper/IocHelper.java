package org.amazing.framework.helper;

import org.amazing.framework.annotation.Inject;
import org.amazing.framework.util.ReflectionUtil;
import sun.reflect.Reflection;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * Created by john on 2017/9/22.
 */
public final class IocHelper {


   static{
       Map<Class<?>,Object> beanMap=BeanHelper.getBeanMap();
       if(beanMap!=null)
       {
           for(Map.Entry<Class<?>,Object> beanEntry:beanMap.entrySet())
           {
               Class<?> beanClass=beanEntry.getKey();
               Object beanInstance=beanEntry.getValue();
               Field[] beanFields=beanClass.getDeclaredFields();
               if(beanFields!=null)
               {
                   for(Field beanField:beanFields)
                   {
                       if(beanField.isAnnotationPresent(Inject.class))
                       {
                           Class<?> beanFieldClass = beanField.getType();
                           Object beanFieldInstance= beanMap.get(beanFieldClass);
                           if(beanFieldInstance!=null)
                           {
                               ReflectionUtil.setField(beanInstance,beanField,beanFieldInstance);
                           }
                       }
                   }
               }
           }


       }

   }



}
