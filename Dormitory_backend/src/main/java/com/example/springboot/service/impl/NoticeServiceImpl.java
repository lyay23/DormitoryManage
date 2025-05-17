package com.example.springboot.service.impl;

import com.example.springboot.entity.Notice;
import com.example.springboot.entity.PageResult;
import com.example.springboot.mapper.NoticeMapper;
import com.example.springboot.service.NoticeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService {

    @Resource
    private NoticeMapper noticeMapper;

    @Override
    public int addNotice(Notice notice) {

        return noticeMapper.insert(notice);
    }

    @Override
    public PageInfo<Notice> findNotices(Integer pageNum, Integer pageSize, String search) {
        PageHelper.startPage(pageNum, pageSize);
        List<Notice> noticeList;
        if (StringUtils.hasText(search)) {
            noticeList = noticeMapper.findByTitle(search);
        } else {
            noticeList = noticeMapper.findAll(); // findAll already orders by release_time desc
        }
        return new PageInfo<>(noticeList);
    }

    @Override
    public int updateNotice(Notice notice) {
        if (notice.getId() == null) {
            return 0; // Or throw exception
        }
        // Consider updating releaseTime if that's part of the update logic
        return noticeMapper.update(notice);
    }

    @Override
    public int deleteNotice(Integer id) {
        return noticeMapper.deleteById(id);
    }

    @Override
    public List<Notice> homePageNotices() {

        return noticeMapper.findAllOrderByReleaseTimeDesc();
    }

    @Override
    public Notice getNoticeById(Integer id) {
        return noticeMapper.findById(id);
    }
}
