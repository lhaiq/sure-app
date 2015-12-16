package com.hengsu.sure.invite.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hkntv.pylon.core.beans.mapping.BeanMapper;
import com.hkntv.pylon.web.rest.ResponseEnvelope;
import com.hkntv.pylon.web.rest.annotation.RestApiController;

import com.hengsu.sure.invite.service.GoodsTypeService;
import com.hengsu.sure.invite.model.GoodsTypeModel;
import com.hengsu.sure.invite.vo.GoodsTypeVO;

import java.util.List;

@RestApiController
@RequestMapping("/sure")
public class GoodsTypeRestApiController {

    private final Logger logger = LoggerFactory.getLogger(GoodsTypeRestApiController.class);

    @Autowired
    private GoodsTypeService goodsTypeService;

    @RequestMapping(value = "/invite/goodsType", method = RequestMethod.GET)
    public ResponseEntity<ResponseEnvelope<Page<GoodsTypeModel>>> listGoodsType(Pageable pageable) {

        GoodsTypeModel param = new GoodsTypeModel();
        List<GoodsTypeModel> goodsTypeModels = goodsTypeService.selectPage(param, pageable);
        Integer count = goodsTypeService.selectCount(param);
        Page<GoodsTypeModel> page = new PageImpl<>(goodsTypeModels, pageable, count);
        ResponseEnvelope<Page<GoodsTypeModel>> responseEnv = new ResponseEnvelope<>(page, true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }


}
