package com.hengsu.sure.invite.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.hkntv.pylon.core.beans.mapping.BeanMapper;
import com.hkntv.pylon.web.rest.ResponseEnvelope;
import com.hkntv.pylon.web.rest.annotation.RestApiController;

import com.hengsu.sure.invite.service.GoodsService;
import com.hengsu.sure.invite.model.GoodsModel;
import com.hengsu.sure.invite.vo.GoodsVO;

import java.util.List;

@RestApiController
@RequestMapping("/sure")
public class GoodsRestApiController {

    private final Logger logger = LoggerFactory.getLogger(GoodsRestApiController.class);

    @Autowired
    private BeanMapper beanMapper;

    @Autowired
    private GoodsService goodsService;

    /**
     * 轻奢详情
     * @param id
     * @return
     */
    @RequestMapping(value = "/invite/goods/{id}", method = RequestMethod.GET)
    public ResponseEntity<ResponseEnvelope<GoodsVO>> getGoodsById(@PathVariable Long id) {
        GoodsModel goodsModel = goodsService.findByPrimaryKey(id);
        GoodsVO goodsVO = beanMapper.map(goodsModel, GoodsVO.class);
        ResponseEnvelope<GoodsVO> responseEnv = new ResponseEnvelope<GoodsVO>(goodsVO);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }

    @RequestMapping(value = "/invite/goods", method = RequestMethod.POST)
    public ResponseEntity<ResponseEnvelope<Integer>> createGoods(@RequestBody GoodsVO goodsVO) {
        GoodsModel goodsModel = beanMapper.map(goodsVO, GoodsModel.class);
        Integer result = goodsService.create(goodsModel);
        ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<Integer>(result);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }

    @RequestMapping(value = "/invite/goods/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<ResponseEnvelope<Integer>> deleteGoodsByPrimaryKey(@PathVariable Long id) {
        Integer result = goodsService.deleteByPrimaryKey(id);
        ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<Integer>(result);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }


    /**
     * 轻奢列表
     * @param cityId
     * @param pageable
     * @return
     */
    @RequestMapping(value = "/invite/{typeId}/goods", method = RequestMethod.GET)
    public ResponseEntity<ResponseEnvelope<Page<GoodsModel>>> listGoods(
            @PathVariable Long typeId,
            @RequestParam Integer cityId,
            Pageable pageable) {
        GoodsModel param = new GoodsModel();
        param.setGoodsType(typeId);
        param.setCityId(cityId);
        List<GoodsModel> goodsModes = goodsService.selectPage(param, pageable);
        Integer count = goodsService.selectCount(param);
        Page<GoodsModel> pageContent = new PageImpl(goodsModes, pageable, count);
        ResponseEnvelope<Page<GoodsModel>> responseEnv = new ResponseEnvelope<>(pageContent);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }


}
