package com.example.springboot.controller;


import com.example.springboot.common.Result;
import com.example.springboot.entity.DormManager;
import com.example.springboot.entity.User;
import com.example.springboot.service.DormManagerService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/dormManager")
public class DormManagerController {

    @Resource
    private DormManagerService dormManagerService;

    /**
     * 宿管添加
     */
    @PostMapping("/add")
    public Result<?> add(@RequestBody DormManager dormManager) {
        // Consider checking if username already exists via getDormManagerByUsername
        int i = dormManagerService.addDormManager(dormManager);
        if (i > 0) {
            return Result.success();
        } else {
            return Result.error("-1", "添加宿管失败");
        }
    }

    /**
     * 宿管信息更新
     */
    @PutMapping("/update")
    public Result<?> update(@RequestBody DormManager dormManager) {
        int i = dormManagerService.updateDormManager(dormManager);
        if (i > 0) {
            return Result.success();
        } else {
            return Result.error("-1", "更新宿管信息失败");
        }
    }

    /**
     * 宿管删除
     */
    @DeleteMapping("/delete/{username}")
    public Result<?> delete(@PathVariable String username) {
        int i = dormManagerService.deleteDormManagerByUsername(username);
        if (i > 0) {
            return Result.success();
        } else {
            return Result.error("-1", "删除宿管失败");
        }
    }

    /**
     * 宿管查找
     */
    @GetMapping("/find")
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search) {
        PageInfo<DormManager> pageInfo = dormManagerService.findDormManagers(pageNum, pageSize, search);
        return Result.success(pageInfo);
    }

    /**
     * 宿管登录
     */
    @PostMapping("/login")
    public Result<?> login(@RequestBody User user, HttpSession session) { // Assuming User DTO is still intended for login request
        DormManager dormManager = dormManagerService.login(user.getUsername(), user.getPassword());
        if (dormManager != null) {
            session.setAttribute("Identity", "dormManager");
            session.setAttribute("User", dormManager); // Store DormManager object
            return Result.success(dormManager);
        } else {
            return Result.error("-1", "用户名或密码错误");
        }
    }

    @GetMapping("/{username}")
    public Result<?> getByUsername(@PathVariable String username) {
        DormManager dormManager = dormManagerService.getDormManagerByUsername(username);
        if (dormManager != null) {
            return Result.success(dormManager);
        } else {
            return Result.error("-1", "未找到宿管");
        }
    }
}
