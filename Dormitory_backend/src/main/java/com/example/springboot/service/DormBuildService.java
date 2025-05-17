package com.example.springboot.service;

import com.example.springboot.entity.DormBuild;
import com.example.springboot.entity.PageResult;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface DormBuildService {

    //新增楼宇
    int addDormBuild(DormBuild dormBuild);


    List<DormBuild> findDormBuilds(String search);


    PageInfo<DormBuild> findPaginatedDormBuilds(Integer pageNum, Integer pageSize, String search);

    //更新楼宇信息
    int updateDormBuild(DormBuild dormBuild);

    //删除楼宇信息
    int deleteDormBuildById(Integer id);

    // 获取所有楼宇信息
    List<DormBuild> findAllDormBuilds();


    DormBuild findDormBuildByName(String name);

    // 根据ID查找楼宇
    DormBuild findDormBuildById(Integer id);


}
