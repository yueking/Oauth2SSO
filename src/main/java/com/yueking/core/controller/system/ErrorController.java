package com.yueking.core.controller.system;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 全局错误消息返回处理
 */
@RestController
@RequestMapping(value = "/error")
public class ErrorController {

    @RequestMapping(value = "/{name}")
    public String error403(@PathVariable String name) {
        return "ErrorCode:"+name;
    }

}
