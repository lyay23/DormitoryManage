package com.example.springboot.service;

import com.example.springboot.entity.Notice;
import com.example.springboot.entity.PageResult;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface NoticeService {

    //新增通知
    int addNotice(Notice notice);

    //查询所有通知
    PageInfo<Notice> findNotices(Integer pageNum, Integer pageSize, String search);

    //更新通知信息
    int updateNotice(Notice notice);

    //删除通知
    int deleteNotice(Integer id);

    // 首页通知展示 (获取所有通知，按发布时间降序)
    List<Notice> homePageNotices();

    Notice getNoticeById(Integer id);
}
