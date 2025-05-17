package com.example.springboot.entity;

// import com.baomidou.mybatisplus.annotation.TableField;
// import com.baomidou.mybatisplus.annotation.TableId;
// import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 系统管理员
 */

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Admin {


    private String username;

    private String password;

    private String name;

    private String gender; // 数据库中是 ENUM('男','女')，Java 类型为 String 保持不变

    private int age;

    private String phoneNum;

    private String email;

    private String avatar;
}
