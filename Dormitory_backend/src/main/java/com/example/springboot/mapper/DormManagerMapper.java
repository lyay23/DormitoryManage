package com.example.springboot.mapper;

import com.example.springboot.entity.DormManager;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface DormManagerMapper {

    DormManager findByUsername(@Param("username") String username);

    List<DormManager> findAll();

    /**
     * Finds DormManagers based on a search string, typically matching username or name.
     * @param search The search criteria.
     * @return A list of matching DormManagers.
     */
    List<DormManager> findByCriteria(@Param("search") String search);

    int insert(DormManager dormManager);

    /**
     * Updates a DormManager. Assumes username is the key for update.
     * @param dormManager The DormManager object with updated details.
     * @return The number of rows affected.
     */
    int update(DormManager dormManager);

    int deleteByUsername(@Param("username") String username);

    // Login method, similar to Admin
    DormManager findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
}
