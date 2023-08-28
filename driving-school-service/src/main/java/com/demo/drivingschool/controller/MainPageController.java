package com.demo.drivingschool.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainPageController {

    @GetMapping("/stuMainPage")
    public String stuMainPage() throws Exception {
        return "student/stuMainPage";
    }

    @GetMapping("/adminMainPage")
    public String adminMainPage() throws Exception {
        return "admin/adminMainPage";
    }

}
