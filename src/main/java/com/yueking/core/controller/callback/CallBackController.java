package com.yueking.core.controller.callback;

import org.springframework.web.bind.annotation.*;

@RestController
public class CallBackController {
    @GetMapping(value = "/callback")
    public String callBackCode(@RequestParam String code) {
        return code;
    }

}
