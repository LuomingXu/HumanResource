package com.qst.human_resources.handles;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/*
 * @description:
 * @program: human_resources
 * @author: syun
 * @create: 2018-07-13 09:49
 */

/**
 * 全局处理异常类
 */
@ControllerAdvice
public class GlobalDefultExceptionHandler {


    //声明要捕获的异常
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public String defultExcepitonHandler(HttpServletRequest request,Exception e) {
        return "error";
    }

}
