package com.toptal.steppin.webapp.controller;

import com.toptal.steppin.core.error.CoreException;
import com.toptal.steppin.core.model.User;
import com.toptal.steppin.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;

/**
 * Created by ggomes on 10/05/14.
 */
@Controller
public class RegistrationController {

    @Autowired
    UserService userService;

    @RequestMapping(value = {"/register"})
    public String render(){
        return "register";
    }

    @RequestMapping(value="/register", method = RequestMethod.POST)
    public String register(User newUser) throws CoreException, UnsupportedEncodingException, GeneralSecurityException {
        userService.persist(newUser);
        return "login";
    }
}
