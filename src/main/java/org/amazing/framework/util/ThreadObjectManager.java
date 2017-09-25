package org.amazing.framework.util;

import org.amazing.framework.dao.DataAccessException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by john on 2017/9/24.
 */
public class ThreadObjectManager {

    private final Log logger= LogFactory.getLog(this.getClass());

    private static  ThreadLocal threadLocal= new ThreadLocal(){

        protected Object initialValue() {
            return new HashMap();
        }
    };

    private Map getThreadObjectMap(){
        return (Map)threadLocal.get();
    }



    public boolean hasThreadObject(Object key){return getThreadObjectMap().containsKey(key);}


    public Object getThreadObject(Object key)
    {
        return getThreadObjectMap().get(key);
    }

    public void bindThreadObject(Object key,Object value)
    {
        if(getThreadObjectMap().containsKey(key))
            logger.debug(key.toString()+"has existed in threadObject");
        else
            getThreadObjectMap().put(key,value);

    }


    public void remvoeThreadObject(Object key)
    {
        if(getThreadObjectMap().containsKey(key))
            getThreadObjectMap().remove(key);
        else
            logger.debug(key+"is not exist in ThreadObject");
    }


}
