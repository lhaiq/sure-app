package com.hengsu.sure.invite.controller;

import com.alibaba.fastjson.JSON;
import com.hengsu.sure.ReturnCode;
import com.hengsu.sure.invite.BillNumberBuilder;
import com.hengsu.sure.invite.model.TradeModel;
import com.hengsu.sure.invite.vo.TradeVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.hkntv.pylon.core.beans.mapping.BeanMapper;
import com.hkntv.pylon.web.rest.ResponseEnvelope;
import com.hkntv.pylon.web.rest.annotation.RestApiController;

import com.hengsu.sure.invite.service.IndentService;
import com.hengsu.sure.invite.model.IndentModel;
import com.hengsu.sure.invite.vo.IndentVO;

import java.util.List;

@RestApiController
@RequestMapping("/sure")
public class IndentRestApiController {

    private final Logger logger = LoggerFactory.getLogger(IndentRestApiController.class);

    private static final String TRADE_SUCSESS_STATUS = "success";

    private static final String TRADE_FAILURE_STATUS = "failure";

    private static final String TRADE_FINISHED = "TRADE_FINISHED";

    @Autowired
    private BeanMapper beanMapper;

    @Autowired
    private IndentService indentService;


    /**
     * 申请订单号
     *
     * @return
     */
    @RequestMapping(value = "/invite/indentno", method = RequestMethod.GET)
    public ResponseEntity<ResponseEnvelope<String>> cancelIndent() {
        String indentNo = BillNumberBuilder.nextBillNumber();
        ResponseEnvelope<String> responseEnv = new ResponseEnvelope<>(indentNo, true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }

    /**
     * 订单列表
     *
     * @param type
     * @param pageable
     * @return
     */
    @RequestMapping(value = "/invite/indent", method = RequestMethod.GET)
    public ResponseEntity<ResponseEnvelope<Page<IndentModel>>> queryIndents(
            @RequestParam Integer type,
            Pageable pageable) {
        IndentModel param = new IndentModel();
        param.setType(type);

        Integer count = indentService.selectCount(param);
        List<IndentModel> indentModels = indentService.selectPage(param, pageable);
        Page<IndentModel> page = new PageImpl<>(indentModels, pageable, count);

        ResponseEnvelope<Page<IndentModel>> responseEnv = new ResponseEnvelope<>(page, true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }


    /**
     * 支付宝回写
     *
     * @param tradeVO
     * @return
     */
    @RequestMapping(value = "/invite/trade", method = RequestMethod.POST)
    public ResponseEntity<String> receiveTrade(@RequestBody TradeVO tradeVO) {
        logger.info(JSON.toJSONString(tradeVO));
        TradeModel tradeModel = beanMapper.map(tradeVO, TradeModel.class);
        indentService.receiveTrade(tradeModel);
        return new ResponseEntity<>(TRADE_SUCSESS_STATUS, HttpStatus.OK);
    }

    /**
     * 取消订单
     *
     * @param id
     * @param userId
     * @return
     */
    @RequestMapping(value = "/invite/indent/cancel/{id}", method = RequestMethod.GET)
    public ResponseEntity<ResponseEnvelope<String>> cancelIndent(
            @PathVariable Long id,
            @Value("#{request.getAttribute('userId')}") Long userId) {
        indentService.cancelIndent(id, userId);
        ResponseEnvelope<String> responseEnv = new ResponseEnvelope<>(ReturnCode.OPERATION_SUCCESS, true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }

}
