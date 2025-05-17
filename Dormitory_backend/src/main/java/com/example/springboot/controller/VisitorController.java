package com.example.springboot.controller;

import com.example.springboot.common.Result;
import com.example.springboot.entity.Visitor;
import com.example.springboot.service.VisitorService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/visitor")
public class VisitorController {

    @Resource
    private VisitorService visitorService;

    /**
     * 访客添加
     */
    @PostMapping("/add")
    public Result<?> add(@RequestBody Visitor visitor) {
        int i = visitorService.add(visitor);
        if (i == 1) {
            return Result.success();
        } else {
            return Result.error("-1", "添加失败");
        }
    }

    /**
     * 访客信息更新
     */
    @PutMapping("/update")
    public Result<?> update(@RequestBody Visitor visitor) {
        int i = visitorService.update(visitor);
        if (i == 1) {
            return Result.success();
        } else {
            return Result.error("-1", "更新失败");
        }
    }

    /**
     * 访客删除
     */
    @DeleteMapping("/delete/{id}")
    public Result<?> delete(@PathVariable Integer id) {
        int i = visitorService.delete(id);
        if (i == 1) {
            return Result.success();
        } else {
            return Result.error("-1", "删除失败");
        }
    }

    /**
     * 访客查询
     */
    @GetMapping("/find")
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search) {
        PageInfo<Visitor> pageInfo = visitorService.findPage(pageNum, pageSize, search);
        if (pageInfo != null) {
            return Result.success(pageInfo);
        } else {
            return Result.error("-1", "查询失败");
        }
    }
}
