package com.hengsu.sure.invite.controller;

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

import com.hengsu.sure.invite.service.IndentService;
import com.hengsu.sure.invite.model.IndentModel;
import com.hengsu.sure.invite.vo.IndentVO;

@RestApiController
@RequestMapping("/sure")
public class IndentRestApiController {

	private final Logger logger = LoggerFactory.getLogger(IndentRestApiController.class);
	
	@Autowired
	private BeanMapper beanMapper;
	
	@Autowired
	private IndentService indentService;
	
	@RequestMapping(value = "/invite/indent/{id}", method = RequestMethod.GET)
	public ResponseEntity<ResponseEnvelope<IndentVO>> getIndentById(@PathVariable Long id){
		IndentModel indentModel = indentService.findByPrimaryKey(id);
		IndentVO indentVO =beanMapper.map(indentModel, IndentVO.class);
		ResponseEnvelope<IndentVO> responseEnv = new ResponseEnvelope<IndentVO>(indentVO);
		return new ResponseEntity<>(responseEnv, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/invite/indent", method = RequestMethod.POST)
	public ResponseEntity<ResponseEnvelope<Integer>> createIndent(@RequestBody IndentVO indentVO){
		IndentModel indentModel = beanMapper.map(indentVO, IndentModel.class);
		Integer  result = indentService.create(indentModel);
		ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<Integer>(result);
		return new ResponseEntity<>(responseEnv, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/invite/indent/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<ResponseEnvelope<Integer>> deleteIndentByPrimaryKey(@PathVariable Long id){
		Integer  result = indentService.deleteByPrimaryKey(id);
		ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<Integer>(result);
		return new ResponseEntity<>(responseEnv, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/invite/indent/{id}", method = RequestMethod.PUT)
	public ResponseEntity<ResponseEnvelope<Integer>> updateIndentByPrimaryKeySelective(@PathVariable Long id, @RequestBody IndentVO indentVO){
		IndentModel indentModel = beanMapper.map(indentVO, IndentModel.class);
		indentModel.setId(id);
		Integer  result = indentService.updateByPrimaryKeySelective(indentModel);
		ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<Integer>(result);
		return new ResponseEntity<>(responseEnv, HttpStatus.OK);
	}
	
}
