package com.demo.drivingschool.mapper;

import com.demo.drivingschool.model.entity.AppointmentEntity;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author
 * 
 */
@Repository
public interface AppointmentMapper extends Mapper<AppointmentEntity> {
}
