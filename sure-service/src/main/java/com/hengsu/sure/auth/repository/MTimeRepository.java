package com.hengsu.sure.auth.repository;

import com.hengsu.sure.auth.entity.MTime;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MTimeRepository {
    int deleteByPrimaryKey(@Param("id") Long id);

    int insert(@Param("mtime") MTime mTime);

    int insertSelective(@Param("mtime") MTime mTime);

    MTime selectByPrimaryKey(@Param("id") Long id);

    int updateByPrimaryKeySelective(@Param("mtime") MTime mTime);

    int updateByPrimaryKey(@Param("mtime") MTime mtime);

    int selectCount(@Param("mtime") MTime mtime);

    List<MTime> selectPage(@Param("mtime") MTime mtime, @Param("pageable") Pageable pageable);
}