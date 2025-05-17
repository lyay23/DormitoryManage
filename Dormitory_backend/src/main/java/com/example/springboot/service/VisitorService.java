package com.example.springboot.service;

import com.example.springboot.entity.Visitor;
// import com.example.springboot.entity.PageResult; // 移除旧的 PageResult
import com.github.pagehelper.PageInfo; // 导入 PageInfo

public interface VisitorService {

    // 新增访客
    int add(Visitor visitor);

    /**
     * 分页查找访客
     * @param pageNum 页码
     * @param pageSize 每页数量
     * @param search 搜索关键词 (如姓名)
     * @return 分页结果 PageInfo<Visitor>
     */
    PageInfo<Visitor> findPage(Integer pageNum, Integer pageSize, String search);

    // 更新访客信息
    int update(Visitor visitor);

    // 删除访客
    int delete(Integer id);

    // 根据ID获取访客详情
    Visitor findById(Integer id);
}
