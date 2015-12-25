package com.hengsu.sure.invite.controller;

import com.hengsu.sure.invite.model.GoodsModel;
import com.hengsu.sure.invite.service.GoodsService;
import com.hengsu.sure.invite.vo.GoodsVO;
import com.hkntv.pylon.core.beans.mapping.BeanMapper;
import com.hkntv.pylon.web.rest.ResponseEnvelope;
import com.hkntv.pylon.web.rest.annotation.RestApiController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RestApiController
@RequestMapping("/sure")
public class GoodsRestApiController {

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
        ResponseEnvelope<GoodsVO> responseEnv = new ResponseEnvelope<>(goodsVO,true);
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
        ResponseEnvelope<Page<GoodsModel>> responseEnv = new ResponseEnvelope<>(pageContent,true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }


}
