
package com.hengsu.sure.invite.service;

import com.hengsu.sure.invite.model.InvitationModel;

import java.util.Date;

public interface InvitationService {

    public int create(InvitationModel invitationModel);

    public int createSelective(InvitationModel invitationModel);

    public InvitationModel findByPrimaryKey(Long id);

    public int updateByPrimaryKey(InvitationModel invitationModel);

    public int updateByPrimaryKeySelective(InvitationModel invitationModel);

    public int deleteByPrimaryKey(Long id);

    public int selectCount(InvitationModel invitationModel);

    /**
     * 查询可发布邀约的次数
     *
     * @param userId
     * @return
     */
    public int queryTimeOfPublish(Long userId);

    /**
     * 发布邀约
     *
     * @param invitationModel
     */
    public void publishInvitation(InvitationModel invitationModel);

    /**
     * 拒绝邀约
     *
     * @param id
     * @param userId
     */
    public void refuseInvitation(Long id, Long userId);

    /**
     * 接受邀约
     *
     * @param id
     * @param userId
     */
    public void receiveInvitation(Long id, Long userId);


    /**
     * 确认邀约
     *
     * @param id
     * @param userId
     * @param receivedUserId
     */
    public void confirmInvitation(Long id, Long userId, Long receivedUserId);

}