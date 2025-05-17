package com.example.springboot.service;

import com.example.springboot.entity.DormManager;
import com.example.springboot.entity.PageResult;
import com.github.pagehelper.PageInfo;


public interface DormManagerService {

    DormManager login(String username, String password);

    int addDormManager(DormManager dormManager);


    PageInfo<DormManager> findDormManagers(Integer pageNum, Integer pageSize, String search);

    int updateDormManager(DormManager dormManager);

    int deleteDormManagerByUsername(String username);

    DormManager getDormManagerByUsername(String username);
}
