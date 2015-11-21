package com.hengsu.sure.sns.controller;

import com.hengsu.sure.auth.model.UserModel;
import com.hengsu.sure.auth.service.UserService;
import com.hengsu.sure.sns.vo.ListCommentsVO;
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

import com.hengsu.sure.sns.service.CommentService;
import com.hengsu.sure.sns.model.CommentModel;
import com.hengsu.sure.sns.vo.CommentVO;

import java.util.ArrayList;
import java.util.List;

@RestApiController
@RequestMapping("/sure")
public class CommentRestApiController {

    private final Logger logger = LoggerFactory.getLogger(CommentRestApiController.class);

    @Autowired
    private BeanMapper beanMapper;

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    /**
     * 添加评论或点赞
     *
     * @param commentVO
     * @return
     */
    @RequestMapping(value = "/sns/{mid}/comment", method = RequestMethod.POST)
    public ResponseEntity<ResponseEnvelope<Integer>> createComment(
            @RequestBody CommentVO commentVO,
            @Value("#{request.getAttribute('userId')}") Long userId) {
        CommentModel commentModel = beanMapper.map(commentVO, CommentModel.class);
        Integer result = commentService.create(commentModel);
        ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<Integer>(result);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }

    /**
     * 删除评论
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/sns/comment/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<ResponseEnvelope<Long>> deleteCommentByPrimaryKey(
            @PathVariable Long id,
            @Value("#{request.getAttribute('userId')}") Long userId) {
        commentService.deleteComment(id, userId);
        ResponseEnvelope<Long> responseEnv = new ResponseEnvelope<>(id, true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }

    /**
     * 评论列表
     *
     * @param mid
     * @param page
     * @param size
     * @return
     */
    @RequestMapping(value = "/sns/{mid}/comment", method = RequestMethod.GET)
    public ResponseEntity<ResponseEnvelope<Page<ListCommentsVO>>> listComments(
            @PathVariable Long mid,
            @RequestParam Integer page,
            @RequestParam Integer size) {

        //分页查询
        Pageable pageable = new PageRequest(page, size);
        CommentModel param = new CommentModel();
        param.setMid(mid);
        List<CommentModel> commentModels = commentService.listComments(param, pageable);
        Integer count = commentService.selectCount(param);


        //添加评论关联User
        List<ListCommentsVO> listCommentsVOs = new ArrayList<>();
        for (CommentModel commentModel : commentModels) {
            ListCommentsVO listCommentsVO = beanMapper.map(commentModel, ListCommentsVO.class);
            UserModel userModel = userService.findByPrimaryKey(commentModel.getUserId());
            SNSUserVO snsUserVO = beanMapper.map(userModel, SNSUserVO.class);
            listCommentsVO.setUser(snsUserVO);
            listCommentsVOs.add(listCommentsVO);
        }

        Page<ListCommentsVO> pageContent = new PageImpl<>(listCommentsVOs, pageable, count);
        ResponseEnvelope<Page<ListCommentsVO>> responseEnv = new ResponseEnvelope<>(pageContent, true);
        return new ResponseEntity<>(responseEnv, HttpStatus.OK);
    }

}
