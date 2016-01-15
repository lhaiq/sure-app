package com.hengsu.sure.invite.repository;

import com.hengsu.sure.invite.entity.InvitationReceive;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface InvitationReceiveRepository {
    int deleteByPrimaryKey(@Param("id") Long id);

    int insert(@Param("invitationreceive") InvitationReceive invitationreceive);

    int insertSelective(@Param("invitationreceive") InvitationReceive invitationreceive);

    InvitationReceive selectByPrimaryKey(@Param("id") Long id);

    int updateByPrimaryKeySelective(@Param("invitationreceive") InvitationReceive invitationreceive);

    int updateByPrimaryKey(@Param("invitationreceive") InvitationReceive invitationreceive);

    int selectCount(@Param("invitationreceive") InvitationReceive invitationreceive);

    List<InvitationReceive> selectPage(@Param("invitationreceive") InvitationReceive invitationreceive, @Param("pageable") Pageable pageable);
}