package com.hengsu.sure.auth.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.hkntv.pylon.core.beans.mapping.BeanMapper;
import com.hkntv.pylon.web.rest.ResponseEnvelope;
import com.hkntv.pylon.web.rest.annotation.RestApiController;

import com.hengsu.sure.auth.service.MTimeService;
import com.hengsu.sure.auth.model.MTimeModel;
import com.hengsu.sure.auth.vo.MTimeVO;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

@RestApiController
@RequestMapping("/sure")
public class MTimeRestApiController {

	private final Logger logger = LoggerFactory.getLogger(MTimeRestApiController.class);
	
	@Autowired
	private BeanMapper beanMapper;
	
	@Autowired
	private MTimeService mTimeService;
	
	@RequestMapping(value = "/auth/mTime/{id}", method = RequestMethod.GET)
	public ResponseEntity<ResponseEnvelope<MTimeVO>> getMTimeById(@PathVariable Long id){
		MTimeModel mTimeModel = mTimeService.findByPrimaryKey(id);
		MTimeVO mTimeVO =beanMapper.map(mTimeModel, MTimeVO.class);
		ResponseEnvelope<MTimeVO> responseEnv = new ResponseEnvelope<MTimeVO>(mTimeVO);
		return new ResponseEntity<>(responseEnv, HttpStatus.OK);
	}

	/**
	 * 添加
	 * @param files
	 * @param mTimeVO
	 * @return
	 */
	@RequestMapping(value = "/auth/mTime", method = RequestMethod.POST)
	public ResponseEntity<ResponseEnvelope<Integer>> createMTime(
			@RequestParam("file") CommonsMultipartFile[] files,
			@RequestBody MTimeVO mTimeVO){
		MTimeModel mTimeModel = beanMapper.map(mTimeVO, MTimeModel.class);
		Integer  result = mTimeService.create(mTimeModel);
		ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<Integer>(result);
		return new ResponseEntity<>(responseEnv, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/auth/mTime/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<ResponseEnvelope<Integer>> deleteMTimeByPrimaryKey(@PathVariable Long id){
		Integer  result = mTimeService.deleteByPrimaryKey(id);
		ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<Integer>(result);
		return new ResponseEntity<>(responseEnv, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/auth/mTime/{id}", method = RequestMethod.PUT)
	public ResponseEntity<ResponseEnvelope<Integer>> updateMTimeByPrimaryKeySelective(@PathVariable Long id, @RequestBody MTimeVO mTimeVO){
		MTimeModel mTimeModel = beanMapper.map(mTimeVO, MTimeModel.class);
		mTimeModel.setId(id);
		Integer  result = mTimeService.updateByPrimaryKeySelective(mTimeModel);
		ResponseEnvelope<Integer> responseEnv = new ResponseEnvelope<Integer>(result);
		return new ResponseEntity<>(responseEnv, HttpStatus.OK);
	}
	
}
