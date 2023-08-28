package com.demo.drivingschool.controller;

import com.demo.drivingschool.model.entity.CarEntity;
import com.demo.drivingschool.service.CarService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

@Controller
@AllArgsConstructor
@Log
@Api("交通工具管理")
public class CarController {

    @Resource
    private CarService vehicleManagementService;

    @RequestMapping("/findAllCarPage")
    public String findAllCarPage() throws Exception {

        return "admin/findAllCar";
    }

    @RequestMapping("/findAllCar")
    @ApiOperation(value = "查找所有car", notes = "")
    public List<CarEntity> findAllCar() throws Exception {
        return vehicleManagementService.list();
    }

    @RequestMapping("/findCarByMark")
    public CarEntity findCarByMark(String mark) throws Exception {
        return vehicleManagementService.findCarByMark(mark);
    }

    @RequestMapping("/goAddCar")
    public String goAddCar() throws Exception {

        return "admin/addCar";

    }

    @RequestMapping("/addCar")
    public String addCar(CarEntity car, MultipartFile car_picture) throws Exception {

        vehicleManagementService.insertCar(car, car_picture);

        return "redirect:findAllCarPage";
    }

    @RequestMapping("/getCarDetails")
    public ModelAndView getCarDetails(Integer id) throws Exception {

        ModelAndView modelAndView = new ModelAndView();

        CarEntity car = vehicleManagementService.getCarById(id);

        modelAndView.addObject("car", car);

        modelAndView.setViewName("admin/carDetails");

        return modelAndView;

    }

    @RequestMapping("/goModifyCar")
    public ModelAndView goModifyCar(Integer id) throws Exception {

        ModelAndView modelAndView = new ModelAndView();

        CarEntity car = vehicleManagementService.getCarById(id);

        modelAndView.addObject("car", car);

        modelAndView.setViewName("admin/modifyCar");

        return modelAndView;

    }

    @RequestMapping("/modifyCar")
    public String modifyCar(CarEntity car, MultipartFile car_picture) throws Exception {

        vehicleManagementService.updateCar(car, car_picture);

        return "redirect:findAllCarPage";
    }

    @RequestMapping("/deleteCar")
    public String deleteCar(Integer id) throws Exception {

        vehicleManagementService.deleteCar(id);

        return "redirect:findAllCarPage";
    }
}
