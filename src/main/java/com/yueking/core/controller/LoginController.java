package com.yueking.core.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
    @RequestMapping("/")
    public String login(){
        return "redirect:login.html";
    }

    @RequestMapping("/toMain")
    public String main() {
        return "redirect:main.html";
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
