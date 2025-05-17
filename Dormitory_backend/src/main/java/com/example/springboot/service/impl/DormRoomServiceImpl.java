package com.example.springboot.service.impl;

import com.example.springboot.ExceptionHandler.BusinessException;
import com.example.springboot.entity.AdjustRoom;
import com.example.springboot.entity.DormRoom;
import com.example.springboot.entity.PageResult;
import com.example.springboot.mapper.DormRoomMapper;
import com.example.springboot.service.DormRoomService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

import com.example.springboot.mapper.StudentMapper;
import com.example.springboot.entity.Student;

@Service
public class DormRoomServiceImpl implements DormRoomService {

    @Resource
    private DormRoomMapper dormRoomMapper;

    @Resource
    private StudentMapper studentMapper;

    @Override
    public int getNotFullRoomCount() {
        return dormRoomMapper.countNonFullRooms();
    }

    @Override
    public int addDormRoom(DormRoom dormRoom) {

        return dormRoomMapper.insert(dormRoom);
    }

    @Override
    public PageInfo<DormRoom> findDormRooms(Integer pageNum, Integer pageSize, String search) {
        PageHelper.startPage(pageNum, pageSize);
        List<DormRoom> list;
        if (StringUtils.hasText(search)) {
            list = dormRoomMapper.findByCriteria(search);
        } else {
            list = dormRoomMapper.findAll();
        }
        return new PageInfo<>(list);
    }

    @Override
    public int updateDormRoom(DormRoom dormRoom) {
        try {
            if (dormRoom.getDormRoomId() == null) return 0;
            return dormRoomMapper.update(dormRoom);
        } catch (Exception e) {
            throw new BusinessException("房间重复请重新添加");
        }
    }

    @Override
    public int deleteDormRoomById(Integer dormRoomId) {
        return dormRoomMapper.deleteByRoomId(dormRoomId);
    }

    @Override
    @Transactional
    public int clearBed(String bedNameColumn, Integer dormRoomId) {

        return dormRoomMapper.clearBedAndUpdateCapacity(dormRoomId, bedNameColumn);
    }

    @Override
    public DormRoom getRoomByOccupant(String studentName) {
        DormRoom room = dormRoomMapper.findByOccupant(studentName);
        if (room != null) {
            if (room.getFirstBed() != null) {
                Student stu = studentMapper.findByUsername(room.getFirstBed());
                if (stu != null) room.setFirstBed(stu.getName());
            }
            if (room.getSecondBed() != null) {
                Student stu = studentMapper.findByUsername(room.getSecondBed());
                if (stu != null) room.setSecondBed(stu.getName());
            }
            if (room.getThirdBed() != null) {
                Student stu = studentMapper.findByUsername(room.getThirdBed());
                if (stu != null) room.setThirdBed(stu.getName());
            }
            if (room.getFourthBed() != null) {
                Student stu = studentMapper.findByUsername(room.getFourthBed());
                if (stu != null) room.setFourthBed(stu.getName());
            }
        }
        return room;
    }

    @Override
    public Long getTotalOccupiedBedsCount() {
        return dormRoomMapper.sumCurrentCapacity();
    }

    @Override
    public Long getOccupiedBedsCountByBuilding(Integer dormBuildId) {
        return dormRoomMapper.sumCurrentCapacityByBuilding(dormBuildId);
    }

    @Override
    @Transactional
    public int processAdjustRoomUpdate(AdjustRoom adjustRoom) {
        DormRoom oldRoom = dormRoomMapper.findByOccupant(adjustRoom.getUsername());
        DormRoom newRoomInitialCheck = dormRoomMapper.findByRoomId(adjustRoom.getTowardsRoomId());

        if (newRoomInitialCheck == null) return -2; // New room does not exist
        if (newRoomInitialCheck.getCurrentCapacity() >= newRoomInitialCheck.getMaxCapacity()) return -3; // New room is full

        if (oldRoom != null) {
            String oldBedColumn = null;
            if (adjustRoom.getUsername().equals(oldRoom.getFirstBed())) oldBedColumn = "first_bed";
            else if (adjustRoom.getUsername().equals(oldRoom.getSecondBed())) oldBedColumn = "second_bed";
            else if (adjustRoom.getUsername().equals(oldRoom.getThirdBed())) oldBedColumn = "third_bed";
            else if (adjustRoom.getUsername().equals(oldRoom.getFourthBed())) oldBedColumn = "fourth_bed";
            
            if (oldBedColumn != null) {
                dormRoomMapper.clearBedAndUpdateCapacity(oldRoom.getDormRoomId(), oldBedColumn);
            }
        }

        DormRoom newRoom = newRoomInitialCheck;

        String newBedColumn = null;
        if (newRoom.getFirstBed() == null) newBedColumn = "first_bed";
        else if (newRoom.getSecondBed() == null) newBedColumn = "second_bed";
        else if (newRoom.getThirdBed() == null) newBedColumn = "third_bed";
        else if (newRoom.getFourthBed() == null) newBedColumn = "fourth_bed";
        
        if (newBedColumn == null) return -4; // Should not happen if capacity check passed
        
        if ("first_bed".equals(newBedColumn)) newRoom.setFirstBed(adjustRoom.getUsername());
        else if ("second_bed".equals(newBedColumn)) newRoom.setSecondBed(adjustRoom.getUsername());
        else if ("third_bed".equals(newBedColumn)) newRoom.setThirdBed(adjustRoom.getUsername());
        else if ("fourth_bed".equals(newBedColumn)) newRoom.setFourthBed(adjustRoom.getUsername());
        
        newRoom.setCurrentCapacity(newRoom.getCurrentCapacity() + 1);
        int updateResult = dormRoomMapper.update(newRoom);

        return updateResult > 0 ? 1 : -5;
    }

    @Override
    public DormRoom getRoomById(Integer dormRoomId) {
        return dormRoomMapper.findByRoomId(dormRoomId);
    }
} 