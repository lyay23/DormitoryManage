package com.example.springboot.mapper;

import com.example.springboot.entity.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
@Mapper
public interface StudentMapper {

    Student findByUsername(@Param("username") String username);

    Student findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    List<Student> findAll();


    List<Student> findByCriteria(@Param("search") String search);

    int insert(Student student);


    int update(Student student);

    int deleteByUsername(@Param("username") String username);

    Long countAll();
}
