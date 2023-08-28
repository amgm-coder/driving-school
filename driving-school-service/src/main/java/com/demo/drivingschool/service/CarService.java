package com.demo.drivingschool.service;

import com.demo.drivingschool.mapper.CarMapper;
import com.demo.drivingschool.model.entity.CarEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author
 * 
 */
@Service
@AllArgsConstructor
public class CarService {

    private final CarMapper mapper;

    public List<CarEntity> list() {
        List<CarEntity> entities = mapper.selectAll();
        return entities;
    }

    public void deleteCar(Integer id) {
        mapper.deleteByPrimaryKey(id);
    }

    public void updateCar(CarEntity car, MultipartFile car_picture) {
        mapper.updateByPrimaryKeySelective(car);
    }

    public CarEntity getCarById(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }

    public void insertCar(CarEntity car, MultipartFile car_picture) {
//        String picturePath=FileUpload.pictureUpload(carPicture, "F:\\picture\\");
//
//        car.setCarPicture(picturePath);
//        car.setCreateTime(new Date());
//        car.setUpdateTime(new Date());

        mapper.insert(car);
    }

    public CarEntity findCarByMark(String mark) {
        Example example = new Example(CarEntity.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("mark", mark);

        CarEntity entity = mapper.selectOneByExample(example);
        return entity;
    }
}
