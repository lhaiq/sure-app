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

import com.hengsu.sure.sns.service.RelationService;
import com.hengsu.sure.sns.model.RelationModel;
import com.hengsu.sure.sns.vo.RelationVO;

@RestApiController
@RequestMapping("/sure")
public class RelationRestApiController {

	private final Logger logger = LoggerFactory.getLogger(RelationRestApiController.class);
	
	@Autowired
	private BeanMapper beanMapper;
	
	@Autowired
	private RelationService relationService;
	
	@RequestMapping(value = "/sns/relation/{id}", method = RequestMethod.GET)
	public ResponseEntity<ResponseEnvelope<RelationVO>> getRelationById(@PathVariable Long id){
		RelationModel relationModel = relationService.findByPrimaryKey(id);
		RelationVO relationVO =beanMapper.map(relationModel, RelationVO.class);
		ResponseEnvelope<RelationVO> responseEnv = new ResponseEnvelope<RelationVO>(relationVO);
		return new ResponseEntity<>(responseEnv, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/sns/relation", method = RequestMethod.POST)
	public ResponseEntity<ResponseEnvelope<Integer>> createRelation(@RequestBody RelationVO relationVO){
		RelationModel relationModel = beanMapper.map(relationVO, RelationModel.class);
		Integer  result = relationService.create(relationModel);
		ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<Integer>(result);
		return new ResponseEntity<>(responseEnv, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/sns/relation/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<ResponseEnvelope<Integer>> deleteRelationByPrimaryKey(@PathVariable Long id){
		Integer  result = relationService.deleteByPrimaryKey(id);
		ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<Integer>(result);
		return new ResponseEntity<>(responseEnv, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/sns/relation/{id}", method = RequestMethod.PUT)
	public ResponseEntity<ResponseEnvelope<Integer>> updateRelationByPrimaryKeySelective(@PathVariable Long id, @RequestBody RelationVO relationVO){
		RelationModel relationModel = beanMapper.map(relationVO, RelationModel.class);
		relationModel.setId(id);
		Integer  result = relationService.updateByPrimaryKeySelective(relationModel);
		ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<Integer>(result);
		return new ResponseEntity<>(responseEnv, HttpStatus.OK);
	}
	
}
