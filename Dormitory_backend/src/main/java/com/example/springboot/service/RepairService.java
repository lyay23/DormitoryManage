package com.example.springboot.service;

import com.example.springboot.entity.Repair;
import com.example.springboot.entity.PageResult;
import com.github.pagehelper.PageInfo;

public interface RepairService {

    // 获取维修订单总数
    Long getRepairCount();

    // 新增维修订单
    int addRepair(Repair repair);

    /**
     * (管理员/宿管) 分页查找维修订单
     * @param pageNum 页码
     * @param pageSize 每页数量
     * @param search 搜索关键词 (如标题、内容)
     * @return 分页结果
     */
    PageInfo<Repair> findRepairs(Integer pageNum, Integer pageSize, String search);

    /**
     * (学生) 根据报修人分页查找维修订单
     * @param pageNum 页码
     * @param pageSize 每页数量
     * @param search 搜索关键词
     * @param repairerName 报修人姓名/用户名
     * @return 分页结果
     */
    PageInfo<Repair> findRepairsByRepairer(Integer pageNum, Integer pageSize, String search, String repairerName);

    // 更新维修订单信息 (通常是更新状态、完成时间)
    int updateRepair(Repair repair);

    // 删除维修订单
    int deleteRepair(Integer id);

    // 根据ID获取维修订单详情
    Repair getRepairById(Integer id);
}
