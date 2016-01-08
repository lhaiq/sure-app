package com.hengsu.sure.invite.controller;

import com.alibaba.fastjson.JSON;
import com.alipay.util.AlipayNotify;
import com.hengsu.sure.ReturnCode;
import com.hengsu.sure.core.ErrorCode;
import com.hengsu.sure.invite.BillNumberBuilder;
import com.hengsu.sure.invite.IndentType;
import com.hengsu.sure.invite.model.*;
import com.hengsu.sure.invite.service.IndentCommentService;
import com.hengsu.sure.invite.service.IndentService;
import com.hengsu.sure.invite.vo.IndentCommentVO;
import com.hengsu.sure.invite.vo.TradeVO;
import com.hkntv.pylon.core.beans.mapping.BeanMapper;
import com.hkntv.pylon.web.rest.ResponseEnvelope;
import com.hkntv.pylon.web.rest.annotation.RestApiController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

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
     * 单个订单
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/invite/indent/{id}", method = RequestMethod.GET)
    public ResponseEntity<ResponseEnvelope<IndentModel>> getIndent(
            @PathVariable Long id) {

        IndentModel indentModel = indentService.findByPrimaryKey(id);
        ResponseEnvelope<IndentModel> responseEnv = new ResponseEnvelope<>(indentModel, true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }

    /**
     * 根据订单号单个订单
     *
     * @param indentNo
     * @return
     */
    @RequestMapping(value = "/invite/indent", method = RequestMethod.GET)
    public ResponseEntity<ResponseEnvelope<IndentModel>> getIndentByIndentNo(
            @RequestParam String  indentNo) {
        IndentModel indentModel = indentService.findByNo(indentNo);
        ResponseEnvelope<IndentModel> responseEnv = new ResponseEnvelope<>(indentModel, true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }

    /**
     * 买家订单
     *
     * @param type
     * @param userId
     * @param pageable
     * @return
     */
    @RequestMapping(value = "/invite/indent/buyers", method = RequestMethod.GET)
    public ResponseEntity<ResponseEnvelope<Page<IndentModel>>> queryBuyerIndents(
            @RequestParam Integer type,
            @Value("#{request.getAttribute('userId')}") Long userId,
            Pageable pageable) {
        IndentModel param = new IndentModel();
        param.setType(type);
        param.setCustomerId(userId);

        Integer count = indentService.selectCount(param);
        List<IndentModel> indentModels = indentService.selectPage(param, pageable);
        Page<IndentModel> page = new PageImpl<>(indentModels, pageable, count);

        ResponseEnvelope<Page<IndentModel>> responseEnv = new ResponseEnvelope<>(page, true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }

    /**
     * 卖家订单
     *
     * @param type
     * @param userId
     * @param pageable
     * @return
     */
    @RequestMapping(value = "/invite/indent/sellers", method = RequestMethod.GET)
    public ResponseEntity<ResponseEnvelope<Page<IndentModel>>> querySellerIndents(
            @RequestParam Integer type,
            @Value("#{request.getAttribute('userId')}") Long userId,
            Pageable pageable) {

        if ((IndentType.RENT.getCode() != type) && (IndentType.INVITATION.getCode() != type)) {
            ErrorCode.throwBusinessException(ErrorCode.INDENT_TYPE_INVALID);
        }
        IndentModel param = new IndentModel();
        param.setType(type);
        param.setSellerId(userId);

        Integer count = indentService.selectCount(param);
        List<IndentModel> indentModels = indentService.selectPage(param, pageable);
        Page<IndentModel> page = new PageImpl<>(indentModels, pageable, count);

        ResponseEnvelope<Page<IndentModel>> responseEnv = new ResponseEnvelope<>(page, true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }


//    /**
//     * 支付宝回写
//     *
//     * @param tradeVO
//     * @return
//     */
//    @RequestMapping(value = "/invite/trade", method = RequestMethod.POST)
//    public ResponseEntity<String> receiveTrade(@RequestBody TradeVO tradeVO) {
//        logger.info(JSON.toJSONString(tradeVO));
//        TradeModel tradeModel = beanMapper.map(tradeVO, TradeModel.class);
//        indentService.receiveTrade(tradeModel);
//        return new ResponseEntity<>(TRADE_SUCSESS_STATUS, HttpStatus.OK);
//    }

    /**
     * 支付宝回写
     *
     * @return
     */
    @RequestMapping(value = "/invite/trade", method = RequestMethod.POST)
    public ResponseEntity<String> receiveTrade(HttpServletRequest request) throws Exception {
        Map<String, String> params = new HashMap<>();
        Map requestParams = request.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }

            params.put(name, valueStr);
        }

        logger.info(JSON.toJSONString(params));

        //获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
        //商户订单号
        String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");

//        支付宝交易号
        String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");

        //seller_id
        String seller_id = new String(request.getParameter("seller_id").getBytes("ISO-8859-1"), "UTF-8");

        //total_fee
        Double total_fee = Double.parseDouble(request.getParameter("seller_id"));

        //交易状态
        String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"), "UTF-8");

        if (AlipayNotify.verify(params)) {//验证成功
            if (trade_status.equals("TRADE_FINISHED")) {
                TradeModel tradeModel = new TradeModel();
                tradeModel.setSellerId(seller_id);
                tradeModel.setTotalFee(total_fee);
                tradeModel.setOutTradeNo(out_trade_no);
                tradeModel.setTradeNo(trade_no);
                indentService.receiveTrade(tradeModel);
                return new ResponseEntity<>(TRADE_SUCSESS_STATUS, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(TRADE_FAILURE_STATUS, HttpStatus.OK);
    }


    /**
     * 准备取消订单
     *
     * @param id
     * @param userId
     * @return
     */
    @RequestMapping(value = "/invite/indent/prepareCancel/{id}", method = RequestMethod.GET)
    public ResponseEntity<ResponseEnvelope<CancelIndentModel>> prepareCancelIndent(
            @PathVariable Long id,
            @Value("#{request.getAttribute('userId')}") Long userId) {
        CancelIndentModel cancelIndentModel = indentService.prepareCancelIdent(id, userId);
        ResponseEnvelope<CancelIndentModel> responseEnv = new ResponseEnvelope<>(cancelIndentModel, true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }

    /**
     * 取消订单
     *
     * @param id
     * @param userId
     * @return
     */
    @RequestMapping(value = "/invite/indent/cancel/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<ResponseEnvelope<CashModel>> cancelIndent(
            @PathVariable Long id,
            @Value("#{request.getAttribute('userId')}") Long userId) {
        CashModel cashModel = indentService.cancelIndent(id, userId);
        ResponseEnvelope<CashModel> responseEnv = new ResponseEnvelope<>(cashModel, true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }

    /**
     * 评论订单
     *
     * @param id
     * @param indentCommentVO
     * @param userId
     * @return
     */
    @RequestMapping(value = "/invite/indent/comment/{id}", method = RequestMethod.POST)
    public ResponseEntity<ResponseEnvelope<String>> commentIndent(
            @PathVariable Long id,
            @RequestBody IndentCommentVO indentCommentVO,
            @Value("#{request.getAttribute('userId')}") Long userId) {
        IndentCommentModel indentCommentModel = beanMapper.map(indentCommentVO, IndentCommentModel.class);
        indentCommentModel.setIndentId(id);
        indentCommentModel.setUserId(userId);
        indentService.commentIndent(indentCommentModel);
        ResponseEnvelope<String> responseEnv = new ResponseEnvelope<>(ReturnCode.OPERATION_SUCCESS, true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }

}
