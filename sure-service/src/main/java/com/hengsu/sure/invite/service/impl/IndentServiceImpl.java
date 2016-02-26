package com.hengsu.sure.invite.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.hengsu.sure.auth.service.UserService;
import com.hengsu.sure.core.Constants;
import com.hengsu.sure.core.ErrorCode;
import com.hengsu.sure.core.service.ConfService;
import com.hengsu.sure.core.service.PushService;
import com.hengsu.sure.invite.*;
import com.hengsu.sure.invite.entity.Indent;
import com.hengsu.sure.invite.model.*;
import com.hengsu.sure.invite.repository.IndentRepository;
import com.hengsu.sure.invite.service.*;
import com.hengsu.sure.sns.RelationType;
import com.hengsu.sure.sns.model.RelationModel;
import com.hengsu.sure.sns.service.RelationService;
import com.hkntv.pylon.core.beans.mapping.BeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class IndentServiceImpl implements IndentService {

    private final Logger logger = LoggerFactory.getLogger(IndentServiceImpl.class);

    @Value("${sure.seller.account}")
    private String sellerAccount;


    private static final String INVITATION_PAYED = "invitation_payed";
    private static final String RENT_PAYED = "rent_payed";

    private static final String TRADE_FINISHED = "TRADE_FINISHED";


    @Autowired
    private BeanMapper beanMapper;

    @Autowired
    private IndentRepository indentRepo;

    @Autowired
    private TradeService tradeService;

    @Autowired
    private PushService pushService;

    @Autowired
    private UserService userService;

    @Autowired
    private RelationService relationService;

    @Autowired
    private CashService cashService;

    @Autowired
    private ConfService confService;

    @Autowired
    private StatementService statementService;

    @Autowired
    private IndentCommentService indentCommentService;


    @Transactional
    @Override
    public int create(IndentModel indentModel) {
        return indentRepo.insert(beanMapper.map(indentModel, Indent.class));
    }

    @Transactional
    @Override
    public int createSelective(IndentModel indentModel) {
        return indentRepo.insertSelective(beanMapper.map(indentModel, Indent.class));
    }

    @Transactional
    @Override
    public int deleteByPrimaryKey(Long id) {
        return indentRepo.deleteByPrimaryKey(id);
    }

    @Transactional(readOnly = true)
    @Override
    public IndentModel findByPrimaryKey(Long id) {
        Indent indent = indentRepo.selectByPrimaryKey(id);
        return beanMapper.map(indent, IndentModel.class);
    }

    @Override
    public IndentModel findByNo(String no) {
        Indent indent = indentRepo.selectByNO(no);
        return beanMapper.map(indent, IndentModel.class);
    }

    @Transactional(readOnly = true)
    @Override
    public int selectCount(IndentModel indentModel) {
        return indentRepo.selectCount(beanMapper.map(indentModel, Indent.class));
    }

    @Override
    public int selectIndentCount(IndentModel indentModel) {
        return indentRepo.selectIndentCount(beanMapper.map(indentModel, Indent.class));
    }

    @Transactional
    @Override
    public void receiveTrade(TradeModel tradeModel) {

        //保存记录
        tradeService.createSelective(tradeModel);
        Long tradeId = tradeModel.getId();

        IndentModel indentModel = findByNo(tradeModel.getOutTradeNo());

        //判断状态是否正确
        if (IndentStatus.CONFIRMED.getCode() != indentModel.getStatus()) {
            logger.info("error status: indent no: {}, indent status: {}",
                    indentModel.getIndentNo(), indentModel.getStatus());
            ErrorCode.throwBusinessException(ErrorCode.INVITATION_STATUS_ERROR);
        }

        //检查卖家账号
        if (!sellerAccount.equals(tradeModel.getSellerEmail())) {
            ErrorCode.throwBusinessException(ErrorCode.SELLER_ERROR);
        }

        //检查交易 金额是否足够
        if (indentModel.getMoney().doubleValue() != tradeModel.getTotalFee().doubleValue()) {
            ErrorCode.throwBusinessException(ErrorCode.AMOUNT_ERROR);
        }

        //推送
        JSONObject message = new JSONObject();
        Integer type = indentModel.getType();
        if (IndentType.INVITATION.getCode() == type) {
            message.put("pushId", INVITATION_PAYED);
        } else if (IndentType.RENT.getCode() == type) {
            message.put("pushId", RENT_PAYED);
        }


        //添加关系
        if (IndentType.GOODS.getCode() != indentModel.getType()) {
            RelationModel relationModel = new RelationModel();
            relationModel.setFromUser(indentModel.getCustomerId());
            relationModel.setToUser(indentModel.getSellerId());
            relationModel.setType(RelationType.FRIEND.getCode());
            relationService.addRelationIfNotExisted(relationModel);
            message.put("indent", indentModel);
            pushService.pushMessage(message.toJSONString(),
                    userService.findByPrimaryKeyNoPass(indentModel.getSellerId()));
        }

        //更新状态
        IndentModel param = new IndentModel();
        param.setId(indentModel.getId());
        param.setStatus(IndentStatus.PAYED.getCode());
        param.setApplyTime(new Date());
        param.setTradeId(tradeId);
        updateByPrimaryKeySelective(param);
    }

    @Transactional
    @Override
    public CashModel cancelIndent(Long id, Long userId) {

        //判断是否为自己的订单
        IndentModel indentModel = findByPrimaryKey(id);
        if (userId != indentModel.getCustomerId()) {
            ErrorCode.throwBusinessException(ErrorCode.CANNOT_CANCEL_OTHERS_INDENT);
        }

        //判断订单是否可以取消
        if (IndentStatus.PAYED.getCode() != indentModel.getStatus()) {
            ErrorCode.throwBusinessException(ErrorCode.CANNOT_CANCEL_STATUS_ERROR);
        }

        //判断订单是否开始,只对即时邀约 发现邀约有效
        if ((IndentType.GOODS.getCode() != indentModel.getType())
                && new Date().after(indentModel.getStartTime())) {
            ErrorCode.throwBusinessException(ErrorCode.INDENT_HAS_START);
        }

        //更新订单状态
        IndentModel param = new IndentModel();
        param.setId(indentModel.getId());
        param.setStatus(IndentStatus.CANCELING.getCode());
        updateByPrimaryKeySelective(param);

        //退款
        CancelIndentModel cancelIndentModel = getCancelIndentModel(indentModel);
        CashModel cashModel = new CashModel();
        cashModel.setUserId(userId);
        cashModel.setCreateTime(new Date());
        cashModel.setExpireHour(cancelIndentModel.getExpireHour());
        cashModel.setIndentId(id);
        cashModel.setMoney(cancelIndentModel.getMoney());
        cashModel.setPoundage(cancelIndentModel.getPoundage());
        cashModel.setRate(cancelIndentModel.getRate());
        cashModel.setStatus(CashStatus.APPLYING.getCode());
        cashModel.setType(CashType.REFUND.getCode());
        cashService.createSelective(cashModel);

        return cashModel;
    }

    @Override
    public CancelIndentModel prepareCancelIdent(Long id, Long userId) {

        //判断是否为自己的订单
        IndentModel indentModel = findByPrimaryKey(id);
        if (userId != indentModel.getCustomerId()) {
            ErrorCode.throwBusinessException(ErrorCode.CANNOT_CANCEL_OTHERS_INDENT);
        }

        //判断订单是否可以取消
        if (IndentStatus.PAYED.getCode() != indentModel.getStatus()) {
            ErrorCode.throwBusinessException(ErrorCode.CANNOT_CANCEL_STATUS_ERROR);
        }

        //判断订单是否开始,只对即时邀约 发现邀约有效
        if ((IndentType.GOODS.getCode() != indentModel.getType())
                && new Date().after(indentModel.getStartTime())) {
            ErrorCode.throwBusinessException(ErrorCode.INDENT_HAS_START);
        }

        return getCancelIndentModel(indentModel);
    }

    @Override
    public List<IndentModel> selectPage(IndentModel indentModel, Pageable pageable) {
        List<Indent> indents = indentRepo.selectPage(beanMapper.map(indentModel, Indent.class), pageable);
        return beanMapper.mapAsList(indents, IndentModel.class);
    }

    @Override
    public List<IndentModel> selectIndent(IndentModel indentModel, Pageable pageable) {
        List<Indent> indents = indentRepo.selectIndent(beanMapper.map(indentModel, Indent.class), pageable);
        return beanMapper.mapAsList(indents, IndentModel.class);
    }

    @Transactional
    @Override
    public void commentIndent(IndentCommentModel indentCommentModel) {

        //只有买家才可以评论
        IndentModel indentModel = findByPrimaryKey(indentCommentModel.getIndentId());
        if (indentCommentModel.getUserId().longValue() != indentModel.getCustomerId().longValue()) {
            ErrorCode.throwBusinessException(ErrorCode.CANNOT_COMMENT_INDENT);
        }

        //判断订单是否可以取消
        if (IndentStatus.FINISHED.getCode() != indentModel.getStatus()) {
            ErrorCode.throwBusinessException(ErrorCode.CANNOT_COMMENT_INDENT_AS_STATUS);
        }

        //检查是否已经评论过
        IndentCommentModel param = new IndentCommentModel();
        param.setIndentId(indentCommentModel.getIndentId());
        int count = indentCommentService.selectCount(param);
        if (count > 0) {
            ErrorCode.throwBusinessException(ErrorCode.HAVE_COMMENTED);
        }

        indentCommentModel.setCreateTime(new Date());
        indentCommentService.createSelective(indentCommentModel);

    }

    @Transactional
    @Override
    public int updateByPrimaryKey(IndentModel indentModel) {
        return indentRepo.updateByPrimaryKey(beanMapper.map(indentModel, Indent.class));
    }

    @Transactional
    @Override
    public int updateByPrimaryKeySelective(IndentModel indentModel) {
        return indentRepo.updateByPrimaryKeySelective(beanMapper.map(indentModel, Indent.class));
    }

    public void scheduleFinishIndent() {
        List<Indent> indents = indentRepo.selectFinishing(IndentStatus.STARTING.getCode(), new Date());
        for (Indent indent : indents) {
            finishIndent(beanMapper.map(indent, IndentModel.class));
            logger.info("the indent finished, the indent no:{}", indent.getIndentNo());
        }

    }

    public void scheduleStartIndent() {
        List<Indent> indents = indentRepo.selectStarting(IndentStatus.PAYED.getCode(), new Date());
        for (Indent indent : indents) {
            startIndent(beanMapper.map(indent, IndentModel.class));
            logger.info("the indent started, the indent no:{}", indent.getIndentNo());
        }

    }

    @Transactional
    public void startIndent(IndentModel indentModel) {

        try {
            //更新状态
            IndentModel param = new IndentModel();
            param.setId(indentModel.getId());
            param.setStatus(IndentStatus.STARTING.getCode());
            updateByPrimaryKeySelective(param);
        } catch (Exception e) {
            logger.info("unexpected error", e);
        }
    }

    @Transactional
    public void finishIndent(IndentModel indentModel) {

        try {
            //根据订单类型计算扣费金额
            Double money = 0.0;
            if (IndentType.INVITATION.getCode() == indentModel.getType()) {
                money = indentModel.getMoney() - confService.getDouble(Constants.INVITATION_POUNDAGE);
            } else if (IndentType.RENT.getCode() == indentModel.getType()) {
                money = indentModel.getMoney() - indentModel.getMoney() * confService.getDouble(Constants.RENT_PERCENTAGE)/100;
            }

            //创建流水
            Date now = new Date();
            StatementModel statementModel = new StatementModel();
            statementModel.setName("收入");
            statementModel.setTime(now);
            statementModel.setType(StatementType.INCOME.getCode());
            statementModel.setUserId(indentModel.getSellerId());
            statementModel.setMoney(money);
            statementService.createSelective(statementModel);

            //为卖家账户加钱
            userService.addBalance(indentModel.getSellerId(), money);

            //更新状态
            IndentModel param = new IndentModel();
            param.setId(indentModel.getId());
            param.setFinishTime(now);
            param.setStatus(IndentStatus.FINISHED.getCode());
            updateByPrimaryKeySelective(param);
        } catch (Exception e) {
            logger.info("unexpected error", e);
        }
    }


    private CancelIndentModel getCancelIndentModel(IndentModel indentModel) {

        Date applyTime = indentModel.getApplyTime();
        Date now = new Date();

        int expireHour = (int) (now.getTime() - applyTime.getTime()) / (1000 * 3600);

        //轻奢不扣除任何费用
        if (IndentType.GOODS.getCode() == indentModel.getType()) {
            CancelIndentModel cancelIndentModel = new CancelIndentModel();
            cancelIndentModel.setExpireHour(expireHour);
            cancelIndentModel.setRate(0d);
            cancelIndentModel.setPoundage(0d);
            cancelIndentModel.setMoney(indentModel.getMoney());
            return cancelIndentModel;
        }

        String percentageStr = null;
        if (expireHour <= 1) {
            percentageStr = confService.selectByPrimaryKey(Constants.INDENT_REFUND_PERCENTAGE_LESS1HOUR).getConfValue();
        } else if (expireHour > 3) {
            percentageStr = confService.selectByPrimaryKey(Constants.INDENT_REFUND_PERCENTAGE_GREATER3HOUR).getConfValue();
        } else {
            percentageStr = confService.selectByPrimaryKey(Constants.INDENT_REFUND_PERCENTAGE_1TO3HOUR).getConfValue();
        }

        double percentage = Double.parseDouble(percentageStr);

        CancelIndentModel cancelIndentModel = new CancelIndentModel();
        cancelIndentModel.setExpireHour(expireHour);
        cancelIndentModel.setRate(percentage);
        cancelIndentModel.setPoundage(indentModel.getMoney() * (percentage / 100));
        cancelIndentModel.setMoney(indentModel.getMoney());
        return cancelIndentModel;
    }

}
