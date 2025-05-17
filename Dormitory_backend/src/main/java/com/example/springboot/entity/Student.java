package com.example.springboot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 学生
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    private String username;

    private String password;

    private String name;

    private Integer age;

    private String gender;

    private String phoneNum;

    private String email;

    private String avatar;
}
