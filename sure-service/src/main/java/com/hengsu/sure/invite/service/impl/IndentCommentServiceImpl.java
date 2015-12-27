package com.hengsu.sure.invite.service.impl;

import com.hengsu.sure.auth.model.UserModel;
import com.hengsu.sure.auth.service.UserService;
import com.hengsu.sure.invite.entity.IndentComment;
import com.hengsu.sure.invite.entity.IndentCommentScore;
import com.hengsu.sure.invite.model.IndentCommentModel;
import com.hengsu.sure.invite.repository.IndentCommentRepository;
import com.hengsu.sure.invite.service.IndentCommentService;
import com.hkntv.pylon.core.beans.mapping.BeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class IndentCommentServiceImpl implements IndentCommentService {

    @Autowired
    private BeanMapper beanMapper;

    @Autowired
    private IndentCommentRepository indentCommentRepo;

    @Autowired
    private UserService userService;

    @Transactional
    @Override
    public int create(IndentCommentModel indentCommentModel) {
        return indentCommentRepo.insert(beanMapper.map(indentCommentModel, IndentComment.class));
    }

    @Transactional
    @Override
    public int createSelective(IndentCommentModel indentCommentModel) {
        return indentCommentRepo.insertSelective(beanMapper.map(indentCommentModel, IndentComment.class));
    }

    @Transactional
    @Override
    public int deleteByPrimaryKey(Long id) {
        return indentCommentRepo.deleteByPrimaryKey(id);
    }

    @Transactional(readOnly = true)
    @Override
    public IndentCommentModel findByPrimaryKey(Long id) {
        IndentComment indentComment = indentCommentRepo.selectByPrimaryKey(id);
        return beanMapper.map(indentComment, IndentCommentModel.class);
    }

    @Transactional(readOnly = true)
    @Override
    public int selectCount(IndentCommentModel indentCommentModel) {
        return indentCommentRepo.selectCount(beanMapper.map(indentCommentModel, IndentComment.class));
    }

    @Transactional
    @Override
    public int updateByPrimaryKey(IndentCommentModel indentCommentModel) {
        return indentCommentRepo.updateByPrimaryKey(beanMapper.map(indentCommentModel, IndentComment.class));
    }

    @Transactional
    @Override
    public int updateByPrimaryKeySelective(IndentCommentModel indentCommentModel) {
        return indentCommentRepo.updateByPrimaryKeySelective(beanMapper.map(indentCommentModel, IndentComment.class));
    }

    /**
     * calculate method 求平均数
     */
    @Scheduled(cron = "0 0 01 * * ? *")
    public void calculateScore() {
        List<IndentCommentScore> indentCommentScores = indentCommentRepo.selectScore();
        for (IndentCommentScore indentCommentScore : indentCommentScores) {
            UserModel param = new UserModel();
            param.setId(indentCommentScore.getUserId());
            param.setFaceScore(indentCommentScore.getFaceScore());
            param.setCredit(indentCommentScore.getCredit());
            userService.updateByPrimaryKeySelective(param);
        }

    }

}
