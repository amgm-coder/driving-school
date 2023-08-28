package com.demo.drivingschool.service;

import com.demo.drivingschool.mapper.UserInfoMapper;
import com.demo.drivingschool.model.entity.UserInfoEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author
 * 
 */
@Service
@AllArgsConstructor
public class UserInfoService {

    private final UserInfoMapper userInfoMapper;

    public List<UserInfoEntity> list() {
        List<UserInfoEntity> entities = userInfoMapper.selectAll();
        return entities;
    }
}
