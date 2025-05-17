package com.example.springboot.entity;

// import com.baomidou.mybatisplus.annotation.TableField;
// import com.baomidou.mybatisplus.annotation.TableId;
// import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 宿舍管理员
 */
@Data
@AllArgsConstructor
@NoArgsConstructor

public class DormManager {

    private String username;

    private String password;

    private Integer dormBuildId;

    private String name;

    private String gender;

    private Integer age;

    private String phoneNum;

    private String email;

    private String avatar;
}
