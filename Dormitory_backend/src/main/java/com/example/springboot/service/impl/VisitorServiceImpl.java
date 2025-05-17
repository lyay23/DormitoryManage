package com.example.springboot.service.impl;

import com.example.springboot.entity.Visitor;
import com.example.springboot.mapper.VisitorMapper;
import com.example.springboot.service.VisitorService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class VisitorServiceImpl implements VisitorService {

    @Resource
    private VisitorMapper visitorMapper;

    @Override
    public int add(Visitor visitor) {
        return visitorMapper.insert(visitor);
    }

    @Override
    public PageInfo<Visitor> findPage(Integer pageNum, Integer pageSize, String search) {
        PageHelper.startPage(pageNum, pageSize);
        List<Visitor> visitorList = visitorMapper.findByName(search);
        return new PageInfo<>(visitorList);
    }

    @Override
    public int update(Visitor visitor) {
        if (visitor.getId() == null) {
            return 0; // Or throw exception
        }
        return visitorMapper.update(visitor);
    }

    @Override
    public int delete(Integer id) {
        return visitorMapper.deleteById(id);
    }

    @Override
    public Visitor findById(Integer id) {
        return visitorMapper.findById(id);
    }
}
