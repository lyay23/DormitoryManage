package com.example.springboot.controller;

import com.example.springboot.common.Result;
import com.example.springboot.entity.Repair;
import com.example.springboot.entity.PageResult;
import com.example.springboot.service.RepairService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/repair")
public class RepairController {

    @Resource
    private RepairService repairService;

    /**
     * 添加订单
     */
    @PostMapping("/add")
    public Result<?> add(@RequestBody Repair repair) {
        int i = repairService.addRepair(repair);
        if (i > 0) {
            return Result.success();
        } else {
            return Result.error("-1", "添加失败");
        }
    }

    /**
     * 更新订单
     */
    @PutMapping("/update")
    public Result<?> update(@RequestBody Repair repair) {
        int i = repairService.updateRepair(repair);
        if (i > 0) {
            return Result.success();
        } else {
            return Result.error("-1", "更新失败");
        }
    }

    /**
     * 删除订单
     */
    @DeleteMapping("/delete/{id}")
    public Result<?> delete(@PathVariable Integer id) {
        int i = repairService.deleteRepair(id);
        if (i > 0) {
            return Result.success();
        } else {
            return Result.error("-1", "删除失败");
        }
    }

    /**
     * 查找订单 (管理员/宿管视角)
     */
    @GetMapping("/find")
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search) {
        PageInfo<Repair> pageInfo = repairService.findRepairs(pageNum, pageSize, search);
        return Result.success(pageInfo);
    }

    /**
     * 个人申报报修 分页查询 (学生视角)
     * @param repairerName 报修人用户名/姓名, passed as a path variable
     */
    @GetMapping("/find/byRepairer/{repairerName}")
    public Result<?> findByRepairer(@RequestParam(defaultValue = "1") Integer pageNum,
                                      @RequestParam(defaultValue = "10") Integer pageSize,
                                      @RequestParam(defaultValue = "") String search,
                                      @PathVariable String repairerName) {
        PageInfo<Repair> pageResult = repairService.findRepairsByRepairer(pageNum, pageSize, search, repairerName);
        return Result.success(pageResult);
    }

    /**
     * 首页顶部：报修统计 (总数)
     */
    @GetMapping("/count")
    public Result<?> getRepairCount() {
        Long count = repairService.getRepairCount();
        return Result.success(count);
    }

    /**
     * 根据ID获取维修单详情
     */
    @GetMapping("/{id}")
    public Result<?> getRepairById(@PathVariable Integer id) {
        Repair repair = repairService.getRepairById(id);
        if (repair != null) {
            return Result.success(repair);
        } else {
            return Result.error("-1", "查询失败或维修单不存在");
        }
    }
}
