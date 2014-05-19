package com.toptal.steppin.webservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * User Class.
 * User: ggomes
 */
@Controller
public class DocsController {

    @RequestMapping("/apidocs")
    public String render(){
        return "index";
    }
}
