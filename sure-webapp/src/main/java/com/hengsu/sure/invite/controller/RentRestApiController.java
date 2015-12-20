package com.hengsu.sure.invite.controller;

import com.alibaba.fastjson.JSON;
import com.hengsu.sure.ReturnCode;
import com.hengsu.sure.core.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hkntv.pylon.core.beans.mapping.BeanMapper;
import com.hkntv.pylon.web.rest.ResponseEnvelope;
import com.hkntv.pylon.web.rest.annotation.RestApiController;

import com.hengsu.sure.invite.service.RentService;
import com.hengsu.sure.invite.model.RentModel;
import com.hengsu.sure.invite.vo.RentVO;

@RestApiController
@RequestMapping("/sure")
public class RentRestApiController {

    private final Logger logger = LoggerFactory.getLogger(RentRestApiController.class);

    @Autowired
    private BeanMapper beanMapper;

    @Autowired
    private RentService rentService;

    @RequestMapping(value = "/invite/rent/{id}", method = RequestMethod.GET)
    public ResponseEntity<ResponseEnvelope<RentVO>> getRentById(@PathVariable Long id) {
        RentModel rentModel = rentService.findByPrimaryKey(id);
        RentVO rentVO = beanMapper.map(rentModel, RentVO.class);
        ResponseEnvelope<RentVO> responseEnv = new ResponseEnvelope<RentVO>(rentVO);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }

    /**
     * 发布发现邀约
     *
     * @param rentVO
     * @param userId
     * @return
     */
    @RequestMapping(value = "/invite/rent", method = RequestMethod.POST)
    public ResponseEntity<ResponseEnvelope<String>> createRent(
            @RequestBody RentVO rentVO,
            @Value("#{request.getAttribute('userId')}") Long userId) {
        RentModel rentModel = beanMapper.map(rentVO, RentModel.class);
        rentModel.setUserId(userId);

        rentModel.setScene(JSON.toJSONString(rentVO.getScenes()));
        rentModel.setImages(JSON.toJSONString(rentVO.getImages()));
        rentModel.setDate(JSON.toJSONString(rentVO.getDates()));

        rentService.publishRent(rentModel);
        ResponseEnvelope<String> responseEnv = new ResponseEnvelope<>(ReturnCode.OPERATION_SUCCESS, true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }

    @RequestMapping(value = "/invite/rent/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<ResponseEnvelope<Integer>> deleteRentByPrimaryKey(@PathVariable Long id) {
        Integer result = rentService.deleteByPrimaryKey(id);
        ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<Integer>(result);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }

    @RequestMapping(value = "/invite/rent/{id}", method = RequestMethod.PUT)
    public ResponseEntity<ResponseEnvelope<Integer>> updateRentByPrimaryKeySelective(@PathVariable Long id, @RequestBody RentVO rentVO) {
        RentModel rentModel = beanMapper.map(rentVO, RentModel.class);
        rentModel.setId(id);
        Integer result = rentService.updateByPrimaryKeySelective(rentModel);
        ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<Integer>(result);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }

}
