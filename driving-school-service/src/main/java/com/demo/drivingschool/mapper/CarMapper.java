package com.demo.drivingschool.mapper;

import com.demo.drivingschool.model.entity.CarEntity;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author
 * 
 */
@Repository
public interface CarMapper extends Mapper<CarEntity> {
}
