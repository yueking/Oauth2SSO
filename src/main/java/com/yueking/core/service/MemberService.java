package com.yueking.core.service;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberService {
    @RequestMapping("/addMember")
    public String addMember() {
        return "addMember";
    }

    @RequestMapping("/delMember")
    public String delMember() {
        return "delMember";
    }

    @RequestMapping("/updateMember")
    public String updateMember() {
        return "updateMember";
    }

    @RequestMapping("/showMember")
    public String showMember() {
        return "showMember";
    }

}
