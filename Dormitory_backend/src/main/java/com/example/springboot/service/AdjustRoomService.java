package com.example.springboot.service;


import com.example.springboot.entity.AdjustRoom;
import com.example.springboot.entity.PageResult; // Import PageResult
import com.github.pagehelper.PageInfo;

import java.util.List; // For returning lists



public interface AdjustRoomService { // Removed extends IService<AdjustRoom>

    /**
     * 查询调宿申请 (分页)
     * @param pageNum 当前页码
     * @param pageSize 每页数量
     * @param search 搜索关键词 (根据实现，可能用于状态、学号等)
     * @return PageResult<AdjustRoom> 包含分页信息和数据列表
     */
    PageInfo<AdjustRoom> findAdjustRequests(Integer pageNum, Integer pageSize, String search);

    /**
     * 查询所有调宿申请
     * @return AdjustRoom 列表
     */
    List<AdjustRoom> findAllAdjustRequests();

    /**
     * 查询某个学生的所有调宿申请
     * @param username 学生用户名
     * @return AdjustRoom 列表
     */
    List<AdjustRoom> findAdjustRequestsByStudentUsername(String username);

    /**
     * 删除调宿申请
     * @param id 申请ID
     * @return 影响行数
     */
    int deleteAdjustment(Integer id);

    /**
     * 更新调宿申请状态等信息
     * @param adjustRoom 包含更新信息的申请对象
     * @return 影响行数
     */
    int updateApply(AdjustRoom adjustRoom);

    /**
     * 添加调宿申请
     * @param adjustRoom 新的申请对象
     * @return 影响行数 (通常是1如果成功，0如果失败)
     */
    int addApply(AdjustRoom adjustRoom);

    AdjustRoom getAdjustRequestById(Integer id);

    /**
     * 根据用户名查找调宿申请
     */
    PageInfo<AdjustRoom> findAdjustRequestsByUsername(String username, Integer pageNum, Integer pageSize, String search);
}
