package com.hengsu.sure.auth.repository;

import com.hengsu.sure.auth.entity.UserLBS;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserLBSRepository {
    int deleteByPrimaryKey(@Param("id") Long id);

    int insert(@Param("userlbs") UserLBS userlbs);

    int insertSelective(@Param("userlbs") UserLBS userlbs);

    UserLBS selectByPrimaryKey(@Param("id") Long id);

    int updateByPrimaryKeySelective(@Param("userlbs") UserLBS userlbs);

    int updateByPrimaryKey(@Param("userlbs") UserLBS userlbs);

    int selectCount(@Param("userlbs") UserLBS userlbs);

    List<UserLBS> selectPage(@Param("userlbs") UserLBS userlbs, @Param("pageable") Pageable pageable);
}