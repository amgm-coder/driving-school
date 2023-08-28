package com.demo.drivingschool.controller;

import com.demo.drivingschool.model.entity.UserInfoEntity;
import com.demo.drivingschool.service.UserInfoService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author
 * 
 */
@RestController
@AllArgsConstructor
public class HomeController {
    private final UserInfoService userInfoService;

    @GetMapping("home")
    public String home() {
        return "hello world!";
    }

    @GetMapping("list")
    public List<UserInfoEntity> list() {
        return userInfoService.list();
    }
}
