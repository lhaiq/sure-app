package com.hengsu.sure.invite.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hengsu.sure.invite.entity.Statement;
import com.hengsu.sure.invite.repository.StatementRepository;
import com.hengsu.sure.invite.model.StatementModel;
import com.hengsu.sure.invite.service.StatementService;
import com.hkntv.pylon.core.beans.mapping.BeanMapper;

import java.util.List;

@Service
public class StatementServiceImpl implements StatementService {

    @Autowired
    private BeanMapper beanMapper;

    @Autowired
    private StatementRepository statementRepo;

    @Transactional
    @Override
    public int create(StatementModel statementModel) {
        return statementRepo.insert(beanMapper.map(statementModel, Statement.class));
    }

    @Transactional
    @Override
    public int createSelective(StatementModel statementModel) {
        return statementRepo.insertSelective(beanMapper.map(statementModel, Statement.class));
    }

    @Transactional
    @Override
    public int deleteByPrimaryKey(Long id) {
        return statementRepo.deleteByPrimaryKey(id);
    }

    @Transactional(readOnly = true)
    @Override
    public StatementModel findByPrimaryKey(Long id) {
        Statement statement = statementRepo.selectByPrimaryKey(id);
        return beanMapper.map(statement, StatementModel.class);
    }

    @Transactional(readOnly = true)
    @Override
    public int selectCount(StatementModel statementModel) {
        return statementRepo.selectCount(beanMapper.map(statementModel, Statement.class));
    }

    @Override
    public List<StatementModel> selectPage(StatementModel statementModel, Pageable pageable) {
        List<Statement> statements = statementRepo.selectPage(
                beanMapper.map(statementModel, Statement.class), pageable);
        return beanMapper.mapAsList(statements,StatementModel.class);
    }

    @Transactional
    @Override
    public int updateByPrimaryKey(StatementModel statementModel) {
        return statementRepo.updateByPrimaryKey(beanMapper.map(statementModel, Statement.class));
    }

    @Transactional
    @Override
    public int updateByPrimaryKeySelective(StatementModel statementModel) {
        return statementRepo.updateByPrimaryKeySelective(beanMapper.map(statementModel, Statement.class));
    }

}
