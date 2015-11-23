package com.hengsu.sure.sns.service.impl;

import com.hengsu.sure.core.ErrorCode;
import com.hengsu.sure.sns.RelationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hengsu.sure.sns.entity.Relation;
import com.hengsu.sure.sns.repository.RelationRepository;
import com.hengsu.sure.sns.model.RelationModel;
import com.hengsu.sure.sns.service.RelationService;
import com.hkntv.pylon.core.beans.mapping.BeanMapper;

import java.util.List;

@Service
public class RelationServiceImpl implements RelationService {

    @Autowired
    private BeanMapper beanMapper;

    @Autowired
    private RelationRepository relationRepo;

    @Transactional
    @Override
    public int create(RelationModel relationModel) {
        return relationRepo.insert(beanMapper.map(relationModel, Relation.class));
    }

    @Transactional
    @Override
    public int createSelective(RelationModel relationModel) {

        //检查fromUser是否等于toUser
        if (relationModel.getFromUser() == relationModel.getToUser()) {
            ErrorCode.throwBusinessException(ErrorCode.RELATION_ERROR);
        }

        //先检查该关系是否已经存在
        RelationModel param = new RelationModel();
        param.setFromUser(relationModel.getFromUser());
        param.setToUser(relationModel.getToUser());
        param.setType(relationModel.getType());
        Integer count = relationRepo.selectCount(beanMapper.map(param, Relation.class));
        if (count > 0) {
            ErrorCode.throwBusinessException(ErrorCode.RELATION_EXISTED);
        }

        return relationRepo.insertSelective(beanMapper.map(relationModel, Relation.class));
    }

    @Transactional
    @Override
    public int deleteByPrimaryKey(Long id) {
        return relationRepo.deleteByPrimaryKey(id);
    }

    @Transactional(readOnly = true)
    @Override
    public RelationModel findByPrimaryKey(Long id) {
        Relation relation = relationRepo.selectByPrimaryKey(id);
        return beanMapper.map(relation, RelationModel.class);
    }

    @Transactional(readOnly = true)
    @Override
    public int selectCount(RelationModel relationModel) {
        return relationRepo.selectCount(beanMapper.map(relationModel, Relation.class));
    }

    @Transactional
    @Override
    public void addRelation(RelationModel relationModel) {
        int type = relationModel.getType();

        if (RelationType.RELATION.getCode() == type) {
            createSelective(relationModel);
        } else {
            //好友关系创建双向
            createSelective(relationModel);
            Long fromUser = relationModel.getFromUser();
            relationModel.setFromUser(relationModel.getToUser());
            relationModel.setToUser(fromUser);
            createSelective(relationModel);
        }

    }

    @Transactional
    @Override
    public void deleteRelation(Long fromUser, Long toUser, Integer type) {
        if (type == RelationType.RELATION.getCode()) {
            Relation relation = new Relation();
            relation.setFromUser(fromUser);
            relation.setToUser(toUser);
            relation.setType(type);
            relationRepo.deleteRelation(relation);
        } else {
            //双向删除
            Relation relation = new Relation();
            relation.setFromUser(fromUser);
            relation.setToUser(toUser);
            relation.setType(type);
            relationRepo.deleteRelation(relation);
            relation.setFromUser(toUser);
            relation.setToUser(fromUser);
            relationRepo.deleteRelation(relation);

        }

    }

    @Override
    public List<RelationModel> listRelations(RelationModel relationModel, Pageable pageable) {
        List<Relation> relations = relationRepo.selectPage(
                beanMapper.map(relationModel, Relation.class), pageable);
        return beanMapper.mapAsList(relations, RelationModel.class);
    }

    @Transactional
    @Override
    public int updateByPrimaryKey(RelationModel relationModel) {
        return relationRepo.updateByPrimaryKey(beanMapper.map(relationModel, Relation.class));
    }

    @Transactional
    @Override
    public int updateByPrimaryKeySelective(RelationModel relationModel) {
        return relationRepo.updateByPrimaryKeySelective(beanMapper.map(relationModel, Relation.class));
    }

}
