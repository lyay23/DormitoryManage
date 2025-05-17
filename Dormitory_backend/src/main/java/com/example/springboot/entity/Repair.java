package com.example.springboot.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 报修单
 */
@Data
@AllArgsConstructor
@NoArgsConstructor


public class Repair {


    private Integer id;

    private String repairer;

    private int dormBuildId;

    private int dormRoomId;

    private String title;

    private String content;

    private String state;

    private String orderBuildTime;

    private String orderFinishTime;
}
