package com.hengsu.sure.auth.controller;

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

import com.hengsu.sure.auth.service.FriendService;
import com.hengsu.sure.auth.model.FriendModel;
import com.hengsu.sure.auth.vo.FriendVO;

@RestApiController
@RequestMapping("/sure")
public class FriendRestApiController {

	private final Logger logger = LoggerFactory.getLogger(FriendRestApiController.class);
	
	@Autowired
	private BeanMapper beanMapper;
	
	@Autowired
	private FriendService friendService;
	
	@RequestMapping(value = "/auth/friend/{id}", method = RequestMethod.GET)
	public ResponseEntity<ResponseEnvelope<FriendVO>> getFriendById(@PathVariable Long id){
		FriendModel friendModel = friendService.findByPrimaryKey(id);
		FriendVO friendVO =beanMapper.map(friendModel, FriendVO.class);
		ResponseEnvelope<FriendVO> responseEnv = new ResponseEnvelope<FriendVO>(friendVO);
		return new ResponseEntity<>(responseEnv, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/auth/friend", method = RequestMethod.POST)
	public ResponseEntity<ResponseEnvelope<Integer>> createFriend(@RequestBody FriendVO friendVO){
		FriendModel friendModel = beanMapper.map(friendVO, FriendModel.class);
		Integer  result = friendService.create(friendModel);
		ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<Integer>(result);
		return new ResponseEntity<>(responseEnv, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/auth/friend/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<ResponseEnvelope<Integer>> deleteFriendByPrimaryKey(@PathVariable Long id){
		Integer  result = friendService.deleteByPrimaryKey(id);
		ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<Integer>(result);
		return new ResponseEntity<>(responseEnv, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/auth/friend/{id}", method = RequestMethod.PUT)
	public ResponseEntity<ResponseEnvelope<Integer>> updateFriendByPrimaryKeySelective(@PathVariable Long id, @RequestBody FriendVO friendVO){
		FriendModel friendModel = beanMapper.map(friendVO, FriendModel.class);
		friendModel.setId(id);
		Integer  result = friendService.updateByPrimaryKeySelective(friendModel);
		ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<Integer>(result);
		return new ResponseEntity<>(responseEnv, HttpStatus.OK);
	}
	
}
