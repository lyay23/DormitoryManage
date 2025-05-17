package com.example.springboot.controller;

import com.example.springboot.common.Result;

import com.example.springboot.entity.Admin;
import com.example.springboot.entity.User;
import com.example.springboot.service.AdminService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/admin")
public class AdminController {

    // String uid = new UID().produceUID(); // UID 实例似乎未被使用

    @Resource
    private AdminService adminService;

    /**
     * 管理员登录
     */
    @PostMapping("/login")
    public Result<?> login(@RequestBody User user, HttpSession session) {
        Admin admin = adminService.adminLogin(user.getUsername(), user.getPassword());
        if (admin != null) {
            // System.out.println(admin);
            session.setAttribute("Identity", "admin");
            session.setAttribute("User", admin);
            return Result.success(admin);
        } else {
            return Result.error("-1", "用户名或密码错误");
        }
    }

    /**
     * 管理员信息更新
     */
    @PutMapping("/update")
    public Result<?> update(@RequestBody Admin admin) {
        // 确保username不为空，因为它是更新的依据
        if (admin.getUsername() == null || admin.getUsername().isEmpty()){
            return Result.error("-1", "更新目标用户名不能为空");
        }
        int i = adminService.updateAdmin(admin);
        if (i > 0) { // 通常影响行数大于0即为成功
            return Result.success();
        } else {
            // 可以更细致地判断是未找到记录还是其他更新失败原因
            return Result.error("-1", "更新失败或用户不存在");
        }
    }


}
