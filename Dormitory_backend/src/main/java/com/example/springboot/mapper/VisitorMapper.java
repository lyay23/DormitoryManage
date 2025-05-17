package com.example.springboot.mapper;

import com.example.springboot.entity.Visitor;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface VisitorMapper {

    Visitor findById(@Param("id") Integer id);

    List<Visitor> findAll();

    List<Visitor> findByName(@Param("name") String name);

    int insert(Visitor visitor);

    int update(Visitor visitor);

    int deleteById(@Param("id") Integer id);
}
