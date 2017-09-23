package org.amazing.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by john on 2017/9/22.
 */
public final class PropsUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(PropsUtil.class);


    /*
    * load property file
    * */
    public static Properties loadProps(String fileName)
    {
        Properties props=null;
        InputStream is=null;
        try
        {
            is=ClassUtil.getClassLoader().getResourceAsStream(fileName);
            if(is==null)
                throw new FileNotFoundException(fileName+":file not found");
            props=new Properties();
            props.load(is);

        }
        catch (Exception e)
        {
            LOGGER.debug("LOAD FILE FAILD",e);
        }
        finally {

            if(is!=null)
            {
                try
                {
                    is.close();
                }
                catch (Exception e)
                {
                    LOGGER.debug("is close faild",e);
                }

            }

        }
        return props;
    }


    /*
       get value by key with default value ""
    * */
    public static String getString(Properties props,String key)
    {
        return getString(props,key,"");
    }

    public static String getString(Properties props,String key,String defaultValue)
    {
        String value=defaultValue;
        if(props!=null)
           value=props.getProperty(key);
        return value;
    }












}
