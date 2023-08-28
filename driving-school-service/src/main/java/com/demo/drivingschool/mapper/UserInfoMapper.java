package com.demo.drivingschool.mapper;

import com.demo.drivingschool.model.entity.UserInfoEntity;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author
 * 
 */
@Repository
public interface UserInfoMapper extends Mapper<UserInfoEntity> {
}
