package com.yueking.core.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
    @RequestMapping("/")
    public String login(){
        return "login";
    }

    @RequestMapping("/toMain")
    public String main() {
        return "main";
    }

    @RequestMapping("/toError")
    public String error() {
        return "redirect:error.html";
    }

    @RequestMapping("/toLogin")
    public String showLogin() {
        return "login";
    }
}
