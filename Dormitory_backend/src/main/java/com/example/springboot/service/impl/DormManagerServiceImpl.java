package com.example.springboot.service.impl;

import com.example.springboot.ExceptionHandler.BusinessException;
import com.example.springboot.entity.DormManager;
import com.example.springboot.entity.PageResult;
import com.example.springboot.mapper.DormManagerMapper;
import com.example.springboot.service.DormManagerService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Service

public class DormManagerServiceImpl implements DormManagerService {

    @Resource
    private DormManagerMapper dormManagerMapper;

    @Override
    public DormManager login(String username, String password) {
        return dormManagerMapper.findByUsernameAndPassword(username, password);
    }

    @Override
    public int addDormManager(DormManager dormManager) {
        try {
            return dormManagerMapper.insert(dormManager);
        } catch (DuplicateKeyException e) {
            throw new BusinessException("用户名已存在，请更换用户名！");
        } catch (Exception e) {
            throw new BusinessException("添加失败，系统异常！");
        }

    }

    @Override
    public PageInfo<DormManager> findDormManagers(Integer pageNum, Integer pageSize, String search) {
        PageHelper.startPage(pageNum, pageSize);
        List<DormManager> list;
        if (StringUtils.hasText(search)) {
            list = dormManagerMapper.findByCriteria(search);
        } else {
            list = dormManagerMapper.findAll();
        }
        return new PageInfo<>(list);
    }

    @Override
    public int updateDormManager(DormManager dormManager) {

        if (!StringUtils.hasText(dormManager.getUsername())) {
             return 0;
        }
        return dormManagerMapper.update(dormManager);
    }

    @Override
    public int deleteDormManagerByUsername(String username) {
        return dormManagerMapper.deleteByUsername(username);
    }

    @Override
    public DormManager getDormManagerByUsername(String username) {
        return dormManagerMapper.findByUsername(username);
    }
}
