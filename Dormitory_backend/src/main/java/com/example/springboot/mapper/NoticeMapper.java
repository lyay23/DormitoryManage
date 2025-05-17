package com.example.springboot.mapper;

import com.example.springboot.entity.Notice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface NoticeMapper {

    Notice findById(@Param("id") Integer id);

    List<Notice> findAll();

    /**
     * Finds Notices by title (case-insensitive, partial match).
     * @param title The search criteria for the title.
     * @return A list of matching Notices.
     */
    List<Notice> findByTitle(@Param("title") String title);

    /**
     * Finds all Notices, ordered by release_time descending.
     * @return A list of all Notices sorted by release time.
     */
    List<Notice> findAllOrderByReleaseTimeDesc();

    int insert(Notice notice);

    int update(Notice notice);

    int deleteById(@Param("id") Integer id);
}
