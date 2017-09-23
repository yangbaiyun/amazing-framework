package org.amazing.framework.helper;

import org.amazing.framework.annotation.Controller;
import org.amazing.framework.annotation.Service;
import org.amazing.framework.util.ClassUtil;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by john on 2017/9/22.
 */
public class ClassHelper {


    private static final Set<Class<?>>  CLASS_SET;

    static{
        String BASE_PACKAGE=HelperConfig.getBasePackage();
        CLASS_SET=ClassUtil.getClassSet(BASE_PACKAGE);
    }


    public static Set<Class<?>> getClassSet()
    {
        return CLASS_SET;
    }

    public static Set<Class<?>> getServiceClassSet()
    {
       Set<Class<?>> serviceClassSet=new HashSet<Class<?>>();
       for(Class<?> cls:CLASS_SET)
       {
            if(cls.isAnnotationPresent(Service.class))
            {
                serviceClassSet.add(cls);
            }
       }

       return serviceClassSet;
    }

    public static Set<Class<?>> getControlClassSet()
    {
        Set<Class<?>> controlClassSet=new HashSet<Class<?>>();
        for(Class<?> cls:CLASS_SET)
        {
            if(cls.isAnnotationPresent(Controller.class))
            {
                controlClassSet.add(cls);
            }
        }

        return controlClassSet;
    }

    public static Set<Class<?>> getAllBeanClassSet()
    {
        Set<Class<?>> allBeanClassSet=new HashSet<Class<?>>();
        allBeanClassSet.addAll(getControlClassSet());
        allBeanClassSet.addAll(getServiceClassSet());
        return allBeanClassSet;
    }

     //get the implement class for a father class
     public static Set<Class<?>> getClassSetBySuper(Class<?> superClass)
     {
         Set<Class<?>> classSet=new HashSet<Class<?>>();
         for(Class<?> cls:CLASS_SET)
         {
             if(superClass.isAssignableFrom(cls)&& !superClass.equals(cls))
             {
                 classSet.add(cls);
             }
         }

         return classSet;

     }

    //get all the class which has a typical annotation
    public static Set<Class<?>> getClassSetByAnnotation(Class<? extends Annotation> annotationClass)
    {
        Set<Class<?>> classSet=new HashSet<Class<?>>();
        for(Class<?> cls:CLASS_SET)
        {
            if(cls.isAnnotationPresent(annotationClass))
            {
                classSet.add(cls);
            }
        }

        return classSet;

    }






}
