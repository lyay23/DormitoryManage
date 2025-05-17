package com.example.springboot.service.impl;

import com.example.springboot.ExceptionHandler.BusinessException;
import com.example.springboot.entity.DormBuild;
import com.example.springboot.entity.PageResult;
import com.example.springboot.mapper.DormBuildMapper;
import com.example.springboot.service.DormBuildService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class DormBuildServiceImpl implements DormBuildService {

    @Autowired
    private DormBuildMapper dormBuildMapper;

    @Override
    public int addDormBuild(DormBuild dormBuild) {
        try {
            return dormBuildMapper.insert(dormBuild);
        } catch (DuplicateKeyException e) {
            // Spring 的 DuplicateKeyException，需 import org.springframework.dao.DuplicateKeyException
            throw new BusinessException("楼宇已经存在");
        } catch (Exception e) {
            throw new BusinessException("添加失败，系统异常！");
        }

    }


    @Override
    public List<DormBuild> findDormBuilds(String search) {
        if (StringUtils.hasText(search)) {
            return dormBuildMapper.findByName(search);
        }
        return dormBuildMapper.findAll();
    }


    @Override
    public PageInfo<DormBuild> findPaginatedDormBuilds(Integer pageNum, Integer pageSize, String search) {
        PageHelper.startPage(pageNum, pageSize);
        List<DormBuild> list;
        if (StringUtils.hasText(search)) {
            list = dormBuildMapper.findByName(search);
        } else {
            list = dormBuildMapper.findAll();
        }

        return new PageInfo<>(list);
    }

    @Override
    public int updateDormBuild(DormBuild dormBuild) {
        if (dormBuild.getId() == null) {

            return 0;
        }
        return dormBuildMapper.update(dormBuild);
    }

    @Override
    public int deleteDormBuildById(Integer id) {
        return dormBuildMapper.deleteById(id);
    }

    @Override
    public List<DormBuild> findAllDormBuilds() {
        return dormBuildMapper.findAll();
    }


    @Override
    public DormBuild findDormBuildByName(String name) {
        List<DormBuild> list = dormBuildMapper.findByName(name); // findByName now uses LIKE
        if (list != null && !list.isEmpty()) {
            return list.get(0); // Returns the first match, might not be ideal if names are not unique
        }
        return null;
    }

    @Override
    public DormBuild findDormBuildById(Integer id) {
        return dormBuildMapper.findById(id);
    }
} 