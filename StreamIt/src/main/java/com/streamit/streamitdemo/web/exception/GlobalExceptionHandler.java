package com.streamit.streamitdemo.web.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ModelAndView globalExceptionHandlerMethod(Exception ex){
        ModelAndView modelAndVew = new ModelAndView("error");
        modelAndVew.addObject("errorMsg", ex.getMessage());
        return modelAndVew;
    }
}
