package com.demo.drivingschool.model.pojo;

import lombok.Data;

/**
 * @author
 * 
 */
@Data
public class User {
    private long userId;
    private String userName;
    private String phone;
    private String userPassword;
    private String password;
    private String loginType;
}
