/*
Java实体类,判断
 */


package com.example.springboot.entity;


// import com.baomidou.mybatisplus.annotation.IdType;
// import com.baomidou.mybatisplus.annotation.TableField;
// import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdjustRoom {
    private Integer id; // 主键，数据库中 auto_increment

    private String username;

    private String name;

    private Integer currentRoomId;

    private Integer currentBedId;

    private Integer towardsRoomId;

    private Integer towardsBedId;

    private String state; // 数据库中是 ENUM，Java 类型为 String

    private String applyTime;

    private String finishTime;
}
