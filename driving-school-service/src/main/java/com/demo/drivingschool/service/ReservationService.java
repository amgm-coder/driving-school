package com.demo.drivingschool.service;

import com.demo.drivingschool.mapper.ReservationMapper;
import com.demo.drivingschool.model.dto.OrderDateMessage;
import com.demo.drivingschool.model.dto.OrderQueryVo;
import com.demo.drivingschool.model.entity.ReservationEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author
 * 
 */
@Service
@AllArgsConstructor
public class ReservationService {

    private final ReservationMapper mapper;

    public List<ReservationEntity> list() {
        List<ReservationEntity> entities = mapper.selectAll();
        return entities;
    }

    public OrderDateMessage checkOrder(Integer studentId, Integer carId, Date submitDate, Integer timeId) {

        return null;
    }

    public OrderQueryVo getOrderInformation(Integer studentId) {
        return null;
    }

    public void cancelOrder(Integer id) {

    }

    public OrderQueryVo getReservations(Date submitDate) {
        return null;
    }

    public OrderQueryVo getReservations(Date submitDate, Integer carId) {
        return null;
    }

    public OrderDateMessage isAlreadyOrder(String phone, Date submitDate) {
        return null;
    }

    public boolean submitOrder(Integer studentId, Integer carId, Integer timeId, Date submitDate) {
        return false;
    }
}
