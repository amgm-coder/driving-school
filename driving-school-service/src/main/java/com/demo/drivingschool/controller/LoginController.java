package com.demo.drivingschool.controller;

import com.demo.drivingschool.model.pojo.User;
import com.demo.drivingschool.service.AdminService;
import com.demo.drivingschool.service.TraineeService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@AllArgsConstructor
@Log
public class LoginController {

    private final TraineeService studentService;
    private final AdminService administratorService;

    @GetMapping("/")
    public ModelAndView index(ModelAndView modelAndView)
            throws Exception {
        User user = new User();
        user.setPhone("123456");
        user.setPassword("123456");
        user.setLoginType("1");
        modelAndView.addObject("user", user);
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @PostMapping("/login")
    public ModelAndView login(HttpSession session, ModelAndView modelAndView, User user)
            throws Exception {
        String loginType = user.getLoginType();
        String phone = user.getPhone();
        String password = user.getPassword();
        if (loginType.equals("1")) {
            if (studentService.studentLogin(phone, password)) {
                session.setAttribute("phone", phone);

                session.setAttribute("loginType", loginType);

                modelAndView.addObject("phone", phone);

                modelAndView.setViewName("student/stuMainPage");

                return modelAndView;
            }
        } else if (loginType.equals("0")) {

            if (administratorService.adminLogin(phone, password)) {
                session.setAttribute("phone", phone);

                session.setAttribute("loginType", loginType);

                modelAndView.addObject("phone", phone);

                modelAndView.setViewName("admin/adminMainPage");

                return modelAndView;
            }

        }

        modelAndView.addObject("error", "用户名或密码错误");

        modelAndView.setViewName("index");

        return modelAndView;

    }

    @RequestMapping("/exit")
    public ModelAndView exit(HttpServletRequest req,ModelAndView modelAndView)
            throws Exception {
        req.getSession().invalidate();
        modelAndView.addObject("user", new User());
        modelAndView.setViewName("index");
        return modelAndView;
    }
}
