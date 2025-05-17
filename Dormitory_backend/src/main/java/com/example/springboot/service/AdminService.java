package com.example.springboot.service;


import com.example.springboot.entity.Admin;

public interface AdminService { // Removed extends IService<Admin>

    /**
     * 管理员登录
     * @param username 用户名
     * @param password 密码
     * @return Admin对象或null
     */
    Admin adminLogin(String username, String password);

    /**
     * 更新管理员信息
     * @param admin 管理员对象
     * @return 更新操作影响的行数，通常 1 表示成功，0 表示失败
     */
    int updateAdmin(Admin admin);

    /**
     * 管理员注册
     * @param admin 包含注册信息的管理员对象
     * @return 注册成功的Admin对象（可能包含数据库生成的信息），或null表示失败（如用户名已存在）
     */
    Admin registerAdmin(Admin admin);

    /**
     * 根据用户名查找管理员 (在Controller中用于判断用户是否存在)
     * @param username 用户名
     * @return Admin对象或null
     */
    Admin getAdminByUsername(String username);

}
