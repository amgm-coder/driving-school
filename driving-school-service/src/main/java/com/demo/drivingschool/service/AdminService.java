package com.demo.drivingschool.service;

import com.demo.drivingschool.mapper.AdminMapper;
import com.demo.drivingschool.model.entity.AdminEntity;
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
public class AdminService {

    private final AdminMapper mapper;

    public List<AdminEntity> list() {
        List<AdminEntity> entities = mapper.selectAll();
        return entities;
    }


    public boolean modifyPassword(String phone, String oldPassword, String onePassword, String doublePassword) {
        if (phone != "" && phone != null) {
            Example example = new Example(AdminEntity.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("phone", phone);

            AdminEntity administrator = mapper.selectOneByExample(example);

            if (administrator != null) {
                if (administrator.getPwd().equals(oldPassword) && onePassword.equals(doublePassword)) {
                    administrator.setPwd(onePassword);
                    mapper.updateByPrimaryKeySelective(administrator);
                    return true;
                }
            }
        }
        return Boolean.FALSE;
    }

    public boolean adminLogin(String phone, String password) {
        if (phone != "" && phone != null) {
            Example example = new Example(AdminEntity.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("phone", phone);

            AdminEntity administrator = mapper.selectOneByExample(example);

            return administrator != null && administrator.getPwd().equals(password);
        }
        return Boolean.FALSE;
    }

    public void updateAdminById(AdminEntity administrator) {
        mapper.updateByPrimaryKeySelective(administrator);
    }

    public AdminEntity getAdminByPhone(String phone) {
        if (phone != "" && phone != null) {
            Example example = new Example(AdminEntity.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("phone", phone);
            return mapper.selectOneByExample(example);
        }
        return null;
    }
}
