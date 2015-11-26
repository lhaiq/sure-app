package com.hengsu.sure.invite.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.hengsu.sure.auth.model.UserModel;
import com.hengsu.sure.auth.service.UserService;
import com.hengsu.sure.core.ErrorCode;
import com.hengsu.sure.core.service.PushService;
import com.hengsu.sure.invite.InvitationStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hengsu.sure.invite.entity.Invitation;
import com.hengsu.sure.invite.repository.InvitationRepository;
import com.hengsu.sure.invite.model.InvitationModel;
import com.hengsu.sure.invite.service.InvitationService;
import com.hkntv.pylon.core.beans.mapping.BeanMapper;

import java.util.Date;
import java.util.List;

@Service
public class InvitationServiceImpl implements InvitationService {

    private static final Double DEFAULT_DISTANCE = 10.0;
    private static final Long DEFAULT_INTERVAL = 30 * 60L;

    private static final String INVITATION_REQUEST = "invitation_request";
    private static final String INVITATION_RECEIVE = "invitation_receive";
    private static final String INVITATION_CONFIRM = "invitation_confirm";


    @Autowired
    private BeanMapper beanMapper;

    @Autowired
    private InvitationRepository invitationRepo;

    @Autowired
    private UserService userService;

    @Autowired
    private PushService pushService;

    @Transactional
    @Override
    public int create(InvitationModel invitationModel) {
        return invitationRepo.insert(beanMapper.map(invitationModel, Invitation.class));
    }

    @Transactional
    @Override
    public int createSelective(InvitationModel invitationModel) {
        Invitation invitation = beanMapper.map(invitationModel, Invitation.class);
        int retVal = invitationRepo.insertSelective(invitation);
        return retVal;
    }

    @Transactional
    @Override
    public int deleteByPrimaryKey(Long id) {
        return invitationRepo.deleteByPrimaryKey(id);
    }

    @Transactional(readOnly = true)
    @Override
    public InvitationModel findByPrimaryKey(Long id) {
        Invitation invitation = invitationRepo.selectByPrimaryKey(id);
        return beanMapper.map(invitation, InvitationModel.class);
    }

    @Transactional(readOnly = true)
    @Override
    public int selectCount(InvitationModel invitationModel) {
        return invitationRepo.selectCount(beanMapper.map(invitationModel, Invitation.class));
    }

    @Transactional
    @Override
    public int updateByPrimaryKey(InvitationModel invitationModel) {
        return invitationRepo.updateByPrimaryKey(beanMapper.map(invitationModel, Invitation.class));
    }

    @Transactional
    @Override
    public int updateByPrimaryKeySelective(InvitationModel invitationModel) {
        return invitationRepo.updateByPrimaryKeySelective(beanMapper.map(invitationModel, Invitation.class));
    }

    @Override
    public int queryTimeOfPublish(Long userId) {
        return 0;
    }

    @Transactional
    @Override
    public void publishInvitation(InvitationModel invitationModel) {

        //判断是否有邀约尚未完成
        InvitationModel param = new InvitationModel();
        param.setUserId(invitationModel.getUserId());
        param.setStatus(InvitationStatus.UNFINISHED.getCode());
        Integer count = selectCount(param);
        if (count > 0) {
            ErrorCode.throwBusinessException(ErrorCode.HAVE_UNFINISHED_INVITATION);
        }

        //将邀约信息保存
        invitationModel.setCreateTime(new Date());
        createSelective(invitationModel);

        //查询符合条件的user
        List<UserModel> userModels = userService.queryUserByTimeAndLocation(
                DEFAULT_INTERVAL,
                DEFAULT_DISTANCE,
                invitationModel.getLongitude(),
                invitationModel.getLatitude());

        //查询发布者
        UserModel userModel = userService.findByPrimaryKey(invitationModel.getUserId());

        //广播邀约
        JSONObject message = new JSONObject();
        message.put("pushId", INVITATION_REQUEST);
        message.put("request", invitationModel);
        message.put("user", userModel);
        message.put("count", userModels.size());
        pushService.pushMessage(message.toJSONString(), userModels);
    }

    @Override
    public void refuseInvitation(Long id, Long userId) {

    }

    @Override
    public void receiveInvitation(Long id, Long userId) {

        //判断该邀约是否已经确认
        InvitationModel invitationModel = findByPrimaryKey(id);
        if (invitationModel.getStatus() == InvitationStatus.FINISHED.getCode()) {
            ErrorCode.throwBusinessException(ErrorCode.INVITATION_FINISHED);
        }
        UserModel userModel = userService.findByPrimaryKey(invitationModel.getUserId());
        UserModel receivedUser = userService.findByPrimaryKey(userId);

        //向发布邀约者推送消息
        JSONObject message = new JSONObject();
        message.put("pushId", INVITATION_RECEIVE);
        message.put("receivedUser", receivedUser);
        pushService.pushMessage(message.toJSONString(), userModel);

    }

    @Transactional
    @Override
    public void confirmInvitation(Long id, Long userId, Long receivedUserId) {

        //判断该邀约是否为自己发布
        InvitationModel invitationModel = findByPrimaryKey(id);
        if (invitationModel.getUserId() != userId) {
            ErrorCode.throwBusinessException(ErrorCode.INVITATION_PUBLISHER_NOT_MATCH);
        }

        //更新状态
        InvitationModel newInvitationModel = new InvitationModel();
        newInvitationModel.setStatus(InvitationStatus.FINISHED.getCode());
        updateByPrimaryKeySelective(newInvitationModel);

        //通知接受着
        JSONObject message = new JSONObject();
        message.put("pushId", INVITATION_CONFIRM);
        message.put("invitation", invitationModel);
        pushService.pushMessage(message.toJSONString(),
                userService.findByPrimaryKey(receivedUserId));

    }


}
