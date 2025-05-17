package com.example.springboot.mapper;

import com.example.springboot.entity.AdjustRoom;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface AdjustRoomMapper {

    AdjustRoom findById(@Param("id") Integer id);

    List<AdjustRoom> findAll();

    List<AdjustRoom> findByUsername(@Param("username") String username);

    int insert(AdjustRoom adjustRoom);

    int update(AdjustRoom adjustRoom);

    int deleteById(@Param("id") Integer id);

    List<AdjustRoom> findByState(@Param("state") String state);

    /**
     * 根据用户名和搜索条件查询调宿舍申请
     */
    List<AdjustRoom> findByUsernameAndCriteria(@Param("username") String username, @Param("search") String search);

    /**
     * 根据搜索条件查询调宿舍申请
     */
    List<AdjustRoom> findByCriteria(String search);
}
