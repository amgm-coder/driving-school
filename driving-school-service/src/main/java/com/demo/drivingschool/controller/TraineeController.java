package com.demo.drivingschool.controller;


import com.demo.drivingschool.model.dto.DataGridResult;
import com.demo.drivingschool.model.entity.TraineeEntity;
import com.demo.drivingschool.service.TraineeService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@AllArgsConstructor
@Log
public class TraineeController {

    private final TraineeService traineesManagementService;

    @RequestMapping("/findAllStudent")
    public String findAllStudent() throws Exception {

        return "admin/findAllStudent";

    }

    @RequestMapping("/pagingFindStudent")
    @ResponseBody
    public DataGridResult pagingFindStudent(HttpServletRequest req, Integer page, Integer pageSize) throws Exception {

        req.getSession().setAttribute("pageIndex", page);

        return traineesManagementService.list(page, pageSize);

    }

    @RequestMapping(value = "/findStudentByName")
    @ResponseBody
    public List<TraineeEntity> findStudentByName(String name) throws Exception {

        System.out.println(name);

        return traineesManagementService.findEntityByName(name);
    }

    @RequestMapping("/deleteStudentById")
    public String deleteStudentById(Integer id) throws Exception {

        traineesManagementService.del(id);

        return "redirect:findAllStudent";
    }

    @RequestMapping("/modifyStudentById")
    public ModelAndView modifyStudentById(Integer id) throws Exception {

        ModelAndView modelAndView = new ModelAndView();

        TraineeEntity student = traineesManagementService.getEntityById(id);

        modelAndView.addObject("student", student);

        modelAndView.setViewName("admin/modifyStudent");

        return modelAndView;


    }

    @RequestMapping("/updateStudent")
    public String updateStudent(TraineeEntity student) throws Exception {

        traineesManagementService.updateTrainee(student);

        return "redirect:findAllStudent";
    }

    @RequestMapping("/addStudent")
    public String addStudent(TraineeEntity student) throws Exception {

        traineesManagementService.insertTrainee(student);

        return "redirect:findAllStudent";
    }

    @RequestMapping("/goAddStudent")
    public String goAddStudent() throws Exception {

        return "admin/addStudent";
    }
}
