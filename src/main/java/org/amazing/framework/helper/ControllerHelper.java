package org.amazing.framework.helper;

import org.amazing.framework.annotation.Action;
import org.amazing.framework.bean.Handler;
import org.amazing.framework.bean.Request;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by john on 2017/9/22.
 */
public final class ControllerHelper {


    /**
     * store the mapping of request and handler
     */
    private static final Map<Request,Handler > ACTION_MAP=new HashMap<Request, Handler>();

    static{
        Set<Class<?>> controllerClass=ClassHelper.getControlClassSet();
        if(controllerClass!=null)
        {
            for(Class<?> cls:controllerClass)
            {
                Method[] methods=cls.getDeclaredMethods();
                if(methods!=null)
                {
                    for(Method method:methods)
                    {
                        if(method.isAnnotationPresent(Action.class))
                        {
                            Action action=method.getAnnotation(Action.class);
                            String mapping=action.value();
                            if(mapping.matches("\\w+:/\\w*"))
                            {
                                 String[] array=mapping.split(":");
                                 String requestMethod=array[0];
                                 String requestPath=array[1];
                                 Request request=new Request(requestMethod,requestPath);
                                 Handler handler=new Handler(cls,method);
                                 ACTION_MAP.put(request,handler);
                            }
                        }
                    }
                }
            }
        }



    }



    public static Handler getHandler(String requestMethod,String requestPath)
    {
               Request request=new Request(requestMethod,requestPath);

                   return ACTION_MAP.get(request);


    }



}
