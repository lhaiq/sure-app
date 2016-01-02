package com.hengsu.sure.invite.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.hengsu.sure.auth.service.UserService;
import com.hengsu.sure.core.ErrorCode;
import com.hengsu.sure.core.service.ConfService;
import com.hengsu.sure.core.service.PushService;
import com.hengsu.sure.invite.*;
import com.hengsu.sure.invite.model.*;
import com.hengsu.sure.invite.service.CashService;
import com.hengsu.sure.invite.service.StatementService;
import com.hengsu.sure.invite.service.TradeService;
import com.hengsu.sure.sns.RelationType;
import com.hengsu.sure.sns.model.RelationModel;
import com.hengsu.sure.sns.service.RelationService;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hengsu.sure.invite.entity.Indent;
import com.hengsu.sure.invite.repository.IndentRepository;
import com.hengsu.sure.invite.service.IndentService;
import com.hkntv.pylon.core.beans.mapping.BeanMapper;

import java.util.Date;
import java.util.List;

@Service
public class IndentServiceImpl implements IndentService {

    private static final String INVITATION_PAYED = "invitation_payed";
    private static final String RENT_PAYED = "rent_payed";

    private static final String TRADE_FINISHED = "TRADE_FINISHED";

    private static final String INDENT_REFUND_PERCENTAGE_LESS1HOUR = "indent.refund.percentage.less1hour";
    private static final String INDENT_REFUND_PERCENTAGE_1TO3HOUR = "indent.refund.percentage.1to3hour";
    private static final String INDENT_REFUND_PERCENTAGE_GREATER3HOUR = "indent.refund.percentage.greater3hour";
    private static final String INVITATION_POUNDAGE = "invitation.poundage";
    private static final String RENT_POUNDAGE = "rent.poundage";

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

    @Transactional
    @Override
    public void receiveTrade(TradeModel tradeModel) {

        //保存记录
        tradeService.createSelective(tradeModel);
        Long tradeId = tradeModel.getId();

        //判断是否交易成功
        if (!TRADE_FINISHED.equals(tradeModel.getTradeStatus())) {
            ErrorCode.throwBusinessException(ErrorCode.TRADE_NOT_SUCCESS);
        }

        IndentModel indentModel = findByNo(tradeModel.getTradeNo());

        //检查交易 金额是否足够
        if (indentModel.getMoney() != tradeModel.getTotalFee()) {
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
        message.put("content", indentModel.getSnapshot());
        pushService.pushMessage(message.toJSONString(),
                userService.findByPrimaryKeyNoPass(indentModel.getSellerId()));

        //添加关系
        if (IndentType.GOODS.getCode() != indentModel.getType()) {
            RelationModel relationModel = new RelationModel();
            relationModel.setFromUser(indentModel.getCustomerId());
            relationModel.setToUser(indentModel.getSellerId());
            relationModel.setType(RelationType.FRIEND.getCode());
            relationService.addRelationIfNotExisted(relationModel);
        }

        //更新状态
        IndentModel param = new IndentModel();
        param.setId(indentModel.getId());
        param.setStatus(IndentStatus.PAYED.getCode());
        param.setApplyTime(new Date());
        param.setTradeId(tradeId);
    }

    @Transactional
    @Override
    public void cancelIndent(Long id, Long userId) {

        //判断是否为自己的订单
        IndentModel indentModel = findByPrimaryKey(id);
        if (userId != indentModel.getCustomerId()) {
            ErrorCode.throwBusinessException(ErrorCode.CANNOT_CANCEL_OTHERS_INDENT);
        }

        //判断订单是否可以取消
        if (IndentStatus.PAYED.getCode() != indentModel.getStatus()) {
            ErrorCode.throwBusinessException(ErrorCode.CANNOT_CANCEL_STATUS_ERROR);
        }

        //更新订单状态
        IndentModel param = new IndentModel();
        param.setId(indentModel.getId());
        param.setStatus(IndentStatus.CANCELING.getCode());
        param.setApplyTime(new Date());
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

        return getCancelIndentModel(indentModel);
    }

    @Override
    public List<IndentModel> selectPage(IndentModel indentModel, Pageable pageable) {
        List<Indent> indents = indentRepo.selectPage(beanMapper.map(indentModel, Indent.class), pageable);
        return beanMapper.mapAsList(indents, IndentModel.class);
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

    @Scheduled(fixedDelay = 5000)
    public void scheduleFinishIndent() {
        List<Indent> indents = indentRepo.selectFinishing(IndentStatus.PAYED.getCode(), new Date());
        for (Indent indent : indents) {
            finishIndent(beanMapper.map(indent, IndentModel.class));
        }

    }

    @Transactional
    public void finishIndent(IndentModel indentModel) {

        //根据订单类型计算扣费金额
        Double money = 0.0;
        if (IndentType.INVITATION.getCode() == indentModel.getType()) {
            money = indentModel.getMoney() - confService.getDouble(INVITATION_POUNDAGE);
        } else if (IndentType.RENT.getCode() == indentModel.getType()) {
            money = indentModel.getMoney() - confService.getDouble(RENT_POUNDAGE);
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
    }

    private CancelIndentModel getCancelIndentModel(IndentModel indentModel) {

        Date applyTime = indentModel.getApplyTime();
        Date now = new Date();
        String percentageStr = null;
        int expireHour = (int) (now.getTime() - applyTime.getTime()) / (1000 * 3600);
        if (expireHour <= 1) {
            percentageStr = confService.selectByPrimaryKey(INDENT_REFUND_PERCENTAGE_LESS1HOUR).getConfValue();
        } else if (expireHour > 3) {
            percentageStr = confService.selectByPrimaryKey(INDENT_REFUND_PERCENTAGE_GREATER3HOUR).getConfValue();
        } else {
            percentageStr = confService.selectByPrimaryKey(INDENT_REFUND_PERCENTAGE_1TO3HOUR).getConfValue();
        }

        CancelIndentModel cancelIndentModel = new CancelIndentModel();
        cancelIndentModel.setExpireHour(expireHour);
        cancelIndentModel.setPoundage(Double.parseDouble(percentageStr));
        cancelIndentModel.setMoney(indentModel.getMoney());
        return cancelIndentModel;
    }

}
