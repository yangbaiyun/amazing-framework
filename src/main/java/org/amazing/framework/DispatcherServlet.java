package org.amazing.framework;

import org.amazing.framework.bean.Data;
import org.amazing.framework.bean.Handler;
import org.amazing.framework.bean.Param;
import org.amazing.framework.bean.View;
import org.amazing.framework.helper.BeanHelper;
import org.amazing.framework.helper.ClassHelper;
import org.amazing.framework.helper.ControllerHelper;
import org.amazing.framework.helper.HelperConfig;
import org.amazing.framework.util.*;
import org.omg.CORBA.DATA_CONVERSION;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by john on 2017/9/22.
 */

//@WebServlet(urlPatterns="/*")
public class DispatcherServlet extends HttpServlet{

    @Override
    public void init(ServletConfig servletConfig) throws ServletException
    {
        HelperLoader.init();
        ServletContext context=servletConfig.getServletContext();
        registerServlet(context);


    }
    private void registerServlet(ServletContext servletContext) {
     //   ServletRegistration jspServlet = servletContext.getServletRegistration("jsp");
       // jspServlet.addMapping("/index.jsp");
      //  jspServlet.addMapping("/WEB_INF/view/customer.jsp");

      //  ServletRegistration defaultServlet = servletContext.getServletRegistration("default");
       // defaultServlet.addMapping("/favicon.ico");
      //  defaultServlet.addMapping(HelperConfig.getAssetPath() + "*");
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        myService(req,resp);
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        myService(req,resp);
        //req.getRequestDispatcher("/WEB-INF/view/customer.jsp").forward(req, resp);
    }



    protected void myService(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestMethod = req.getMethod().toLowerCase();
        String requestPath=req.getPathInfo();
         System.out.println("4444444444444444444");
        Handler handler= ControllerHelper.getHandler(requestMethod,requestPath);
        if(handler!=null)
        {
            Class<?> controllerClass=handler.getControllerClass();
            Object controllerBean= BeanHelper.getBean(controllerClass);
            //create request parametner object
            Map<String,Object> paramMap=new HashMap<String, Object>();
            Enumeration<String> paramNames=req.getParameterNames();
            while(paramNames.hasMoreElements())
            {
                String paramName=paramNames.nextElement();
                String paramValue=req.getParameter(paramName);
                paramMap.put(paramName,paramValue);

            }

            String body = CodecUtil.decodeURL(StreamUtil.getString(req.getInputStream()));
            if (StringUtil.isNotEmpty(body)) {
                String[] kvs = StringUtil.splitString(body, "&");
                if (ArrayUtil.isNotEmpty(kvs)) {
                    for (String kv : kvs) {
                        String[] array = StringUtil.splitString(kv, "=");
                        if (ArrayUtil.isNotEmpty(array) && array.length == 2) {
                            String fieldName = array[0];
                            String fieldValue = array[1];
                            paramMap.put(fieldName,fieldValue);
                        }
                    }
                }
            }

            Param param=new Param(paramMap);
            Method actionMethod = handler.getActionMethod();
            Object result = ReflectionUtil.invokeMethod(controllerBean, actionMethod, param);
            if(result instanceof Data)
            {
                Data data=(Data)result;
                Object model=data.getModel();
                if(model!=null)
                {
                    resp.setContentType("application/json");
                    resp.setCharacterEncoding("UTF-8");
                    PrintWriter writer = resp.getWriter();
                    String json = JsonUtil.toJson(model);
                    writer.write(json);
                    writer.flush();
                    writer.close();
                }
            }
            else if(result instanceof View)
            {
                handleViewResult((View) result, req, resp);
            }



        }




    }





    private void handleViewResult(View view, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String path = view.getPath();
        if (StringUtil.isNotEmpty(path)) {
            if (path.startsWith("/")) {
                response.sendRedirect(request.getContextPath() + path);
            } else {
                Map<String, Object> model = view.getModel();
                for (Map.Entry<String, Object> entry : model.entrySet()) {
                    request.setAttribute(entry.getKey(), entry.getValue());
                }
                String tt= request.getContextPath();
                String path2=HelperConfig.getJspPath() + path;
                request.getRequestDispatcher(HelperConfig.getJspPath()+path).forward(request, response);
            }
        }
    }



}
