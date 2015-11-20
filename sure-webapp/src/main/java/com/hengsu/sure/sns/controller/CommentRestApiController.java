package com.hengsu.sure.sns.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hkntv.pylon.core.beans.mapping.BeanMapper;
import com.hkntv.pylon.web.rest.ResponseEnvelope;
import com.hkntv.pylon.web.rest.annotation.RestApiController;

import com.hengsu.sure.sns.service.CommentService;
import com.hengsu.sure.sns.model.CommentModel;
import com.hengsu.sure.sns.vo.CommentVO;

@RestApiController
@RequestMapping("/sure")
public class CommentRestApiController {

	private final Logger logger = LoggerFactory.getLogger(CommentRestApiController.class);
	
	@Autowired
	private BeanMapper beanMapper;
	
	@Autowired
	private CommentService commentService;
	
	@RequestMapping(value = "/sns/comment/{id}", method = RequestMethod.GET)
	public ResponseEntity<ResponseEnvelope<CommentVO>> getCommentById(@PathVariable Long id){
		CommentModel commentModel = commentService.findByPrimaryKey(id);
		CommentVO commentVO =beanMapper.map(commentModel, CommentVO.class);
		ResponseEnvelope<CommentVO> responseEnv = new ResponseEnvelope<CommentVO>(commentVO);
		return new ResponseEntity<>(responseEnv, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/sns/comment", method = RequestMethod.POST)
	public ResponseEntity<ResponseEnvelope<Integer>> createComment(@RequestBody CommentVO commentVO){
		CommentModel commentModel = beanMapper.map(commentVO, CommentModel.class);
		Integer  result = commentService.create(commentModel);
		ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<Integer>(result);
		return new ResponseEntity<>(responseEnv, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/sns/comment/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<ResponseEnvelope<Integer>> deleteCommentByPrimaryKey(@PathVariable Long id){
		Integer  result = commentService.deleteByPrimaryKey(id);
		ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<Integer>(result);
		return new ResponseEntity<>(responseEnv, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/sns/comment/{id}", method = RequestMethod.PUT)
	public ResponseEntity<ResponseEnvelope<Integer>> updateCommentByPrimaryKeySelective(@PathVariable Long id, @RequestBody CommentVO commentVO){
		CommentModel commentModel = beanMapper.map(commentVO, CommentModel.class);
		commentModel.setId(id);
		Integer  result = commentService.updateByPrimaryKeySelective(commentModel);
		ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<Integer>(result);
		return new ResponseEntity<>(responseEnv, HttpStatus.OK);
	}
	
}
