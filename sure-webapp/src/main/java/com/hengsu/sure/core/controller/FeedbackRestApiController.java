package com.hengsu.sure.core.controller;

import com.hengsu.sure.ReturnCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
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

import com.hengsu.sure.core.service.FeedbackService;
import com.hengsu.sure.core.model.FeedbackModel;
import com.hengsu.sure.core.vo.FeedbackVO;

import javax.validation.Valid;
import java.util.Date;

@RestApiController
@RequestMapping("/sure")
public class FeedbackRestApiController {

	private final Logger logger = LoggerFactory.getLogger(FeedbackRestApiController.class);
	
	@Autowired
	private BeanMapper beanMapper;
	
	@Autowired
	private FeedbackService feedbackService;
	

	@RequestMapping(value = "/core/feedback", method = RequestMethod.POST)
	public ResponseEntity<ResponseEnvelope<String>> createFeedback(
			@Valid@RequestBody FeedbackVO feedbackVO,
			@Value("#{request.getAttribute('userId')}") Long userId){
		FeedbackModel feedbackModel = beanMapper.map(feedbackVO, FeedbackModel.class);
		feedbackModel.setFeedbackDate(new Date());
		feedbackModel.setUserId(userId);
		feedbackService.createSelective(feedbackModel);
		ResponseEnvelope<String> responseEnv = new ResponseEnvelope<>(ReturnCode.OPERATION_SUCCESS,true);
		return new ResponseEntity<>(responseEnv, HttpStatus.OK);
	}
	
}
