package com.atguigu.spring.curd.handlers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class HandleException {
    @ExceptionHandler(ArithmeticException.class)
    public ModelAndView handlerArithmeticException(Exception ex){
        System.out.println("出异常了3：" + ex);
        ModelAndView mv = new ModelAndView("error");
        mv.addObject("exception",ex);
        return mv;
    }
}
