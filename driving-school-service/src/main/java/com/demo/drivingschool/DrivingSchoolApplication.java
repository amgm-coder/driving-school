package com.demo.drivingschool;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;

@MapperScan(basePackages = "com.demo.drivingschool.mapper")
@SpringBootApplication
@EnableSwagger2
@EnableKnife4j
public class DrivingSchoolApplication {

    public static void main(String[] args) {
        SpringApplication.run(DrivingSchoolApplication.class, args);
    }

}
