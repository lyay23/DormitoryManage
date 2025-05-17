package com.example.springboot.mapper;

import com.example.springboot.entity.DormBuild;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DormBuildMapper {

    DormBuild findById(@Param("id") Integer id);

    List<DormBuild> findAll();

    int insert(DormBuild dormBuild);

    int update(DormBuild dormBuild);

    int deleteById(@Param("id") Integer id);

    List<DormBuild> findByName(@Param("name") String name);
}
