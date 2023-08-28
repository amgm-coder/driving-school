package com.demo.drivingschool.controller;

import com.demo.drivingschool.model.entity.TraineeEntity;
import com.demo.drivingschool.service.TraineeService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@AllArgsConstructor
@Log
public class StudentPersonController {

    @Autowired
    private TraineeService studentService;

    @RequestMapping("/goStuModifyPersonInformation")
    public ModelAndView goStuModifyPersonInformation(HttpServletRequest req) throws Exception {

        ModelAndView modelAndView = new ModelAndView();

        String phone = (String) req.getSession().getAttribute("phone");

        TraineeEntity student = studentService.getStudentByPhone(phone);

        modelAndView.addObject("student", student).setViewName("student/stuPersonInformation");

        return modelAndView;

    }

    @RequestMapping("/submitStuInformation")
    public String submitStuInformation(TraineeEntity student) throws Exception {

        studentService.updateInformation(student);

        return "redirect:stuMainPage";
    }

    @RequestMapping("/stuModifyPassword")
    public String stuModifyPassword() throws Exception {

        return "student/stuModifyPassword";
    }

    @RequestMapping("/stuCheckPassword")
    @ResponseBody
    public boolean checkPassword(HttpServletRequest req, String password) throws Exception {

        String phone = (String) req.getSession().getAttribute("phone");

        return studentService.studentLogin(phone, password);

    }

    @RequestMapping("/stuSubmitPassword")
    public ModelAndView stuSubmitPassword(HttpServletRequest req,
                                          String password, String onePassword, String doublePassword) throws Exception {

        ModelAndView modelAndView = new ModelAndView();

        String phone = (String) req.getSession().getAttribute("phone");

        boolean state = studentService.stuModifyPassword(phone, password, onePassword, doublePassword);

        if (state) {

            req.getSession().invalidate();

            modelAndView.addObject("success", "修改密码成功，请重新登录！").setViewName("index");

        } else {

            modelAndView.addObject("defeat", "修改密码失败！").setViewName("student/stuModifyPassword");
        }

        return modelAndView;
    }

    @RequestMapping("/orderStudyCar")
    public ModelAndView orderStudyCar(HttpServletRequest req) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        String phone = (String) req.getSession().getAttribute("phone");
        TraineeEntity student = studentService.getStudentByPhone(phone);
        modelAndView.addObject("student", student).setViewName("student/studyCar");
        return modelAndView;
    }

}
