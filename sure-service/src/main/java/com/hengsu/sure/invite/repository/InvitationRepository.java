package com.hengsu.sure.invite.repository;

import com.hengsu.sure.invite.entity.Invitation;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface InvitationRepository {
    int deleteByPrimaryKey(@Param("id") Long id);

    int insert(@Param("invitation") Invitation invitation);

    int insertSelective(@Param("invitation") Invitation invitation);

    Invitation selectByPrimaryKey(@Param("id") Long id);

    int updateByPrimaryKeySelective(@Param("invitation") Invitation invitation);

    int updateByPrimaryKey(@Param("invitation") Invitation invitation);

    int selectCount(@Param("invitation") Invitation invitation);

    List<Invitation> selectPage(@Param("invitation") Invitation invitation, @Param("pageable") Pageable pageable);

    Integer getInvitedCount(@Param("userId") Long userId, @Param("startDate") Date startDate, @Param("endDate") Date endDate);
}