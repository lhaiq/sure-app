package com.hengsu.sure.invite.controller;

import com.alibaba.fastjson.JSON;
import com.hengsu.sure.ReturnCode;
import com.hengsu.sure.core.Constants;
import com.hengsu.sure.invite.model.QueryRentModel;
import com.hengsu.sure.invite.model.QueryRentParamModel;
import com.hengsu.sure.invite.model.RentConfirmModel;
import com.hengsu.sure.invite.vo.QueryRentVO;
import com.hengsu.sure.invite.vo.RentConfirmVO;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@RestApiController
@RequestMapping("/sure")
public class RentRestApiController {

    private final Logger logger = LoggerFactory.getLogger(RentRestApiController.class);

    @Autowired
    private BeanMapper beanMapper;

    @Autowired
    private RentService rentService;

    /**
     * 单个发现邀约
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/invite/rent/{id}", method = RequestMethod.GET)
    public ResponseEntity<ResponseEnvelope<RentVO>> getRentById(@PathVariable Long id) {
        RentModel rentModel = rentService.findByPrimaryKey(id);
        RentVO rentVO = beanMapper.map(rentModel, RentVO.class);
        if (!StringUtils.isEmpty(rentModel.getImages())) {
            rentVO.setImageIds(JSON.parseArray(rentModel.getImages(), String.class));
        }
        rentVO.setScenes(JSON.parseArray(rentModel.getScene(), String.class));
        ResponseEnvelope<RentVO> responseEnv = new ResponseEnvelope<>(rentVO, true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }

    /**
     * 筛选发现邀约
     *
     * @param queryRentVO
     * @param pageable
     * @return
     */
    @RequestMapping(value = "/invite/queryRent", method = RequestMethod.POST)
    public ResponseEntity<ResponseEnvelope<Page<QueryRentModel>>> queryRent(@Valid @RequestBody QueryRentVO queryRentVO,
                                                                            Pageable pageable) {
        QueryRentParamModel queryRentParamModel = beanMapper.map(queryRentVO, QueryRentParamModel.class);
        List<QueryRentModel> queryRentModels = rentService.queryRent(queryRentParamModel, pageable);
        Integer count = rentService.queryRentCount(queryRentParamModel);
        Page<QueryRentModel> content = new PageImpl<>(queryRentModels, pageable, count);
        ResponseEnvelope<Page<QueryRentModel>> responseEnv = new ResponseEnvelope<>(content, true);
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
            @Valid @RequestBody RentVO rentVO,
            @Value("#{request.getAttribute('userId')}") Long userId) {
        RentModel rentModel = beanMapper.map(rentVO, RentModel.class);
        rentModel.setUserId(userId);

        rentModel.setScene(JSON.toJSONString(rentVO.getScenes()));
        if (CollectionUtils.isNotEmpty(rentVO.getImageIds())) {
            rentModel.setImages(JSON.toJSONString(rentVO.getImageIds()));
        }

        rentService.publishRent(rentModel);
        ResponseEnvelope<String> responseEnv = new ResponseEnvelope<>(ReturnCode.OPERATION_SUCCESS, true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }

    /**
     * 确认邀约
     *
     * @param id
     * @param rentConfirmVO
     * @param userId
     * @return
     */
    @RequestMapping(value = "/invite/rent/confirm/{id}", method = RequestMethod.POST)
    public ResponseEntity<ResponseEnvelope<String>> confirmRent(
            @PathVariable Long id,
            @RequestBody RentConfirmVO rentConfirmVO,
            @Value("#{request.getAttribute('userId')}") Long userId) {

        RentConfirmModel rentConfirmModel = beanMapper.map(rentConfirmVO, RentConfirmModel.class);
        rentConfirmModel.setUserId(userId);
        rentConfirmModel.setId(id);

        rentService.confirmRent(rentConfirmModel);
        ResponseEnvelope<String> responseEnv = new ResponseEnvelope<>(ReturnCode.OPERATION_SUCCESS, true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }

}
