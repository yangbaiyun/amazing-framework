package org.amazing.framework.bean;

import java.lang.reflect.Method;

/**
 * Created by john on 2017/9/22.
 */
public class Handler {


    private Class<?> controllerClass;

    private Method actionMethod;

    public Handler(Class<?> controllerClass, Method actionMethod) {
        this.controllerClass = controllerClass;
        this.actionMethod = actionMethod;
    }

    public Class<?> getControllerClass() {
        return controllerClass;
    }

    public Method getActionMethod() {
        return actionMethod;
    }






}
