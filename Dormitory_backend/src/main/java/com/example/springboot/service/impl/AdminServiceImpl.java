package com.example.springboot.service.impl;

import com.example.springboot.entity.Admin;
import com.example.springboot.mapper.AdminMapper;
import com.example.springboot.service.AdminService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
// public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {
public class AdminServiceImpl implements AdminService {

    /**
     * 注入DAO层对象
     */
    @Resource
    private AdminMapper adminMapper;

    /**
     * 管理员登录
     */
    @Override
    public Admin adminLogin(String username, String password) {

        Admin admin = adminMapper.findByUsernameAndPassword(username, password);
        return admin;
    }

    /**
     * 管理员信息更新
     */
    @Override
    public int updateAdmin(Admin admin) {

        if (admin.getUsername() == null || admin.getUsername().isEmpty()) {

            return 0; 
        }
        int i = adminMapper.updateByUsername(admin);
        return i;
    }

    /**
     * 管理员注册
     */
    @Override
    public Admin registerAdmin(Admin admin) {

        Admin existingAdmin = adminMapper.findByUsername(admin.getUsername());
        if (existingAdmin != null) {
            // 用户名已存在
            return null;
        }
        

        Admin adminToInsert = new Admin();
        adminToInsert.setUsername(admin.getUsername());
        adminToInsert.setPassword(admin.getPassword());
        adminToInsert.setName(admin.getName());
        adminToInsert.setGender(admin.getGender());
        adminToInsert.setAge(admin.getAge()); 
        adminToInsert.setPhoneNum(admin.getPhoneNum());
        adminToInsert.setEmail(admin.getEmail());
        adminToInsert.setAvatar(admin.getAvatar());

        int result = adminMapper.insert(adminToInsert);
        if (result > 0) {
            return adminToInsert; // Return the object that was inserted
        } else {
            return null;
        }
    }

    @Override
    public Admin getAdminByUsername(String username) {
        return adminMapper.findByUsername(username);
    }
}
