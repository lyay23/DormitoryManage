package com.example.springboot.mapper;

import com.example.springboot.entity.DormRoom;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface DormRoomMapper {

    DormRoom findByRoomId(@Param("dormRoomId") Integer dormRoomId);

    List<DormRoom> findAll();

    /**
     * Finds DormRooms based on a search string.
     * Search could be by dormRoomId (e.g., "101"), or dormBuildId.
     * @param search The search criteria.
     * @return A list of matching DormRooms.
     */
    List<DormRoom> findByCriteria(@Param("search") String search);

    int insert(DormRoom dormRoom);

    int update(DormRoom dormRoom); // Assumes dormRoomId is in the object for WHERE clause

    int deleteByRoomId(@Param("dormRoomId") Integer dormRoomId);

    // For judgeHadBed: find a room where any bed is occupied by the studentName
    DormRoom findByOccupant(@Param("studentName") String studentName);

    // For noFullRoom: count rooms where currentCapacity < maxCapacity
    int countNonFullRooms();

    // For deleteBedInfo: This is complex, involves setting a bed field to null and decrementing currentCapacity.
    // This might be better handled by fetching the room, modifying in service, then calling update.
    // Or a specific mapper method:
    int clearBedAndUpdateCapacity(@Param("dormRoomId") Integer dormRoomId, @Param("bedNameColumn") String bedNameColumn);

    // For selectHaveRoomStuNum: sum of currentCapacity across all rooms.
    Long sumCurrentCapacity();

    // For getEachBuildingStuNum: sum of currentCapacity for a specific dormBuildId.
    Long sumCurrentCapacityByBuilding(@Param("dormBuildId") Integer dormBuildId);

    // For checkBedState: check if a specific bed in a specific room is occupied.
    // This can be done by findByRoomId and checking the field in service, or a specific query.
    // Let's assume findByRoomId and service logic for now.

}
