package com.example.springboot.mapper;


import com.example.springboot.entity.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param; // Mybatis Param annotation

@Mapper
public interface AdminMapper {

    /**
     * 根据用户名和密码查询管理员 (用于登录)
     * @param username 用户名
     * @param password 密码
     * @return Admin对象或null
     */
    Admin findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    /**
     * 根据用户名查询管理员 (用于查重或获取信息)
     * @param username 用户名
     * @return Admin对象或null
     */
    Admin findByUsername(@Param("username") String username);

    /**
     * 插入管理员信息
     * @param admin 管理员对象
     * @return 影响行数
     */
    int insert(Admin admin);

    /**
     * 更新管理员信息 (根据用户名)
     * @param admin 管理员对象，需要包含username以定位记录
     * @return 影响行数
     */
    int updateByUsername(Admin admin);

    // 根据您的 SQL 文件，admin 表的主键是 username，所以通常不会有 updateById 这样的方法，除非 id 是指其他。
    // 如果需要其他查询，例如查询所有管理员，可以添加如下方法：
    // List<Admin> findAll();
}
