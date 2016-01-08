package com.hengsu.sure.invite.controller;

import com.alibaba.fastjson.JSON;
import com.hengsu.sure.ReturnCode;
import com.hengsu.sure.invite.model.InvitationConfirmModel;
import com.hengsu.sure.invite.model.InvitationModel;
import com.hengsu.sure.invite.model.InvitationPriceModel;
import com.hengsu.sure.invite.model.InvitationResultModel;
import com.hengsu.sure.invite.service.InvitationService;
import com.hengsu.sure.invite.vo.InvitationConfirmVO;
import com.hengsu.sure.invite.vo.InvitationVO;
import com.hkntv.pylon.core.beans.mapping.BeanMapper;
import com.hkntv.pylon.web.rest.ResponseEnvelope;
import com.hkntv.pylon.web.rest.annotation.RestApiController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;

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
    public ResponseEntity<ResponseEnvelope<InvitationResultModel>> createInvitation(
            @Valid @RequestBody InvitationVO invitationVO,
            @Value("#{request.getAttribute('userId')}") Long userId) {
        InvitationModel invitationModel = beanMapper.map(invitationVO, InvitationModel.class);
        invitationModel.setScene(JSON.toJSONString(invitationVO.getScenes()));
        invitationModel.setUserId(userId);
        InvitationResultModel response = invitationService.publishInvitation(invitationModel);
        ResponseEnvelope<InvitationResultModel> responseEnv = new ResponseEnvelope<>(response, true);
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
     * 查询各时间段价格
     *
     * @return
     */
    @RequestMapping(value = "/invite/invitation/price", method = RequestMethod.GET)
    public ResponseEntity<ResponseEnvelope<List<InvitationPriceModel>>> getPrice() {
        List<InvitationPriceModel> invitationPrices = invitationService.queryPrice();
        ResponseEnvelope<List<InvitationPriceModel>> responseEnv = new ResponseEnvelope<>(invitationPrices, true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }

    /**
     * 确认邀约
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/invite/invitation/confirm/{id}", method = RequestMethod.POST)
    public ResponseEntity<ResponseEnvelope<String>> confirmInvitation(
            @PathVariable Long id,
            @Valid@RequestBody InvitationConfirmVO confirmVO,
            @Value("#{request.getAttribute('userId')}") Long userId) {
        InvitationConfirmModel confirmModel = beanMapper.map(confirmVO, InvitationConfirmModel.class);
        confirmModel.setId(id);
        confirmModel.setUserId(userId);
        invitationService.confirmInvitation(confirmModel);
        ResponseEnvelope<String> responseEnv = new ResponseEnvelope<>(ReturnCode.OPERATION_SUCCESS, true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }


}
