package com.demo.drivingschool.service;

import com.demo.drivingschool.mapper.AdminMapper;
import com.demo.drivingschool.mapper.AppointmentMapper;
import com.demo.drivingschool.model.entity.AdminEntity;
import com.demo.drivingschool.model.entity.AppointmentEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author
 * 
 */
@Service
@AllArgsConstructor
public class AppointmentService {

    private final AppointmentMapper mapper;

    public List<AppointmentEntity> list() {
        List<AppointmentEntity> entities = mapper.selectAll();
        return entities;
    }

}
