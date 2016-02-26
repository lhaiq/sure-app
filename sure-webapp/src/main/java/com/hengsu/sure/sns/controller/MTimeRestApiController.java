package com.hengsu.sure.sns.controller;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.hengsu.sure.auth.model.UserModel;
import com.hengsu.sure.auth.service.UserService;
import com.hengsu.sure.sns.CommentType;
import com.hengsu.sure.sns.RelationType;
import com.hengsu.sure.sns.model.CommentModel;
import com.hengsu.sure.sns.model.MTimeModel;
import com.hengsu.sure.sns.model.RelationModel;
import com.hengsu.sure.sns.service.CommentService;
import com.hengsu.sure.sns.service.MTimeService;
import com.hengsu.sure.sns.service.RelationService;
import com.hengsu.sure.sns.vo.ListMTimesVO;
import com.hengsu.sure.sns.vo.MTimeVO;
import com.hengsu.sure.sns.vo.SNSUserVO;
import com.hkntv.pylon.core.beans.mapping.BeanMapper;
import com.hkntv.pylon.web.rest.ResponseEnvelope;
import com.hkntv.pylon.web.rest.annotation.RestApiController;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;


@RestApiController
@RequestMapping("/sure")
public class MTimeRestApiController {

    private final Logger logger = LoggerFactory.getLogger(MTimeRestApiController.class);

    @Autowired
    private BeanMapper beanMapper;

    @Autowired
    private MTimeService mTimeService;

    @Autowired
    private UserService userService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private RelationService relationService;


    /**
     * 获取单个时光
     *
     * @param id
     * @param userId
     * @return
     */
    @RequestMapping(value = "/sns/mtime/{id}", method = RequestMethod.GET)
    public ResponseEntity<ResponseEnvelope<ListMTimesVO>> getMTimeById(
            @PathVariable Long id,
            @Value("#{request.getAttribute('userId')}") Long userId) {
        MTimeModel mTimeModel = mTimeService.findByPrimaryKey(id);
        ListMTimesVO listMTimesVO = beanMapper.map(mTimeModel, ListMTimesVO.class);
        if (!StringUtils.isEmpty(mTimeModel.getImages())) {
            listMTimesVO.setImageIds(JSON.parseArray(mTimeModel.getImages(), String.class));
        }
        SNSUserVO snsUserVO = getSNSUser( mTimeModel.getUserId());
        listMTimesVO.setUser(snsUserVO);
        //检查本人是否点赞
        listMTimesVO.setIsStatus(checkIsStatus(userId, mTimeModel.getId()));

        //检查本人是否关注
        listMTimesVO.setIsAttention(checkIsStatus(userId, mTimeModel.getUserId()));

        ResponseEnvelope<ListMTimesVO> responseEnv = new ResponseEnvelope<>(listMTimesVO, true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }

    /**
     * 添加时光
     *
     * @param mTimeVO
     * @return
     */
    @RequestMapping(value = "/sns/mtime", method = RequestMethod.POST)
    public ResponseEntity<ResponseEnvelope<Integer>> createMTime(
            @Valid @RequestBody MTimeVO mTimeVO,
            @Value("#{request.getAttribute('userId')}") Long userId) {
        MTimeModel mTimeModel = beanMapper.map(mTimeVO, MTimeModel.class);
        mTimeModel.setUserId(userId);

        //设置图片
        if (CollectionUtils.isNotEmpty(mTimeVO.getImageIds())) {
            mTimeModel.setImages(JSON.toJSONString(mTimeVO.getImageIds()));
        }

        mTimeModel.setTime(new Date());
        Integer result = mTimeService.createSelective(mTimeModel);
        ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<Integer>(result, true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }

    /**
     * 删除时光
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/sns/mtime/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<ResponseEnvelope<Long>> deleteMTimeByPrimaryKey(
            @PathVariable Long id,
            @Value("#{request.getAttribute('userId')}") Long userId) {
        mTimeService.deleteMTime(id, userId);
        ResponseEnvelope<Long> responseEnv = new ResponseEnvelope<>(id, true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }

    /**
     * 时光列表
     *
     * @param cityId
     * @return
     */
    @RequestMapping(value = "/sns/mtime", method = RequestMethod.GET)
    public ResponseEntity<ResponseEnvelope<Page<ListMTimesVO>>> listMTime(
            @RequestParam(required = true) Integer cityId,
            @Value("#{request.getAttribute('userId')}") Long userId,
            @RequestParam Integer page,
            @RequestParam Integer size) {

        Pageable pageable = new PageRequest(page, size, Sort.Direction.DESC, "time");
        MTimeModel param = new MTimeModel();
        param.setCityId(cityId);
        List<MTimeModel> mTimeModels = mTimeService.listMTimeModels(param, pageable);
        Integer count = mTimeService.selectCount(param);

        //SNS user列表
        Map<Long,SNSUserVO> snsUsers = new HashMap<>();

        //添加User
        List<ListMTimesVO> listMTimesVOs = new ArrayList<>();
        for (MTimeModel mTimeModel : mTimeModels) {
            ListMTimesVO listMTimesVO = beanMapper.map(mTimeModel, ListMTimesVO.class);
            if (!StringUtils.isEmpty(mTimeModel.getImages())) {
                listMTimesVO.setImageIds(JSON.parseArray(mTimeModel.getImages(), String.class));
            }
            //SNS User
            SNSUserVO snsUserVO = snsUsers.get(mTimeModel.getUserId());
            if(null==snsUserVO){
                snsUserVO=getSNSUser(mTimeModel.getUserId());
                snsUsers.put(mTimeModel.getUserId(),snsUserVO);
            }
            listMTimesVO.setUser(snsUserVO);

            //点赞评论数
//            setCommentAndStatueCount(listMTimesVO);

            //检查本人是否点赞
            listMTimesVO.setIsStatus(checkIsStatus(userId, mTimeModel.getId()));

            //检查本人是否关注
            listMTimesVO.setIsAttention(checkIsAttention(userId, mTimeModel.getUserId()));

            listMTimesVOs.add(listMTimesVO);
        }


        Page<ListMTimesVO> pageContent = new PageImpl<>(listMTimesVOs, pageable, count);
        ResponseEnvelope<Page<ListMTimesVO>> responseEnv = new ResponseEnvelope<>(pageContent, true);

        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }

    private SNSUserVO getSNSUser(Long userId) {
        UserModel userModel = userService.findByPrimaryKey(userId);
        SNSUserVO snsUserVO = beanMapper.map(userModel, SNSUserVO.class);
        return snsUserVO;
    }

    private boolean checkIsStatus(Long userId, Long mid) {
        CommentModel param = new CommentModel();
        param.setmId(mid);
        param.setUserid(userId);
        param.setType(CommentType.STATUSES.getCode());
        int count = commentService.selectCount(param);
        if (count > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkIsAttention(Long fromUserId, Long toUserId) {
        RelationModel param = new RelationModel();
        param.setFromUser(fromUserId);
        param.setToUser(toUserId);
        param.setType(RelationType.RELATION.getCode());
        int count = relationService.selectCount(param);
        if (count > 0) {
            return true;
        } else {
            return false;
        }
    }


}
