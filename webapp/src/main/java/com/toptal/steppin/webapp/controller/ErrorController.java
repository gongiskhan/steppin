package com.toptal.steppin.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ErrorController {

    @RequestMapping(value = "/403.html")
    public String notAuthorized(){
        return "notAuthorized";
    }
    @RequestMapping(value = "/404.html")
    public String notFound(){
        return "notFound";
    }
    @RequestMapping(value = "/500.html")
    public String serverError(){
        return "serverError";
    }
    @RequestMapping(value = "/503.html")
    public String serviceUnavailable(){
        return "serviceUnavailable";
    }
    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(Exception ex){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("exception");
        mv.addObject("exceptionMessage",ex.getMessage());
        return mv;
    }
}
