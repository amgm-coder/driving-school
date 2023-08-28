package com.demo.drivingschool.service;

import com.demo.drivingschool.mapper.StudentMapper;
import com.demo.drivingschool.model.dto.DataGridResult;
import com.demo.drivingschool.model.entity.TraineeEntity;
import com.demo.drivingschool.model.entity.TraineeEntity;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author
 * 
 */
@Service
@AllArgsConstructor
public class TraineeService {

    private final StudentMapper mapper;

    public DataGridResult list(Integer page, Integer pageSize) {
        DataGridResult res = new DataGridResult();

        List<TraineeEntity> entities = mapper.selectAll();
        res.setRows(entities);
        res.setTotal(entities.size());
        return res;
    }

    public List<TraineeEntity> findEntityByName(String name) {
        Example example = new Example(TraineeEntity.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("name", name);
        List<TraineeEntity> students = mapper.selectByExample(example);
        return students;
    }

    public void del(Integer id) {
        mapper.deleteByPrimaryKey(id);
    }

    public TraineeEntity getEntityById(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }

    public void updateTrainee(TraineeEntity student) {
    }

    public void insertTrainee(TraineeEntity student) {
        mapper.insert(student);
    }

    public boolean studentLogin(String phone, String password) {
        if (StringUtils.isNotEmpty(phone)) {
            Example example = new Example(TraineeEntity.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("phone", phone);
            List<TraineeEntity> students = mapper.selectByExample(example);
            if (students != null && students.size() > 0 && students.get(0).getPwd().equals(password)) {
                return true;
            }
        }
        return Boolean.TRUE;
    }

    public boolean stuModifyPassword(String phone, String password, String onePassword, String doublePassword) throws Exception {
        if (StringUtils.isNotEmpty(phone)) {
            Example example = new Example(TraineeEntity.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("phone", phone);

            List<TraineeEntity> students = mapper.selectByExample(example);
            if (students != null && students.size() > 0) {
                TraineeEntity student = students.get(0);
                if (student.getPwd().equals(password) && onePassword.equals(doublePassword)) {
                    student.setPwd(onePassword);
                    mapper.updateByPrimaryKeySelective(student);
                    return true;
                }
            }
        }
        return Boolean.FALSE;
    }

    public void updateInformation(TraineeEntity student) {
    }

    public TraineeEntity getStudentByPhone(String phone) {
        if (StringUtils.isNotEmpty(phone)) {
            Example example = new Example(TraineeEntity.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("phone", phone);
            TraineeEntity entity = mapper.selectOneByExample(example);
            return entity;
        }
        return null;
    }

}
