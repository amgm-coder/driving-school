package com.demo.drivingschool.mapper;

import com.demo.drivingschool.model.entity.TraineeEntity;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author
 * 
 */
@Repository
public interface StudentMapper extends Mapper<TraineeEntity> {
}
