package com.hengsu.sure.invite.controller;

import com.hengsu.sure.ReturnCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.hkntv.pylon.core.beans.mapping.BeanMapper;
import com.hkntv.pylon.web.rest.ResponseEnvelope;
import com.hkntv.pylon.web.rest.annotation.RestApiController;

import com.hengsu.sure.invite.service.InvitationService;
import com.hengsu.sure.invite.model.InvitationModel;
import com.hengsu.sure.invite.vo.InvitationVO;

@RestApiController
@RequestMapping("/sure")
public class InvitationRestApiController {

    private final Logger logger = LoggerFactory.getLogger(InvitationRestApiController.class);

    @Autowired
    private BeanMapper beanMapper;

    @Autowired
    private InvitationService invitationService;


    /**
     * 发布邀约
     *
     * @param invitationVO
     * @return
     */
    @RequestMapping(value = "/invite/invitation", method = RequestMethod.POST)
    public ResponseEntity<ResponseEnvelope<String>> createInvitation(
            @RequestBody InvitationVO invitationVO,
            @Value("#{request.getAttribute('userId')}") Long userId) {
        InvitationModel invitationModel = beanMapper.map(invitationVO, InvitationModel.class);
        invitationModel.setUserId(userId);
        invitationService.publishInvitation(invitationModel);
        ResponseEnvelope<String> responseEnv = new ResponseEnvelope<>(ReturnCode.OPERATION_SUCCESS, true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }

    /**
     * 接受邀约
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/invite/invitation/receive/{id}", method = RequestMethod.GET)
    public ResponseEntity<ResponseEnvelope<String>> receiveInvitation(
            @PathVariable Long id,
            @Value("#{request.getAttribute('userId')}") Long userId) {
        invitationService.receiveInvitation(id, userId);
        ResponseEnvelope<String> responseEnv = new ResponseEnvelope<>(ReturnCode.OPERATION_SUCCESS, true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }

    /**
     * 确定邀约
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/invite/invitation/confirm/{id}", method = RequestMethod.GET)
    public ResponseEntity<ResponseEnvelope<String>> confirmInvitation(
            @PathVariable Long id,
            @Value("#{request.getAttribute('userId')}") Long userId,
            @RequestParam Long receivedUserId) {
        invitationService.confirmInvitation(id, userId, receivedUserId);
        ResponseEnvelope<String> responseEnv = new ResponseEnvelope<>(ReturnCode.OPERATION_SUCCESS, true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }


}
