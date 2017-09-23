package org.amazing.framework.helper;


import org.amazing.framework.ConfigConstant;
import org.amazing.framework.util.PropsUtil;

import java.util.Properties;

/**
 * 属性文件助手类
 * Created by john on 2017/9/22.
 */
public final class HelperConfig {


     private static final Properties CONFIG_PROPS= PropsUtil.loadProps(ConfigConstant.CONFIG_FILE);

     /*
         get jdbcDriver
      */
     public static String getJdbcDriver()
     {
         return PropsUtil.getString(CONFIG_PROPS,ConfigConstant.JDBC_DRIVER);
     }

     public static String getJdbcUrl()
     {
         return PropsUtil.getString(CONFIG_PROPS,ConfigConstant.JDBC_URL);
     }

     public static String getJdbcUserName()
     {
         return PropsUtil.getString(CONFIG_PROPS,ConfigConstant.JDBC_USERNAME);
     }

    public static String getJdbcPassword()
    {
        return PropsUtil.getString(CONFIG_PROPS,ConfigConstant.JDBC_PASSWORD);
    }


    public static String getBasePackage()
    {
        return PropsUtil.getString(CONFIG_PROPS,ConfigConstant.APP_BASE_PACKAGE);
    }

    public static String getJspPath()
    {
        return PropsUtil.getString(CONFIG_PROPS,ConfigConstant.APP_JSP_PATH,"/WEB_INF/view/");
    }

    public static String getAssetPath()
    {
        return PropsUtil.getString(CONFIG_PROPS,ConfigConstant.APP_ASSET_PATH,"/asset/");
    }



}
