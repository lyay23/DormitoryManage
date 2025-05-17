package com.example.springboot.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 访客
 */

@Data
@AllArgsConstructor
@NoArgsConstructor


public class Visitor {


    private Integer id;

    private String name;

    private String gender;

    private String phoneNum;

    private String originCity;

    private String visitTime;

    private String content;

}
