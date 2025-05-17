package com.example.springboot.service;

import com.example.springboot.entity.AdjustRoom;
import com.example.springboot.entity.DormRoom;
import com.example.springboot.entity.PageResult;
import com.github.pagehelper.PageInfo;

public interface DormRoomService {

    int getNotFullRoomCount();

    int addDormRoom(DormRoom dormRoom);

    PageInfo<DormRoom> findDormRooms(Integer pageNum, Integer pageSize, String search);

    int updateDormRoom(DormRoom dormRoom);

    int deleteDormRoomById(Integer dormRoomId);

    int clearBed(String bedName, Integer dormRoomId);

    DormRoom getRoomByOccupant(String studentName);

    Long getTotalOccupiedBedsCount();

    Long getOccupiedBedsCountByBuilding(Integer dormBuildId);

    int processAdjustRoomUpdate(AdjustRoom adjustRoom);

    DormRoom getRoomById(Integer dormRoomId);
}
