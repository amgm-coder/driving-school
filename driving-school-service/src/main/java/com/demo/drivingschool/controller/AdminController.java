package com.demo.drivingschool.controller;

import com.demo.drivingschool.model.entity.AdminEntity;
import com.demo.drivingschool.service.AdminService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author
 * 
 */
@Controller
@AllArgsConstructor
@Log
public class AdminController {

    @Autowired
    private AdminService administratorService;

    @GetMapping("/getPersonInformation")
    public ModelAndView getPersonInformation(HttpServletRequest req) throws Exception {

        ModelAndView modelAndView = new ModelAndView();

        String phone = (String) req.getSession().getAttribute("phone");

        AdminEntity administrator = administratorService.getAdminByPhone(phone);
        modelAndView.addObject("administrator", administrator).setViewName("admin/personInformation");
        return modelAndView;
    }

    @RequestMapping(value = "/submitInformation", method = {RequestMethod.POST})
    public String submitInformation(AdminEntity administrator) throws Exception {

        administratorService.updateAdminById(administrator);

        return "redirect:adminMainPage";
    }

    @RequestMapping("/goModifyPassword")
    public String goModifyPassword() throws Exception {

        return "admin/modifyPassword";
    }

    @RequestMapping("/checkPassword")
    @ResponseBody
    public boolean checkPassword(HttpServletRequest req, String password) throws Exception {

        String phone = (String) req.getSession().getAttribute("phone");

        return administratorService.adminLogin(phone, password);
    }

    @RequestMapping("/submitPassword")
    public ModelAndView submitPassword(HttpServletRequest req, String oldPassword, String onePassword, String doublePassword)
            throws Exception {

        ModelAndView modelAndView = new ModelAndView();

        String phone = (String) req.getSession().getAttribute("phone");

        boolean state = administratorService.modifyPassword(phone, oldPassword, onePassword, doublePassword);

        if (state) {

            req.getSession().invalidate();

            modelAndView.addObject("success", "修改密码成功，请重新登录！").setViewName("index");

        } else {

            modelAndView.addObject("defeat", "修改密码失败！").setViewName("admin/modifyPassword");
        }

        return modelAndView;
    }
}
