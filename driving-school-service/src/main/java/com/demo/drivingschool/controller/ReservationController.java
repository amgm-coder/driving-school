package com.demo.drivingschool.controller;

import com.demo.drivingschool.model.dto.OrderDateMessage;
import com.demo.drivingschool.model.dto.OrderQueryVo;
import com.demo.drivingschool.model.entity.AppointmentEntity;
import com.demo.drivingschool.model.entity.CarEntity;
import com.demo.drivingschool.service.AppointmentService;
import com.demo.drivingschool.service.CarService;
import com.demo.drivingschool.service.ReservationService;
import com.demo.drivingschool.service.TraineeService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Controller
@AllArgsConstructor
@Log
public class ReservationController {

    private final ReservationService reservationService;
    private final AppointmentService appointmentService;

    private final CarService vehicleManagementService;
    private final TraineeService studentService;

    @GetMapping("/getAllMark")
    @ResponseBody
    public List<CarEntity> getAllMark() throws Exception {
        return vehicleManagementService.list();
    }

	@GetMapping("/getAllTime")
	@ResponseBody
	public List<AppointmentEntity> getAllTime()throws Exception{
		return appointmentService.list();
	}

    @RequestMapping("/isHaveOrder")
    @ResponseBody
    public OrderDateMessage isHaveOrder(HttpServletRequest req, Date submitDate) throws Exception {

        String phone = (String) req.getSession().getAttribute("phone");

        return reservationService.isAlreadyOrder(phone, submitDate);
    }

    @RequestMapping("/checkCar")
    @ResponseBody
    public CarEntity checkCar(Integer id) throws Exception {

        return vehicleManagementService.getCarById(id);
    }

    @RequestMapping(value = "/checkOrder", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public OrderDateMessage checkOrder(HttpServletRequest req, Integer carId, Date submitDate, Integer timeId) throws Exception {


        String phone = (String) req.getSession().getAttribute("phone");

        Integer studentId = studentService.getStudentByPhone(phone).getId();

        return reservationService.checkOrder(studentId, carId, submitDate, timeId);

    }

    @RequestMapping("/submitOrder")
    public String submitDate(HttpServletRequest req, Integer carId, Date submitDate, Integer timeId)
            throws Exception {

        ModelMap modelMap = new ModelMap();

        String phone = (String) req.getSession().getAttribute("phone");

        Integer studentId = studentService.getStudentByPhone(phone).getId();

        boolean flag = reservationService.submitOrder(studentId, carId, timeId, submitDate);

        if (flag) {

            return "redirect:stuMainPage";

        } else {
            modelMap.addAttribute("orderInformation", "预约失败");

            return "student/studyCar";
        }


    }

    @RequestMapping("/myOrderPage")
    public String myOrderPage() throws Exception {

        return "student/myOrder";

    }

    @RequestMapping("/myOrderInformation")
    @ResponseBody
    public OrderQueryVo myOrderInformation(HttpServletRequest req) throws Exception {

        String phone = (String) req.getSession().getAttribute("phone");

        Integer studentId = studentService.getStudentByPhone(phone).getId();

        return reservationService.getOrderInformation(studentId);

    }

    @RequestMapping("/cancelOrder")
    public String cancelOrder(Integer id) throws Exception {

        reservationService.cancelOrder(id);

        return "redirect:myOrderPage";

    }

    @RequestMapping("/bookingManagement")
    public String bookingManagement() throws Exception {

        return "admin/bookingManagement";

    }

    @RequestMapping("/findOrderBySubmitDate")
    @ResponseBody
    public OrderQueryVo findOrderBySubmitDate(Date submitDate) throws Exception {

        return reservationService.getReservations(submitDate);
    }

    @RequestMapping("/findOrderByCar")
    @ResponseBody
    public OrderQueryVo findOrderByCar(Date submitDate, Integer carId) throws Exception {
        return reservationService.getReservations(submitDate, carId);
    }

}
