package com.example.springboot.service.impl;

import com.example.springboot.entity.AdjustRoom;
import com.example.springboot.entity.PageResult;
import com.example.springboot.mapper.AdjustRoomMapper;
import com.example.springboot.service.AdjustRoomService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

@Service
public class AdjustRoomServiceImpl implements AdjustRoomService {

    @Resource
    private AdjustRoomMapper adjustRoomMapper;

    /**
     * 添加调宿申请
     */
    @Override
    public int addApply(AdjustRoom adjustRoom) {
        return adjustRoomMapper.insert(adjustRoom);
    }

    /**
     * 查找调宿申请
     */
    @Override
    public PageInfo<AdjustRoom> findAdjustRequests(Integer pageNum, Integer pageSize, String search) {
        PageHelper.startPage(pageNum, pageSize);
        List<AdjustRoom> list = adjustRoomMapper.findByCriteria(search);
        return new PageInfo<>(list);
    }

    @Override
    public List<AdjustRoom> findAllAdjustRequests() {
        return adjustRoomMapper.findAll();
    }
    
    @Override
    public List<AdjustRoom> findAdjustRequestsByStudentUsername(String username) {
        if (StringUtils.hasText(username)) {
            return adjustRoomMapper.findByUsername(username);
        }
        return Collections.emptyList();
    }

    /**
     * 删除调宿申请
     */
    @Override
    public int deleteAdjustment(Integer id) {
        return adjustRoomMapper.deleteById(id);
    }

    /**
     * 更新调宿申请
     */
    @Override
    public int updateApply(AdjustRoom adjustRoom) {
        if (adjustRoom.getId() == null) {
            return 0;
        }
        return adjustRoomMapper.update(adjustRoom);
    }

    @Override
    public AdjustRoom getAdjustRequestById(Integer id) {
        return adjustRoomMapper.findById(id);
    }

    @Override
    public PageInfo<AdjustRoom> findAdjustRequestsByUsername(String username, Integer pageNum, Integer pageSize, String search) {
        PageHelper.startPage(pageNum, pageSize);
        List<AdjustRoom> list = adjustRoomMapper.findByUsernameAndCriteria(username, search);
        return new PageInfo<>(list);
    }
}
