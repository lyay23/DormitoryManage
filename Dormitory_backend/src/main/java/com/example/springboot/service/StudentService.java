package com.example.springboot.service;

import com.example.springboot.entity.Student;
import com.github.pagehelper.PageInfo;

public interface StudentService {

    //学生登陆
    Student stuLogin(String username, String password);

    //新增学生
    int addNewStudent(Student student);

    //查询学生信息
    PageInfo<Student> find(Integer pageNum, Integer pageSize, String search);

    //更新学生信息
    int updateNewStudent(Student student);

    //删除学生信息
    int deleteStudent(String username);

    //统计学生人数
    Long stuNum();

    //床位信息，查询该学生信息
    Student stuInfo(String username);

    /**
     * 根据用户名获取学生信息
     */
    Student getStudentByUsername(String username);

    /**
     * 学生注册
     */
    Student registerStudent(Student student);

}
