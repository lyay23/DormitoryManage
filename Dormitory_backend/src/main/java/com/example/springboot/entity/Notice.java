package com.example.springboot.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 公告
 */

@Data
@AllArgsConstructor
@NoArgsConstructor


public class Notice {


    private Integer id;

    private String title;

    private String content;

    private String author;

    private String releaseTime;
}
