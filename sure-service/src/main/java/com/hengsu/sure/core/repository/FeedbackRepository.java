package com.hengsu.sure.core.repository;

import com.hengsu.sure.core.entity.Feedback;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackRepository {
    int deleteByPrimaryKey(@Param("id") Integer id);

    int insert(@Param("feedback") Feedback feedback);

    int insertSelective(@Param("feedback") Feedback feedback);

    Feedback selectByPrimaryKey(@Param("id") Integer id);

    int updateByPrimaryKeySelective(@Param("feedback") Feedback feedback);

    int updateByPrimaryKeyWithBLOBs(@Param("feedback") Feedback feedback);

    int updateByPrimaryKey(@Param("feedback") Feedback feedback);

    int selectCount(@Param("feedback") Feedback feedback);

    List<Feedback> selectPage(@Param("feedback") Feedback feedback, @Param("pageable") Pageable pageable);
}