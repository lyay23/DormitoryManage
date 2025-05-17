package com.example.springboot.controller;

import com.example.springboot.common.Result;
import com.example.springboot.entity.DormRoom;
import com.example.springboot.service.DormRoomService;
import com.github.pagehelper.PageInfo;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;

@RestController
@RequestMapping("/room")
public class DormRoomController {

    @Resource
    private DormRoomService dormRoomService;

    /**
     * 添加房间
     */
    @PostMapping("/add")
    public Result<?> add(@RequestBody DormRoom dormRoom) {
        int i = dormRoomService.addDormRoom(dormRoom);
        if (i > 0) return Result.success();
        return Result.error("-1", "添加房间失败");
    }

    /**
     * 更新房间
     */
    @PutMapping("/update")
    public Result<?> update(@RequestBody DormRoom dormRoom) {
        int i = dormRoomService.updateDormRoom(dormRoom);
        if (i > 0) return Result.success();
        return Result.error("-1", "更新房间失败");
    }

    /**
     * 删除房间
     */
    @DeleteMapping("/delete/{dormRoomId}")
    public Result<?> delete(@PathVariable Integer dormRoomId) {
        int i = dormRoomService.deleteDormRoomById(dormRoomId);
        if (i > 0) return Result.success();
        return Result.error("-1", "删除房间失败");
    }

    /**
     * 查找房间
     */
    @GetMapping("/find")
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search) {
        PageInfo<DormRoom> pageResult = dormRoomService.findDormRooms(pageNum, pageSize, search);
        return Result.success(pageResult);
    }

    /**
     * 首页顶部：空宿舍统计
     */
    @GetMapping("/noFullRoom")
    public Result<?> noFullRoom() {
        int num = dormRoomService.getNotFullRoomCount();
        // Assuming num >= 0 is always true for a count
        return Result.success(num);
    }

    /**
     * 删除床位学生信息
     */
    @DeleteMapping("/deleteBed/{dormRoomId}/{bedName}")
    public Result<?> deleteBedInfo(@PathVariable Integer dormRoomId, @PathVariable String bedName) {

        String bedNameColumn = bedName; // TODO: Validate or map bedName to actual DB column name if needed

        
        int i = dormRoomService.clearBed(bedNameColumn, dormRoomId);
        if (i > 0) return Result.success();
        return Result.error("-1", "清空床位失败或床位已空");
    }

    /**
     * 床位信息，查询该学生是否已有床位
     */
    @GetMapping("/judgeHadBed/{studentName}")
    public Result<?> judgeHadBed(@PathVariable String studentName) {
        DormRoom dormRoom = dormRoomService.getRoomByOccupant(studentName);
        if (dormRoom == null) return Result.success(); // No room found, student does not have a bed
        return Result.error("-1", "该学生已有宿舍: " + dormRoom.getDormRoomId());
    }

    /**
     * 主页 住宿人数
     */
    @GetMapping("/selectHaveRoomStuNum")
    public Result<?> selectHaveRoomStuNum() {
        Long count = dormRoomService.getTotalOccupiedBedsCount();
        return Result.success(count);
    }

    /**
     * 住宿分布人数
     */
    @GetMapping("/getEachBuildingStuNum/{buildingCount}") // Parameter is count of buildings, not a building ID
    public Result<?> getEachBuildingStuNum(@PathVariable int buildingCount) {
        // This seems to assume building IDs are 1 to buildingCount.
        // If building IDs are different, this logic needs to fetch actual building IDs first.
        ArrayList<Long> arrayList = new ArrayList<>();
        for (int i = 1; i <= buildingCount; i++) { // Assuming building IDs are 1, 2, ..., buildingCount
            Long eachBuildingStuNum = dormRoomService.getOccupiedBedsCountByBuilding(i);
            arrayList.add(eachBuildingStuNum);
        }
        return Result.success(arrayList); // Success even if list is empty or contains zeros
    }

    /**
     * 学生功能： 我的宿舍
     */
    @GetMapping("/getMyRoom/{studentName}") // Changed from name to studentName
    public Result<?> getMyRoom(@PathVariable String studentName) {
        DormRoom dormRoom = dormRoomService.getRoomByOccupant(studentName);
        if (dormRoom != null) return Result.success(dormRoom);
        return Result.error("-1", "未找到该学生的宿舍信息");
    }

    /**
     * 检查房间是否满员
     */
    @GetMapping("/checkRoomState/{dormRoomId}") // Checks if room is full
    public Result<?> checkRoomState(@PathVariable Integer dormRoomId) {
        DormRoom dormRoom = dormRoomService.getRoomById(dormRoomId);
        if (dormRoom == null) return Result.error("-1", "不存在该房间");
        if (dormRoom.getCurrentCapacity() >= dormRoom.getMaxCapacity()) {
            return Result.error("-1", "该房间人满了");
        }
        return Result.success(dormRoom); // Room exists and is not full
    }

    /**
     * 检查床位是否已经有人
     */
    @GetMapping("/checkBedState/{dormRoomId}/{bedNum}") // bedNum 1-4
    public Result<?> checkBedState(@PathVariable Integer dormRoomId, @PathVariable int bedNum) {
        DormRoom dormRoom = dormRoomService.getRoomById(dormRoomId);
        if (dormRoom == null) return Result.error("-1", "不存在该房间");
        String occupant = null;
        switch (bedNum) {
            case 1: occupant = dormRoom.getFirstBed(); break;
            case 2: occupant = dormRoom.getSecondBed(); break;
            case 3: occupant = dormRoom.getThirdBed(); break;
            case 4: occupant = dormRoom.getFourthBed(); break;
            default: return Result.error("-1", "无效的床位号");
        }
        if (StringUtils.hasText(occupant)) { // Use StringUtils.hasText for null/empty check
            return Result.error("-1", "该床位已有人: " + occupant);
        }
        return Result.success(); // Bed is available
    }

    /**
     * 检查房间是否满员
     */
    @GetMapping("/checkRoomExist/{dormRoomId}")
    public Result<?> checkRoomExist(@PathVariable Integer dormRoomId) {
        DormRoom dormRoom = dormRoomService.getRoomById(dormRoomId);
        if (dormRoom != null) return Result.success(dormRoom);
        return Result.error("-1", "不存在该房间");
    }
}
