package com.qst.human_resources.handles;

import com.qst.human_resources.dto.UserDTO;
import com.qst.human_resources.utils.LogUtil;
import org.slf4j.LoggerFactory;
import org.springframework.core.NamedThreadLocal;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import sun.swing.FilePane;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Map;
import java.util.logging.Logger;

/*
 * @description:
 * @program: human_resources
 * @author: syun
 * @create: 2018-07-09 13:55
 */

public class LoginInterceptor implements HandlerInterceptor
{


    private static final org.slf4j.Logger LOGGER =
            LoggerFactory.getLogger(LoggerFactory.class);

    private static final ThreadLocal<Long> startTimeThreadLocal =
            new NamedThreadLocal<>("ThreadLocal StartTime");

    private String getParamString(Map<String, String[]> map)
    {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String[]> e : map.entrySet())
        {
            sb.append(e.getKey()).append("=");
            String[] value = e.getValue();
            if (value != null && value.length == 1)
            {
                sb.append(value[0]).append("\t");
            }
            else
            {
                sb.append(Arrays.toString(value)).append("\t");
            }
        }
        return sb.toString();
    }

    /**
     * 将ErrorStack转化为String.
     *
     * @param e throw 过来的exception
     * @return
     */
    private static String getStackTraceAsString(Throwable e)
    {
        if (e == null)
        {
            return "";
        }
        StringWriter stringWriter = new StringWriter();
        e.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException
    {
        long startTime = System.currentTimeMillis();
        request.setAttribute("startTime", startTime);
        //线程绑定变量（该数据只有当前请求的线程可见）
        startTimeThreadLocal.set(startTime);
        if (handler instanceof HandlerMethod)
        {
            HandlerMethod h = (HandlerMethod) handler;
            String log = "-----------------------开始计时:" +
                    new SimpleDateFormat("hh:mm:ss.SSS").format(startTime) +
                    "-----------------------\n" +
                    "IP        : " + request.getRemoteAddr() + "\n" +
                    "Host      : " + request.getRemoteHost() + "\n" +
                    "ServerPort: " + request.getServerPort() + "\n" +
                    "LocalPort : " + request.getLocalPort() + "\n" +
                    "RemotePort: " + request.getRemotePort() + "\n" +
                    "RemoteUser: " + request.getRemoteUser() + "\n" +
                    "Controller: " + h.getBean().getClass().getName() + "\n" +
                    "Method    : " + h.getMethod().getName() + "\n" +
                    "Params    : " + getParamString(request.getParameterMap()) + "\n" +
                    "URI       : " + request.getRequestURI() + "\n";
            System.err.println(log);
            LogUtil.LogWriteIn(log);
        }

        if (request.getRequestURI().equals("/LoginTo") || request.getRequestURI().equals("/toLogin"))
        {
            System.out.println("not redirect. ");
        }
        else
        {
            UserDTO user = (UserDTO) request.getSession().getAttribute("user");
            //System.err.println("preHandle");
            if (null == user)
            {
                //System.err.println("user is not exist! ");
                response.sendRedirect("toLogin");
                return false;
            }
//        else
//        {
//            System.err.println("user exist. ");
//            System.err.println(log.getName() + user.getUsername());
//        }
        }

        return true;
    }

    /**
     * 在当前请求进行处理之后，也就是Controller 方法调用之后执行，
     * 但是它会在DispatcherServlet 进行视图返回渲染之前被调用，
     * 所以我们可以在这个方法中对Controller 处理之后的ModelAndView 对象进行操作。
     *
     * @param request      http请求
     * @param response     返回
     * @param handler
     * @param modelAndView
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
    {
        long startTime = (Long) request.getAttribute("startTime");
        long endTime = System.currentTimeMillis();
        long executeTime = endTime - startTime;
        if (handler instanceof HandlerMethod)
        {
            String log = "CostTime  : " + executeTime + "ms" + "\n" +
                    "----------------------------------------------\n\n\n\n";
            System.err.println(log);
            LogUtil.LogWriteIn(log);
        }
    }


    /**
     * 该方法将在整个请求结束之后，也就是在DispatcherServlet
     * 渲染了对应的视图之后执行。这个方法的主要作用是用于进行资源清理工作的。
     *
     * @param request  http请求
     * @param response 返回
     * @param handler  对象
     * @param ex       异常
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
    {
        // 打印JVM信息。
        if (LOGGER.isDebugEnabled())
        {
            //得到线程绑定的局部变量（开始时间）
            long beginTime = startTimeThreadLocal.get();
            //结束时间
            long endTime = System.currentTimeMillis();

            //如果controller报错，则记录异常错误
            if (ex != null)
            {
                System.err.println("Controller异常: " + getStackTraceAsString(ex));
            }

            String log =
                    (
            "-----------------------计时结束：" +
                    new SimpleDateFormat("hh:mm:ss.SSS").format(endTime) +
                    "-----------------------\n" +
                    "耗时                 ：" + (endTime - beginTime) + "ms\n" +
                    "URI                 :" + request.getRequestURI() + "\n" +
                    "最大内存             : " + Runtime.getRuntime().maxMemory() / 1024 / 1024 + "m\n" +
                    "已分配内存           : " + Runtime.getRuntime().totalMemory() / 1024 / 1024 + "m\n" +
                    "已分配内存中的剩余空间: " + Runtime.getRuntime().freeMemory() / 1024 / 1024 + "m\n" +
                    "最大可用内存         : " + (Runtime.getRuntime().maxMemory() -
                    Runtime.getRuntime().totalMemory() + Runtime.getRuntime().freeMemory())
                    / 1024 / 1024 + "m");
            LogUtil.LogWriteIn(log);
            startTimeThreadLocal.remove();
        }
    }
}
