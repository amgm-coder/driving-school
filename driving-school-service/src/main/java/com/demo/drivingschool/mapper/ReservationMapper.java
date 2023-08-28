package com.demo.drivingschool.mapper;

import com.demo.drivingschool.model.entity.ReservationEntity;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author
 * 
 */
@Repository
public interface ReservationMapper extends Mapper<ReservationEntity> {
}
