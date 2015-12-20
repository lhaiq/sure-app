package com.hengsu.sure.sns.controller;

import com.hengsu.sure.ReturnCode;
import com.hengsu.sure.auth.model.UserModel;
import com.hengsu.sure.auth.service.UserService;
import com.hengsu.sure.sns.RelationType;
import com.hengsu.sure.sns.vo.SNSUserVO;
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

import com.hengsu.sure.sns.service.RelationService;
import com.hengsu.sure.sns.model.RelationModel;
import com.hengsu.sure.sns.vo.RelationVO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestApiController
@RequestMapping("/sure")
public class RelationRestApiController {

    private final Logger logger = LoggerFactory.getLogger(RelationRestApiController.class);

    @Autowired
    private BeanMapper beanMapper;

    @Autowired
    private RelationService relationService;

    @Autowired
    private UserService userService;

    /**
     * 关注
     *
     * @param relationVO
     * @param userId
     * @return
     */
    @RequestMapping(value = "/sns/relation", method = RequestMethod.POST)
    public ResponseEntity<ResponseEnvelope<String>> createRelation(
            @RequestBody RelationVO relationVO,
            @Value("#{request.getAttribute('userId')}") Long userId) {
        RelationModel relationModel = beanMapper.map(relationVO, RelationModel.class);
        relationModel.setFromUser(userId);
        relationModel.setType(RelationType.RELATION.getCode());
        relationModel.setTime(new Date());
        relationService.addRelationIfNotExisted(relationModel);
        ResponseEnvelope<String> responseEnv = new ResponseEnvelope<>(ReturnCode.OPERATION_SUCCESS, true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }

    /**
     * 移除粉丝
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/sns/relation/fans/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<ResponseEnvelope<String>> deleteFans(
            @PathVariable Long id,
            @Value("#{request.getAttribute('userId')}") Long userId) {
        relationService.deleteRelation(id, userId, RelationType.RELATION.getCode());
        ResponseEnvelope<String> responseEnv = new ResponseEnvelope<>(ReturnCode.OPERATION_SUCCESS, true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }

    /**
     * 取消关注
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/sns/relation/attention/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<ResponseEnvelope<String>> cancelAttention(
            @PathVariable Long id,
            @Value("#{request.getAttribute('userId')}") Long userId) {
        Integer result = relationService.deleteByPrimaryKey(id);
        relationService.deleteRelation(userId, id, RelationType.RELATION.getCode());
        ResponseEnvelope<String> responseEnv = new ResponseEnvelope<>(ReturnCode.OPERATION_SUCCESS,true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }

    /**
     * 粉丝列表
     *
     * @param userId
     * @param page
     * @param size
     * @return
     */
    @RequestMapping(value = "/sns/relation/fans", method = RequestMethod.GET)
    public ResponseEntity<ResponseEnvelope<Page<SNSUserVO>>> listFans(
            @Value("#{request.getAttribute('userId')}") Long userId,
            @RequestParam Integer page,
            @RequestParam Integer size) {

        RelationModel param = new RelationModel();
        param.setToUser(userId);
        param.setType(RelationType.RELATION.getCode());
        Pageable pageable = new PageRequest(page, size);
        List<RelationModel> relationModels = relationService.listRelations(param, pageable);

        Integer count = relationService.selectCount(param);

        List<SNSUserVO> snsUserVOs = new ArrayList<>();
        for (RelationModel relationModel : relationModels) {
            UserModel userModel = userService.findByPrimaryKey(relationModel.getFromUser());
            snsUserVOs.add(beanMapper.map(userModel, SNSUserVO.class));
        }

        Page<SNSUserVO> pageContent = new PageImpl<SNSUserVO>(snsUserVOs, pageable, count);
        ResponseEnvelope<Page<SNSUserVO>> responseEnv = new ResponseEnvelope<>(pageContent, true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }

    /**
     * 关注列表
     *
     * @param userId
     * @param page
     * @param size
     * @return
     */
    @RequestMapping(value = "/sns/relation/attention", method = RequestMethod.GET)
    public ResponseEntity<ResponseEnvelope<Page<SNSUserVO>>> listAttention(
            @Value("#{request.getAttribute('userId')}") Long userId,
            @RequestParam Integer page,
            @RequestParam Integer size) {

        RelationModel param = new RelationModel();
        param.setFromUser(userId);
        param.setType(RelationType.RELATION.getCode());
        Pageable pageable = new PageRequest(page, size);
        List<RelationModel> relationModels = relationService.listRelations(param, pageable);

        Integer count = relationService.selectCount(param);

        List<SNSUserVO> snsUserVOs = new ArrayList<>();
        for (RelationModel relationModel : relationModels) {
            UserModel userModel = userService.findByPrimaryKey(relationModel.getToUser());
            snsUserVOs.add(beanMapper.map(userModel, SNSUserVO.class));
        }

        Page<SNSUserVO> pageContent = new PageImpl<SNSUserVO>(snsUserVOs, pageable, count);
        ResponseEnvelope<Page<SNSUserVO>> responseEnv = new ResponseEnvelope<>(pageContent, true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }

    /**
     * 朋友列表
     * @param userId
     * @param page
     * @param size
     * @return
     */
    @RequestMapping(value = "/sns/relation/friend", method = RequestMethod.DELETE)
    public ResponseEntity<ResponseEnvelope<Page<SNSUserVO>>> listFriends(
            @Value("#{request.getAttribute('userId')}") Long userId,
            @RequestParam Integer page,
            @RequestParam Integer size) {

        RelationModel param = new RelationModel();
        param.setFromUser(userId);
        param.setType(RelationType.FRIEND.getCode());
        Pageable pageable = new PageRequest(page, size);
        List<RelationModel> relationModels = relationService.listRelations(param, pageable);

        Integer count = relationService.selectCount(param);

        List<SNSUserVO> snsUserVOs = new ArrayList<>();
        for (RelationModel relationModel : relationModels) {
            UserModel userModel = userService.findByPrimaryKey(relationModel.getToUser());
            snsUserVOs.add(beanMapper.map(userModel, SNSUserVO.class));
        }

        Page<SNSUserVO> pageContent = new PageImpl<SNSUserVO>(snsUserVOs, pageable, count);
        ResponseEnvelope<Page<SNSUserVO>> responseEnv = new ResponseEnvelope<>(pageContent, true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }

}
