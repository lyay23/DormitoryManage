package com.example.springboot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: 李阳
 * @Date: 2025/05/16/17:43
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResult <T>{
    //页码
    private Long total;
    //当前页数据
    private List<T> rows;


}
