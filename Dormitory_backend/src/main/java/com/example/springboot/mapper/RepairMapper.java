package com.example.springboot.mapper;

import com.example.springboot.entity.Repair;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface RepairMapper {

    Repair findById(@Param("id") Integer id);

    List<Repair> findAll(); // For general admin/manager view, ordered by build time

    List<Repair> findByCriteria(@Param("search") String search); // Search by title, content

    List<Repair> findByRepairerAndCriteria(@Param("repairerName") String repairerName, @Param("search") String search);

    int insert(Repair repair);

    int update(Repair repair); // Typically updates state and finish_time

    int deleteById(@Param("id") Integer id);

    Long countAll();

}
