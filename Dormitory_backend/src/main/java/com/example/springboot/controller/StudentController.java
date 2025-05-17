package com.example.springboot.controller;


import com.example.springboot.common.Result;
import com.example.springboot.entity.Student;
import com.example.springboot.entity.User;
import com.example.springboot.service.StudentService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/stu")
public class StudentController {

    @Resource
    private StudentService studentService;

    /**
     * 学生注册
     */
    @PostMapping("/register")
    public Result<?> register(@RequestBody Student student) {
        if (student.getUsername() == null || student.getUsername().isEmpty() || 
            student.getPassword() == null || student.getPassword().isEmpty()) {
            return Result.error("-1", "用户名和密码不能为空");
        }
        
        // 检查用户名是否已存在
        if (studentService.getStudentByUsername(student.getUsername()) != null) {
            return Result.error("-1", "用户名已存在");
        }
        
        Student registeredStudent = studentService.registerStudent(student);
        if (registeredStudent != null) {
            return Result.success(registeredStudent);
        } else {
            return Result.error("-1", "注册失败，请稍后再试");
        }
    }

    /**
     * 添加学生信息
     */
    @PostMapping("/add")
    public Result<?> add(@RequestBody Student student) {
        int i = studentService.addNewStudent(student);
        if (i == 1) {
            return Result.success();
        } else {
            return Result.error("-1", "添加失败");
        }

    }

    /**
     * 更新学生信息
     */
    @PutMapping("/update")
    public Result<?> update(@RequestBody Student student) {
        int i = studentService.updateNewStudent(student);
        if (i == 1) {
            return Result.success();
        } else {
            return Result.error("-1", "更新失败");
        }
    }

    /**
     * 删除学生信息
     */
    @DeleteMapping("/delete/{username}")
    public Result<?> delete(@PathVariable String username) {
        int i = studentService.deleteStudent(username);
        if (i == 1) {
            return Result.success();
        } else {
            return Result.error("-1", "删除失败");
        }
    }

    /**
     * 查找学生信息
     */
    @GetMapping("/find")
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search) {
        PageInfo<Student> pageInfo = studentService.find(pageNum, pageSize, search);
        if (pageInfo != null) {
            return Result.success(pageInfo);
        } else {
            return Result.error("-1", "查询失败");
        }
    }

    /**
     * 学生登录
     */
    @PostMapping("/login")
    public Result<?> login(@RequestBody User user, HttpSession session) {
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        Object o = studentService.stuLogin(user.getUsername(), user.getPassword());
        if (o != null) {
            System.out.println(o);
            //存入session
            session.setAttribute("Identity", "stu");
            session.setAttribute("User", o);
            return Result.success(o);
        } else {
            return Result.error("-1", "用户名或密码错误");
        }
    }

    /**
     * 主页顶部：学生统计
     */
    @GetMapping("/stuNum")
    public Result<?> stuNum() {
        Long num = studentService.stuNum();
        if (num != null && num > 0) {
            return Result.success(num);
        } else if (num != null && num == 0) {
            return Result.success(0L);
        } else {
            return Result.error("-1", "查询失败");
        }
    }


    /**
     * 床位信息，查询是否存在该学生
     * 床位信息，查询床位上的学生信息
     */
    @GetMapping("/exist/{value}")
    public Result<?> exist(@PathVariable String value) {
        Student student = studentService.stuInfo(value);
        if (student != null) {
            return Result.success(student);
        } else {
            return Result.error("-1", "不存在该学生");
        }
    }

    /**
     * 将头像名称更新到数据库中
     * The Student object in the body should contain the identifier (e.g., username)
     * of the student whose avatar needs to be updated.
     */
    @PostMapping("/uploadAvatar/stu/{filename}")
    public Result<?> uploadStuAvatar(@RequestBody Student student, @PathVariable String filename) {
        if (filename == null || filename.isEmpty()) {
            return Result.error("-1", "文件名不能为空");
        }
        // 确保 student 对象中包含 username，因为更新操作依赖 username
        if (student.getUsername() == null || student.getUsername().isEmpty()) {
            return Result.error("-1", "学生信息错误，缺少用户名");
        }
        student.setAvatar(filename); // 设置新的头像文件名
        int i = studentService.updateNewStudent(student); // 使用更新后的方法名
        if (i > 0) {
            return Result.success(filename);
        } else {
            return Result.error("-1", "设置学生头像失败");
        }
    }
}
