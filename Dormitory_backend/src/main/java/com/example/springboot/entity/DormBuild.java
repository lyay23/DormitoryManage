package com.example.springboot.entity;

// import com.baomidou.mybatisplus.annotation.IdType;
// import com.baomidou.mybatisplus.annotation.TableField;
// import com.baomidou.mybatisplus.annotation.TableId;
// import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 宿舍楼
 */
@Data
@AllArgsConstructor
@NoArgsConstructor

public class DormBuild {


    private Integer id; // 数据库自增主键

    private int dormBuildId; // 业务上的楼号

    private String dormBuildName;

    private String dormBuildDetail;

}
