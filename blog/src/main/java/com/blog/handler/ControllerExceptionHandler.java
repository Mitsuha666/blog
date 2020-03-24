package com.blog.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice   //拦截所有controller抛出的异常，对异常进行统一的处理
public class ControllerExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(Exception.class)  //表示该方法可以处理所有类型异常
    public ModelAndView exceptionHandler(HttpServletRequest request, Exception e) throws Exception {

        //日志打印异常信息
        logger.error("Request url: {}, Exception: {}", request.getRequestURI(), e);

        //不处理带有ResponseStatus注解的异常
        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null) {
            throw e;
        }

        //返回异常信息到自定义error页面
        ModelAndView mv = new ModelAndView();
        mv.addObject("url", request.getRequestURI());
        mv.addObject("Exception", e);
        mv.setViewName("error/error");

        return mv;
    }
}
