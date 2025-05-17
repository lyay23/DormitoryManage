package com.example.springboot.controller;

import com.example.springboot.common.Result;
import com.example.springboot.entity.AdjustRoom;
import com.example.springboot.service.AdjustRoomService;
import com.example.springboot.service.DormRoomService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/adjustRoom")
public class AdjustRoomController {

    @Resource
    private AdjustRoomService adjustRoomService;

    @Resource
    private DormRoomService dormRoomService;


    /**
     * 添加订单
     */
    @PostMapping("/add")
    public Result<?> add(@RequestBody AdjustRoom adjustRoom) {

        int result = adjustRoomService.addApply(adjustRoom);
        if (result > 0) {
            return Result.success();
        } else {
            return Result.error("-1", "添加申请失败");
        }
    }


    /**
     * 更新订单
     */
    @PutMapping("/update/{state}")
    public Result<?> update(@RequestBody AdjustRoom adjustRoom, @PathVariable Boolean state) {
        if (state) {
            // 更新房间表信息
            int i = dormRoomService.processAdjustRoomUpdate(adjustRoom);
            if (i == -1) {
                return Result.error("-1", "重复操作或目标房间更新失败");
            } else if (i == -2) {
                return Result.error("-2", "目标房间不存在");
            } else if (i == -3) {
                return Result.error("-3", "目标房间已满");
            } else if (i == -4) {
                return Result.error("-4", "目标房间没有可用床位 (逻辑错误)");
            } else if (i == -5) {
                return Result.error("-5", "更新目标房间信息失败");
            } else if (i == 0) {
                return Result.error("-1", "处理调宿更新房间信息时发生未知错误或没有更新");
            }
        }
        //更新调宿表信息
        int i = adjustRoomService.updateApply(adjustRoom);
        if (i > 0) {
            return Result.success();
        } else {
            return Result.error("-1", "更新申请状态失败");
        }
    }

    /**
     * 删除订单
     */
    @DeleteMapping("/delete/{id}")
    public Result<?> delete(@PathVariable Integer id) {
        int i = adjustRoomService.deleteAdjustment(id);
        if (i > 0) {
            return Result.success();
        } else {
            return Result.error("-1", "删除失败");
        }
    }

    /**
     * 查找调宿申请 (分页)
     */
    @GetMapping("/find")
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search) {
        PageInfo<AdjustRoom> pageResult = adjustRoomService.findAdjustRequests(pageNum, pageSize, search);
        return Result.success(pageResult);
    }

    /**
     * 根据用户名查找调宿申请 (分页)
     */
    @GetMapping("/find/byUsername/{username}")
    public Result<?> findPageByUsername(@PathVariable String username,
                                      @RequestParam(defaultValue = "1") Integer pageNum,
                                      @RequestParam(defaultValue = "10") Integer pageSize,
                                      @RequestParam(defaultValue = "") String search) {
        PageInfo<AdjustRoom> pageResult = adjustRoomService.findAdjustRequestsByUsername(username, pageNum, pageSize, search);
        return Result.success(pageResult);
    }
}
