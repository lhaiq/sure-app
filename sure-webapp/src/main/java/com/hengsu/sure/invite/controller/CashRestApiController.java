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

import com.hengsu.sure.invite.service.CashService;
import com.hengsu.sure.invite.model.CashModel;
import com.hengsu.sure.invite.vo.CashVO;

@RestApiController
@RequestMapping("/sure")
public class CashRestApiController {

	private final Logger logger = LoggerFactory.getLogger(CashRestApiController.class);
	
	@Autowired
	private BeanMapper beanMapper;
	
	@Autowired
	private CashService cashService;

	@RequestMapping(value = "/invite/cash", method = RequestMethod.POST)
	public ResponseEntity<ResponseEnvelope<Integer>> createCash(@RequestBody CashVO cashVO){
		CashModel cashModel = beanMapper.map(cashVO, CashModel.class);
		Integer  result = cashService.create(cashModel);
		ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<Integer>(result);
		return new ResponseEntity<>(responseEnv, HttpStatus.OK);
	}
	
}
