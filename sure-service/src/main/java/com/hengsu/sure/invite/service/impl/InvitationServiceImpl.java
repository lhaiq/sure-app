package com.hengsu.sure.invite.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hengsu.sure.auth.UserRole;
import com.hengsu.sure.auth.model.UserModel;
import com.hengsu.sure.auth.service.UserService;
import com.hengsu.sure.core.ErrorCode;
import com.hengsu.sure.core.service.PushService;
import com.hengsu.sure.invite.IndentStatus;
import com.hengsu.sure.invite.IndentType;
import com.hengsu.sure.invite.InvitationStatus;
import com.hengsu.sure.invite.model.IndentModel;
import com.hengsu.sure.invite.model.InvitationConfirmModel;
import com.hengsu.sure.invite.model.InvitationResultModel;
import com.hengsu.sure.invite.service.IndentService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hengsu.sure.invite.entity.Invitation;
import com.hengsu.sure.invite.repository.InvitationRepository;
import com.hengsu.sure.invite.model.InvitationModel;
import com.hengsu.sure.invite.service.InvitationService;
import com.hkntv.pylon.core.beans.mapping.BeanMapper;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class InvitationServiceImpl implements InvitationService {

    private static final Double DEFAULT_DISTANCE = 10.0;
    private static final Long DEFAULT_INTERVAL = 30 * 60L;
    private static final Integer DEFAULT_COUNT = 4;
    private static final Integer DEAULT_PUBLISH_COUNT = 20;

    private static final String INVITATION_REQUEST = "invitation_request";
    private static final String INVITATION_RECEIVE = "invitation_receive";


    @Autowired
    private BeanMapper beanMapper;

    @Autowired
    private InvitationRepository invitationRepo;

    @Autowired
    private UserService userService;

    @Autowired
    private PushService pushService;

    @Autowired
    private IndentService indentService;

    @Value("${push.retry.count}")
    private Integer retryCount;

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
        invitationModel.setId(invitation.getId());
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
    public InvitationResultModel publishInvitation(InvitationModel invitationModel) {

        //角色是否为买家
        Long userId = invitationModel.getUserId();
        UserModel userModel = userService.findByPrimaryKeyNoPass(userId);
        if (UserRole.CUSTOMER.getCode() != userModel.getRole()) {
            ErrorCode.throwBusinessException(ErrorCode.INVITATION_ROLE_ERROR);
        }


        //判断是否超出次数
        Integer invitedCount = getInvitedCount(invitationModel.getUserId());
        if (invitedCount >= retryCount) {
            ErrorCode.throwBusinessException(ErrorCode.HAVE_EXCEED_INVITATION);
        }

        //剩余次数
        InvitationResultModel invitationResultModel = new InvitationResultModel();
        Integer residueCount = retryCount - (invitedCount + 1);
        invitationResultModel.setResidueCount(residueCount);


        List<UserModel> userModels = null;

        //查询符合条件的user,
        for (int i = 1; i < DEFAULT_COUNT; i++) {
            userModels = userService.queryUserByTimeAndLocation(
                    DEFAULT_INTERVAL * i,
                    DEFAULT_DISTANCE * i,
                    invitationModel.getLongitude(),
                    invitationModel.getLatitude(),
                    invitationModel.getUserId(),
                    UserRole.CUSTOMER,
                    invitationModel.getCity());

            if (CollectionUtils.isNotEmpty(userModels) || userModels.size() <= DEAULT_PUBLISH_COUNT) {
                continue;
            }
        }

        //判断是否有合适的人
        if (CollectionUtils.isEmpty(userModels)) {
            invitationModel.setStatus(InvitationStatus.VOID.getCode());
            //将邀约信息保存
            invitationModel.setCreateTime(new Date());
            createSelective(invitationModel);
            invitationResultModel.setSendCount(0);
            return invitationResultModel;
        }


        //将邀约信息保存
        invitationModel.setCreateTime(new Date());
        invitationModel.setStatus(InvitationStatus.PUBLISHED.getCode());
        invitationModel.setMoney(invitationModel.getPrice());
        createSelective(invitationModel);

        //返回邀约Id
        invitationResultModel.setInvitationId(invitationModel.getId());


        //广播邀约
        JSONObject message = new JSONObject();
        message.put("pushId", INVITATION_REQUEST);
        message.put("request", invitationModel);
        message.put("user", userModel);
        message.put("count", userModels.size());
        pushService.pushMessage(message.toJSONString(), userModels);
        invitationResultModel.setSendCount(userModels.size());

        return invitationResultModel;
    }

    @Override
    public void refuseInvitation(Long id, Long userId) {

    }

    @Override
    public void receiveInvitation(Long id, Long userId) {

        //判断该邀约是否已经完成
        InvitationModel invitationModel = findByPrimaryKey(id);
        if (invitationModel.getStatus() != InvitationStatus.PUBLISHED.getCode()) {
            ErrorCode.throwBusinessException(ErrorCode.INVITATION_FINISHED);
        }
        UserModel userModel = userService.findByPrimaryKey(invitationModel.getUserId());
        UserModel receivedUser = userService.findByPrimaryKeyNoPass(userId);

        //不能接受自己的邀约
        if (userModel.getId() == receivedUser.getId()) {
            ErrorCode.throwBusinessException(ErrorCode.CANNOT_RECEIVE_SLEF_INVITATION);
        }

        //向发布邀约者推送消息
        JSONObject message = new JSONObject();
        message.put("pushId", INVITATION_RECEIVE);
        message.put("receivedUser", receivedUser);
        pushService.pushMessage(message.toJSONString(), userModel);

    }

    @Transactional
    @Override
    public void confirmInvitation(InvitationConfirmModel confirmModel) {

        //判断该邀约是否为自己发布
        InvitationModel invitationModel = findByPrimaryKey(confirmModel.getId());
        if (invitationModel.getUserId() != confirmModel.getUserId()) {
            ErrorCode.throwBusinessException(ErrorCode.INVITATION_PUBLISHER_NOT_MATCH);
        }

        //更新状态
        InvitationModel newInvitationModel = new InvitationModel();
        newInvitationModel.setId(confirmModel.getId());
        newInvitationModel.setStatus(InvitationStatus.FINISHED.getCode());
        updateByPrimaryKeySelective(newInvitationModel);

        //创建订单
        IndentModel indentModel = new IndentModel();
        indentModel.setCustomerId(confirmModel.getUserId());
        indentModel.setSellerId(confirmModel.getReceivedUserId());
        indentModel.setQuantity(1);
        indentModel.setPrice(confirmModel.getMoney());
        indentModel.setMoney(confirmModel.getMoney());
        indentModel.setIndentNo(confirmModel.getIndentNo());
        indentModel.setReferId(confirmModel.getId());
        indentModel.setType(IndentType.INVITATION.getCode());
        indentModel.setStatus(IndentStatus.CONFIRMED.getCode());
        indentModel.setCreateTime(new Date());
        indentModel.setSnapshot(JSON.toJSONString(invitationModel));
        //TODO 完成开始时间
//        indentModel.setStartTime();
        indentService.createSelective(indentModel);

    }

    private Integer getInvitedCount(Long userId) {
        Date startDate = DateUtils.truncate(new Date(), Calendar.DATE);
        Date endDate = DateUtils.addDays(startDate, 1);
        return invitationRepo.getInvitedCount(userId, startDate, endDate);
    }


}
