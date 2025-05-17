package com.example.springboot.service.impl;

import com.example.springboot.ExceptionHandler.BusinessException;
import com.example.springboot.entity.Student;
import com.example.springboot.mapper.StudentMapper;
import com.example.springboot.service.StudentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Resource
    private StudentMapper studentMapper;

    @Override
    public Student stuLogin(String username, String password) {
        return studentMapper.findByUsernameAndPassword(username, password);
    }

    @Override
    public int addNewStudent(Student student) {
        try {
            return studentMapper.insert(student);
        } catch (DuplicateKeyException e) {
            // Spring 的 DuplicateKeyException，需 import org.springframework.dao.DuplicateKeyException
            throw new BusinessException("用户名已存在，请更换用户名！");
        } catch (Exception e) {
            throw new BusinessException("添加失败，系统异常！");
        }

    }

    @Override
    public PageInfo<Student> find(Integer pageNum, Integer pageSize, String search) {
        PageHelper.startPage(pageNum, pageSize);
        List<Student> studentList = studentMapper.findByCriteria(search);
        if (studentList == null) {
            studentList = Collections.emptyList();
        }
        return new PageInfo<>(studentList);
    }

    @Override
    public int updateNewStudent(Student student) {
        // Ensure student object has username, as it's used in WHERE clause of mapper
        if (student.getUsername() == null || student.getUsername().isEmpty()) {
            return 0; // Or throw an exception
        }
        return studentMapper.update(student);
    }

    @Override
    public int deleteStudent(String username) {
        return studentMapper.deleteByUsername(username);
    }

    @Override
    public Long stuNum() {
        return studentMapper.countAll();
    }

    @Override
    public Student stuInfo(String username) {
        return studentMapper.findByUsername(username);
    }

    @Override
    public Student getStudentByUsername(String username) {
        return studentMapper.findByUsername(username);
    }

    @Override
    public Student registerStudent(Student student) {
        // 检查用户名是否已存在
        Student existingStudent = studentMapper.findByUsername(student.getUsername());
        if (existingStudent != null) {
            return null;
        }
        
        // 创建新的学生对象
        Student studentToInsert = new Student();
        studentToInsert.setUsername(student.getUsername());
        studentToInsert.setPassword(student.getPassword());
        studentToInsert.setName(student.getName());
        studentToInsert.setGender(student.getGender());
        studentToInsert.setAge(student.getAge());
        studentToInsert.setPhoneNum(student.getPhoneNum());
        studentToInsert.setEmail(student.getEmail());
        studentToInsert.setAvatar(student.getAvatar());

        int result = studentMapper.insert(studentToInsert);
        if (result > 0) {
            return studentToInsert;
        } else {
            return null;
        }
    }
}
