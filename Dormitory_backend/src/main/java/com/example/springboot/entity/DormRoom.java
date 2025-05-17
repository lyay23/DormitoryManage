package com.example.springboot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 宿舍房间
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DormRoom {

    private Integer dormRoomId;

    private Integer dormBuildId;

    private Integer floorNum;

    private Integer maxCapacity;

    private Integer currentCapacity;

    private String firstBed;

    private String secondBed;

    private String thirdBed;

    private String fourthBed;

}
