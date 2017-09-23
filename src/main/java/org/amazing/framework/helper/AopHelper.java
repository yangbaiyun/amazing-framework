package org.amazing.framework.helper;

import org.amazing.framework.annotation.Aspect;
import org.amazing.framework.proxy.AspectProxy;
import org.amazing.framework.proxy.Proxy;
import org.amazing.framework.proxy.ProxyManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.util.*;

/**
 * Created by john on 2017/9/23.
 */
public class AopHelper {




    private static final Logger LOGGER = LoggerFactory.getLogger(AopHelper.class);

    static {
        try {
            Map<Class<?>, Set<Class<?>>> proxyMap = createProxyMap();
            Map<Class<?>, List<Proxy>> targetMap = createTargetMap(proxyMap);
            for (Map.Entry<Class<?>, List<Proxy>> targetEntry : targetMap.entrySet()) {
                Class<?> targetClass = targetEntry.getKey();
                List<Proxy> proxyList = targetEntry.getValue();
                Object proxy = ProxyManager.createProxy(targetClass, proxyList);
                BeanHelper.setBean(targetClass, proxy);
            }
        } catch (Exception e) {
            LOGGER.error("aop failure", e);
        }
    }





    private static Set<Class<?>> createTargetClassSet(Aspect aspect)
    {
        Set<Class<?>> classSet=new HashSet<Class<?>>();
        Class<? extends Annotation>  annotation=aspect.value();
        if(annotation!=null && !annotation.equals(Aspect.class))
        {
              classSet.addAll(ClassHelper.getClassSetByAnnotation(annotation));
        }
        return classSet;
    }

    private static Map<Class<?>,Set<Class<?>>> createProxyMap() throws Exception
    {
        Map<Class<?>,Set<Class<?>>> proxyMap=new HashMap<Class<?>, Set<Class<?>>>();
        Set<Class<?>> proxyClassSet=ClassHelper.getClassSetBySuper(AspectProxy.class);
        for(Class<?> cls:proxyClassSet)
        {
             if(cls.isAnnotationPresent(Aspect.class) && !cls.equals(Aspect.class))
             {
                 Aspect aspect=cls.getAnnotation(Aspect.class);
                 Set<Class<?>> targetClassSet=createTargetClassSet(aspect);
                 proxyMap.put(cls,targetClassSet);
             }

        }

         return proxyMap;


    }

    private static Map<Class<?>,List<Proxy>>  createTargetMap(Map<Class<?>,Set<Class<?>>> proxyMap) throws  Exception
    {
        Map<Class<?>,List<Proxy>> targetMap=new HashMap<Class<?>, List<Proxy>>();
        for(Map.Entry<Class<?>,Set<Class<?>>> proxyEntry:proxyMap.entrySet())
        {
            Class<?> proxyClass=proxyEntry.getKey();
            Set<Class<?>> targetClassSet=proxyEntry.getValue();
            for(Class<?> targetClass:targetClassSet)
            {
                Proxy proxy=(Proxy)proxyClass.newInstance();
                if(targetMap.containsKey(targetClass))
                {
                    targetMap.get(proxy).add(proxy);
                }
                else
                {
                     List<Proxy> list=new ArrayList<Proxy>();
                     list.add(proxy);
                     targetMap.put(targetClass,list);
                }


            }

        }
      return  targetMap;
    }






}
