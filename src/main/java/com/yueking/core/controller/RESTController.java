package com.yueking.core.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class RESTController {
    @GetMapping("/version")
    @ResponseBody
    public String version() {
        return "v1.2";
    }
}
