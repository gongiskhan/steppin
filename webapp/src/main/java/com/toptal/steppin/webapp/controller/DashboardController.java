package com.toptal.steppin.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by ggomes on 10/05/14.
 */
@Controller
public class DashboardController {
    @RequestMapping(value = {"/","/index.html","/dashboard.html"})
    public String render(){
        return "index";
    }
}
