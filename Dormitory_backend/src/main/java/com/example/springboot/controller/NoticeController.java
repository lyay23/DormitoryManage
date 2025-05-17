package com.example.springboot.controller;

import com.example.springboot.common.Result;
import com.example.springboot.entity.Notice;
import com.example.springboot.service.NoticeService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/notice")
public class NoticeController {

    @Resource
    NoticeService noticeService;

    /**
     * 公告添加
     */
    @PostMapping("/add")
    public Result<?> add(@RequestBody Notice notice) {
        int i = noticeService.addNotice(notice);
        if (i > 0) {
            return Result.success();
        } else {
            return Result.error("-1", "添加失败");
        }
    }

    /**
     * 公告更新
     */
    @PutMapping("/update")
    public Result<?> update(@RequestBody Notice notice) {
        int i = noticeService.updateNotice(notice);
        if (i > 0) {
            return Result.success();
        } else {
            return Result.error("-1", "更新失败");
        }
    }

    /**
     * 公告删除
     */
    @DeleteMapping("/delete/{id}")
    public Result<?> delete(@PathVariable Integer id) {
        int i = noticeService.deleteNotice(id);
        if (i > 0) {
            return Result.success();
        } else {
            return Result.error("-1", "删除失败");
        }
    }

    /**
     * 公告查找 (分页)
     */
    @GetMapping("/find")
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search) {
        PageInfo<Notice> pageInfo = noticeService.findNotices(pageNum, pageSize, search);
        return Result.success(pageInfo);
    }

    /**
     * 首页公告展示
     */
    @GetMapping("/homePageNotices")
    public Result<?> homePageNotices() {
        List<Notice> list = noticeService.homePageNotices();
        return Result.success(list);
    }

    /**
     * 根据ID获取公告详情
     */
    @GetMapping("/{id}")
    public Result<?> getNoticeById(@PathVariable Integer id) {
        Notice notice = noticeService.getNoticeById(id);
        if (notice != null) {
            return Result.success(notice);
        } else {
            return Result.error("-1", "查询失败或公告不存在");
        }
    }
}
